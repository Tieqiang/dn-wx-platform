package com.dn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dn.sys.entity.MenuVsApiPermission;
import com.dn.sys.vo.MenuPermissionVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
public interface IMenuVsApiPermissionService extends IService<MenuVsApiPermission> {

    public void saveOrUpdateMenuVsApiPermission(MenuPermissionVo menuPermissionVo);

}
