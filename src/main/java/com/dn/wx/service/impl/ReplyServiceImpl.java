package com.dn.wx.service.impl;

import com.dn.wx.entity.Reply;
import com.dn.wx.mapper.ReplyMapper;
import com.dn.wx.service.IReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 消息回复 服务实现类
 * </p>
 *
 * @author dn
 * @since 2019-09-22
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

}
