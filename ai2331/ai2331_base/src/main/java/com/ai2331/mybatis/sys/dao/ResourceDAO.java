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

	/**
	 * 根据角色codes获取资源列表
	 * @param roleCodes
	 * @return
	 */
	@Select("select r.* from sys_role_resource rr,sys_resource r where rr.resource_id=r.id and r.enabled=1 and rr.role_code in(#{0})")
	List<Resource> findByRoleCodes(Set<String> roleCodes);
	
	/**
	 * 获取所有资源列表
	 * @param enabled
	 * @return
	 */
	@Select("<script>"
			+ "select * from sys_resource sr where 1=1 "
			+ "<if test='#{0}!=null'> enabled=#{0} </if>"
			+ "</script>")
	List<Resource> find(Integer enabled);
}
