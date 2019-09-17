package com.dn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dn.sys.entity.Menu;
import com.dn.sys.vo.MenuPermissionVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
public interface MenuMapper extends BaseMapper<Menu> {

    public List<MenuPermissionVo> getMenuPermission();
}
