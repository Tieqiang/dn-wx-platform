package com.dn.sys.controller;


import com.dn.common.controller.BaseController;
import com.dn.common.exception.validate.NullParamException;
import com.dn.sys.entity.Menu;
import com.dn.sys.service.IMenuService;
import com.dn.sys.vo.MenuPermissionVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@RestController
@RequestMapping("/api/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService iMenuService;

    @ApiOperation(value="查询所有的菜单和下面的权限",httpMethod = "GET",notes = "查询所有的菜单和下面的权限")
    @GetMapping
    public List<MenuPermissionVo> getMenusWithPermission(){

        return iMenuService.getMenuPermission();

    }

    /**
     * 根据系统id 查询所有的菜单信息
     * @return
     */
    @ApiOperation(value="查询所有的菜单列表",httpMethod = "GET",notes = "查询所有的菜单列表,前台展示为树形")
    @GetMapping("/get-menus")
    public List<Menu> getMenus(){
        return iMenuService.getMenus();
    }

    /**
     * 新增或者修改菜单信息
     * @param menu
     */
    @ApiOperation(value="新增和修改角色信息",httpMethod = "POST",notes = "新增和修改角色信息")
    @PostMapping
    public String saveOrUpdateMenu(@RequestBody Menu menu){
        iMenuService.saveOrUpdateMenu(menu);
        return "success";
    }

    @ApiOperation(value="删除菜单",httpMethod = "DELETE",notes = "根据id 删除菜单")
    @GetMapping("/remove-menu-by-id")
    public String removeMenuById(String id){
        iMenuService.removeMenu(id);
        return "success";
    }

    @ApiOperation(value="根据id查询菜单信息",httpMethod = "GET",notes = "根据id查询菜单信息")
    @GetMapping("/get-menu-by-id")
    public Menu getMenuById(String id){
        if(id == null || "".equals(id)){
            throw new NullParamException("参数不正确");
        }
        return iMenuService.getById(id);
    }

}
