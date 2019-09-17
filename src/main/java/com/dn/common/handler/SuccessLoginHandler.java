package com.dn.common.handler;


import com.dn.common.config.SystemProperties;
import com.dn.common.util.JwtUtils;
import com.dn.sys.entity.User;
import com.dn.sys.service.IUserService;
import com.dn.sys.vo.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

@Component
public class SuccessLoginHandler implements AuthenticationSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(SuccessLoginHandler.class);


    @Autowired
    private SystemProperties systemProperties;


    @Autowired
    private IUserService userService ;

    @Autowired
    private JwtUtils jwtUtils ;

    @Autowired
    private ObjectMapper objectMapper ;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功。。。。");
        User sysUser = (User) authentication.getPrincipal();
        Iterator<? extends GrantedAuthority> iterator = sysUser.getAuthorities().iterator();
        if(iterator.hasNext()){
            GrantedAuthority grantedAuthority = iterator.next();
            logger.info("拥有的权限："+grantedAuthority.getAuthority());
        }
        String token = jwtUtils.createToken(sysUser);
        if(systemProperties.getAuthention().isSessionEnable()){
            HttpSession session = request.getSession();
            session.setAttribute(session.getId()+"|token",token);
        }
        response.setHeader(systemProperties.getAuthention().getName(),systemProperties.getAuthention().getPrefix()+token);
//      //支持前端跨域请求的时候header中添加授权的header
        response.addHeader("Access-Control-Expose-Headers",systemProperties.getAuthention().getName());
        logger.info("认证成功。获取认证信息："+sysUser+";并生成token="+token);
//        response.setContentType("text/plain");
        response.setContentType("application/json");
        UserInfo currentUserInfo = userService.getCurrentUserInfo(sysUser);
        response.getWriter().write(objectMapper.writeValueAsString(currentUserInfo));
        response.getWriter().flush();

    }
}
