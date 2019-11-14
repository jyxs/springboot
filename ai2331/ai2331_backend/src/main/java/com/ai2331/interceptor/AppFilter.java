package com.ai2331.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "zzglobalFileter", urlPatterns = "/*")
public class AppFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(AppFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		logger.info(">>>>>>>>>>>>>>>>>doFilter");
		chain.doFilter(request, response);
	}
}
