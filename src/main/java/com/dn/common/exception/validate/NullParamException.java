package com.dn.common.exception.validate;

/**
 * 参数为空异常
 */
public class NullParamException extends ValidateException {
    public NullParamException() {
        this("参数不能为空！");
    }

    public NullParamException(String message) {
        super(message);
    }
}
