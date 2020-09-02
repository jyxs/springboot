package com.ai2331.service.impl;

import java.util.List;

import org.apache.commons.lang3.builder.HashCodeExclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai2331.common.cache.api.CacheService;
import com.ai2331.corp.dao.CorpStaffDAO;
import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.service.T1Service;
import com.ai2331.sys.entity.Role;
import com.ai2331.test.dao.T1Mapper;
import com.ai2331.test.entity.T1;

@Service
public class T1ServiceImpl implements T1Service {

	@Autowired(required = false)
	private CacheService cache;
	@Autowired
	private T1Mapper t1mapper;
	@Autowired
	private CorpStaffDAO adminUserDAO;
	

	@Override
	public void cacheMethod() {
		cache.set("1", "1");
		System.out.println(cache.get("1"));
	}

	@Override
	public T1 findById(Integer id) {
		return t1mapper.findById(id);
	}

	@Override
	public CorpStaff findAdminByUsername(String username) {
		return adminUserDAO.findOneByUsername(username);
	}
	
	@Override
	public List<Role> findRole(Integer uid) {
		return null;
	}
}
