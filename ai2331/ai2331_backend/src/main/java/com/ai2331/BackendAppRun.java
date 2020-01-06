package com.ai2331;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ServletComponentScan
@ImportResource({"ai2331-*.xml"})
@MapperScan(basePackages = {"com.ai2331.dao","com.ai2331.*.dao"})
public class BackendAppRun {

	public static void main(String[] args) {
		new SpringApplicationBuilder().web(WebApplicationType.SERVLET).sources(BackendAppRun.class).run(args);
	}
}
