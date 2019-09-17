package com.dn.common.handler.custom;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public interface CustomPermissionEvaluator extends PermissionEvaluator {

    boolean hasPermission(Authentication authentication, Object invokeTarget, Object targetDomainObject, Object permission);
}
