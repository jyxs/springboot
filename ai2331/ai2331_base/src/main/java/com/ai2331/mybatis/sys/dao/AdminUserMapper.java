package com.ai2331.mybatis.sys.dao;

import org.springframework.stereotype.Repository;

import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.sys.entity.AdminUser;

@Repository
@DataSource(DataSourceKey.SLAVER)
public interface AdminUserMapper{

	AdminUser findOneByUsername(String userName);

}
