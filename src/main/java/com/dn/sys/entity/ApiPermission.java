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
@TableName("sys_api_permission")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SysApiPermission对象", description = "")
public class ApiPermission extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    @ApiModelProperty(value = "权限编码")
    private String permissionCode;


}
