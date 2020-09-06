package com.ai2331.ds.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = { "com.ai2331.*.dao", "com.ai2331.*.*.dao" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisSqlSessionFactory {
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration globalConfiguration() {
		return new org.apache.ibatis.session.Configuration();
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource, org.apache.ibatis.session.Configuration config) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dynamicDataSource);
		bean.setConfiguration(config);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*-mapper.xml"));
		return bean.getObject();
	}
}
