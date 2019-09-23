package com.dn.wx.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.dn.common.controller.BaseController;

/**
 * <p>
 * 接收到的消息 前端控制器
 * </p>
 *
 * @author dn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/wx/message")
public class MessageController extends BaseController {

}
