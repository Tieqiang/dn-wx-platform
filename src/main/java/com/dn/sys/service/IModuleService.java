package com.dn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dn.sys.entity.Module ;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dna
 * @since 2019-06-19
 */
public interface IModuleService extends IService<Module> {

    public void saveOrUpdateModule(Module module);

    public void removeModule(String id);

}
