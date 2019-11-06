package com.ai2331;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WebAppRun {

	public static void main(String[] args) {
		new SpringApplicationBuilder().web(WebApplicationType.SERVLET).sources(WebAppRun.class).run(args);
	}
}
