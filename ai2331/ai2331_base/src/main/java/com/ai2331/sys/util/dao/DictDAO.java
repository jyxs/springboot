package com.ai2331.sys.util.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.ai2331.common.dict.entity.Dict;
import com.ai2331.common.dict.entity.DictItem;
import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;

import io.lettuce.core.dynamic.annotation.Param;

@DataSource(DataSourceKey.MASTER)
public interface DictDAO{

	@Select("<script>"
			+ "select * from sys_dict where 1=1"
			+ "<if test='#{status}!=null'> and status=#{status}</if>"
			+ "</script>")
	
	List<Dict> listEnableDicts(@Param("status") Integer status);
	
	@Select("select * from sys_dict_item order by sort_order")
	List<DictItem> listDictItems();
	
	@Select("select * from sys_dict_item where group_code=#{groupCode} order by sort_order")
	List<DictItem> listDictItemsByGroupCode(@Param("groupCode") String groupCode);
	
}
