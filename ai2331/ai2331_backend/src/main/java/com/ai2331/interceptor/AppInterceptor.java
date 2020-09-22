package com.ai2331.interceptor;

import javax.servlet.http.Cookie;
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

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug(">>>>>>>>>>>>>>>>>preHandle");
		if (request.getRequestURI().contentEquals("/login")) {
			return true;
		}
		Cookie[] cookies = request.getCookies();
		String token = "";
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if ("ai2331_token".equals(cookie.getName())) {
					token = cookie.getValue();
				}
			}
		}
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
