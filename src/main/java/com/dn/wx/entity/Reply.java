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
 * 消息回复
 * </p>
 *
 * @author dn
 * @since 2019-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wx_reply")
@ApiModel(value = "Reply对象", description = "消息回复")
public class Reply extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "回复的消息")
    private String messageId;

    @ApiModelProperty(value = "回复内容")
    private String replyContent;


}
