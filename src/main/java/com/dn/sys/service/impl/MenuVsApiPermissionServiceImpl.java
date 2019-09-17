package com.dn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dn.sys.entity.ApiPermission;
import com.dn.sys.entity.Menu;
import com.dn.sys.entity.MenuVsApiPermission;
import com.dn.sys.mapper.MenuVsApiPermissionMapper;
import com.dn.sys.service.IMenuVsApiPermissionService;
import com.dn.sys.vo.MenuPermissionVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@Service
public class MenuVsApiPermissionServiceImpl extends ServiceImpl<MenuVsApiPermissionMapper, MenuVsApiPermission> implements IMenuVsApiPermissionService {

    @Override
    public void saveOrUpdateMenuVsApiPermission(MenuPermissionVo menuPermissionVo) {

        if(menuPermissionVo.getId() != null || "".equals(menuPermissionVo.getId())){
            //编辑
            //删除该菜单下所有权限
            Menu menu = menuPermissionVo.getMenu();
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("menu_id",menu.getId());
            this.baseMapper.delete(queryWrapper);
            List<ApiPermission> permissionList = menuPermissionVo.getPermissions();
            for (ApiPermission permission :permissionList){
                MenuVsApiPermission menuVsApiPermission = new MenuVsApiPermission();
                menuVsApiPermission.setMenuId(menu.getId());
                menuVsApiPermission.setPermissionId(permission.getId());
                this.baseMapper.insert(menuVsApiPermission);
            }

        }else {
            //新增
            Menu menu = menuPermissionVo.getMenu();
            List<ApiPermission> permissionList = menuPermissionVo.getPermissions();
            for (ApiPermission permission :permissionList){
                MenuVsApiPermission menuVsApiPermission = new MenuVsApiPermission();
                menuVsApiPermission.setMenuId(menu.getId());
                menuVsApiPermission.setPermissionId(permission.getId());
                this.baseMapper.insert(menuVsApiPermission);
            }
        }



    }
}
