package com.ai2331.ds;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai2331.ds.DataSourceHolder.DataSourceKey;

@Aspect
@Component
public class DataSourceAspect {
	private Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

	@Before("@within(mds)")
	public void beforeDataSource(JoinPoint point, DataSource mds) {
		DataSourceKey value = mds.value();
		DataSourceHolder.setDataSourceKey(value);
		logger.info(">>>>>>>>>>>>>>>>>>current data source is:{}", value);
	}

	@After("@within(mds)")
	public void afterDataSource(JoinPoint point, DataSource mds) {
		DataSourceHolder.clearDataSourceKey();
	}
}
