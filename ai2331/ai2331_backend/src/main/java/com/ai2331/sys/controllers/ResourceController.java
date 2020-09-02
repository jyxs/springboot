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
import com.ai2331.sys.entity.Resource;
import com.ai2331.sys.service.ResourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "运维-资源管理")
@RequestMapping("sys/resource")
@RestController
public class ResourceController extends BaseController {

	@Autowired
	private ResourceService service;

	@ApiOperation("获取资源列表")
	@PostMapping("query")
	public List<Resource> query(@RequestParam(name = "pid", required = false) Integer pid, @RequestParam(name = "enabled", required = false) Integer enabled) {
		return service.query(pid, enabled);
	}

	@ApiOperation("获取资源明细")
	@PostMapping("info/{id}")
	public Resource info(@PathVariable("id") Integer id) {
		return service.info(id);
	}

	@ApiOperation("新增资源明细")
	@PostMapping("add")
	public ResultX add(@RequestBody Resource resource) {
		return service.insert(resource);
	}

	@ApiOperation("修改资源明细")
	@PostMapping("edit")
	public ResultX edit(@RequestBody Resource resource) {
		return service.update(resource);
	}

	@ApiOperation("修改资源状态")
	@PostMapping("editEnabled")
	public ResultX editEnabled(@RequestParam("id") Integer id, @RequestParam("enabled") Integer enabled) {
		return service.updateEnabled(id, enabled);
	}

	@ApiOperation("修改资源排序")
	@PostMapping("editSort")
	public ResultX editSort(@RequestParam("id") Integer id, @RequestParam(name = "enabled", required = false) Integer sort) {
		if (null == sort) {
			sort = service.getMaxSort(id);
		}
		return service.updateSort(id, sort);
	}

	@ApiOperation("获取角色下的资源列表")
	@PostMapping("roleResources")
	public List<Resource> listRoleResources(@RequestBody List<String> roleCodes) {
		if (null == roleCodes || roleCodes.size() == 0) {
			return null;
		}
		return service.listRoleResources(roleCodes);
	}

	@ApiOperation("获取角色下的资源列表")
	@PostMapping("roleResources/{roleCode}")
	public List<Resource> listRoleResources(@PathVariable("roleCode") String roleCode) {
		return service.listRoleResources(roleCode);
	}
}
