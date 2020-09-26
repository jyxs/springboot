package com.ai2331.sys.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai2331.AppConstants;
import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;
import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.sys.dao.ResourceDAO;
import com.ai2331.sys.entity.Resource;
import com.ai2331.sys.entity.dto.ResourceTree;
import com.ai2331.sys.service.ResourceMenuService;
import com.ai2331.sys.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDAO dao;

	@Autowired
	private ResourceMenuService resourceMenuService;

	@Override
	public List<Resource> query(Integer pid, Integer enabled) {
		return dao.find(pid, enabled);
	}

	@Override
	public Resource info(Integer id) {
		return dao.getOne(id);
	}

	@Override
	public ResultX insert(Resource resource) {
		if (null == resource.getEnabled()) {
			resource.setEnabled(AppConstants.YES);
		}
		if (null == resource.getSortOrder()) {
			resource.setSortOrder(1 + dao.getMaxSort(resource.getPid()));
		}
		resource.setCreateTime(Date.from(Instant.now()));
		int result = dao.insert(resource);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS);
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	@Override
	public ResultX update(Resource resource) {
		if (null == resource.getId()) {
			return new ResultX(ResultXCode.ERROR, "资源id无效");
		}
		int result = dao.update(resource);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS);
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	public ResultX delete(Integer id) {
		int result = dao.delete(id);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS);
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	@Override
	public ResultX updateEnabled(Integer id, Integer enabled) {
		if (null == id) {
			return new ResultX(ResultXCode.ERROR, "资源id无效");
		}
		if (null == enabled) {
			enabled = AppConstants.YES;
		}
		int result = dao.updateEnabled(id, enabled);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS);
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	@Override
	public ResultX updateSort(Integer id, Integer sort) {
		if (null == id) {
			return new ResultX(ResultXCode.ERROR, "资源id无效");
		}
		if (null == sort) {
			Resource r = dao.getOne(id);
			sort = 1 + dao.getMaxSort(r.getPid());
		}
		int result = dao.updateSort(id, sort);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS);
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	@Override
	public int getMaxSort(Integer id) {
		Resource r = dao.getOne(id);
		return dao.getMaxSort(r.getPid());
	}

	@Override
	public List<Resource> listRoleResources(List<String> roleCodes) {
		if (null != roleCodes && roleCodes.size() > 0) {
			return dao.findByRoleCodes(new HashSet<String>(roleCodes));
		}
		return null;
	}

	@Override
	public List<Resource> listRoleResources(String roleCode) {
		if (StringUtils.isAllBlank(roleCode)) {
			return null;
		}
		Set<String> roleCodes = new HashSet<>();
		roleCodes.add(roleCode);
		return dao.findByRoleCodes(roleCodes);
	}

	@Override
	public Map<String, Object> listMenuAndPerm(CorpStaff user) {

		List<Resource> resources = resourceMenuService.listStaffResource(user);
		List<ResourceTree> listStafResourceTree = resourceMenuService.listStafResourceTree(resources);

		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("menus", resourceMenuService.getMenus(listStafResourceTree));
		datas.put("prems", resources.stream().filter(s -> s.getResourceType().equals(Resource.RESOURCE_TYPE_ACTION)).map(s -> s.getPrivCode()).collect(Collectors.toSet()));
		return datas;
	}

}
