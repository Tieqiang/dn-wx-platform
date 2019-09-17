package com.dn.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageCode implements Serializable {

    //验证码值
    private String imageCode ;
    //创建时间
    private Long createDateTime ;



}
