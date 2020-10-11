package com.ai2331.util.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai2331.common.dict.api.LookupServices;
import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;
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
	public ResultX getOptions(@PathVariable("groupCode") String group) {
		return new ResultX(ResultXCode.SUCCESS, "ok", service.getOptions(group));
	}

	@ApiOperation("根据groupCode与选项值code获取具体选项信息")
	@GetMapping("option/{groupCode}/{code}")
	public ResultX getOption(@PathVariable("groupCode") String group, @PathVariable("code") String code) {
		return new ResultX(ResultXCode.SUCCESS, "ok", service.getOption(group, code));

	}

	@ApiOperation("根据groupCode与选项值数组code获取多个选项信息")
	@GetMapping("options/multi/{groupCode}")
	public ResultX getMultiOption(@PathVariable("groupCode") String group, @RequestParam("codes") String[] codes) {
		return new ResultX(ResultXCode.SUCCESS, "ok", service.getMultiOption(group, codes));
	}

	@ApiOperation("根据groupCode与选项值code获取具体选项名称")
	@GetMapping("option/name/{groupCode}/{code}")
	public ResultX getName(@PathVariable("groupCode") String group, @PathVariable("code") String code) {
		return new ResultX(ResultXCode.SUCCESS, "ok", service.getName(group, code));
	}

	@ApiOperation("根据groupCode与选项值数组code获取多个选项名称")
	@GetMapping("options/name/multi/{groupCode}")
	public ResultX getMultiName(@PathVariable("groupCode") String group, @RequestParam("codes") String[] codes) {

		return new ResultX(ResultXCode.SUCCESS, "ok", service.getMultiName(group, codes));
	}

	@ApiOperation("根据groupCode与选项名称获取具体选项code")
	@GetMapping("option/code/{groupCode}/{name}")
	public ResultX getCode(@PathVariable("groupCode") String group, @PathVariable("name") String name) {
		return new ResultX(ResultXCode.SUCCESS, "ok", service.getCode(group, name));
	}

	@ApiOperation("根据groupCode、选项值code与扩展值名称获取具体扩展值")
	@GetMapping("option/ext/{groupCode}/{code}/{ext}")
	public ResultX getOptionExt(String group, String code, String extName) {
		return new ResultX(ResultXCode.SUCCESS, "ok", service.getOptionExt(group, code, extName));
	}
}
