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
 * 数据访问级别
 * </p>
 *
 * @author dna
 * @since 2019-06-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_data_level")
@Accessors(chain = true)
@ApiModel(value = "SysDataLevel对象", description = "数据访问级别")
public class DataLevel extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据级别显示名称")
    private String dataLevelName;

    @ApiModelProperty(value = "数据级别")
    private Integer dataLevel;



}
