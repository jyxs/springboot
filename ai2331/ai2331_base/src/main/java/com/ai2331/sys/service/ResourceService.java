package com.ai2331.sys.service;

import java.util.List;
import java.util.Map;

import com.ai2331.common.entity.ResultX;
import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.sys.entity.Resource;

public interface ResourceService {

	List<Resource> query(Integer pid, Integer enabled);

	Resource info(Integer id);

	ResultX insert(Resource resource);

	ResultX update(Resource resource);

	ResultX updateEnabled(Integer id, Integer enabled);

	ResultX updateSort(Integer id, Integer sort);
	
	ResultX delete(Integer id);

	int getMaxSort(Integer id);

	List<Resource> listRoleResources(List<String> roleCodes);

	List<Resource> listRoleResources(String roleCode);
	
	/**
	 * 获取用户的菜单以及操作权限
	 * @param user
	 * @return
	 */
	Map<String, Object> listMenuAndPerm(CorpStaff user);
}
