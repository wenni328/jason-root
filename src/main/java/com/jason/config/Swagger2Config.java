package com.jason.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger api配置
 *
 * @author xy
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
@PropertySource("classpath:boot.properties")
public class Swagger2Config extends WebMvcConfigurerAdapter {

    @Value("${swagger.enable}")
    private Boolean enableSwagger;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket buildDocket() {
        if (!enableSwagger) {
            return null;
        }
        Docket docket = new Docket(DocumentationType.SWAGGER_2).groupName("v1").apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.jason.controller"))// controller路径
                .paths(PathSelectors.any()).build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Jason使用Swagger2构建RESTful APIs").description("").contact("Jason")
                .version("1.0").build();
    }
}
