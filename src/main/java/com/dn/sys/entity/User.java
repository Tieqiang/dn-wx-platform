package com.dn.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dn.common.domain.BaseDomain;
import com.dn.common.handler.SysGrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author dna
 * @since 2019-06-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "")
public class User extends BaseDomain implements UserDetails {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "昵称")
    private String nikeName;

    @ApiModelProperty(value = "登录名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "用户过期标志")
    private boolean accountNonExpired;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "用户锁定标志")
    private boolean accountNonLocked;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "密码过期标志")
    private boolean credentialsNoExpired;

    @ApiModelProperty(value = "关系人")
    private String associatedUserId;

    @ApiModelProperty(value = "职称")
    private String title;

    @ApiModelProperty(value = "电话号码")
    private String phoneNumber;

    @ApiModelProperty(value = "数据级别")
    private String dataLevelId;

    @ApiModelProperty(value = "所属单位")
    private String orgId;


    @ApiModelProperty(value = "邮箱")
    private String email;

//    @JsonIgnore
    @Override
    public  Collection<? extends GrantedAuthority> getAuthorities() {
        SysGrantedAuthority sysGrantedAuthority = new SysGrantedAuthority(this);
        List<SysGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(sysGrantedAuthority);
        return grantedAuthorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNoExpired;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
