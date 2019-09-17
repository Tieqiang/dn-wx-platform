package com.dn.common.exception.validate;

/**
 * 参数类型错误异常
 */
public class ParamTypeErrorException extends ValidateException {
    public ParamTypeErrorException() {
        this("参数类型异常！");
    }

    public ParamTypeErrorException(String message) {
        super(message);
    }
}
