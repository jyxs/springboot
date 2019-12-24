package com.ai2331.common.cache.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

/**
 * 没有失效机制.
 *
 */
@Service("mapcache")
public class MapCacheService extends AbstractCacheService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1106141067859736342L;
	private Map<Object, Object> cache = new ConcurrentHashMap<Object, Object>();

	@Override
	public void set(Object key, Object v, int ttl) {
		cache.put(key, v);
	}

	@Override
	public Object get(Object key) {
		return cache.get(key);
	}

	@Override
	public void remove(Object key) {
		cache.remove(key);
	}

	@Override
	public void init() {
		// NOOP
	}

	@Override
	public void exit() {
		removeAll();
	}

	@Override
	public boolean removeAll() {
		cache.clear();
		return true;
	}

}
