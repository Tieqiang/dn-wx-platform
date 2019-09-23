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
    * 随访明细表
    * </p>
*
* @author dn
* @since 2019-09-23
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("wx_follow_detail")
    @ApiModel(value="FollowDetail对象", description="随访明细表")
    public class FollowDetail extends BaseDomain {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "所属随访")
    private String recordId;

            @ApiModelProperty(value = "随访事件")
    private String followEnventId;

            @ApiModelProperty(value = "随访数据 备选字段。如果随访事件要自动建表，则这个字段是无用的。该字段和随访事件中的HTML相对应。")
    private String followData;

            @ApiModelProperty(value = "提交时间")
    private Long commitDate;

            @ApiModelProperty(value = "随访数据标识 对应于数据存储表中的ID")
    private String followDataId;


}
