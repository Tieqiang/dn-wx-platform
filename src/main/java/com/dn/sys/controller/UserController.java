package com.dn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dn.common.config.SystemProperties;
import com.dn.common.controller.BaseController;
import com.dn.common.exception.DcException;
import com.dn.sys.entity.User;
import com.dn.sys.service.IUserService;
import com.dn.sys.vo.EditUserInfoVo;
import com.dn.sys.vo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
@Api(value = "用户信息服务", tags = "用户信息服务", authorizations = {@Authorization("login")})
@RestController
@RequestMapping("/api/sys/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private SystemProperties systemProperties;


    @ApiOperation(value = "添加修改用户", httpMethod = "POST", notes = "添加修改用户")
    @PostMapping
    //@PriOperation(operationName = "用户添加", operationCode = "user:saveOrUpdate")
    //@PreAuthorize("hasPermission('','user:saveOrUpdate')")
    public String saveOrUpdate(@RequestBody EditUserInfoVo editUserInfoVo) {
        User user = editUserInfoVo.getUser();
        if (user.getPassword() != null && !"".equals(user.getPassword())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
        }
        iUserService.saveOrUpdateUser(editUserInfoVo);
        return "success";
    }

    /**
     * 密码修改功能
     *
     * @param request     当前请求的会话 获取当前登录者
     * @param oldPassword
     * @param newPassword
     */
    @ApiOperation(value = "用户密码修改", httpMethod = "POST", notes = "用户密码修改")
    @PostMapping("change-pwd")
    public void changePassword(HttpServletRequest request, @RequestParam("oldPassword") String oldPassword,
                               @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
            throw new DcException("密码不能为空");
        }
        UserInfo currentLoginUser = this.getCurrentLoginUser(request);
        User user = currentLoginUser.getUser();
        user = iUserService.getById(user.getId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(oldPassword, user.getPassword())) {
            throw new DcException("旧密码错误");
        }
        user.setPassword(encoder.encode(newPassword));
        iUserService.saveOrUpdate(user);
    }

    /**
     * 根据所属系统，查询系统下的所有用户，进行分页
     *
     * @return
     */
    @ApiOperation(value = "获取用户列表", httpMethod = "GET", notes = "根据条件查询用户列表")
    @GetMapping
    public IPage<EditUserInfoVo> getUsers(Long page, Long pageSize, String realName, String nikeName, String orgId) {
        if (page == null) page = 1L;
        if (pageSize == null) pageSize = 10L;

        Page<EditUserInfoVo> pageEntity = new Page<EditUserInfoVo>(page, pageSize);
        IPage<EditUserInfoVo> userIPage = iUserService.getUserPage(pageEntity, realName, nikeName, orgId);
        return userIPage;
    }


    @ApiOperation(value = "获取当前登陆用户", httpMethod = "GET", notes = "获取当前登陆用户信息")
    @GetMapping("get-current-user")
    public UserInfo getCurrentLoginUser(HttpServletRequest request) {
        UserInfo userInfo = iUserService.getCurrentUserInfo(request);
        return userInfo;
    }

    /**
     * 根据用户id 返回用户信息，包括用户的角色和部门信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "编辑用户时查询用户信息", httpMethod = "GET", notes = "编辑用户时查询用户信息")
    @GetMapping("/get-user-by-id")
    public EditUserInfoVo getUserByid(String id) {
        return iUserService.getUserByid(id);
    }

    /**
     * @return
     */
    @ApiOperation(value = "查询所有用户列表", httpMethod = "GET", notes = "查询所有用户列表，不分页，给关系人用")
    @GetMapping("/get-all-users")
    public List<User> getAllUsers( String queryParms) {
        return iUserService.getAllUsers( queryParms);
    }

    @ApiOperation(value = "删除用户", httpMethod = "DELETE", notes = "删除用户")
    @DeleteMapping("/remove-by-id")
    public String removeById(String id) {

        boolean b = iUserService.removeById(id);
        if (b) {
            return "success";
        } else {
            return "fail";
        }
    }

    @PostMapping("rest-pwd")
    @ApiOperation(value = "重置用户密码", httpMethod = "POST", notes = "重置用户密码")
    public boolean resetPassword(String id, @RequestParam(required = false) String defaultPassword) {
        User byId = iUserService.getById(id);
        if (byId == null) {
            throw new DcException("未找到用户信息,请确认后重试！");
        }

        if (StringUtils.isBlank(defaultPassword)) {
            defaultPassword = systemProperties.getDefaultPassword();
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        byId.setPassword(encoder.encode(defaultPassword));
        return iUserService.saveOrUpdate(byId);
    }

    /**
     * 查询关系人
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "查询当前用户的关系人",httpMethod = "GET",tags = "查询当前用户的关系人")
    @GetMapping("get-associated-users")
    public List<User> getAssociatedUsers(String ids) {
        String[] split = ids.split(",");

        QueryWrapper<User> query = new QueryWrapper<>();
        query.in("id", split);
        return this.iUserService.getBaseMapper().selectList(query);
    }


    /**
     * 查询关系人为当前用户的人
     * @param id
     * @return
     */
    @ApiOperation(value = "查询关系人为当前用户的用户",httpMethod = "GET",tags = "查询关系人为当前用户的用户")
    @GetMapping("get-associate-users")
    public List<User> getAssociateUsers(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("associated_user_id", "%" + id + "%");
        return this.iUserService.getBaseMapper().selectList(queryWrapper);
    }

}
