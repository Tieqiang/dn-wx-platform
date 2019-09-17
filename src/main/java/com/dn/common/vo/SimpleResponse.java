package com.dn.common.vo;

import java.io.Serializable;

public class SimpleResponse implements Serializable {

    private String resCode;
    private String resDescription;


    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResDescription() {
        return resDescription;
    }

    public void setResDescription(String resDescription) {
        this.resDescription = resDescription;
    }
}
