package com.ai2331.sys.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai2331.common.entity.ResultX;
import com.ai2331.controllers.BaseController;
import com.ai2331.sys.entity.Role;
import com.ai2331.sys.entity.dto.RoleX;
import com.ai2331.sys.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "角色管理")
@RequestMapping("sys/role")
@RestController
public class RoleController extends BaseController {
	@Autowired
	private RoleService service;

	@ApiOperation("角色列表")
	@PostMapping("query")
	private List<Role> query(@RequestParam(value = "roleCode", required = false) String roleCode, @RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "enabled", required = false) Integer enabled) {
		return service.query(roleCode, name, enabled);
	}

	@ApiOperation("角色明细")
	@PostMapping("info/{roleCode}")
	public RoleX info(@PathVariable("roleCode") String roleCode) {
		return service.getOne(roleCode);
	}

	@ApiOperation("添加角色")
	@PostMapping("add")
	public ResultX add(@RequestBody Role role) {
		return service.insert(role);
	}

	@ApiOperation("编辑角色")
	@PostMapping("edit")
	public ResultX edit(@RequestBody Role role) {
		return service.update(role);
	}

	@ApiOperation("删除角色")
	@PostMapping("delete/{roleCode}")
	public ResultX delete(@PathVariable("roleCode") String roleCode) {
		return service.delete(roleCode);
	}

	@ApiOperation("获取套装角色列表")
	@PostMapping("suitRole/{suitCode}")
	public List<Role> suitRole(@PathVariable("suitCode") String suitCode) {
		return service.listSuitRole(suitCode);
	}
}
