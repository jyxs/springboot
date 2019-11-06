package com.ai2331.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;

@RestController
public class AppErrorController implements ErrorController{

	@RequestMapping("/error")
	public ResultX error(HttpServletRequest request) {
		String statusCode = request.getAttribute("javax.servlet.error.status_code").toString();
		return new ResultX(ResultXCode.ERROR, statusCode);
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
