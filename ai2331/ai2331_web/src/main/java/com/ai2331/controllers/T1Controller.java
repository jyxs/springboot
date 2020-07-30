package com.ai2331.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai2331.common.entity.ResultX;
import com.ai2331.service.T1Service;

@RestController
@RequestMapping("/t1")
public class T1Controller {
	private Logger logger=LoggerFactory.getLogger(T1Controller.class);
	@Autowired
	private T1Service service;

	
	@GetMapping("/t/e")
	public Object testException() {
//		T1 t=null;
//		t.getCreateTime();
		//throw new Exception("自己抛出异常");
		int i = 1/0;
		return new ResultX();
	}
}
