package com.ai2331.common.sql.dialect;

import com.ai2331.common.entity.PageX;

/**
 * XXX 调用存储过程进行分页.
 * SpqPagination(sql, pageNo, pageSize)
 */
public class DialectSybase extends AbstractDialect {
	public static final String SP_NAME = "SpqPagination";

	@Override
	public String getPageSql(String sql, PageX pager) {
		return String.format("{ call %s(\"%s\",%d,%d) }", SP_NAME, sql, pager.getPageNumber(), pager.getPageSize());
	}

	@Override
	public String getTopSql(String sql, int count) {
	    int pos = sql.toUpperCase().indexOf("SELECT");
	    return String.format("%s top %d %s", new Object[] { sql.substring(0, pos + 6), count, sql.substring(pos + 6) });
	}

	@Override
	public boolean isSupportPage() {
		return false;
	}

}
