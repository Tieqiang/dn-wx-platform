package com.dn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dn.common.controller.BaseController;
import com.dn.common.exception.DcException;
import com.dn.sys.entity.Dict;
import com.dn.sys.entity.DictType;
import com.dn.sys.service.IDictService;
import com.dn.sys.service.IDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author dna
 * @since 2019-08-16
 */
@Api(value = "字典服务",tags = "字典服务")
@RestController
@RequestMapping("/api/sys/dict")
public class DictController extends BaseController {

    @Autowired
    private IDictService dictService;

    @Autowired
    private IDictTypeService dictTypeService ;

    @GetMapping("get-dict-values")
    @ApiOperation(value = "根据字典类型获取该字典的键值对", notes = "根据字典类型获取该字典的键值对", httpMethod = "GET")
    public List<Dict> findDictByTypeId(String typeId) {
        if (StringUtils.isBlank(typeId)) {
            throw new DcException("错误的字典类型");
        }
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", typeId);
        return dictService.list(queryWrapper);
    }

    @ApiOperation(value = "获取所有字典",notes = "获取所有字典",httpMethod = "GET")
    @GetMapping
    public List<DictType> findDictType(){
        return dictTypeService.list() ;
    }

}
