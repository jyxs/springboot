package com.ai2331.error;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object errorHandler(HttpServletRequest request, Exception e) {
		String msg = e.getMessage();
		if (StringUtils.isEmpty(msg)) {
			msg = e.getLocalizedMessage();
		}
		if (StringUtils.isEmpty(msg)) {
			msg = e.toString();
		}
		if (e instanceof UnauthorizedException) {
			return new ResultX(ResultXCode.UNAUTHORIZED, "没有权限");
		}
		return new ResultX(ResultXCode.ERROR, msg);
	}
}
