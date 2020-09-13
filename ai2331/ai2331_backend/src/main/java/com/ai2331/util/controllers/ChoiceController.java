package com.ai2331.util.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai2331.common.choice.Choice;
import com.ai2331.common.choice.api.ChoiceService;
import com.ai2331.common.entity.GridX;
import com.ai2331.common.entity.PageX;
import com.ai2331.controllers.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "选择列表查询")
@RequestMapping("util/chioce")
@RestController
public class ChoiceController extends BaseController {

	@Autowired
	private ChoiceService service;

	@ApiOperation("根据choiceCode，分页查询匹配对应参数q，及扩展extraKyes对应的数据列表")
	@GetMapping("grid/{choiceCode}")
	public GridX<List<Choice>> queryChoice(@PathVariable("choiceCode") String choiceCode, @RequestParam(name = "q", required = false) String q,
			@RequestParam(name = "extraKeys", required = false) String extraKeys, @RequestParam(name = "cp", required = false) Integer cp,
			@RequestParam(name = "ps", required = false) Integer ps) {
		PageX px = new PageX();
		if (null != cp && cp > 0) {
			px.setPageNumber(0);
		}
		if (null != ps && ps > 0) {
			px.setPageSize(0);
		}
		return service.queryChoice(choiceCode, q, extraKeys, px);
	}

	@ApiOperation("根据choiceCode，查询匹配对应参数ids数组，及扩展extraKyes对应的数据列表，keepOrder参数为是否按照提交时顺序返回结果集0否，1是，默认0")
	@GetMapping("list/{choiceCode}")
	public List<List<Choice>> listChoice(@PathVariable("choiceCode") String choiceCode, @RequestParam("ids") String[] ids,
			@RequestParam(name = "keepOrder", required = false) Integer keepOrder, @RequestParam(name = "extraKeys", required = false) String extraKeys) {
		boolean ko = null == keepOrder || keepOrder != 1 ? false : true;
		return service.listChoice(choiceCode, ids, ko, extraKeys);
	}

	@ApiOperation("根据choiceCode，查询匹配对应参数ids数组，及扩展extraKyes对应的名称，多个以英文逗号分隔")
	@GetMapping("name/{choiceCode}")
	public String mapName(@PathVariable("choiceCode") String choiceCode, @RequestParam("ids") String ids, @RequestParam(name = "extraKeys", required = false) String extraKeys) {
		return service.mapName(choiceCode, ids, extraKeys);
	}
}
