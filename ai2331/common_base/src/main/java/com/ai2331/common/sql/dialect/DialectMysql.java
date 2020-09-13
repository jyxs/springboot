package com.ai2331.common.sql.dialect;

import com.ai2331.common.entity.PageX;

public class DialectMysql extends AbstractDialect {

	@Override
	public String getPageSql(String sql, PageX pager) {
	    int from = pager.getPageSize() * (pager.getPageNumber() - 1);
	    if (from < 0) from = 0;
	    
	    return sql += " limit " + from +"," + pager.getPageSize();
	}

	@Override
	public String getTopSql(String sql, int count) {
		return sql + " limit " + count;
	}

}
