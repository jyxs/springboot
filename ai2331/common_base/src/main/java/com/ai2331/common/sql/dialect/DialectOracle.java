package com.ai2331.common.sql.dialect;

import com.ai2331.common.entity.PageX;

public class DialectOracle extends AbstractDialect {

	@Override
	public String getPageSql(String sql, PageX pager) {
		int startrow = (pager.getPageNumber()-1) * pager.getPageSize() + 1;
		int endrow = pager.getPageNumber() * pager.getPageSize();
		
		return String.format("select * from (select row_.*,rownum rownum_ from ( %s ) row_  where rownum <= %d) where rownum_ >= %d", 
				new Object[] { sql, endrow, startrow });
	}

	@Override
	public String getTopSql(String sql, int count) {
		return String.format("select row_.* from (%s) row_ where rownum <=%d", new Object[] { sql, count });
	}

}
