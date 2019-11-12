package com.ai2331;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class BackendAppRun {

	public static void main(String[] args) {
		new SpringApplicationBuilder().web(WebApplicationType.SERVLET).sources(BackendAppRun.class).run(args);
	}
}
