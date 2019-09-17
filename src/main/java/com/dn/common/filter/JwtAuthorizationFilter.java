package com.dn.common.filter;


import com.dn.common.config.SystemProperties;
import com.dn.common.service.SysUserService;
import com.dn.common.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);


    private List<RequestMatcher> requestMatchers = new ArrayList<>();

    @Autowired
    public JwtAuthorizationFilter(AuthenticationFailureHandler failureHandler, SystemProperties systemProperties, SysUserService sysUserService, JwtUtils jwtUtils) {
        this.failureHandler = failureHandler;
        this.systemProperties = systemProperties;
        this.sysUserService = sysUserService;
        this.jwtUtils = jwtUtils;

        String exceptUrls = systemProperties.getExceptUrls();
        if (StringUtils.isNotEmpty(exceptUrls)) {
            String[] urls = exceptUrls.split(",");
            for (String url : urls) {
                RequestMatcher requestMatcher = new AntPathRequestMatcher(url);
                requestMatchers.add(requestMatcher);
            }
        }

    }

    private AuthenticationFailureHandler failureHandler;

    private SystemProperties systemProperties;

    private SysUserService sysUserService;

    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean sessionEnable = systemProperties.getAuthention().isSessionEnable();
        //判断是否需要验证
        if (!needAuthorization(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        //如果是MockUser用于测试的时候
        SecurityContext securityContext = (SecurityContext) request.getAttribute("org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors$SecurityContextRequestPostProcessorSupport$TestSecurityContextRepository.REPO");
        if (securityContext != null) {
            Authentication authentication = securityContext.getAuthentication();
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        String header = request.getHeader(systemProperties.getAuthention().getName());
        if (header != null && !"null".equals(header) && header.startsWith(systemProperties.getAuthention().getPrefix())) {
            String token = header.substring(systemProperties.getAuthention().getPrefix().length());
            logger.info("token信息为：" + token);

            if (sessionEnable) {
                HttpSession requestSession = request.getSession();
                String tokenInSession = (String) requestSession.getAttribute(requestSession.getId() + "|token");
                if (!token.equals(tokenInSession)) {
                    //如果携带的token和会话中的token不一致，但是经判断访问的登陆页面，则允许进行登陆操作
                    if (request.getRequestURI().contains("login")){
                        filterChain.doFilter(request,response);
                        return ;
                    }else{
                        failureHandler.onAuthenticationFailure(request, response, new InvalidCookieException("无效的token"));
                        return;
                    }

                }
            }

            String username = null;
            try {
                username = jwtUtils.getUserName(token);
            } catch (ExpiredJwtException e) {
                AuthenticationException ex = new CredentialsExpiredException("凭证已过期");
                failureHandler.onAuthenticationFailure(request, response, ex);
                return;
            }



            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = sysUserService.loadUserByUsername(username);
                if (jwtUtils.validToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }

            filterChain.doFilter(request, response);
        } else if ("null".equals(header)||header==null && request.getRequestURI().contains("login")) {
            filterChain.doFilter(request, response);
        } else {
            failureHandler.onAuthenticationFailure(request, response, new InvalidCookieException("无效的认证信息"));
        }
    }

    private boolean needAuthorization(HttpServletRequest request) {
        for (RequestMatcher matcher : requestMatchers) {
            if (matcher.matches(request)) {
                return false;
            }
        }
        return true;
    }
}
