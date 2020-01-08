package com.ai2331.ds;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ai2331.ds.DataSourceHolder.DataSourceKey;
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

	@Bean(name = "dynamicDataSource")
	public DynamicDataSource DataSource(@Qualifier("masterds") DataSource masterds, @Qualifier("slaverds") DataSource slaverds) {
		Map<Object, Object> targetDataSource = new HashMap<>();
		targetDataSource.put(DataSourceKey.MASTER.getDskey(), masterds);
		targetDataSource.put(DataSourceKey.SLAVER.getDskey(), slaverds);
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSource);
		dataSource.setDefaultTargetDataSource(masterds);
		return dataSource;
	}
}
