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
 * 数据授权记录
 * </p>
 *
 * @author dna
 * @since 2019-06-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_data_authorized_record")
@Accessors(chain = true)
@ApiModel(value = "SysDataAuthorizedRecord对象", description = "数据授权记录")
public class DataAuthorizedRecord extends BaseDomain {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据级别显示名称")
    private String dataLevelId;

    @ApiModelProperty(value = "被授权用户")
    private String userId;

    @ApiModelProperty(value = "授权开始时间")
    private Long startDate;

    @ApiModelProperty(value = "结束时间")
    private Long endDate;

    @ApiModelProperty(value = "所属系统")
    private String sysId;


}
