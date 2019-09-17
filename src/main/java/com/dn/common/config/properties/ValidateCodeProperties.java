package com.dn.common.config.properties;

import lombok.Data;

@Data
public class ValidateCodeProperties {

    private int width = 200;//生成验证码的长度
    private int height = 40;//生成验证码宽度
    private int codeCount = 6;
    private int lineCount = 100;
    private String needValidateCodeUrls="" ;//需要在验证码进行验证的页面

    private long expireTime = 60000 ; //默认60秒失效
    //待生成的字符
    private String[] codeSequence = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
}
