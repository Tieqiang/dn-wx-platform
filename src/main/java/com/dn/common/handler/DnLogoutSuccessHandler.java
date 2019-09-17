package com.dn.common.handler;

import com.dn.common.vo.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DnLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper ;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        SimpleResponse simpleResponse = new SimpleResponse();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        simpleResponse.setResCode("0");
        simpleResponse.setResDescription("登出成功");
        try {
            response.getWriter().write(objectMapper.writeValueAsString(simpleResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
