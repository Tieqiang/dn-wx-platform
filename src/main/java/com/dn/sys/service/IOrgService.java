package com.dn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dn.sys.entity.Org;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dna
 * @since 2019-06-23
 */
public interface IOrgService extends IService<Org> {

    boolean removeOrgById(String id);

    Org getOrgCompanyByOrgId(String orgId);
}
