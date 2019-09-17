package com.dn.sys.vo;

import com.dn.sys.entity.Org;
import com.dn.sys.entity.Role;
import com.dn.sys.entity.User;
import lombok.Data;

import java.util.List;

/**
 * 编辑用户 根据id返回的用户实体，包括用户信息，角色信息，和部门信息
 */
@Data
public class EditUserInfoVo {
    private String id;
    private User user;
    private Org org;
    private List<Role> roleList;
}
