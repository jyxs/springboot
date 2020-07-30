package com.ai2331.controllers;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ai2331.common.entity.PageX;

@Controller
@RequestMapping("t2")
public class T2Controller {
	
	@RequiresPermissions(value ="t1-list")
	@GetMapping("list")
	public ModelAndView list(PageX pager) {
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	@GetMapping("thymleaftest")
	public String thymeleafTest() {
		return "thymleaf/test";
	}
}
