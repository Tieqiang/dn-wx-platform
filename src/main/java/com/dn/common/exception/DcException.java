package com.dn.common.exception;

/**
 * 平台异常
 */
public class DcException extends RuntimeException {
    public DcException() {
        this("平台调用出现异常！");
    }

    public DcException(String message) {
        super(message);
    }
}
