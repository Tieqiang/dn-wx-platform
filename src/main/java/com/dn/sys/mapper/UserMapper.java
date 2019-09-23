package com.dn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dn.sys.entity.User;
import com.dn.sys.vo.EditUserInfoVo;
import com.dn.sys.vo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取当前用户所拥有的权限
     *
     * @param user
     * @return
     */
    List<String> getUserPermission(User user);


    /**
     * 获取用户信息
     *
     * @param user
     * @return
     */
    UserInfo getCurrentUserInfo(User user);

    IPage<EditUserInfoVo> getUserPage(Page page,  @Param("realName") String realName, @Param("nikeName") String nikeName, @Param("orgId") String orgId);


    EditUserInfoVo getUserById(@Param("id") String id);

    public List<User> getAllUsers( @Param("queryParms") String queryParms);


}
