package com.ai2331.mybatis.test.dao;

import org.springframework.stereotype.Repository;

import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.test.entity.T1;

@Repository
@DataSource(DataSourceKey.MASTER)
public interface T1Mapper{
	T1 findById(Integer id);
}
