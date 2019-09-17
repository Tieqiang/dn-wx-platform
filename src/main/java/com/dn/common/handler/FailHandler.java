package com.dn.common.handler;

import com.dn.common.config.SystemProperties;
import com.dn.common.vo.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FailHandler implements AuthenticationFailureHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ObjectMapper objectMapper ;
    @Autowired
    private SystemProperties systemProperties ;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");
        logger.info(exception.getMessage());
        boolean sessionEnable = systemProperties.getAuthention().isSessionEnable();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        SimpleResponse simpleResponse = new SimpleResponse();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        simpleResponse.setResCode("4003");
        if(sessionEnable){
            response.setHeader(systemProperties.getAuthention().getName(),"");
        }
        if(exception instanceof InternalAuthenticationServiceException){
            simpleResponse.setResDescription("用户名不存在！");
        } else if (exception instanceof BadCredentialsException){
            simpleResponse.setResDescription("错误的密码！");
        }else {
            simpleResponse.setResDescription(exception.getMessage());
        }
        response.getWriter().write(objectMapper.writeValueAsString(simpleResponse));

    }
}
