package com.dn.wx.entity;

import com.dn.common.domain.BaseDomain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 随访记录表
 * </p>
 *
 * @author dn
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wx_follow_record")
@ApiModel(value = "FollowRecord对象", description = "随访记录表")
public class FollowRecord extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "病人标识")
    private String patId;

    @ApiModelProperty(value = "出院日期")
    private Long outDate;

    @ApiModelProperty(value = "出院诊断")
    private String diagnosis;

    @ApiModelProperty(value = "随访管理人员")
    private String followManagerId;

    @ApiModelProperty(value = "出院科室")
    private String outDeptId;

    @ApiModelProperty(value = "随访方案")
    private String followPlanId;



}
