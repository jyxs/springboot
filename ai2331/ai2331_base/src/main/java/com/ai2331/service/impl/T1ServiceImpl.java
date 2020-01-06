package com.ai2331.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ai2331.common.cache.api.CacheService;
import com.ai2331.common.entity.PageX;
import com.ai2331.dao.T1DAO;
import com.ai2331.dao.mapper.T1Mapper;
import com.ai2331.entity.T1;
import com.ai2331.service.T1Service;
import com.ai2331.sys.dao.mapper.AdminUserMapper;
import com.ai2331.sys.dao.mapper.RoleMapper;
import com.ai2331.sys.entity.AdminUser;
import com.ai2331.sys.entity.Role;

@Service
public class T1ServiceImpl implements T1Service {

	@Autowired
	private T1DAO dao;
	
	@Autowired(required = false)
	private CacheService cache;
	@Autowired
	private T1Mapper t1mapper;
	@Autowired
	private AdminUserMapper adminUserMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public Page<T1> list(PageX pr) {
		Sort.by(Direction.DESC, "createTime");
		Pageable pager = PageRequest.of(pr.getPageNumber(), pr.getPageSize(), Sort.by(Direction.DESC, "createTime"));
		return dao.findAll(pager);
	}

	@Override
	public T1 insert(T1 t) {
		 return dao.save(t);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

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
		return adminUserMapper.findOneByUsername(username);
	}
	
	@Override
	public List<Role> findRole(Integer uid) {
		return roleMapper.findRolesByUserId(uid);
	}
}
