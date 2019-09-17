package com.dn.common.config;


import com.dn.common.handler.AuthPermissionEvaluator;
import com.dn.common.handler.custom.CustomMethodSecurityExpressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private AuthPermissionEvaluator premissionEvaluator ;

    /**
     * 如果要扩展表达式，可以使用这样的方式。
     * @return
     */
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
//        defaultMethodSecurityExpressionHandler.setPermissionEvaluator(premissionEvaluator);
        //重写方法拦截
        CustomMethodSecurityExpressionHandler handler = new CustomMethodSecurityExpressionHandler();
        handler.setCustomPermissionEvaluator(premissionEvaluator);
        handler.setPermissionEvaluator(premissionEvaluator);

        return handler;
    }


}