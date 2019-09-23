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
    * 随访计划详细表
    * </p>
*
* @author dn
* @since 2019-09-23
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("wx_follow_plan_detail")
    @ApiModel(value="FollowPlanDetail对象", description="随访计划详细表")
    public class FollowPlanDetail extends BaseDomain {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "具出院时间长度")
    private Long timeFromOut;

            @ApiModelProperty(value = "随访事件")
    private String eventId;

            @ApiModelProperty(value = "提醒次数 提醒次数")
    private Integer remindTimes;

    private String planId;


}
