package com.ai2331.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.util.AesUtil;
import com.ai2331.util.WebUtil;

@Component
public class AppInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(AppInterceptor.class);

	// 白名单
	private static final Set<String> WHITE_LIST = new HashSet<String>();
	{
		WHITE_LIST.add("/css/**");
		WHITE_LIST.add("/images/**");
		WHITE_LIST.add("/js/**");
		WHITE_LIST.add("/fonts/**");
		WHITE_LIST.add("/login");
		WHITE_LIST.add("/logout");
		WHITE_LIST.add("/error/400");
		WHITE_LIST.add("/error/403");
		WHITE_LIST.add("/error/500");
		// swagger
		WHITE_LIST.add("/doc.html");
		WHITE_LIST.add("/doc.html#/**");
		WHITE_LIST.add("/swagger-ui/**");
		WHITE_LIST.add("/swagger-resources/**");
		WHITE_LIST.add("/v2/api-docs");
		WHITE_LIST.add("/v3/api-docs");
		WHITE_LIST.add("/webjars/**");
		WHITE_LIST.add("/swagger-ui.html");

		WHITE_LIST.add("/t1/thymleaftest");
		WHITE_LIST.add("/util/**");
		WHITE_LIST.add("/t2/thymleaftest");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug(">>>>>>>>>>>>>>>>>preHandle");
		String uri = request.getRequestURI();
		for (String s : WHITE_LIST) {
			if (s.endsWith("/**")) {
				if (uri.startsWith(s.substring(0, s.lastIndexOf("/**")))) {
					return true;
				}
			} else {
				if (s.contentEquals(uri)) {
					return true;
				}
			}
		}
		//header中需要将登陆成功的token设置到key为ai2331_token的变量中
		String token = request.getHeader("ai2331_token");
		if (StringUtils.isEmpty(token)) {
			throw new UnauthorizedException("token失效，请重新登录");
		}
		Object obj = SecurityUtils.getSubject().getPrincipal();
		if (null == obj) {
			throw new UnauthorizedException("登录超时，请重新登录");
		}
		CorpStaff staff = (CorpStaff) obj;
		String decrypt = AesUtil.decrypt(token, staff.getUsername());
		if (StringUtils.isEmpty(decrypt)) {
			throw new UnauthorizedException("token无效，请重新登录");
		}
		String[] deTokens = decrypt.split("&");
		if (deTokens.length != 2) {
			throw new UnauthorizedException("token无效，请重新登录");
		}
		if (!deTokens[1].equals(staff.getUsername())) {
			throw new UnauthorizedException("token无效，请重新登录");
		}
		if (!WebUtil.getRemoteAddr(request).contentEquals(deTokens[0])) {
			throw new UnauthorizedException("token无效，请重新登录");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.debug(">>>>>>>>>>>>>>>>>postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.debug(">>>>>>>>>>>>>>>>>afterCompletion");
	}

}
