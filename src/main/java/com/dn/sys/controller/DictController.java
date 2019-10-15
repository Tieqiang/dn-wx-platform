package com.dn.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dn.common.controller.BaseController;
import com.dn.common.exception.DcException;
import com.dn.sys.entity.Dict;
import com.dn.sys.entity.DictType;
import com.dn.sys.service.IDictService;
import com.dn.sys.service.IDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author dna
 * @since 2019-08-16
 */
@Api(value = "字典服务", tags = "字典服务")
@RestController
@RequestMapping("/api/sys/dict")
public class DictController extends BaseController {

    @Autowired
    private IDictService dictService;

    @Autowired
    private IDictTypeService dictTypeService;

    @GetMapping("get-dict-values")
    @ApiOperation(value = "根据字典类型获取该字典的键值对", notes = "根据字典类型获取该字典的键值对", httpMethod = "GET")
    public List<Dict> findDictByTypeId(String typeId) {
        if (StringUtils.isBlank(typeId)) {
            throw new DcException("错误的字典类型");
        }
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id", typeId);
        queryWrapper.orderByDesc("create_date");
        return dictService.list(queryWrapper);
    }

    @ApiOperation(value = "获取所有字典", notes = "获取所有字典", httpMethod = "GET")
    @GetMapping
    public List<DictType> findDictType() {
        return dictTypeService.list();
    }

    @ApiOperation(value = "根据字典编码获取字典类型信息",notes = "根据字典编码获取字典类型信息",httpMethod = "GET")
    @GetMapping("get-dict-type-by-code")
    public DictType getDictTypeByCode(String typeCode){
        QueryWrapper<DictType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_code",typeCode);
        queryWrapper.orderByDesc("create_date");
        return dictTypeService.getOne(queryWrapper);
    }

    @GetMapping("get-dict-values-by-code")
    @ApiOperation(value = "根据字典code获取字典信息",httpMethod = "GET")
    public List<Dict> findDictByTypeCode(String typeCode){
        DictType dictTypeByCode = this.getDictTypeByCode(typeCode);
        if(dictTypeByCode==null){
            return null ;
        }
        String id = dictTypeByCode.getId();
        return this.findDictByTypeId(id);
    }

    @ApiOperation(value = "分页获取字典", notes = "分页获取字典", httpMethod = "GET")
    @GetMapping("get-dict-type-page")
    public IPage<DictType> findDictTypeByPage(@RequestParam(required = false) String typeName,
                                              @RequestParam(required = false, defaultValue = "20") long pageSize,
                                              @RequestParam(required = false, defaultValue = "1") long current) {
        if (StringUtils.isBlank(typeName) || StringUtils.isEmpty(typeName)) {
            typeName = "%%";
        } else {
            typeName = "%" + typeName + "%";
        }
        QueryWrapper<DictType> quer = new QueryWrapper<>();
        quer.like("name", typeName);
        quer.orderByDesc("create_date");
        return dictTypeService.page(new Page<DictType>(current, pageSize), quer);
    }

    @ApiOperation(value = "删除字典", httpMethod = "DELETE", tags = "删除字典")
    @DeleteMapping
    public boolean deleteDictType(String id) {
        return dictTypeService.removeById(id);
    }

    @PostMapping
    public boolean saveDictType(@RequestBody DictType dictType) {
        return dictTypeService.saveOrUpdate(dictType);
    }

    @PostMapping("save-dict")
    public boolean saveDict(@RequestBody Dict dict) {
        if (StringUtils.isBlank(dict.getTypeId())) {
            throw new DcException("字典类型不能为空");
        }

        this.findDictByTypeId(dict.getTypeId()).forEach(item -> {
            if (item.getName().equals(dict.getName()) || item.getValue().equals(dict.getValue())) {
                throw new DcException("错的键或值,请检查,键值不能重复");
            }
        });
        return dictService.save(dict);
    }


    @DeleteMapping("remove-dict-by-id")
    public boolean deleteDict(String id) {
        return dictService.removeById(id);
    }

}
