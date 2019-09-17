package com.dn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dn.sys.entity.Dict;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author dna
 * @since 2019-08-16
 */
public interface IDictService extends IService<Dict> {

    public List<Dict> getByTypeId(String typeId);

}
