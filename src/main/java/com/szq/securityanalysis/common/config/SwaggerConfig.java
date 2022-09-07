package com.szq.securityanalysis.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/8/18 22:21
 * @Version 1.0.0
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.szq.securityanalysis.controller")
public class SwaggerConfig {

    public Docket webApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .groupName("证券分析api")
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("苏中秋","http://sxxxx.com", "1300853631@qq.com");
        return new ApiInfo(
                "证券分析",
                "证券分析api",
                "V1.0",
                "http://xxxxx.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
