package com.ai2331.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ai2331.interceptor.AppInterceptor;

/**
 * @author jingyu
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Resource
	private AppInterceptor appInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(appInterceptor).addPathPatterns("/**");
	}

}
