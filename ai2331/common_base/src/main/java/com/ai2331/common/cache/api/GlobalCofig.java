package com.ai2331.common.cache.api;

import org.springframework.beans.factory.annotation.Value;

public class GlobalCofig {
	@Value("${ai2331.cache.ttl}")
	private int cacheTTL = 20;
	@Value("${ai2331.cache.server.url}")
	private String cacheServerUrl;
	@Value("${ai2331.cache.ehcache.maxHeapSize}")
	private String ehcacheMaxHeapSize;
	@Value("${ai2331.cache.ehcache.maxOffHeapSize}")
	private String ehcacheMaxOffHeapSize;

	public int getCacheTTL() {
		return cacheTTL;
	}

	public void setCacheTTL(int cacheTTL) {
		this.cacheTTL = cacheTTL;
	}

	public String getCacheServerUrl() {
		return cacheServerUrl;
	}

	public void setCacheServerUrl(String cacheServerUrl) {
		this.cacheServerUrl = cacheServerUrl;
	}

	public String getEhcacheMaxHeapSize() {
		return ehcacheMaxHeapSize;
	}

	public void setEhcacheMaxHeapSize(String ehcacheMaxHeapSize) {
		this.ehcacheMaxHeapSize = ehcacheMaxHeapSize;
	}

	public String getEhcacheMaxOffHeapSize() {
		return ehcacheMaxOffHeapSize;
	}

	public void setEhcacheMaxOffHeapSize(String ehcacheMaxOffHeapSize) {
		this.ehcacheMaxOffHeapSize = ehcacheMaxOffHeapSize;
	}

}
