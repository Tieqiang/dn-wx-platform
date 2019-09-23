package com.dn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dn.common.config.SystemProperties;
import com.dn.common.exception.validate.NullParamException;
import com.dn.common.exception.validate.ValidateException;
import com.dn.common.service.SysUserService;
import com.dn.common.util.JwtUtils;
import com.dn.sys.entity.Org;
import com.dn.sys.entity.Role;
import com.dn.sys.entity.RoleVsUser;
import com.dn.sys.entity.User;
import com.dn.sys.mapper.UserMapper;
import com.dn.sys.service.IRoleVsUserService;
import com.dn.sys.service.IUserService;
import com.dn.sys.vo.EditUserInfoVo;
import com.dn.sys.vo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private SystemProperties systemProperties;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private IRoleVsUserService iRoleVsUserService;

    @Override
    public IPage<EditUserInfoVo> getUserPage(Page page, String realName, String nikeName, String orgId) {

        IPage<EditUserInfoVo> userIPage = this.baseMapper.getUserPage(page, realName, nikeName, orgId);
        return userIPage;
    }

    /**
     * 获取当前用户的登陆信息
     *
     * @param user
     * @return
     */
    @Override
    public UserInfo getCurrentUserInfo(User user) {
        return this.baseMapper.getCurrentUserInfo(user);
    }

    @Override
    public UserInfo getCurrentUserInfo(HttpServletRequest request) {
        String header = request.getHeader(systemProperties.getAuthention().getName());
        String token = header.substring(systemProperties.getAuthention().getPrefix().length());
        if (logger.isDebugEnabled()) {
            logger.info("从请求信息中，获取的token=" + token);
        }
        String userName = jwtUtils.getUserName(token);
        return this.getCurrentUserInfo(this.sysUserService.loadUserByUsername(userName));
    }

    @Override
    public EditUserInfoVo getUserByid(String id) {
        return this.baseMapper.getUserById(id);
    }

    @Override
    public void saveOrUpdateUser(EditUserInfoVo editUserInfoVo) {
        User user = editUserInfoVo.getUser();
        List<Role> roleList = editUserInfoVo.getRoleList();
        Org org = editUserInfoVo.getOrg();
        User user1 = sysUserService.loadUserByUsername(user.getUsername());
        if(user1 != null && !user1.getId().equals(user.getId())){
            throw new ValidateException("用户名已存在");
        }

        if(roleList==null || roleList.size()==0){
            throw new NullParamException("必须选择角色");
        }

        if(editUserInfoVo.getId()==null || "".equals(editUserInfoVo.getId())){
            //保存用户
            user.setId(UUID.randomUUID().toString());
            user.setAccountNonExpired(true);
            user.setCredentialsNoExpired(true);
            user.setAccountNonLocked(true);
            user.setOrgId(org.getId());
            this.baseMapper.insert(user);



            for(int i =0;i<roleList.size();i++){
                Role role = roleList.get(i);
                RoleVsUser roleVsUser = new RoleVsUser();
                roleVsUser.setUserId(user.getId());
                roleVsUser.setRoleId(role.getId());
                iRoleVsUserService.save(roleVsUser);
            }
        }else{
            //编辑用户
            if(user.getId()==null || "".equals(user.getId())){
                user.setId(editUserInfoVo.getId());
            }
            user.setAccountNonExpired(true);
            user.setCredentialsNoExpired(true);
            user.setAccountNonLocked(true);
            user.setOrgId(org.getId());
            this.baseMapper.updateById(user);
            iRoleVsUserService.remove(new QueryWrapper<RoleVsUser>().eq("user_id",user.getId()));

            for(Role role :roleList){
                RoleVsUser roleVsUser = new RoleVsUser();
                roleVsUser.setUserId(user.getId());
                roleVsUser.setRoleId(role.getId());
                iRoleVsUserService.save(roleVsUser);
            }
        }

    }

    @Override
    public List<User> getAllUsers( String queryParms) {
        return this.baseMapper.getAllUsers(queryParms);
    }

    /**
     * 获取 关系人为userId 的用户列表
     * @param userId
     * @return
     */
    @Override
    public List<User> getAssociatedUsers(String userId) {

        QueryWrapper<User> query = new QueryWrapper<>();
        query.like("associated_user_id","%"+userId+"%") ;
        return this.baseMapper.selectList(query) ;
    }


    public List<User> getUserByOrgId(String orgId){
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("org_id",orgId) ;
        return this.baseMapper.selectList(query) ;
    }
}
