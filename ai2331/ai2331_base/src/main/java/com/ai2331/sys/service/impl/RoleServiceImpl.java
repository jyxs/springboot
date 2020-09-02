package com.ai2331.sys.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai2331.AppConstants;
import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;
import com.ai2331.sys.dao.ResourceDAO;
import com.ai2331.sys.dao.RoleDAO;
import com.ai2331.sys.entity.Role;
import com.ai2331.sys.entity.dto.RoleX;
import com.ai2331.sys.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO dao;
	@Autowired
	private ResourceDAO resourceDAO;

	@Override
	public List<Role> query(String roleCode, String name, Integer enabled) {
		return dao.findRoles(roleCode, name, enabled);
	}

	@Override
	public RoleX getOne(String roleCode) {
		RoleX one = dao.getOne(roleCode);
		if (null != one) {
			Set<String> roleCodes = new HashSet<>();
			roleCodes.add(roleCode);
			one.setResources(resourceDAO.findByRoleCodes(roleCodes));
		}
		return one;
	}

	@Override
	public ResultX insert(Role role) {
		role.setCreateTime(Date.from(Instant.now()));
		if (null == role.getEnabled()) {
			role.setEnabled(AppConstants.YES);
		}
		if (null == role.getPid()) {
			role.setPid(0);
		}
		int result = dao.insert(role);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS, "ok");
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	@Override
	public ResultX update(Role role) {
		int result = dao.update(role);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS, "ok");
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	@Override
	public ResultX delete(String roleCode) {
		int result = dao.delete(roleCode);
		if (result > 0) {
			dao.deleteResourceByRoleCode(roleCode);
		}
		return null;
	}

	@Override
	public List<Role> listSuitRole(String suitCode) {
		Set<String> suitCodes = new HashSet<>();
		suitCodes.add(suitCode);
		return dao.findRolesBySuitCodes(suitCodes);
	}

}
