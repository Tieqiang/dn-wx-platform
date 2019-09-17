package com.dn.sys.vo;

import com.dn.sys.entity.Role;
import com.dn.sys.entity.RoleVsMenu;
import lombok.Data;

import java.util.List;

@Data
public class RoleMenuVo {

    private String id;

    private Role role;

    private List<RoleVsMenu> roleVsMenuList;

}
