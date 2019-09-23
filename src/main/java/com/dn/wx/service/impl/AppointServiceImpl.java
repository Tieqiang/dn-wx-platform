package com.dn.wx.service.impl;

import com.dn.wx.entity.Appoint;
import com.dn.wx.mapper.AppointMapper;
import com.dn.wx.service.IAppointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 预约记录 服务实现类
 * </p>
 *
 * @author dn
 * @since 2019-09-23
 */
@Service
public class AppointServiceImpl extends ServiceImpl<AppointMapper, Appoint> implements IAppointService {

}
