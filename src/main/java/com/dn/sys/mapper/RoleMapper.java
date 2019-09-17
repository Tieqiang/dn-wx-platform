package com.dn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dn.sys.entity.Role;
import com.dn.sys.vo.RoleMenuVo;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
public interface RoleMapper extends BaseMapper<Role> {

    public RoleMenuVo getRoleById(String id);
}
