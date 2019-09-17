package com.dn;

import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.dn.common.aop.CountSqlInterceptor;
import com.dn.common.aop.MyPaginationInterceptor;
import com.dn.common.config.SystemProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;
import java.util.Date;


@SpringBootApplication
@MapperScan(value = "com.dn.*.mapper")
@EnableSwagger2
@RestController
@EnableConfigurationProperties(value = SystemProperties.class)
public class DnSensationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DnSensationApplication.class, args);
    }


    @GetMapping(path = "/api/get-date")
    public Date getData(){
        return new Date() ;
    }


    @Bean
    public LogicSqlInjector createLogicSqlIntercepter(){
        return new LogicSqlInjector() ;
    }

    /**
     * 分页配置
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        CountSqlInterceptor countSqlInterceptor = new CountSqlInterceptor() ;
        MyPaginationInterceptor paginationInterceptor = new MyPaginationInterceptor();
        paginationInterceptor.setCountSqlParser(countSqlInterceptor);
        return paginationInterceptor;
    }
    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize("300MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("300MB");
        return factory.createMultipartConfig();
    }


}
