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
    * 字典类型
    * </p>
*
* @author dna
* @since 2019-08-16
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("sys_dict_type")
    @ApiModel(value="DictType对象", description="字典类型")
    public class DictType extends BaseDomain {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "类型")
    private String name;


}
