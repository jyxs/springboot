package com.ai2331.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ai2331.util.WebConstants;

@Component
public class AppInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(AppInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug(">>>>>>>>>>>>>>>>>preHandle");
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod hm = (HandlerMethod) handler;
		Method method = hm.getMethod();
		boolean body = !method.isAnnotationPresent(ResponseBody.class);
		boolean restController = !hm.getBeanType().isAnnotationPresent(RestController.class);
		request.setAttribute(WebConstants.IS_VIEW_KEY, body || restController);

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
