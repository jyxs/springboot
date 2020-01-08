package com.ai2331.ds.jpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "masterFactory",transactionManagerRef = "transMaster",basePackages = "com.ai2331.jpa.master.*.dao")
public class JPAMasterDBConfig {

	@Autowired
	private DataSource masterds;
	
	@Primary
	@Bean(name = "masterFactory")
	public LocalContainerEntityManagerFactoryBean masterFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(masterds)
				.packages("com.ai2331.*.entity")//设置实体类所在位置.扫描所有带有 @Entity 注解的类
				.persistenceUnit("persisMaster").build();
	}
	
	@Primary
	@Bean(name="transMaster")
	protected PlatformTransactionManager transactionManagerMaster(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(masterFactory(builder).getObject());
	}
}
