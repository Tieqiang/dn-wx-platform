package com.dn.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dn.sys.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DomainMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        //设置插入时间的方法
        this.setInsertFieldValByName("createDate",new Date().getTime(),metaObject);
        this.setInsertFieldValByName("delFlag",0,metaObject);
        this.setFieldValByName("accountNonExpired",true,metaObject);
        this.setFieldValByName("accountNonLocked",true,metaObject);
        this.setFieldValByName("credentialsNoExpired",true,metaObject);
        this.setUpdateFieldValByName("lastUpdateDate",new Date().getTime(),metaObject) ;

        //创建者
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        this.setFieldValByName("creator",user.getId(),metaObject);
//        this.setFieldValByName("id",UUID.randomUUID().toString(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setUpdateFieldValByName("lastUpdateDate",new Date().getTime(),metaObject) ;
    }
}
