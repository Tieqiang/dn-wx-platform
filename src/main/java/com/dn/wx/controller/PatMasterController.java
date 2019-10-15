package com.dn.wx.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dn.wx.entity.PatMaster;
import com.dn.wx.service.IPatMasterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dn.common.controller.BaseController;

/**
 * <p>
 * 病人主信息 前端控制器
 * </p>
 *
 * @author dn
 * @since 2019-09-23
 */
@RestController
@RequestMapping("/api/wx/pat-master")
public class PatMasterController extends BaseController {

    @Autowired
    IPatMasterService patMasterService;

    @PostMapping
    public boolean saveOrUpdatePatMaster(@RequestBody PatMaster patMaster) {
        return patMasterService.saveOrUpdate(patMaster);
    }

    @DeleteMapping
    public boolean removePatMaster(String id) {
        return patMasterService.removeById(id);
    }

    @GetMapping("get-by-id")
    public PatMaster getPatMasterById(String id) {
        return patMasterService.getById(id);
    }

    /**
     * 分页获取等级的病人信息
     *
     * @param idNo
     * @param patName
     * @param pId
     * @param inpNo
     * @param pageSize
     * @param current
     * @return
     */
    @GetMapping
    public IPage<PatMaster> getPatMasterPages(
            @RequestParam(required = false) String idNo,
            @RequestParam(required = false) String patName,
            @RequestParam(required = false) String pId,
            @RequestParam(required = false) String phoneNum,
            @RequestParam(required = false) String inpNo, long pageSize, long current) {
        QueryWrapper<PatMaster> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(idNo)) {
            query.eq("id_no", idNo);
        }
        if (StringUtils.isNotBlank(pId)) {
            query.eq("pat_id", pId);
        }

        if (StringUtils.isNotBlank(patName)) {
            query.like("pat_name", patName);
        }
        if (StringUtils.isNotBlank(phoneNum)) {
            query.like("phone_num", phoneNum);
        }
        if (StringUtils.isNotBlank(inpNo)) {
            query.eq("inp_no", inpNo);
        }
        query.orderByDesc("create_date");
        return patMasterService.page(new Page<>(current, pageSize), query);
    }

}
