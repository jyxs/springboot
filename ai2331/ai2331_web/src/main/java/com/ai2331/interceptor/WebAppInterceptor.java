package com.ai2331.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class WebAppInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(WebAppInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>postHandle");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
