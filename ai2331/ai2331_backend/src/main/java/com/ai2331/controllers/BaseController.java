package com.ai2331.controllers;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ai2331.corp.entity.CorpStaff;

@CrossOrigin("*")
public class BaseController {

	protected CorpStaff currentUser() {
		Object obj = SecurityUtils.getSubject().getPrincipal();
		if (null == obj)
			return null;
		return (CorpStaff) obj;
	}
}
