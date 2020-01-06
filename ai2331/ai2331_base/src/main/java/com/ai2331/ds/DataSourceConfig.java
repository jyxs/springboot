package com.ai2331.ds;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class DataSourceConfig {

	
	@Bean("masterds")
	@ConfigurationProperties(prefix = "spring.datasource-master")
	@Primary
	public DataSource masterDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean("slaverds")
	@ConfigurationProperties(prefix = "spring.datasource-slaver")
	public DataSource slaverDataSource() {
		return DruidDataSourceBuilder.create().build();
	}
}
