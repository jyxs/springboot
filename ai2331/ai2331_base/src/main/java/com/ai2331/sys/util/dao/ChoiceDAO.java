package com.ai2331.sys.util.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ai2331.common.choice.ChoiceConfig;
import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;

import io.lettuce.core.dynamic.annotation.Param;

@Repository
@DataSource(DataSourceKey.MASTER)
public interface ChoiceDAO {
	
	@Select("<script>"
			+ "select * from sys_choice_config where 1=1"
			+ "<if test='#{codeLike}!=null'> AND locate(#{codeLike},choice_code)>0 </if>"
			+ "<if test='#{nameLike}!=null'> AND locate(#{nameLike},choice_name)>0 </if>"
			+ "</script>"
			)
	List<ChoiceConfig> listChoiceConfig(@Param("codeLike")String codeLike, @Param("nameLike")String nameLike);

	@Select("select * from sys_choice_config where choice_code=#{choiceCode}")
	ChoiceConfig getChoiceConfig(@Param("choiceCode")String choiceCode);
	
	@Update("update sys_choice_config set "
			+ "choice_name=#{c.choiceName},"
			+ "id_label=#{c.idLabel},"
			+ "name_label=#{c.nameLabel},"
			+ "extra_fields=#{c.extraFields},"
			+ "query_selector=#{c.querySelector},"
			+ "get_selector=#{c.getSelector} "
			+ "WHERE choice_code=#{c.choiceCode}")
	int updateChoiceConfig(@Param("c") ChoiceConfig config);
	
	@Insert("INSERT INTO sys_choice_config (choice_code,choice_name,id_label,name_label,extra_fields,query_selector,get_selector) VALUES"
			+ "(#{c.choiceCode},#{c.choiceName},#{c.idLabel},#{c.nameLabel},#{c.extraFields},#{c.querySelector},#{c.getSelector})")
	int insertChoiceConfig(@Param("c") ChoiceConfig config);
	
	@Delete("delete from sys_choice_config where choice_code=#{choiceCode}")
	int deleteChoiceConfig(@Param("choiceCode") String code);
}
