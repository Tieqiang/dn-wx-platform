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
    * 接收到的消息
    * </p>
*
* @author dn
* @since 2019-09-23
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("wx_message")
    @ApiModel(value="Message对象", description="接收到的消息")
    public class Message extends BaseDomain {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "来自微信号")
    private String openId;

            @ApiModelProperty(value = "消息类型 同步微信消息类型")
    private String eventType;

            @ApiModelProperty(value = "消息内容")
    private String eventContent;

            @ApiModelProperty(value = "回复状态 是否回复")
    private String replyStatus;

            @ApiModelProperty(value = "回复内容标识")
    private String replyId;


}
