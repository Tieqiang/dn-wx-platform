package com.dn.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dn.sys.entity.ApiPermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
public interface IApiPermissionService extends IService<ApiPermission> {

    void saveOrUpdatePermissions(List<ApiPermission> resourceList);

    public IPage<ApiPermission> getPermissions(Page page, String sysId);

    public void saveOrUpdatePermission(ApiPermission apiPermission);

    public void removePermissionById(String id);
}
