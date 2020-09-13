package com.ai2331.util.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai2331.common.dict.Option;
import com.ai2331.common.dict.api.LookupServices;
import com.ai2331.controllers.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "字典查询")
@RequestMapping("util/lkp")
@RestController
public class LookUpController extends BaseController {

	@Autowired
	private LookupServices service;

	@ApiOperation("根据groupCode获取字典选项列表")
	@GetMapping("options/{groupCode}")
	public Collection<Option> getOptions(@PathVariable("groupCode") String group) {
		return service.getOptions(group);
	}

	@ApiOperation("根据groupCode与选项值code获取具体选项信息")
	@GetMapping("option/{groupCode}/{code}")
	public Option getOption(@PathVariable("groupCode") String group, @PathVariable("code") String code) {
		return service.getOption(group, code);
	}

	@ApiOperation("根据groupCode与选项值数组code获取多个选项信息")
	@GetMapping("options/multi/{groupCode}")
	public List<Option> getMultiOption(@PathVariable("groupCode") String group, @RequestParam("codes") String[] codes) {
		return service.getMultiOption(group, codes);
	}

	@ApiOperation("根据groupCode与选项值code获取具体选项名称")
	@GetMapping("option/name/{groupCode}/{code}")
	public String getName(@PathVariable("groupCode") String group, @PathVariable("code") String code) {
		return service.getName(group, code);
	}

	@ApiOperation("根据groupCode与选项值数组code获取多个选项名称")
	@GetMapping("options/name/multi/{groupCode}")
	String getMultiName(@PathVariable("groupCode") String group, @RequestParam("codes") String[] codes) {
		return service.getMultiName(group, codes);
	}

	@ApiOperation("根据groupCode与选项名称获取具体选项code")
	@GetMapping("option/code/{groupCode}/{name}")
	String getCode(@PathVariable("groupCode") String group, @PathVariable("name") String name) {
		return service.getCode(group, name);
	}

	@ApiOperation("根据groupCode、选项值code与扩展值名称获取具体扩展值")
	@GetMapping("option/ext/{groupCode}/{code}/{ext}")
	String getOptionExt(String group, String code, String extName) {
		return service.getOptionExt(group, code, extName);
	}
}
