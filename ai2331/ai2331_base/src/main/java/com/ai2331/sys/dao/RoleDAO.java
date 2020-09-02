package com.ai2331.sys.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.sys.entity.Role;
import com.ai2331.sys.entity.dto.RoleX;

@Mapper
@DataSource(DataSourceKey.MASTER)
public interface RoleDAO{

	@Select("<script>"
			+ "select * from sys_role where 1=1"
			+ "<if test='#{roleCode}!=null'> and role_code =#{roleCode}</if>"
			+ "<if test='#{name}!=null'> and locate(#{name},name)>0</if>"
			+ "<if test='#{enabled}!=null'> and enabled =#{enabled}</if>"
			+ " order by role_code"
			+ "</script>")
	List<Role> findRoles(@Param("roleCode") String roleCode, @Param("name") String name, @Param("enabled") Integer enabled);
	
	@Select("select * from sys_role where role_code=#{roleCode}")
	RoleX getOne(@Param("roleCode") String roleCode);
	
	@Select("select sr.* from sys_suit_role ssr,sys_role sr where ssr.role_code=sr.role_code and ssr.suit_code in(#{0})")
	List<Role> findRolesBySuitCodes(Set<String> suitCodes);
	
	@Insert("INSERT INTO sys_role (pid,role_code,name,create_time,remark,enabled) VALUES(#{pid},#{roleCode},#{name},#{createTime},#{remark},#{enabled})")
	int insert(@Param("role")Role role);
	
	@Update("UPDATE sys_role SET enabled=#{enabled} where id=#{id}")
	int updateEnabled(@Param("id") Integer id, @Param("enabled") Integer enabled);
	
	@Update("UPDATE sys_role SET pid=#{pid},name=#{name},remark=#{remark},enabled=#{enabled} WHERE id=#{id}")
	int update(@Param("role") Role role);
	
	@Delete("DELETE FROM sys_role WHERE role_code=#{roleCode}")
	int delete(@Param("roleCode") String roleCode);
	
	/**
	 * sys_role_resource
	 */
	
	@Insert("<script>"
			+ "INSERT INTO sys_role_resource(role_code,resource_id) VALUES"
			+ "<foreach item='r' collection='resourceIds' open='(' close=')' separator=','>"
			+ "#{roleCode},#{r}"
			+ "</foreach>"
			+ "</script>"
			+ "")
	int insertResource(@Param("roleCode") String roleCode,@Param("resourceIds") Set<Integer> resourceIds);
	
	@Delete("DELETE FROM sys_role_resource WHERE role_code=#{roleCode}")
	int deleteResourceByRoleCode(@Param("roleCode") String roleCode);
	
	@Delete("DELETE FROM sys_role_resource WHERE resource_id=#{resourceIds}")
	int deleteResourceByResourceId(@Param("resourceIds") Integer resourceIds);
}