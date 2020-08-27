package com.ai2331.mybatis.sys.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.sys.entity.Role;

@Mapper
@DataSource(DataSourceKey.MASTER)
public interface RoleDAO{

	@Select("<script>"
			+ "select * from sys_role where 1=1"
			+ "<if test='#{0}!=null'> and enabled =#{0}</if>"
			+ " order by role_code"
			+ "</script>")
	List<Role> findRoles(Integer enabled);
	
	@Select("select sr.* from sys_suit_role ssr,sys_role sr where ssr.role_code=sr.role_code and ssr.suit_code in(#{0})")
	List<Role> findRolesBySuitCodes(Set<String> suitCodes);
}