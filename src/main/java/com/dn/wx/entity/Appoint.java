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
    * 预约记录
    * </p>
*
* @author dn
* @since 2019-09-23
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("wx_appoint")
    @ApiModel(value="Appoint对象", description="预约记录")
    public class Appoint extends BaseDomain {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "预约信息")
    private String openId;

            @ApiModelProperty(value = "预约医生")
    private String dockerId;

            @ApiModelProperty(value = "预约时间")
    private String appointDate;

            @ApiModelProperty(value = "预约主诉")
    private String appointReason;

            @ApiModelProperty(value = "预约科室")
    private String orgId;

            @ApiModelProperty(value = "预约状态")
    private String appointStatus;

            @ApiModelProperty(value = "确认时间")
    private Long confirmDate;

            @ApiModelProperty(value = "确认意见")
    private String confirmAdvertise;


}
