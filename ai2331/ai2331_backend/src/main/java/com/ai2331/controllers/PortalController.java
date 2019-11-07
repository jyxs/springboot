package com.ai2331.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
@Controller
public class PortalController {

	@GetMapping({"","/","/portal"})
	public String portal(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ("anonymousUser".equals(principal)) {
			return "/login";
		} else {
			User user = (User) principal;
			model.addAttribute("name", user.getUsername());
			return "/portal";
		}
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ("anonymousUser".equals(principal)) {
			return "/login";
		} else {
			User user = (User) principal;
			model.addAttribute("name", user.getUsername());
			return "/portal";
		}
	}
}
