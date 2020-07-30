package com.ai2331.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai2331.common.cache.api.CacheService;
import com.ai2331.mybatis.sys.dao.AdminUserDAO;
import com.ai2331.mybatis.sys.dao.RoleDAO;
import com.ai2331.mybatis.test.dao.T1Mapper;
import com.ai2331.service.T1Service;
import com.ai2331.sys.entity.AdminUser;
import com.ai2331.sys.entity.Role;
import com.ai2331.test.entity.T1;

@Service
public class T1ServiceImpl implements T1Service {

	@Autowired(required = false)
	private CacheService cache;
	@Autowired
	private T1Mapper t1mapper;
	@Autowired
	private AdminUserDAO adminUserDAO;
	@Autowired
	private RoleDAO roleDAO;
	

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
	public AdminUser findAdminByUsername(String username) {
		return adminUserDAO.findOneByUsername(username);
	}
	
	@Override
	public List<Role> findRole(Integer uid) {
		return roleDAO.findRolesByUserId(uid);
	}
}
