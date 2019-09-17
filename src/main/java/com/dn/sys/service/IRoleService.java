package com.dn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dn.sys.entity.Role;
import com.dn.sys.vo.RoleMenuVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
public interface IRoleService extends IService<Role> {

    public RoleMenuVo getRoleById(String id);

    public void saveOrUpdateRole(RoleMenuVo roleMenuVo);

}
