package com.dn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dn.common.controller.BaseController;
import com.dn.common.exception.DcException;
import com.dn.sys.entity.Org;
import com.dn.sys.service.IOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dna
 * @since 2019-06-23
 */
@Api(value = "部门信息服务", tags = "部门信息服务", authorizations = {@Authorization("login")})
@RestController
@RequestMapping("/api/sys/org")
public class OrgController extends BaseController {

    @Autowired
    private IOrgService iOrgService;

    @ApiOperation(value = "获取部门列表", httpMethod = "GET", notes = "获取部门列表")
    @GetMapping
    public List<Org> getOrg(@RequestParam(value = "sysId", required = true) String sysId, String orgName) {
        QueryWrapper<Org> queryWrapper = new QueryWrapper<Org>();
        if (!StringUtils.isNotBlank(orgName)) {
            queryWrapper.like("org_name", orgName);
        }
        queryWrapper.eq("sys_id", sysId);
        return iOrgService.list(queryWrapper);
    }

    @ApiOperation(value = "查询当前机构所属的公司", httpMethod = "GET", tags = "查询当前机构所属的公司")
    @GetMapping("get-org-company")
    public Org getOrgCompany(String orgId) {
        if (StringUtils.isBlank(orgId)) {
            throw new DcException("机构ID不能为空！");
        }

        return iOrgService.getOrgCompanyByOrgId(orgId);
    }

    @ApiOperation(value = "根据机构主键获取机构信息", httpMethod = "GET", tags = "根据机构主键获取机构信息")
    @GetMapping("get-by-id")
    public Org getOrg(String orgId) {
        return iOrgService.getById(orgId);
    }

    @ApiOperation(value = "新增或者修改部门信息", httpMethod = "POST", notes = "新增或者修改部门信息")
    @PostMapping
    public void mergeOrg(@RequestBody Org org) {
        boolean b = iOrgService.saveOrUpdate(org);
        if (!b) {
            throw new DcException("信息存储失败！");
        }
    }

    @ApiOperation(value = "根据主键id删除机构信息", notes = "根据主键id删除机构信息", httpMethod = "DELETE")
    @DeleteMapping("remove-org-by-id")
    public void removeOrg(String id) {
        boolean flag = iOrgService.removeOrgById(id);

        if (!flag) {
            throw new DcException("删除机构信息失败");
        }
    }


}
