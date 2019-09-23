package com.dn.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dn.common.exception.DcException;
import com.dn.sys.entity.Org;
import com.dn.sys.mapper.OrgMapper;
import com.dn.sys.mapper.UserMapper;
import com.dn.sys.service.IOrgService;
import com.dn.sys.vo.EditUserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dna
 * @since 2019-06-23
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean removeOrgById(String id) {
        Org org = this.getById(id);
        if (org == null) {
            throw new DcException("未找到要删除的机构信息");
        }

        IPage<EditUserInfoVo> userPage = userMapper.getUserPage(new Page(0, 100), "", "", org.getId());
        if (userPage.getRecords().size() > 0) {
            throw new DcException("该机构下存在有用户信息，请先处理用户信息，然后在删除机构！");
        }

        return this.removeById(id);
    }

    @Override
    public Org getOrgCompanyByOrgId(String orgId) {
        Org byId = this.getById(orgId);
        if (StringUtils.isEmpty(byId.getParentId()) || StringUtils.isBlank(byId.getParentId())||"0".equals(byId.getParentId())) {
            return byId;
        } else {
            return this.getOrgCompanyByOrgId(byId.getParentId());
        }
    }
}
