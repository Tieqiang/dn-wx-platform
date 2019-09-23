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
    * 随访计划表
    * </p>
*
* @author dn
* @since 2019-09-23
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("wx_follow_plan")
    @ApiModel(value="FollowPlan对象", description="随访计划表")
    public class FollowPlan extends BaseDomain {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "随访计划名称")
    private String planName;

            @ApiModelProperty(value = "随访描述")
    private String planDesc;


}
