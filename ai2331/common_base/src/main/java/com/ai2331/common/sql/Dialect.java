package com.ai2331.common.sql;

import com.ai2331.common.entity.ExceptionX;
import com.ai2331.common.entity.PageX;

public interface Dialect {
	/**
	 * 查询SQL转换成查询记录数.
	 * @param sql 原始SQL
	 * @throws Exception 
	 */
	String getCountSql(String sql) throws ExceptionX;
	/**
	 * 生成分页查询的SQL.
	 * @param sql 原始SQL
	 * @param pager 分页对象
	 */
	String getPageSql(String sql, PageX pager);
	/**
	 * 生成取前x条记录的SQL.
	 * @param sql 原始SQL
	 * @param count 前x条
	 */
	String getTopSql(String sql, int count);
	
	boolean isSupportPage();
	
	/**
	 * 获取查询参数
	 * @param sql
	 * @return
	 * @throws ExceptionX
	 */
	String[] getFields(String sql) throws ExceptionX;
}
