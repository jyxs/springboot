package com.ai2331.mybatis.sys.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.sys.entity.Resource;

@Mapper
@DataSource(DataSourceKey.MASTER)
public interface ResourceDAO{

	@Select("select r.* from t_role_resource rr,t_resource r where rr.resource_id=r.id and r.enabled=1 and rr.role_id in(?1)")
	List<Resource> findByRoleIds(Set<Integer> roleIds);
}
