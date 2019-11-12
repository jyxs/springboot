package com.ai2331.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 用来处理40x，5xx错误
 * @author jingyu
 *
 */
@Controller
public class AppErrorController implements ErrorController{

	@RequestMapping("/error")
	public Object error(HttpServletRequest request) {
		String statusCode = request.getAttribute("javax.servlet.error.status_code").toString();
		ModelAndView view = new ModelAndView();
		view.addObject("errorMsg", statusCode);
		view.setViewName("/error/"+statusCode);
		return view;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
