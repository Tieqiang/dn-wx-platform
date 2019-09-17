package com.dn.common.handler;

import com.dn.common.vo.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DcAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper ;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        SimpleResponse simpleResponse = new SimpleResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        if(authException instanceof BadCredentialsException){
            simpleResponse.setResCode("4003");
            simpleResponse.setResDescription("认证未通过");
        }else {
            simpleResponse.setResCode("4003");
            simpleResponse.setResDescription("无效的token");
        }
        response.getWriter().write(objectMapper.writeValueAsString(simpleResponse));
    }
}
