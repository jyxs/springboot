package com.ai2331.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai2331.common.entity.PageX;
import com.ai2331.common.entity.ResultX;
import com.ai2331.service.T1Service;
import com.ai2331.test.entity.T1;

@RestController
@RequestMapping("/t1")
public class T1Controller {
	private Logger logger=LoggerFactory.getLogger(T1Controller.class);
	@Autowired
	private T1Service service;

	@PostMapping("/list")
	public Page<T1> list(@RequestBody PageX pager) {
		logger.debug(">>>>debug,list");
		logger.warn(">>>>>warn,list");
		logger.info(">>>>>info,list");
		logger.error(System.getProperty("user.home") + " ===== error");
		
		return service.list(pager);
	}

	@PostMapping("/save")
	public T1 save(@RequestBody T1 t) {
		return service.insert(t);
	}

	@GetMapping("/del/{id}")
	public String delete(@PathVariable("id") Integer id) {
		service.delete(id);
		return "ok";
	}
	
	@GetMapping("/t/e")
	public Object testException() {
//		T1 t=null;
//		t.getCreateTime();
		//throw new Exception("自己抛出异常");
		int i = 1/0;
		return new ResultX();
	}
}
