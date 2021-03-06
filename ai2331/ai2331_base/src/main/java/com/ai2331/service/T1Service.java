package com.ai2331.service;

import java.util.List;

import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.sys.entity.Role;
import com.ai2331.test.entity.T1;

public interface T1Service {
	
	void cacheMethod();
	
	T1 findById(Integer id);

	CorpStaff findAdminByUsername(String username);

	List<Role> findRole(Integer uid);
}
