package com.dn.sys.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dn.common.controller.BaseController;
import com.dn.common.exception.validate.NullParamException;
import com.dn.sys.entity.ApiPermission;
import com.dn.sys.service.IApiPermissionService;
import com.dn.sys.vo.EditUserInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@RestController
@RequestMapping("/api/sys/api-permission")
public class ApiPermissionController extends BaseController {

    @Autowired
    private IApiPermissionService iApiPermissionService;
    /**
     * 查询所有的权限信息，分页查询
     * @return
     */
    @ApiOperation(value="查询所有的菜单和下面的权限",httpMethod = "GET",notes = "查询所有的菜单和下面的权限")
    @GetMapping("/get-permissions")
    public IPage<ApiPermission> getPermissions(Long page, Long pageSize){
        if(page==null) page=1L;
        if (pageSize==null) pageSize=10L;

        Page<EditUserInfoVo> pageEntity = new Page<EditUserInfoVo>(page, pageSize);
        IPage<ApiPermission> apiPermissions = iApiPermissionService.getPermissions(pageEntity);
        return apiPermissions;
    }

    /**
     * 新增 或者修改权限信息
     * @param apiPermission
     * @return
     */
    @ApiOperation(value="新增或者修改权限信息",httpMethod = "POST",notes = "新增或者修改权限信息")
    @PostMapping
    public String saveOrUpdatePermission(@RequestBody ApiPermission apiPermission){
        iApiPermissionService.saveOrUpdatePermission(apiPermission);
        return "success";
    }

    /**
     * 根据id删除权限信息
     * @return
     */
    @ApiOperation(value="删除权限信息",httpMethod = "DELETE",notes = "删除权限信息")
    @GetMapping("/remove-permission-by-id")
    public String removePermissionById(String id){
        iApiPermissionService.removePermissionById(id);
        return "success";
    }

    /**
     *
     * @param id
     * @return
     */
    @ApiOperation(value="根据id查询权限",httpMethod = "GET",notes = "根据id查询权限")
    @GetMapping("/get-Apipermission-by-id")
    public ApiPermission getApiPermissionById(String id){
        if(id == null || "".equals(id)){
            throw new NullParamException();
        }
        return iApiPermissionService.getById(id);
    }

}
