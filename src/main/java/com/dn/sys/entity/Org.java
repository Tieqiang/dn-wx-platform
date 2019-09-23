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
@TableName("sys_org")
@Accessors(chain = true)
@ApiModel(value = "SysOrg对象", description = "")
public class Org extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "父机构")
    private String parentId;

    @ApiModelProperty(value = "机构编码")
    private String orgCode;


}
