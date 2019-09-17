package com.dn.common.handler;

import com.dn.common.vo.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String cause = accessDeniedException.getMessage();
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setResCode("403");
        simpleResponse.setResDescription(cause);
        response.setStatus(403);
        response.getWriter().write(objectMapper.writeValueAsString(simpleResponse));
    }
}
