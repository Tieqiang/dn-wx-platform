package com.dn.common.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDomain implements Serializable {

    private String id;

    @TableField(fill = FieldFill.INSERT)
    private Long createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long lastUpdateDate;

    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private String creator;

    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    @TableLogic(value = "0", delval = "1")
    private Integer delFlag;


}
