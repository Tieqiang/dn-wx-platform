package com.dn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dn.common.exception.validate.NullParamException;
import com.dn.sys.entity.Role;
import com.dn.sys.entity.RoleVsMenu;
import com.dn.sys.mapper.RoleMapper;
import com.dn.sys.service.IRoleService;
import com.dn.sys.service.IRoleVsMenuService;
import com.dn.sys.vo.RoleMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleVsMenuService iRoleVsMenuService;

    @Override
    public RoleMenuVo getRoleById(String id) {
        if(id == null || "".equals(id)){
            throw new NullParamException();
        }
        return this.baseMapper.getRoleById(id);
    }

    @Override
    public void saveOrUpdateRole(RoleMenuVo roleMenuVo) {
        String id = roleMenuVo.getId();
        Role role = roleMenuVo.getRole();
        List<RoleVsMenu> roleVsMenuList = roleMenuVo.getRoleVsMenuList();
        if(id ==null || "".equals(id)){
            //新增
            role.setId(UUID.randomUUID().toString());
            this.baseMapper.insert(role);
            for(RoleVsMenu roleVsMenu :roleVsMenuList){
                roleVsMenu.setRoleId(role.getId());
                iRoleVsMenuService.save(roleVsMenu);
            }
        }else{
            //编辑
            this.baseMapper.updateById(role);
            iRoleVsMenuService.remove(new QueryWrapper<RoleVsMenu>().eq("role_id",role.getId()));
            for(RoleVsMenu roleVsMenu :roleVsMenuList){
                roleVsMenu.setRoleId(role.getId());
                iRoleVsMenuService.save(roleVsMenu);
            }
        }
    }
}
