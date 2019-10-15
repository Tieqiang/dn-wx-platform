package com.dn.wx.entity;

import com.dn.common.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 病人主信息
 * </p>
 *
 * @author dn
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wx_pat_master")
@ApiModel(value = "PatMaster对象", description = "病人主信息")
public class PatMaster extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "病人姓名")
    private String patName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "院内主索引")
    private String patId;

    @ApiModelProperty(value = "住院号")
    private String inpNo;

    @ApiModelProperty(value = "联系电话")
    private String phoneNum;

    @ApiModelProperty(value = "身份证号码")
    private String idNo;

    @ApiModelProperty(value = "随访微信号")
    private String openId;

    @ApiModelProperty(value = "通讯地址")
    private String address;


}
