package com.ai2331.sys.service;

import java.util.List;

import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.sys.entity.Resource;
import com.ai2331.sys.entity.dto.Menu;
import com.ai2331.sys.entity.dto.ResourceTree;

public interface ResourceMenuService {
	
	/**
	 * 获取staff所拥有的的资源树
	 * @param staff
	 * @return
	 */
	List<ResourceTree> listStafResourceTree(List<Resource> resources);

	/**
	 * 将资源树转为菜单树
	 * @param rts
	 * @return
	 */
	List<Menu> getMenus(List<ResourceTree> rts);

	/**
	 * 获取staff所拥有的的资源
	 * @param user
	 * @return
	 */
	List<Resource> listStaffResource(CorpStaff user);
}
