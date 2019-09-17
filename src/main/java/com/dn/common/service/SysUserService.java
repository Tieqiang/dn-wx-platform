package com.dn.common.service;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dn.sys.entity.User;
import com.dn.sys.mapper.UserMapper;
import com.dn.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService {


    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper ;

    public User loadUserByUsername(String username) {
        QueryWrapper<User> wapper = new QueryWrapper<>();
        wapper.eq("username", username);
        User currentUser = userService.getOne(wapper);
        return currentUser;
    }

    public List<String> getPermissions(User user){
        return  userMapper.getUserPermission(user);
    }

}
