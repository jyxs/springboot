package com.ai2331.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(Exception.class)
	public Object errorHandler(HttpServletRequest request, Exception e) {
		if (isAjax(request)) {
			return new ResultX(ResultXCode.ERROR, e.toString());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/error/500");
		modelAndView.addObject("errorMsg", e.getMessage());
		return modelAndView;
	}

	/**
	 * 判断是否是Ajax请求
	 *
	 * @param request
	 * @return
	 */
	public boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}
}
