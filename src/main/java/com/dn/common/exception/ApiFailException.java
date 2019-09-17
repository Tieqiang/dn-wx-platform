package com.dn.common.exception;

/**
 * 接口调用的异常
 */
public class ApiFailException extends RuntimeException {

    public ApiFailException(String message) {
        super(message);
    }
}
