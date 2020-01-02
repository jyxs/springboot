package com.ai2331.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai2331.common.cache.impl.RedisCacheService;

@RestController
@RequestMapping("redis")
public class RedisController {

	@Autowired(required = false)
	private RedisCacheService rs;
	@GetMapping("test")
	public Object test(@RequestParam("k") String k,@RequestParam("v") String v) {
//		for (int i=0;i<100;i++) {
//			rs.set(i,i);
//		}
//		rs.set(k, v);
//		Object object = rs.get(k+"");
//		System.out.println(k+"--"+object);
//		rs.remove(k);
//		rs.remove("1","2","3","4","5","6","7","8","9","10");
//		
//		System.out.println("has key ="+k+","+rs.hasKey(k));
//		System.out.println("has key =11,"+rs.hasKey("11"));
//		
//		rs.incr("11", 10);
//		Map<String,Object> map=new HashMap<>();
//		map.put("a", "A");
//		map.put("b", "B");
//		map.put("c", "C");
//		map.put("d", "D");
//		map.put("e", "E");
//		map.put("f", "F");
//		map.put("g", "G");
//		
//		rs.hmset("letterMap", map);
//		rs.hmset("letterMap1", map);
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
//		Object hget = rs.hget("letterMap", "a");
//		System.out.println(hget);
//		Map<Object, Object> rmap = rs.hmget("letterMap");
//		for(Object key :rmap.keySet()) {
//			System.out.println("key="+key+",value="+rmap.get(key));
//		}
//		rs.hdel("letterMap1", "g");
//		System.out.println(rs.hHasKey("letterMap", "a"));
//		System.out.println(rs.hHasKey("letterMap1", "g"));
		
		
//		for (int i = 0; i <100; i++) {
//			rs.sSet("sets", i);
//		}
//		for (int i = 0; i <100; i++) {
//			rs.sSet("sets", i);
//		}
//		System.out.println("set size"+rs.sGetSetSize("sets"));
//		
//		Set<Object> sets = rs.sGet("sets");
//		StringBuffer sb =new StringBuffer();
//		sets.stream().forEach(item->sb.append(item+","));
//		System.out.println(sb);
//		
//		rs.setRemove("sets", 1,2,3,4,6);
		
		for (int i = 0; i <100; i++) {
			rs.lSet("lists", i);
		}
		for (int i = 0; i < 100; i++) {
			rs.lSet("lists", i);
		}
		List<Object> lists = rs.lGet("lists", 99, 105);
		lists.stream().forEach(item->System.out.println(item));
		System.out.println(rs.lGetListSize("lists"));
		Object lindex = rs.lGetIndex("lists", 101);
		System.out.println(lindex);
		rs.lUpdateIndex("lists", 102, 100000);
		long lRemove = rs.lRemove("lists", 10, 2);
		System.out.println(lRemove);
		
		return "ok";
		
	}
}
