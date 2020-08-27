package com.ai2331.controllers;

import org.apache.shiro.SecurityUtils;

import com.ai2331.corp.entity.CorpStaff;

public class BaseController {

	protected CorpStaff currentUser() {
		Object obj = SecurityUtils.getSubject().getPrincipal();
		if (null == obj)
			return null;
		return (CorpStaff) obj;
	}
}
