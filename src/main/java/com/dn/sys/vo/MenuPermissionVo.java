package com.dn.sys.vo;

import com.dn.sys.entity.ApiPermission;
import com.dn.sys.entity.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class MenuPermissionVo {

    private String id ;
    private Menu menu ;
    private List<ApiPermission> permissions = new ArrayList<>() ;


}
