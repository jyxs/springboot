package com.ai2331.mybatis.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.sys.entity.Role;

@Mapper
@DataSource(DataSourceKey.MASTER)
public interface RoleMapper{

	@Select("select r.* from t_admin_role ar,t_role r where ar.role_id=r.id and ar.admin_id=#{uid}")
	List<Role> findRolesByUserId(Integer uid);
}