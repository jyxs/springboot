package com.ai2331.mybatis.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ai2331.common.entity.PageX;
import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.sys.entity.AdminUser;

@Repository
@DataSource(DataSourceKey.MASTER)
public interface AdminUserDAO {

	AdminUser findOneByUsername(String userName);

	@Select("select * from t_admin_user where username like #{user.username} and mobilephone = #{user.mobilephone} order by #{pager.filed} #{pager.order} limit #{pager.offSite},#{pager.pageSize}")
	List<AdminUser> findAll(AdminUser user, PageX pager);
	
	
	@Insert("INSERT INTO t_admin_user (username, fullname, password, title, email, mobilephone, telephone, im, create_time, last_time, last_ip, enabled, is_super) "
			+ "VALUES (#{user.username}, #{user.fullname}, #{user.password}, #{user.title}, #{user.email}, #{user.mobilephone}, #{user.telephone}, #{user.im}, #{user.createTime}, #{user.lastTime}, #{user.lastIp}, #{user.enabled}, #{user.isSuper})")
	int insert(AdminUser user);

}
