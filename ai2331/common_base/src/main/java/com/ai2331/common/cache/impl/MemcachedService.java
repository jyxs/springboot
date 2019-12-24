package com.ai2331.common.cache.impl;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

@Service("memcache")
public class MemcachedService<V> extends AbstractCacheService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3122951817664675217L;

	static Logger log = LoggerFactory.getLogger(MemcachedService.class);

	@Autowired
	private MemcachedClient mcc;
	private String addresses = "localhost:11211";
	private int timeout = 3; // 3 seconds

	@Override
	public Object get(Object key) {
		if (mcc == null)
			return null;

		Object obj = null;
		if (null == key) {
			return null;
		}
		Future<Object> f = mcc.asyncGet(key.toString());
		try {
			obj = f.get(timeout, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			log.warn("timeout while get from memcache:" + key);
			f.cancel(false);
		} catch (ExecutionException e) {
			log.error("Error while get from memcache:" + e.getMessage());
			f.cancel(false);
		} catch (InterruptedException e) {
			log.error("Error while get from memcache:" + e.getMessage());
		}
		return obj;
	}

	@Override
	public void remove(Object key) {
		if (mcc != null && null != key) {
			mcc.delete(key.toString());
		}
	}

	@Override
	public void set(Object key, Object value, int ttl) {
		if (mcc != null && null != key) {
			mcc.set(key.toString(), ttl, value);
		}
	}

	@Override
	public void init() {
		System.out.println("memecached init");
	}

	@Bean
	private MemcachedClient createClient() {
		try {
			log.debug("memcache connected to " + addresses + ", timeout(s) = " + timeout);
			return new MemcachedClient(AddrUtil.getAddresses(addresses));
		} catch (IOException e) {
			log.error("Fail to connect to " + addresses + ":" + e.getMessage());
		}
		return null;
	}

	@Override
	public void exit() {
		if (mcc != null) {
			mcc.shutdown();
			mcc = null;
		}
	}

	// getter & setter
	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
