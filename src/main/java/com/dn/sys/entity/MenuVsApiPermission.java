package com.dn.sys.entity;

import com.dn.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author dna
* @since 2019-06-19
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @ApiModel(value="MenuVsApiPermission对象", description="")
    public class MenuVsApiPermission extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private String permissionId;

    private String menuId;


}
