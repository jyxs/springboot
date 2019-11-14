package com.ai2331.error;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ai2331.common.entity.ResultX;
import com.ai2331.util.WebConstants;

/**
 * 用来处理40x，5xx错误
 * 
 * @author jingyu
 *
 */
@Controller
public class AppErrorController implements ErrorController {

	private Logger loger=LoggerFactory.getLogger(AppErrorController.class);
	@RequestMapping("/error")
	public ModelAndView error(HttpServletRequest request, Exception e) {
		String statusCode = request.getAttribute("javax.servlet.error.status_code").toString();
		ModelAndView view = new ModelAndView();
		boolean isView = (boolean) request.getAttribute(WebConstants.IS_VIEW_KEY);
		String msg = "";
		msg = e.getMessage();
		if (StringUtils.isEmpty(msg)) {
			msg = e.getLocalizedMessage();
		}
		if (StringUtils.isEmpty(msg)) {
			msg = e.toString();
		}
		loger.debug(">>>>>>>>>>>>>>>>>>>request uri" + request.getRequestURI());
		if (isView && isAjaxRequest(request) == false) {
			view.addObject("errorMsg", msg);
			if (statusCode.startsWith("4")) {
				if (statusCode.contentEquals("403")) {
					view.setViewName("/error/403");
				} else {
					view.setViewName("/error/404");
				}
			} else {
				view.setViewName("/error/500");
			}
		} else {
			view.setView(new MappingJackson2JsonView());
			ResultX resultx = new ResultX();
			if (statusCode.startsWith("4")) {
				if (statusCode.contentEquals("403")) {
					msg = "没有权限";
				} else {
					msg = "未找到URL";
				}
			}
			view.addObject("code", statusCode);
			view.addObject("message", msg);
		}
		return view;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	/**
	 * 是否是Ajax异步请求
	 */
	public boolean isAjaxRequest(HttpServletRequest request) {
		String tmp = request.getHeader("accept");
		if (tmp != null && tmp.indexOf("application/json") != -1) {
			return true;
		}

		tmp = request.getHeader("X-Requested-With");
		if (tmp != null && tmp.indexOf("XMLHttpRequest") != -1) {
			return true;
		}

		tmp = request.getRequestURI();
		if (StringUtils.endsWithIgnoreCase(tmp, ".json")) {
			return true;
		}

		return false;
	}
}
