package com.dn.common.listener;


import com.dn.common.annotation.PriOperation;
import com.dn.sys.entity.ApiPermission;
import com.dn.sys.service.IApiPermissionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 系统启动后获取所有的对象
 */
@Component
public class PriContextListener implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    private IApiPermissionService iApiPermissionService;

    @Autowired
    private ObjectMapper objectMapper ;

    private final Logger logger = LoggerFactory.getLogger(PriContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {


        //跟容器
        if (event.getApplicationContext().getParent() == null) {
            ApplicationContext applicationContext = event.getApplicationContext();
            Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RestController.class);
            List<ApiPermission> resourceList = new ArrayList<>();
            for (String key : beansWithAnnotation.keySet()) {
                Object o = beansWithAnnotation.get(key);
                Class<?> aClass = AopUtils.getTargetClass(o);
                String name = aClass.getName();
//                if (!StringUtils.endsWith(StringUtils.upperCase(name), StringUtils.upperCase(key)) || (o instanceof BaseController)) {
//                    //判断是否为Spring创建的临时类的实例
//                    aClass = aClass.getSuperclass();
//                }


                Method[] methods = aClass.getMethods();
                for (Method method : methods) {
                    Annotation[] annotations = method.getAnnotations();
                    PriOperation annotation = method.getAnnotation(PriOperation.class);
                    if (annotation != null) {
                        ApiPermission apiPermission = new ApiPermission();
                        apiPermission.setPermissionCode(aClass.getSimpleName() + ":" + annotation.operationCode());
                        apiPermission.setPermissionName(annotation.operationName());
                        resourceList.add(apiPermission);
                    }
                }


            }

            System.out.println("代保存全县：");
            try {
                System.out.println(objectMapper.writeValueAsString(resourceList));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            iApiPermissionService.saveOrUpdatePermissions(resourceList);
        }
    }


}
