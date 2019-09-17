package com.dn.common.handler;

import com.dn.common.config.SystemProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class DnLogoutHandler implements LogoutHandler {

    private final SystemProperties systemProperties ;
    private final ObjectMapper objectMapper ;

    @Autowired
    public DnLogoutHandler(SystemProperties systemProperties, ObjectMapper objectMapper) {
        this.systemProperties = systemProperties;
        this.objectMapper = objectMapper;
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) {
        boolean sessionEnable = systemProperties.getAuthention().isSessionEnable();
        if(sessionEnable){
            response.setHeader(systemProperties.getAuthention().getName(),"");
            HttpSession session = httpServletRequest.getSession();
            session.removeAttribute(session.getId()+"|token");
        }

    }
}
