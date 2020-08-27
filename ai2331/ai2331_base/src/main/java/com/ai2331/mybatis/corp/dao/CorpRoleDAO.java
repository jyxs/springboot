package com.ai2331.mybatis.corp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.sys.entity.Role;

@Mapper
@DataSource(DataSourceKey.MASTER)
public interface CorpRoleDAO{
	
	@Select("SELECT * FROM b_corp_role WHERE CODE IN( SELECT role_code FROM b_corp_staff_role WHERE username=#{username} AND corp_code=#{corpCode})")
	List<Role> findRolesByUsername(@Param("username") String username, @Param("corpCode") String corpCode);
}