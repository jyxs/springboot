package com.ai2331.controllers;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ai2331.common.entity.PageX;
import com.ai2331.service.T1Service;

@Controller
@RequestMapping("t2")
public class T2Controller {
	@Autowired
	private T1Service service;
	
	@RequiresPermissions(value ="t1-list")
	@GetMapping("list")
	public ModelAndView list(PageX pager) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", service.list(pager));
		mv.setViewName("/t2/list");
		return mv;
	}
}
