package com.ai2331.sys.service;

import java.util.List;

import com.ai2331.common.entity.ResultX;
import com.ai2331.sys.entity.Role;

public interface RoleService {

	List<Role> query(String roleCode, String name, Integer enabled);
	
	Role getOne(String roleCode);
	
	ResultX insert(Role role);
	
	ResultX update(Role role);
	
	ResultX delete(String roleCode);
	
	List<Role> listSuitRole(String suitCode);
}
