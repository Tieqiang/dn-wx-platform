package com.dn.sys.controller;


import com.dn.common.controller.BaseController;
import com.dn.sys.service.IMenuVsApiPermissionService;
import com.dn.sys.vo.MenuPermissionVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@RestController
@RequestMapping("/api/sys/menu-vs-api-permission")
public class MenuVsApiPermissionController extends BaseController {

    @Autowired
    private IMenuVsApiPermissionService iMenuVsApiPermissionService;

    @ApiOperation(value="给菜单设置权限",httpMethod = "POST",notes = "给菜单设置权限")
    @PostMapping
    public String saveOrUpdateMenuVsApiPermission(@RequestBody MenuPermissionVo menuPermissionVo){

        iMenuVsApiPermissionService.saveOrUpdateMenuVsApiPermission(menuPermissionVo);
        return "success";
    }

}
