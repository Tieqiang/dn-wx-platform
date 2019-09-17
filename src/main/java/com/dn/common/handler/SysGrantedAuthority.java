package com.dn.common.handler;


import com.dn.common.service.SysUserService;
import com.dn.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class SysGrantedAuthority implements GrantedAuthority {

    private transient SysUserService sysUserService;

    private transient User username;

    public SysGrantedAuthority(){
    }

    public SysGrantedAuthority(User username) {
        this.username = username;
        ApplicationContext applicationContext = ApplicationContextHandler.APPLICATION_CONTEXT;
        this.sysUserService = (SysUserService) applicationContext.getBean("sysUserService");
    }




    @Override
    public String getAuthority() {
        List<String> authorities=new ArrayList<>();
        if(username!=null){
            List<String> permissions = sysUserService.getPermissions(username);
            if(permissions!=null){
                authorities.addAll(permissions);
            }
        }
        return StringUtils.join(authorities,",") ;
    }


}
