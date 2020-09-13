package com.ai2331.common.sql.dialect;

import com.ai2331.common.entity.PageX;

public class DialectHsql extends AbstractDialect {

	@Override
	public String getPageSql(String sql, PageX pager) {
	    int from = pager.getPageSize() * (pager.getPageNumber() - 1);
	    if (from < 0) from = 0;

	    int pos = sql.toUpperCase().indexOf("SELECT");
	    String newSql = String.format("%s limit %d %d %s", new Object[] { sql.substring(0, pos + 6), from, pager.getPageSize(), sql.substring(pos + 6) });
	    return newSql;
	}

	@Override
	public String getTopSql(String sql, int count) {
	    int pos = sql.toUpperCase().indexOf("SELECT");
	    return sql.substring(0, pos + 6) + " top " + count + " " + sql.substring(pos + 6);
	}

}
