package com.ai2331.sys.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	@Select("<script>"
			+ "select r.* from sys_role_resource rr,sys_resource r where rr.resource_id=r.id and r.enabled=1 and rr.role_code in("
			+ "<foreach item='code' collection='roleCodes' index='ind' separator=','>"
			+ "#{code}"
			+ "</foreach>"
			+ ")"
			+ "</script>")
	List<Resource> findByRoleCodes(Set<String> roleCodes);
	
	@Select("select * from sys_resource where id=#{id}")
	Resource getOne(@Param("id") Integer id);
	/**
	 * 获取所有资源列表
	 * @param enabled
	 * @return
	 */
	@Select("<script>"
			+ "select * from sys_resource sr where 1=1 "
			+ "<if test='#{pid}!=null'> and pid=#{pid} </if>"
			+ "<if test='#{enabled}!=null'> and enabled=#{enabled} </if>"
			+ "</script>")
	List<Resource> find(@Param("pid") Integer pid, @Param("enabled") Integer enabled);
	
	@Insert("insert into sys_resource(pid,priv_code,icon,name,resource_type,sort_order,url,tab_name,create_time,remark,enabled) values(#{pid},#{privCode},#{icon},#{name},#{resourceType},#{sortOrder},#{url},#{tabName},#{createTime},#{remark},#{enabled})")
	int insert(@Param("r")Resource resource);
	
	@Update("update sys_resource set icon=#{icon},name=#{name},resource_type=#{resourceType},sort_order=#{sortOrder},url=#{url},tab_name=#{tabName},remark=#{remark},enabled=#{enabled} where id=#{id}")
	int update(@Param("r")Resource resource);
	
	@Update("update sys_resource set enabled=#{enabled} where id=#{id}")
	int updateEnabled(@Param("id") Integer id, @Param("enabled") Integer enabled);
	
	@Update("update sys_resource set sort_order=#{sortOrder} where id=#{id}")
	int updateSort(@Param("id") Integer id, @Param("sortOrder") Integer sortOrder);
		
	@Select("<script>"
			+ "select max(sort_order) from sys_resource where 1=1"
			+ "<if test='#{pid}!=null'>"
			+ " and pid=#{pid}"
			+ "</if>"
			+ "</script>")
	int getMaxSort(@Param("pid") Integer pid);
}
