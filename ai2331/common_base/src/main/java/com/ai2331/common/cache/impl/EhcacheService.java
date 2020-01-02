package com.ai2331.common.cache.impl;

import java.time.Duration;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.impl.serialization.PlainJavaSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EhcacheService extends AbstractCacheService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2819804261922784866L;

	static Logger log = LoggerFactory.getLogger(EhcacheService.class);

	private int maxHeapSize = 20;
	private int maxOffHeapSize = 30;
	private String cacheName = "EHCACHE";
	private CacheConfiguration<String, String> cacheConfig;
	/* private String diskPath = System.getProperty("java.io.tmpdir"); */
	private CacheManager manager;
	private Cache<String, String> cache;

	@Override
	public Object get(Object k) {
		if (cache == null || null == k)
			return null;
		Object v = cache.get(k.toString());
		if (v != null) {
			return v;
		}
		return null;
	}

	@Override
	public void remove(Object k) {
		if (cache != null && null != k)
			cache.remove(k.toString());
	}

	@Override
	public boolean removeAll() {
		if (cache != null) {
			cache.clear();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void set(Object k, Object v, int ttl) {
		if (cache == null || null == k || null == v)
			return;
		cache.put(k.toString(), v.toString());
	}

	private CacheConfiguration<String, String> createCacheConfig() {
		ResourcePoolsBuilder pools = ResourcePoolsBuilder.newResourcePoolsBuilder().heap(maxHeapSize, MemoryUnit.MB).offheap(maxOffHeapSize, MemoryUnit.MB).disk(500L, MemoryUnit.MB, false);
		CacheConfigurationBuilder<String, String> config = CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, pools);
		config.withValueSerializer(new PlainJavaSerializer<String>(this.getClass().getClassLoader()));
		config.withKeySerializer(new PlainJavaSerializer<String>(this.getClass().getClassLoader()));
		config.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofMinutes(10)));
		config.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(this.getDefaultTTL())));
		return config.build();
	}

	private CacheManager createManager() {
		return CacheManagerBuilder.newCacheManagerBuilder().with(CacheManagerBuilder.persistence("./ehcache")).build(true);
	}

	@Override
	public void init() {
		cacheConfig = createCacheConfig();
		manager = createManager();
		cache = manager.createCache(cacheName, cacheConfig);
	}

	@Override
	public void exit() {
		if (manager != null) {
			try {
				 manager.close();
			} catch (Exception e) {
				log.error("stop cache " + getCacheName() + " error:", e);
			}
		}
	}

	public int getMaxHeapSize() {
		return maxHeapSize;
	}

	public void setMaxHeapSize(int maxHeapSize) {
		this.maxHeapSize = maxHeapSize;
	}

	public int getMaxOffHeapSize() {
		return maxOffHeapSize;
	}

	public void setMaxOffHeapSize(int maxOffHeapSize) {
		this.maxOffHeapSize = maxOffHeapSize;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
}
