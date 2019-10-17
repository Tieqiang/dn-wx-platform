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
 * 随访事件
 * </p>
 *
 * @author dn
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wx_follow_event")
@ApiModel(value = "FollowEvent对象", description = "随访事件")
public class FollowEvent extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "事件类型 发送文本、填写表单")
    private String eventType;

    @ApiModelProperty(value = "表单链接 填写表单类型的地址")
    private String eventUrl;

    @ApiModelProperty(value = "事件名称")
    private String eventName;

    @ApiModelProperty(value = "事件DOM")
    private String eventDom;

    @ApiModelProperty(value = "事件内容 事件类型为文本内容")
    private String eventContent;

    @ApiModelProperty(value = "获取事件API接口")
    private String dataApi;


}
