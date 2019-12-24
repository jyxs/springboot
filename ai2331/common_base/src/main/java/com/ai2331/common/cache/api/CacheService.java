package com.ai2331.common.cache.api;

/**
 * 缓存服务接口.
 */
public interface CacheService {
	public static final CacheService NO_CACHE = new CacheService() {
		@Override
		public int getDefaultTTL() {return 0;}
		@Override
		public void set(Object k, Object v) {}
		@Override
		public void set(Object k, Object v, int ttl) {}
		@Override
		public Object get(Object k) {return null;}
		@Override
		public void remove(Object k) {}
		@Override
		public boolean removeAll() {return false;}
		@Override
		public void init() {}
		
	};
	
	//缺省的对象过期时间，以秒计
	int getDefaultTTL();
	void set(Object k, Object v);
	void set(Object k, Object v, int ttl);
	Object get(Object k);
	void remove(Object k);
	boolean removeAll();
	void init();
}
