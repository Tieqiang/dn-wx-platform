package com.dn.wx.service.impl;

import com.dn.wx.entity.Message;
import com.dn.wx.mapper.MessageMapper;
import com.dn.wx.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接收到的消息 服务实现类
 * </p>
 *
 * @author dn
 * @since 2019-09-23
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
