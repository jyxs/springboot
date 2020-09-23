package com.ai2331.controllers;

import javax.servlet.http.HttpServletRequest;

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
import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.util.AesUtil;
import com.ai2331.util.WebUtil;

@RequestMapping("/")
@Controller
public class PortalController extends BaseController {

	@GetMapping({ "", "/", "/portal" })
	public String portal(Model model) {
		CorpStaff user = currentUser();
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
			@RequestParam(name = "rememberMe", defaultValue = "0") String rememberMe, HttpServletRequest request) {
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
//		Map<String,String> loginCookie=new HashMap<>();
//		Cookie[] cookies = request.getCookies();
//		for (Cookie cookie : cookies) {
//			if(cookie.getName().equals("JSESSIONID")) {
//				loginCookie.put(cookie.getName(), cookie.getValue());
//			}
//		}

		String remoteAddr = WebUtil.getRemoteAddr(request);
		if (StringUtils.isEmpty(remoteAddr)) {
			return new ResultX(ResultXCode.FAIL, "验证失败");
		}
		// 对ip和用户名加密
		String encrypt = AesUtil.encrypt(remoteAddr + "&" + username, username);

		return new ResultX(ResultXCode.SUCCESS, "验证成功", encrypt);
	}

	@GetMapping("me")
	@ResponseBody
	public ResultX me() {
		return new ResultX(ResultXCode.SUCCESS, "ok", this.currentUser());
	}
//	private void initUrlPermit() {
//		// 初始化角色，权限
//		List<Role> roles = roleDAO.findRolesByUserId(this.currentUser().getId());
//		Set<Integer> roleIds = roles.stream().map(Role::getId).collect(Collectors.toSet());
//
//		List<Resource> resources = resourceDAO.findByRoleIds(roleIds);
//		Map<String, String> filterMap = new HashMap<String, String>();
//		filterMap.put("/css/**", "anon");
//		filterMap.put("/images/**", "anon");
//		filterMap.put("/js/**", "anon");
//		filterMap.put("/login", "anon");
//		filterMap.put("/logout", "logout");// 配置退出 过滤器,其中的具体的退出代码Shiro已经实现
//		filterMap.put("/**", "authc");// 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
//
//		updatePermission(filterMap, resources);
//	}
//
//	private synchronized void updatePermission(Map<String, String> filterMap, List<Resource> resources) {
//		AbstractShiroFilter shiroFilter = null;
//		try {
//			shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
//			// 获取过滤管理器
//			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
//			// 清空拦截管理器中的存储
//			DefaultFilterChainManager defaultFilterChainManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
//			defaultFilterChainManager.getFilterChains().clear();
//
//			shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
//
//			Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
//			chains.putAll(filterMap);
//			shiroFilterFactoryBean.setFilterChainDefinitionMap(chains);
//			for (Resource resource : resources) {
//				if (StringUtils.isEmpty(resource.getUrl()) || StringUtils.isEmpty(resource.getPrivCode())) {
//					continue;
//				}
//				defaultFilterChainManager.createChain(resource.getUrl(), resource.getPrivCode());
//			}
//			loger.debug(">>>>>>>>>>>>>>>>>>>>>>>Resources加入url拦截完毕");
//		} catch (Exception e) {
//			loger.error(">>>>>>>>>>>>>>>>>>>>>>>resources加入url拦截出错");
//		}
//	}
}
