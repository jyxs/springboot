package com.ai2331.corp.controllers;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai2331.common.entity.PageX;
import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;
import com.ai2331.controllers.BaseController;
import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.sys.service.CorpStaffService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("corp/staff")
@Controller
public class CorpStaffController extends BaseController {

	@Autowired
	private CorpStaffService service;

	@GetMapping("query")
	public String query() {
		return "sys/admin/query";
	}

	@ApiOperation("用户列表")
	@PostMapping("grid")
	@ResponseBody
	public ResultX grid(CorpStaff user, PageX pager) {
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
	public ResultX saveSubmit(CorpStaff user) {
		if (StringUtils.isBlank(user.getPassword())) {
			user.setPassword("123456");
		}
		user.setPassword(new SimpleHash("md5", user.getPassword(), ByteSource.Util.bytes(user.getUsername()), 2).toHex());
		return new ResultX(ResultXCode.SUCCESS, "ok", service.saveAdminUser(user));
	}
}
