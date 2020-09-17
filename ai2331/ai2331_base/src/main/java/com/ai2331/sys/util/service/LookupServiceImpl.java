package com.ai2331.sys.util.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai2331.AppConstants;
import com.ai2331.common.dict.Option;
import com.ai2331.common.dict.OptionGroup;
import com.ai2331.common.dict.entity.Dict;
import com.ai2331.common.dict.entity.DictItem;
import com.ai2331.common.dict.impl.AbstractLookupService;
import com.ai2331.sys.util.dao.DictDAO;
public class LookupServiceImpl extends AbstractLookupService {

	@Autowired
	private DictDAO dao;

	@Override
	public String getLookupType(String group) {
		return "db";
	}

	@Override
	public void init() {
		reload();
	}

	@Override
	public void reload() {
		List<Dict> dicts = dao.listEnableDicts(AppConstants.YES);
		List<DictItem> items = dao.listDictItems();
		Map<String, List<DictItem>> itemMap = items.stream().collect(Collectors.groupingBy(DictItem::getGroupCode));

		dicts.forEach(item -> {
			Map<String, Option> options = new HashMap<>();

			List<DictItem> dis = itemMap.get(item.getCode());
			dis.forEach(i -> {
				Option o = new Option();
				o.setName(i.getName());
				o.setCode(i.getValue());
				o.setExt("ext1", i.getExt1());
				o.setExt("ext2", i.getExt2());
				o.setExt("ext3", i.getExt3());
				options.put(i.getValue(), o);
			});
			OptionGroup og = new OptionGroup(item.getCode(), options);
			super.groups.put(item.getCode(), og);
		});

	}

	@Override
	public void reload(String group) {
		List<DictItem> items = dao.listDictItemsByGroupCode(group);
		Map<String, Option> options = new HashMap<>();
		items.forEach(item -> {
			Option o = new Option();
			o.setName(item.getName());
			o.setCode(item.getValue());
			o.setExt("ext1", item.getExt1());
			o.setExt("ext2", item.getExt2());
			o.setExt("ext3", item.getExt3());
			options.put(item.getValue(), o);
		});
		OptionGroup og = new OptionGroup(group, options);
		super.groups.put(group, og);
	}
}
