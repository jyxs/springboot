package com.ai2331.mybatis.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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

	@Select("<script>"
			+ "select * from t_admin_user where 1=1"
			+ " <if test='user.username!=null'> and username like #{user.username}</if> "
			+ " <if test='user.mobilephone!=null'> and mobilephone = #{user.mobilephone}</if> "
			+ " order by #{pager.filed} #{pager.order} limit #{pager.offSite},#{pager.pageSize}"
			+ "</script>")
	List<AdminUser> findAll(@Param("user")AdminUser user, @Param("pager")PageX pager);
	
	
	@Insert("INSERT INTO t_admin_user (username, fullname, password, title, email, mobilephone, telephone, im, create_time, last_time, last_ip, enabled, is_super) "
			+ "VALUES (#{user.username}, #{user.fullname}, #{user.password}, #{user.title}, #{user.email}, #{user.mobilephone}, #{user.telephone}, #{user.im}, #{user.createTime}, #{user.lastTime}, #{user.lastIp}, #{user.enabled}, #{user.isSuper})")
	int insert(AdminUser user);

}
