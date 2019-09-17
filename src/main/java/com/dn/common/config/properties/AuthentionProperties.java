package com.dn.common.config.properties;

import lombok.Data;

@Data
public class AuthentionProperties {

    private String name="Authorization" ;//授权名称
    private String prefix ="Bearer ";//授权的前缀

    private long expire = 60*60*24*1000;//过期时间

    private String sceret="dc-platform";

    private String mockUser="user" ;//用于测试的用户名

    //session 是否启用
    private boolean sessionEnable = false;

}
