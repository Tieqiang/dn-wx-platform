package com.dn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dn.sys.entity.Dict;
import com.dn.sys.mapper.DictMapper;
import com.dn.sys.service.IDictService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author dna
 * @since 2019-08-16
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Override
    public List<Dict> getByTypeId(String typeId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(typeId != null && !"".equals(typeId)){
            queryWrapper.eq("type_id",typeId);
        }
        return this.baseMapper.selectList(queryWrapper);
    }
}
