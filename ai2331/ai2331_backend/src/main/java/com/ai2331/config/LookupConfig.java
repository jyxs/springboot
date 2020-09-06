package com.ai2331.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.ai2331.SpringContextUtil;
import com.ai2331.common.dict.api.LookupService;
import com.ai2331.common.dict.api.LookupServices;
import com.ai2331.sys.util.service.LookupServiceImpl;

@Configuration
public class LookupConfig {

	
	@Bean(name = "dblookup")
	@DependsOn("dictDAO")
	protected LookupService createDBLookupService() {
		return new LookupServiceImpl();
	}
	
	@Bean
	@DependsOn("dblookup")
	protected LookupServices createLookupService() {
		LookupServices lkp = new LookupServices();
		List<LookupService> lks = new ArrayList<LookupService>();
		LookupService dblk = (LookupService) SpringContextUtil.getBean("dblookup");
		lks.add(dblk);
		lkp.setServices(lks);
		return lkp;
	}
}
