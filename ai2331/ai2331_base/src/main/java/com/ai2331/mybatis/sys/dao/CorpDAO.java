package com.ai2331.mybatis.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;
import com.ai2331.sys.entity.Corp;

@Repository
@DataSource(DataSourceKey.MASTER)
public interface CorpDAO {

	@Select("<script>"
			+ "select * from sys_corp where 1=1"
			+ "<if test='#{name}!=null'> and locate(#{name},name)>0 </if>"
			+ "<if test='#{code}!=null'> and locate(#{code},code)>0 </if>"
			+ "<if test='#{enabled}!=null'> and enabled = #{enabled} </if>"
			+ "</script>")
	List<Corp> findCorps(@Param("name") String name, @Param("code") String code, @Param("enabled") Integer enabled);
	
	@Select("select * from sys_corp where code=#{0}")
	Corp findOne(String code);
}
