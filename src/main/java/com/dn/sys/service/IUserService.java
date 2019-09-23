package com.dn.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dn.sys.entity.User;
import com.dn.sys.vo.EditUserInfoVo;
import com.dn.sys.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
public interface IUserService extends IService<User> {

    public IPage<EditUserInfoVo> getUserPage(Page page, String realName, String nikeName, String orgId);


    UserInfo getCurrentUserInfo(User user);


    UserInfo getCurrentUserInfo(HttpServletRequest request);


    public EditUserInfoVo getUserByid(String id);

    public void saveOrUpdateUser(EditUserInfoVo editUserInfoVo);

    public List<User> getAllUsers( String queryParms);

    /**
     * 获取关系人为当前用户的用户
     * @param userId
     * @return
     */
    List<User> getAssociatedUsers(String userId) ;

    List<User> getUserByOrgId(String orgId) ;
}
