package com.ccut.dachuang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableWebMvc
public class SwaggerConfig  implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()//扫描的接口

                .apis(RequestHandlerSelectors.basePackage("com.ccut.dachuang.controller")) //扫描的类
//                .paths(PathSelectors.ant("com.ccut.dachuang.controller.MenuController")) //过滤路径
                .build()
//                .groupName("WZ")
                .apiInfo(apiInfo());

        // 扫描所有，项目中的所有接口都会被扫描到
        //        any()
        //// 不扫描接口
        //        none()
        //// 通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
        //        withMethodAnnotation(final Class<? extends Annotation> annotation)
        //// 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
        //        withClassAnnotation(final Class<? extends Annotation> annotation)
        //// 根据包路径扫描接口
        //        basePackage(final String basePackage)

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("外卖项目接口文档1") // 文档标题
                .description("基本的一些接口说明") // 文档基本描述
//                .contact(new Contact("xxxx", "https://blog.csdn.net", "1835187730@qq.com")) // 联系人信息
//                .termsOfServiceUrl("http://terms.service.url/组织链接") // 组织链接
                .version("1.0") // 版本
//                .license("Apache 2.0 许可") // 许可
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0") // 许可链接
//                .extensions(new ArrayList<>()) // 拓展
                .build();
    }

}
