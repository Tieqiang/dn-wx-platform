package com.dn.common.handler;


import com.dn.common.config.SystemProperties;
import com.dn.common.handler.custom.CustomPermissionEvaluator;
import com.dn.common.service.SysUserService;
import com.dn.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 自定义Object
 */
@Component
public class AuthPermissionEvaluator implements CustomPermissionEvaluator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemProperties systemProperties;

    @Autowired
    private RedisOperationsSessionRepository redisOperationsSessionRepository ;

    @Autowired
    private SysUserService sysUserService ;

    public boolean hasPermission(Authentication authentication, Object permission) {
        return this.hasPermission(authentication, null, permission);
    }

    public boolean hasPermission(Authentication authentication, Object invokeTarget, Object targetDomainObject, Object permission) {
        String name = "";


        Object principal = authentication.getPrincipal();

        if (principal instanceof String &&principal.equals("anonymousUser")) {
            //不需要认证
            return true ;
        }

        if (principal instanceof User) {
            if ("user".equals(((User) principal).getUsername()) && "password".equals(((User) principal).getPassword()) && systemProperties.isDebug()) {
                return true;
            }
        }
//
//        PriResource annotation = invokeTarget.getClass().getAnnotation(PriResource.class);
//        String resourceCode = annotation.resourceCode();
        String resourceCode ="" ;
        if (StringUtils.isEmpty(resourceCode)) {
            name = invokeTarget.getClass().getSimpleName();
        } else {
            name = resourceCode;
        }

        logger.info("访问的资源类为：" + name);
        String userName = ((User) principal).getUsername();
        if (StringUtils.equals(userName, systemProperties.getAuthention().getMockUser())) {
            //如果是单元测试用户则直接执行
            return true;
        }
        Set<String> permissions = new HashSet<>();
        Collection<? extends GrantedAuthority> authorities = null ;
        if(null !=this.redisOperationsSessionRepository){
            //使用redis作为session管理
            User currentUser = (User) authentication.getPrincipal();
            User user = sysUserService.loadUserByUsername(currentUser.getUsername());
            authorities = user.getAuthorities() ;
        }else{
            authorities = authentication.getAuthorities();
        }
        for (GrantedAuthority grantedAuthority : authorities) {
            String auths = grantedAuthority.getAuthority();
            String[] split = StringUtils.split(auths, ",");
            permissions.addAll(Arrays.asList(split));
        }
        return permissions.contains(name + ":" + permission);
    }


    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object
            permission) {
        return false;
    }
}
