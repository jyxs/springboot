package com.ai2331.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ai2331.common.entity.PageX;
import com.ai2331.sys.entity.AdminUser;
import com.ai2331.sys.entity.Role;
import com.ai2331.test.entity.T1;

public interface T1Service {
	T1 insert(T1 t);
	
	void delete(Integer id);


	Page<T1> list(PageX pr);

	void cacheMethod();
	
	T1 findById(Integer id);

	AdminUser findAdminByUsername(String username);

	List<Role> findRole(Integer uid);
}
