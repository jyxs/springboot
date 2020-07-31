package com.ai2331.sys.controllers;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai2331.common.entity.PageX;
import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;
import com.ai2331.controllers.BaseController;
import com.ai2331.sys.entity.AdminUser;
import com.ai2331.sys.service.AdminUserService;

@RequestMapping("sys/admin")
@Controller
public class AdminUserController extends BaseController {

	@Autowired
	private AdminUserService service;

	@GetMapping("query")
	public String query() {
		return "sys/admin/query";
	}

	@PostMapping("grid")
	@ResponseBody
	public ResultX grid(AdminUser user, PageX pager) {
		if(StringUtils.isNotEmpty(user.getUsername())) {
			user.setUsername(user.getUsername()+"%");			
		}
		return new ResultX(ResultXCode.SUCCESS, "ok", service.queryAdminUser(user, pager));
	}

	@GetMapping("save")
	public String save() {
		return "sys/admin/save";
	}

	@PostMapping("save")
	@ResponseBody
	public ResultX saveSubmit(AdminUser user) {
		if (StringUtils.isBlank(user.getPassword())) {
			user.setPassword("123456");
		}
		user.setPassword(new SimpleHash("md5", user.getPassword(), ByteSource.Util.bytes(user.getUsername()), 2).toHex());
		return new ResultX(ResultXCode.SUCCESS, "ok", service.saveAdminUser(user));
	}
}
