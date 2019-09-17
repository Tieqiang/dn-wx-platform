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
 * 字典表
 * </p>
 *
 * @author dna
 * @since 2019-08-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dict")
@ApiModel(value = "Dict对象", description = "字典表")
public class Dict extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "类型_id")
    private String typeId;

    @ApiModelProperty(value = "字典名")
    private String name;

    @ApiModelProperty(value = "字典值")
    private String value;


}
