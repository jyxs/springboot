package com.ai2331.controllers;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;
import com.ai2331.sys.entity.AdminUser;

@RequestMapping("/")
@Controller
public class PortalController extends BaseController {

	@GetMapping({ "", "/", "/portal" })
	public String portal(Model model) {
		AdminUser user = currentUser();
		model.addAttribute("user", user);
		return "/portal";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "/login";
	}

	@PostMapping("/login")
	@ResponseBody
	public ResultX loginSubmit(@RequestParam("uname") String username, @RequestParam("pwd") String password,
			@RequestParam(name = "rememberMe", defaultValue = "0") String rememberMe) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return new ResultX(ResultXCode.FAIL, "请填写用户名或密码");
		}
		Subject subject = SecurityUtils.getSubject();
		boolean remeber = rememberMe.contentEquals("1") ? true : false;
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, remeber);
		try {
			subject.login(token);
		} catch (UnknownAccountException uae) {
			return new ResultX(ResultXCode.FAIL, "用户名和密码不匹配");
		} catch (IncorrectCredentialsException ice) {
			return new ResultX(ResultXCode.FAIL, "用户名和密码不匹配");
		} catch (LockedAccountException lae) {
			return new ResultX(ResultXCode.FAIL, "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			return new ResultX(ResultXCode.FAIL, "用户名或密码错误次数太多");
		} catch (AuthenticationException ae) {
			return new ResultX(ResultXCode.FAIL, "验证未通过");
		} catch (Exception e) {
			return new ResultX(ResultXCode.FAIL, "验证未通过");
		}
		return new ResultX(ResultXCode.SUCCESS, "验证成功");
	}
}
