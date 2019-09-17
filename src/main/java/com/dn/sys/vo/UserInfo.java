package com.dn.sys.vo;

import com.dn.sys.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserInfo {

    private String id ;
    private User user ;
    private List<MenuPermissionVo>  menus = new ArrayList<>();
}
