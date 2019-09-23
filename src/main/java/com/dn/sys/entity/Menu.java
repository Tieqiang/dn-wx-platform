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
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value = "SysMenu对象", description = "")
public class Menu extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单编码")
    private String menuCode;

    @ApiModelProperty(value = "菜单路由")
    private String menuRouter;

    @ApiModelProperty(value = "父级菜案")
    private String parentMenuId;



}
