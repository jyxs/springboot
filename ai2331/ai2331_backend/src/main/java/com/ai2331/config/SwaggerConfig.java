package com.ai2331.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@EnableKnife4j
public class SwaggerConfig{
	public static final String VERSION = "1.0.0";
	
	@Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.pathMapping("/")
        		// 定义是否开启swagger，false为关闭，可以通过变量控制
        		.enable(true)
        		 // 将api的元信息设置为包含在json ResourceListing响应中。 
                .apiInfo(apiInfo())
                // 选择哪些接口作为swagger的doc发布
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                // 支持的通讯协议集合
                .protocols(newHashSet("https", "http"))
                
                // 授权信息设置，必要的header token等认证信息
                .securitySchemes(securitySchemes())
                
                // 授权信息全局应用
                .securityContexts(securityContexts());
    }
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("AI2331")
                .description("AI2331请求接口")
                .contact(new Contact("AI2331", "", "56882658@qq.com"))
                .version(VERSION)
                .build();
    }
	
	/**
	 * 设置授权信息
	 */
	private List<SecurityScheme> securitySchemes() {
		return Collections.singletonList(new ApiKey("BASE_TOKEN", "token", "pass"));
	}
	
	/**
	 * 授权信息全局应用
	 */
	private List<SecurityContext> securityContexts() {
		return Collections.singletonList(SecurityContext.builder()
				.securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[] { new AuthorizationScope("global", "") }))).build());
	}

	@SafeVarargs
	private final <T> Set<T> newHashSet(T... ts) {
		if (ts.length > 0) {
			return new LinkedHashSet<>(Arrays.asList(ts));
		}
		return null;
	}
}
