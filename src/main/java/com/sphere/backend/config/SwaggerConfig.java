/**
 * FileName: SwaggerConfig
 * Author: jane
 * Date: 2023/5/17 13:30
 * Description:
 * Version:
 */

package com.sphere.backend.config;

import com.sphere.backend.utils.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;


@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket userDocket(){
        return new Docket(DocumentationType.OAS_30)
                //定义是否开启swagger
                .enable(swaggerProperties.getEnable())
                .groupName("bbx")
                //api展示信息
                .apiInfo(apiInfo())
                //接口调试地址
                .host(swaggerProperties.getTryHost())
                //过滤条件
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sphere.backend.controller"))
                .build();
    }

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                //定义是否开启swagger
                .enable(swaggerProperties.getEnable())
                .groupName("fft");
    }

    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("jane","","");
        return new ApiInfo(
                swaggerProperties.getApplicationName() + "APi Doc",
                swaggerProperties.getApplicationDescription(),
                swaggerProperties.getApplicationVersion(),
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }



}
