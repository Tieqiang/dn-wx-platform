package com.dn.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface PriResource {
    //资源名称
    String resourceName() default "";
    //资源名称
    String resourceCode() default "";
}
