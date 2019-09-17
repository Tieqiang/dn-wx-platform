package com.dn.common.aop;


import com.dn.common.domain.BaseDomain;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Aspect
@Component
public class IdGenerator {

    private final String V_POINT = "execution(* com.dn..mapper..*(..))";

    private final String V_SAVE_BATCH_POINT = "execution(* com.baomidou.mybatisplus.extension.service..*(..))";

    @Pointcut(value = V_POINT)
    public void pointCut() {
    }

    @Pointcut(value = V_SAVE_BATCH_POINT)
    public void pointSaveBatchCut() {
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Around(value = V_POINT)
    public Object generatorId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        String name = signature.getName();
        if (name.startsWith("insert")) {
            Object[] args = proceedingJoinPoint.getArgs();
            System.out.println(objectMapper.writeValueAsString(args));
            for (Object obj : args) {
                this.setObjId(obj);
            }
            System.out.println(objectMapper.writeValueAsString(args));
        }
        return proceedingJoinPoint.proceed();
    }

    @Around(value = V_SAVE_BATCH_POINT)
    public Object generatorBatchId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        String name = signature.getName();
        if (name.startsWith("saveOrUpdateBatch")) {
            Object[] args = proceedingJoinPoint.getArgs();
            System.out.println(objectMapper.writeValueAsString(args));
            for (Object obj : args) {
                if (obj instanceof Collection) {
                    List<Object> objectList = (List) obj;
                    for (Object object : objectList) {
                        setObjId(object);
                    }
                }
            }
            System.out.println(objectMapper.writeValueAsString(args));
        }
        return proceedingJoinPoint.proceed();
    }

    private void setObjId(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (obj instanceof BaseDomain) {
            String id = ((BaseDomain) obj).getId();
            if (StringUtils.isEmpty(id)) {
                Method setId = obj.getClass().getSuperclass().getDeclaredMethod("setId", String.class);
                setId.invoke(obj, UUID.randomUUID().toString());
            }
        }

    }

}
