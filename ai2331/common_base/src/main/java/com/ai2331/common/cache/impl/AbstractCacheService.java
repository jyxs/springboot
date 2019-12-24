package com.ai2331.common.cache.impl;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import com.ai2331.common.cache.api.CacheService;

abstract public class AbstractCacheService implements CacheService,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 556451465931790080L;
	private int defaultTTL;

	@Override
	public int getDefaultTTL() {
		return defaultTTL;
	}

	@Override
	public void set(Object key, Object v) {
		set(key, v, defaultTTL);
	}

	@Override
	public boolean removeAll() {
		return false;
	}

	// setter
	public void setDefaultTTL(int defaultTTL) {
		this.defaultTTL = defaultTTL;
	}

	@PostConstruct
	abstract public void init();

	abstract public void exit();
}
