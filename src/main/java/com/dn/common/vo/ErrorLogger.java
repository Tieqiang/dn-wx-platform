package com.dn.common.vo;

/***
 * 报错错误日志
 */

import java.io.Serializable;

public class ErrorLogger implements Serializable{

    private String code ;
    private String mes ;
    private String type ;
    private String url ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
