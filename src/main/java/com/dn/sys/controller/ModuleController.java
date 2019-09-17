package com.dn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dn.common.controller.BaseController;
import com.dn.common.exception.validate.NullParamException;
import com.dn.sys.service.IModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.dn.sys.entity.Module;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@Api(value = "后台管理模块服务", tags = "后台管理模块服务", authorizations = {@Authorization("login")})
@RestController
@RequestMapping("/api/sys/module")
public class ModuleController extends BaseController {

    @Autowired
    public IModuleService iModuleService;

    @ApiOperation(value="查询模块",httpMethod = "GET",notes = "查询所有模块")
    @GetMapping
    public List<Module> getModules(String moduleName){
        QueryWrapper queryWrapper = new QueryWrapper();
        if(moduleName != null && !"".equals(moduleName)){
            queryWrapper.like("module_name",moduleName);
        }
        return iModuleService.list(queryWrapper);
    }

    @ApiOperation(value="根据id查询模块",httpMethod = "GET",notes = "根据id查询模块")
    @GetMapping("/get-module-by-id")
    public Module getModuleById(String id){
        if(id ==null ||"".equals(id)){
            throw new NullParamException();
        }
        return iModuleService.getById(id);
    }

    @ApiOperation(value="新增或者编辑模块",httpMethod = "POST",notes = "新增或者编辑模块")
    @PostMapping
    public String saveOrUpdateModule(@RequestBody Module module){
        iModuleService.saveOrUpdateModule(module);
        return "success";

    }

    @ApiOperation(value="根据id删除模块",httpMethod = "DELETE",notes = "根据id删除模块")
    @DeleteMapping("/remove-module")
    public String removeModule(String id){
        iModuleService.removeModule(id);
        return "success";
    }

}
