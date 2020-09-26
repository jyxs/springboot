package com.ai2331.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai2331.corp.dao.CorpResourceDAO;
import com.ai2331.corp.dao.CorpRoleDAO;
import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.sys.dao.CorpDAO;
import com.ai2331.sys.entity.Corp;
import com.ai2331.sys.entity.Resource;
import com.ai2331.sys.entity.dto.ResourceTree;
import com.ai2331.sys.service.ResourceMenuService;

public abstract class ResourceMenuServiceAbstract implements ResourceMenuService {

	@Autowired
	protected CorpDAO corpDAO;
	@Autowired
	protected CorpRoleDAO corpRoleDAO;
	@Autowired
	protected CorpResourceDAO resourceDAO;

	@Override
	public List<ResourceTree> listStafResourceTree(List<Resource> resources) {
		return createResourceTree(resources);
	}

	private List<ResourceTree> createResourceTree(List<Resource> resources) {
		List<ResourceTree> menus = new ArrayList<ResourceTree>();
		if (ObjectUtils.isEmpty(resources)) {
			return menus;
		}
		// 存储具有相同pid的数据resources
		Map<Integer, List<ResourceTree>> resMap = new HashMap<>();
		// 将具有相同
		for (Resource re : resources) {
			if (re.getResourceType().equals(Resource.RESOURCE_TYPE_ACTION)) {
				continue;
			}
			ResourceTree rt = new ResourceTree(re);
			List<ResourceTree> res = resMap.get(rt.getPid());
			if (null == res) {
				res = new ArrayList<ResourceTree>();
				resMap.put(rt.getPid(), res);
			}
			res.add(rt);
		}
		List<ResourceTree> root = resMap.get(0);
		resMap.remove(0);
		for (ResourceTree re : root) {
			resourceTree(re, resMap);
		}
		return root;
	}

	private void resourceTree(ResourceTree p, Map<Integer, List<ResourceTree>> resMap) {
		List<ResourceTree> children = resMap.get(p.getId());
		resMap.remove(p.getId());
		if (ObjectUtils.isNotEmpty(children)) {
			p.setChildren(children);
			for (ResourceTree rt : children) {
				resourceTree(rt, resMap);
			}
		}
	}

	@Override
	public List<Resource> listStaffResource(CorpStaff user) {
		Corp corp = corpDAO.findOne(user.getCorpCode());
		// 获取公司所属角色
		String suitCode = corp.getSuitCode();
		String suitCodeExtra = corp.getSuitCodeExtra();
		Set<String> suitCodes = new HashSet<String>();
		if (StringUtils.isNotBlank(suitCode)) {
			suitCodes.addAll(Arrays.asList(suitCode.split(",")));
		}
		if (StringUtils.isNotBlank(suitCodeExtra)) {
			suitCodes.addAll(Arrays.asList(suitCodeExtra.split(",")));
		}
		// 获取用户角色
		List<com.ai2331.corp.entity.Role> roles = corpRoleDAO.findRolesByUsername(user.getUsername(), user.getCorpCode());
		List<String> roleCodes = new ArrayList<String>();
		// 初始化角色，权限
		for (com.ai2331.corp.entity.Role role : roles) {
			roleCodes.add(role.getCode());
		}

		// 公司所具有的所有资源
		List<Resource> resources = resourceDAO.findBySuitCodes(suitCodes);
		// 获取员工所具有的资源
		List<Resource> userResources = resourceDAO.findByRoleCodesAndCorpCode(roleCodes, user.getCorpCode());
		// 取公司和员工资源交集
		resources.retainAll(userResources);
		return resources;
	}

}
