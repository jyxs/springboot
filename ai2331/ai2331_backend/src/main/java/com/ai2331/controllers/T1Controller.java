package com.ai2331.controllers;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai2331.common.entity.ResultX;
import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.service.T1Service;
import com.ai2331.sys.dao.RoleDAO;
import com.ai2331.sys.entity.Role;
import com.ai2331.test.entity.T1;

@RestController
@RequestMapping("/t1")
public class T1Controller {
	@Autowired
	private T1Service service;

	@Autowired
	private RoleDAO roleDAO;


	@GetMapping("/t/e")
	public Object testException() {
//		T1 t=null;
//		t.getCreateTime();
		// throw new Exception("自己抛出异常");
		int i = 1 / 0;
		return new ResultX();
	}

	@GetMapping("thymleaftest")
	public ModelAndView thymeleafTest() {
		return new ModelAndView("thymleaf/test");
	}

	@GetMapping("cachetest")
	public String cachetest() {
		service.cacheMethod();
		return "ok";
	}

	@GetMapping("findById/{id}")
	@RequiresPermissions("abc")
	public T1 findById(@PathVariable("id") Integer id) {
		return service.findById(id);
	}

	@GetMapping("findAdmin/{username}")
	public CorpStaff findByUsername(@PathVariable("username") String username) {
		return service.findAdminByUsername(username);
	}

	@GetMapping("findRole/{uid}")
	public List<Role> findRoleByUid(@PathVariable("uid") Integer uid) {
		return service.findRole(uid);
	}
}
