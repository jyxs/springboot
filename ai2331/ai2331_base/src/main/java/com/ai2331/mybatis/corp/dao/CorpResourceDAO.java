package com.ai2331.mybatis.corp.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.sys.entity.Resource;

@Mapper
@DataSource(DataSourceKey.MASTER)
public interface CorpResourceDAO{
	
	/**
	 * 根据公司套装code集合获取资源列表
	 * @param suitCodes 套件code集合
	 * @return
	 */
	@Select("<script>"
			+ "SELECT * FROM sys_resource WHERE enabled=1 and id IN(SELECT resource_id FROM sys_role_resource WHERE role_code IN(SELECT role_code FROM sys_role WHERE role_code IN(SELECT role_code FROM sys_suit_role WHERE suit_code IN("
			+ " <foreach item='code' index='ind' collection='suitCodes' separator=','>"
			+ " #{code}"
			+ " </foreach>"
			+ "))))"
			+ "</script>")
	List<Resource> findBySuitCodes(@Param("suitCodes")Set<String> suitCodes);
	
	/**
	 * 根据员工username、corpCode获取所具有的资源列表
	 * @param username 用户名
	 * @param corpCode 公司code
	 * @return
	 */
	@Select("SELECT * FROM sys_resource WHERE id IN(SELECT resource_id FROM b_corp_role_resource WHERE role_code IN(SELECT role_code FROM b_corp_staff_role WHERE username=#{username} AND corp_code=#{corpCode}))")
	List<Resource> findByUsernameAndCorpCode(@Param("username") String username, @Param("corpCode") String corpCode);
	
	/**
	 * 根据角色code集合、corpCode获取所具有的资源列表
	 * 
	 * @param roleCodes 角色集合
	 * @param corpCode  公司code
	 * @return
	 */
	@Select("<script>"
			+"SELECT * FROM sys_resource WHERE id IN(SELECT resource_id FROM b_corp_role_resource WHERE role_code IN("
			+ " <foreach item='code' index='ind' collection='roleCodes' separator=','>"
			+ " #{code}"
			+ " </foreach>"
			+ ") AND corp_code=#{corpCode})"
			+ "</script>")
	List<Resource> findByRoleCodesAndCorpCode(@Param("roleCodes") List<String> roleCodes, @Param("corpCode") String corpCode);

}
