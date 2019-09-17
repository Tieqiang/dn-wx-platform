package com.dn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dn.sys.entity.ApiPermission;
import com.dn.sys.mapper.ApiPermissionMapper;
import com.dn.sys.service.IApiPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@Service
public class ApiPermissionServiceImpl extends ServiceImpl<ApiPermissionMapper, ApiPermission> implements IApiPermissionService {

    /**
     * 保存权限
     * @param resourceList
     */
    @Transactional
    public void saveOrUpdatePermissions(List<ApiPermission> resourceList) {
        for(ApiPermission permission:resourceList){
            QueryWrapper<ApiPermission> queryMapper = new QueryWrapper<>();
            queryMapper.eq("permission_code",permission.getPermissionCode()) ;
            ApiPermission one = this.getOne(queryMapper);
            if(one==null){
                this.save(permission);
            }
        }
    }

    @Override
    public IPage<ApiPermission> getPermissions(Page page, String sysId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(sysId != null && !"".equals(sysId)){
            queryWrapper.eq("sys_id",sysId);
        }
        return this.baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void saveOrUpdatePermission(ApiPermission apiPermission) {
        if(apiPermission.getId() != null && !"".equals(apiPermission.getId())){
            //编辑
            this.baseMapper.updateById(apiPermission);
        }else {
            //新增
            this.baseMapper.insert(apiPermission);
        }

    }

    @Override
    public void removePermissionById(String id) {
        this.removeById(id);
    }
}
