package com.ai2331.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceHolder {
	private static Logger logger = LoggerFactory.getLogger(DataSourceHolder.class);

	public enum DataSourceKey {
		MASTER("masterds"), SLAVER("slaverds");
		private String dskey;

		public String getDskey() {
			return dskey;
		}

		public void setDskey(String dskey) {
			this.dskey = dskey;
		}

		DataSourceKey(String dskey) {
			this.dskey = dskey;
		}
	}

	// 存储多数据源key
	private static final ThreadLocal<String> dsKeys = new ThreadLocal<String>();

	public static void setDataSourceKey(DataSourceKey dsk) {
		if (dsk == null) {
			dsKeys.set(DataSourceKey.MASTER.getDskey());
		} else {
			dsKeys.set(dsk.getDskey());
		}
		logger.debug(">>>>>>>>>>>>>>>>>>switch data source target is:{}", dsKeys.get());
	}

	public static String getDataSourceKey() {
		String dsk = dsKeys.get();
		logger.debug(">>>>>>>>>>>>>>>>>>current data source target is:{}", dsk);
		return dsk;
	}

	public static void clearDataSourceKey() {
		dsKeys.remove();
	}
}
