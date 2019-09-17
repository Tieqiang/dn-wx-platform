package com.dn.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dn.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("sys_role")
@Accessors(chain = true)
@ApiModel(value = "SysRole对象", description = "")
public class Role extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    @ApiModelProperty(value = "角色默认首页")
    private String roleIndexMenuId;

    @ApiModelProperty(value = "所属系统")
    private String sysId;


}
