package com.ai2331.sys.util.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ai2331.ds.DataSource;
import com.ai2331.ds.DataSourceHolder.DataSourceKey;

@Repository
@DataSource(DataSourceKey.MASTER)
public interface BasicDAO {

	@Select("${sql}")
	Long getCount(String sql);

	@Select("${sql}")
	List<Map<String,Object>> getDatas(String sql);

	@Select("${sql")
	Object[] getData(String sql);
}
