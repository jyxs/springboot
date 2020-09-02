package com.ai2331.corp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ai2331.common.entity.PageX;
import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;

@Repository
@DataSource(DataSourceKey.MASTER)
public interface CorpStaffDAO {

	CorpStaff findOneByUsername(String userName);

	@Select("<script>"
			+ "select * from b_corp_staff where 1=1"
			+ " <if test='staff.username!=null'> and username like #{staff.username}</if> "
			+ " <if test='staff.mobilephone!=null'> and mobilephone = #{staff.mobilephone}</if> "
			+ " order by #{pager.filed} #{pager.order} limit #{pager.offSite},#{pager.pageSize}"
			+ "</script>")
	List<CorpStaff> findAll(@Param("staff") CorpStaff user, @Param("pager") PageX pager);
	
	
	@Insert("INSERT INTO b_corp_staff (username, fullname, password,corp_code, title, email, mobilephone, telephone, im, create_time, last_time, last_ip, enabled, is_super) "
			+ "VALUES (#{staff.username}, #{staff.fullname}, #{staff.password}, #{staff.corpCode}, #{staff.title}, #{staff.email}, #{staff.mobilephone}, #{staff.telephone}, #{staff.im}, #{staff.createTime}, #{staff.lastTime}, #{staff.lastIp}, #{staff.enabled}, #{staff.isSuper})")
	int insert(@Param("staff") CorpStaff staff);

}
