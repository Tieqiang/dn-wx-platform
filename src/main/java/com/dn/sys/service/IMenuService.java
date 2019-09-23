package com.dn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dn.sys.entity.Menu;
import com.dn.sys.vo.MenuPermissionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
public interface IMenuService extends IService<Menu> {


    public List<MenuPermissionVo> getMenuPermission();

    public List<Menu> getMenus();

    public void saveOrUpdateMenu(Menu menu);

    public void removeMenu(String id);

}
