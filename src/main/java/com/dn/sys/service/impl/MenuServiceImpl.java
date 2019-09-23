package com.dn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dn.sys.entity.Menu;
import com.dn.sys.mapper.MenuMapper;
import com.dn.sys.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<MenuPermissionVo> getMenuPermission() {
        return this.baseMapper.getMenuPermission();
    }

    public List<Menu> getMenus(){
        QueryWrapper queryWrapper = new QueryWrapper();

        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public void saveOrUpdateMenu(Menu menu) {
        if(menu.getId()!= null && "".equals(menu.getId())){
            //编辑
            this.baseMapper.updateById(menu);
        }else{
            //新增
            this.baseMapper.insert(menu);
        }

    }

    @Override
    public void removeMenu(String id) {
        this.baseMapper.deleteById(id);
    }
}
