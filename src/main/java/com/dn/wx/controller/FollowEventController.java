package com.dn.wx.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dn.wx.entity.FollowEvent;
import com.dn.wx.service.IFollowEventService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dn.common.controller.BaseController;

/**
 * <p>
 * 随访事件 前端控制器
 * </p>
 *
 * @author dn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/api/wx/follow-event")
public class FollowEventController extends BaseController {

    @Autowired
    private IFollowEventService followEventService;

    /**
     * 获取所有的随访类型
     *
     * @param pageSize
     * @param current
     * @param eventType
     * @param eventName
     * @return
     */
    @GetMapping
    public IPage<FollowEvent> getFollowEventPages(@RequestParam(required = false, defaultValue = "20") long pageSize,
                                                  @RequestParam(required = false, defaultValue = "1") long current,
                                                  @RequestParam(required = false) String eventType,
                                                  @RequestParam(required = false) String eventName) {
        QueryWrapper<FollowEvent> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(eventName) && StringUtils.isNotEmpty(eventName)) {
            queryWrapper.like("event_name", eventName);
        }
        if (StringUtils.isNotEmpty(eventType) && StringUtils.isNotBlank(eventType)) {
            queryWrapper.eq("event_type", eventType);
        }
        return followEventService.page(new Page<>(current, pageSize), queryWrapper);
    }


    /**
     * 增加或者修改
     *
     * @param followEvent
     * @return
     */
    @PostMapping
    public boolean saveOrUpdateFollowEvent(@RequestBody FollowEvent followEvent) {
        return followEventService.saveOrUpdate(followEvent);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public boolean deleteFollowEvent(String id) {
        return followEventService.removeById(id);
    }
}
