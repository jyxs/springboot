package com.ai2331.ds;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "slaverFactory", transactionManagerRef = "transSlaver", basePackages = "com.ai2331.jpa.slaver.*.dao")
public class JPASlaverDBConfig {

	@Autowired
	@Qualifier("slaverds")
	private DataSource slaverds;

	@Bean(name = "slaverFactory")
	public LocalContainerEntityManagerFactoryBean slaverFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(slaverds).packages("com.ai2331.*.entity")// 设置实体类所在位置.扫描所有带有 @Entity 注解的类
				.persistenceUnit("persisSlaver").build();
	}

	@Bean(name="transSlaver")
	protected PlatformTransactionManager transactionManagerSlaver(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(slaverFactory(builder).getObject());
	}
}
