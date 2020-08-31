package com.ai2331.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;
import com.ai2331.util.WebConstants;

@ControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(Exception.class)
	public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
		StringBuffer params = new StringBuffer();
		Map<String, String[]> requestParamMap = request.getParameterMap();
		for (String key : requestParamMap.keySet()) {
			params.append(key + "=" + requestParamMap.get(key) + ",");
		}

		boolean isView = (boolean) request.getAttribute(WebConstants.IS_VIEW_KEY);
		boolean isParamError = (e instanceof MethodArgumentTypeMismatchException);
		boolean isUnauth = false;
		String msg = "";
		if (e instanceof UnauthorizedException) {
			msg = "没有权限";
			isUnauth = true;
		} else if (isParamError) {
			msg = "参数异常";
		} else {
			msg = e.getMessage();
			if (StringUtils.isEmpty(msg)) {
				msg = e.getLocalizedMessage();
			}
			if (StringUtils.isEmpty(msg)) {
				msg = e.toString();
			}
		}
		e.printStackTrace();
		ModelAndView mv = new ModelAndView();
		if (isView) {
			if (isParamError) {
				mv.setViewName("error/404");
			} else if (isUnauth) {
				mv.setViewName("error/403");
			} else {
				mv.setViewName("error/500");
			}
			mv.addObject("errorMsg", msg);
			return mv;
		} else {
			mv.setView(new MappingJackson2JsonView());
			ResultX result = new ResultX();
			if (isParamError) {
				result.setCode(ResultXCode.NOTFOUND.getCode());
			} else if (isUnauth) {
				result.setCode(ResultXCode.UNAUTHORIZED.getCode());
			} else {
				result.setCode(ResultXCode.ERROR.getCode());
			}
			result.setMessage(msg);
			mv.addObject(result);
			return mv;
		}
	}
}
