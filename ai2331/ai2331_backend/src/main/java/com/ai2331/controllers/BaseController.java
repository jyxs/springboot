package com.ai2331.controllers;

import org.apache.shiro.SecurityUtils;

import com.ai2331.sys.entity.AdminUser;

public class BaseController {

	protected AdminUser currentUser() {
		Object obj = SecurityUtils.getSubject().getPrincipal();
		if (null == obj)
			return null;
		return (AdminUser) obj;
	}
}
