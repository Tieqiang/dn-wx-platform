package com.dn.common.exception;

/**
 * 业务处理异常
 */
public class BHException extends DcException {
    public BHException() {
        this("业务处理异常！");
    }

    public BHException(String message) {
        super(message);
    }
}
