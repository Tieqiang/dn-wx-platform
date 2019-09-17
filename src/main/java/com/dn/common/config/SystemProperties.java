package com.dn.common.config;

import com.dn.common.config.properties.AuthentionProperties;
import com.dn.common.config.properties.CorsProperties;
import com.dn.common.config.properties.ValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.dn")
public class SystemProperties {

    private String remoteApiAddress="http://localhost:8088/api/his-service" ;

    private boolean debug=false ;

    private String exceptUrls="";

    private AuthentionProperties authention ;

    private ValidateCodeProperties validateCode = new ValidateCodeProperties()  ;

    //配置跨域访问
    private CorsProperties cors ;

    private String defaultPassword="38222f6c1725ba372b5531f5c3ad1882" ;//默认密码 123456

}
