package com.ai2331.common.sql.dialect;

import com.ai2331.common.entity.ExceptionX;
import com.ai2331.common.sql.Dialect;

public abstract class AbstractDialect implements Dialect {

	@Override
	public String getCountSql(String sql) throws ExceptionX {
		String SQL = sql.toUpperCase(); // for position location
		int pos = SQL.lastIndexOf(" ORDER BY");
		if (pos > 0) {
			sql = sql.substring(0, pos);
		}

		int pselect = SQL.indexOf("SELECT ");
		int pfrom = SQL.indexOf(" FROM ");
		if (pselect >= 0 && pfrom > pselect) {
			return sql.substring(0, pselect + 7) + "count(0)" + sql.substring(pfrom);
		} else {
			throw new ExceptionX("Invalid sql: " + sql);
		}
	}

	@Override
	public String[] getFields(String sql) throws ExceptionX {
		String SQL = sql.toUpperCase(); // for position location
		int pos = SQL.lastIndexOf(" ORDER BY");
		if (pos > 0) {
			sql = sql.substring(0, pos);
		}

		int pselect = SQL.indexOf("SELECT ");
		int pfrom = SQL.indexOf(" FROM ");
		if (pselect >= 0 && pfrom > pselect) {
			String fields = sql.substring(pselect + 7, pfrom);
			if (fields.indexOf("*") != -1) {
				return null;
			}
			return sql.substring(pselect + 7, pfrom).split(",");
		} else {
			throw new ExceptionX("Invalid sql: " + sql);
		}
	}

	@Override
	public boolean isSupportPage() {
		return true;
	}

}
