package com.dn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dn.common.controller.BaseController;
import com.dn.common.exception.DcException;
import com.dn.sys.entity.Role;
import com.dn.sys.service.IRoleService;
import com.dn.sys.vo.RoleMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@Api(value = "角色信息服务", tags = "角色信息服务", authorizations = {@Authorization("login")})
@RestController
@RequestMapping("/api/sys/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService iRoleService;

    /**
     * 查询角色列表 分页查询
     *
     * @param page
     * @param pageSize
     * @param sysId
     * @param roleName
     * @return
     */
    @ApiOperation(value = "分页获取角色列表", httpMethod = "GET", notes = "分页获取角色列表")
    @GetMapping
    public IPage<Role> getPageRoles(Long page, Long pageSize, String sysId, String roleName) {
        Page<Role> pageEntity = new Page<Role>(page, pageSize);
        if (roleName == null) {
            roleName = "";
        }
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.like("role_name", roleName);
        return iRoleService.page(pageEntity, queryWrapper);
    }

    /**
     * 返回所有的角色列表
     *
     * @param sysId
     * @param roleName
     * @return
     */
    @ApiOperation(value = "根据条件获取所有角色列表", httpMethod = "GET", notes = "根据条件获取所有角色列表")
    @GetMapping("/get-all-roles")
    public List<Role> getRoles(String sysId, String roleName) {
        if (roleName == null) {
            roleName = "";
        }
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.like("role_name", roleName);
        return iRoleService.list(queryWrapper);
    }

    /**
     * 编辑角色信息时 查询要编辑的角色信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据角色id查询角色信息", httpMethod = "GET", notes = "根据角色id查询角色信息，编辑角色是查询要编辑的角色内容")
    @GetMapping("/get-role-by-id")
    public RoleMenuVo getRoleById(String id) {
        return iRoleService.getRoleById(id);
    }

    /**
     * 新增和修改角色信息
     *
     * @param roleMenuVo
     */
    @ApiOperation(value = "新增和修改角色信息", httpMethod = "POST", notes = "新增和修改角色信息")
    @PostMapping
    public void saveOrUpdateRole(@RequestBody RoleMenuVo roleMenuVo) {

        iRoleService.saveOrUpdateRole(roleMenuVo);
    }


    /**
     * 根据ID停用角色
     * @param id
     * @return
     */
    @ApiOperation(value = "停用角色信息", httpMethod = "DELETE", notes = "停用角色信息")
    @DeleteMapping
    public boolean deleteRole(String id) {
        RoleMenuVo role = iRoleService.getRoleById(id);
        if (role == null) {
            throw new DcException("没有找到对应的角色");
        } else {
            return iRoleService.removeById(id);
        }
    }
}
