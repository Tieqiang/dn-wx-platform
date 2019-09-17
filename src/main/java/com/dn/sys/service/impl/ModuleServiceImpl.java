package com.dn.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dn.sys.mapper.ModuleMapper;
import com.dn.sys.service.IModuleService;
import org.springframework.stereotype.Service;
import com.dn.sys.entity.Module ;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {

    @Override
    public void saveOrUpdateModule(Module module) {
        if(module.getId() !=null && !"".equals(module.getId())){
            //编辑
            this.baseMapper.updateById(module);
        }else{
            //新增
            this.baseMapper.insert(module);
        }
    }

    @Override
    public void removeModule(String id) {
        this.baseMapper.deleteById(id);
    }
}
