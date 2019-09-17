package com.dn.sys.vo;

import com.dn.sys.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleVO implements Serializable {

    private Role role  ;

    private List<MenuPermissionVo> permissionVos ;



}
