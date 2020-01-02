package com.ai2331.common.cache.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("rawtypes")
public class SimpleCacheService<V extends Comparable> extends AbstractCacheService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5900881126537160247L;

	static Logger log = LoggerFactory.getLogger(SimpleCacheService.class);

	private Map<Object, Object> cache = new ConcurrentHashMap<Object, Object>();
	private DelayQueue<DelayItem<V>> queue = new DelayQueue<DelayItem<V>>();
	private Thread daemonThread;

	@Override
	public Object get(Object k) {
		return cache.get(k);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(Object k) {
		if (cache.remove(k) != null) {
			queue.remove(new DelayItem((Comparable) k));
		}
	}

	@Override
	public boolean removeAll() {
		cache.clear();
		queue.clear();

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void set(Object k, Object v, int ttl) {
		Object oldObj = cache.put(k, v);

		DelayItem<V> item = new DelayItem<V>((V) k, ttl);
		if (oldObj != null) {
			queue.remove(item);
		}

		queue.put(item);
	}

	@Override
	public void init() {
		if (daemonThread == null) {
			Runnable daemonTask = new Runnable() {
				public void run() {
					checkExpire();
				}
			};

			daemonThread = new Thread(daemonTask);
			daemonThread.setDaemon(true);
			daemonThread.setName("SimpleCacheServiceDaemon");
			daemonThread.start();

			log.info(daemonThread.getName() + " started.");
		}
	}

	@Override
	public void exit() {
		removeAll();

		if (daemonThread != null) {
			log.info(daemonThread.getName() + " stopping...");
			daemonThread.interrupt();
			daemonThread = null;
		}
	}

	private void checkExpire() {
		for (;;) {
			try {
				// DelayItem<String> delayItem = queue.take(); // memory leak
				DelayItem<V> delayItem = queue.poll();
				if (delayItem != null) {
					// 超时对象处理
					Object k = delayItem.getItem();
					cache.remove(k);
				} else {
					Thread.sleep(10000);
				}
			} catch (InterruptedException e) {
				log.warn(Thread.currentThread().getName() + " stopped.");
				break;
			}
		}
	}
}

@SuppressWarnings("rawtypes")
class DelayItem<T extends Comparable> implements Delayed {
	private static final long SEC_ORIGIN = System.currentTimeMillis() / 1000;

	final static long now() {
		return System.currentTimeMillis() / 1000 - SEC_ORIGIN;
	}

	private final long time; // seconds
	private final T item;

	public DelayItem(T t, long timeout) {
		this.time = now() + timeout;
		this.item = t;
	}

	public DelayItem(T k) {
		this.time = 0;
		this.item = k;
	}

	public T getItem() {
		return this.item;
	}

	public long getDelay(TimeUnit unit) {
		return time - now();
	}

	public int compareTo(Delayed other) {
		if (other == this) // compare zero ONLY if same object
			return 0;

		if (other instanceof DelayItem) {
			DelayItem x = (DelayItem) other;
			if (this.item.equals(x.item)) {
				return 0;
			} else {
				long diff = time - x.time;
				if (diff < 0)
					return -1;
				else if (diff > 0)
					return 1;
				else
					return 0;
			}
		}

		long d = (getDelay(TimeUnit.SECONDS) - other.getDelay(TimeUnit.SECONDS));
		return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
	}
}