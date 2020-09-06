package com.ai2331.common.dict.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ai2331.common.dict.Groups;
import com.ai2331.common.dict.Option;
import com.ai2331.common.dict.OptionGroup;
import com.ai2331.common.dict.api.LookupService;

abstract public class AbstractLookupService implements LookupService {
	protected Map<String, OptionGroup> groups = new ConcurrentHashMap<String, OptionGroup>();

	@Override
	public boolean containsGroup(String group) {
		return groups.containsKey(group);
	}

	@Override
	public Groups listGroup() {
		Groups gs = new Groups();
		Iterator<String> it = groups.keySet().iterator();
		while (it.hasNext()) {
			String group = it.next();
			gs.addGroup(group, this.getLookupType(group));
		}
		return gs;
	}

	@Override
	public OptionGroup getOptionGroup(String group) {
		return groups.get(group);
	}

	@Override
	public Collection<Option> getOptions(String group) {
		OptionGroup optionGroup = getOptionGroup(group);
		return optionGroup == null ? null : optionGroup.getOptionList();
	}

	@Override
	public Option getOption(String group, String code) {
		OptionGroup optionGroup = getOptionGroup(group);
		return optionGroup == null ? null : optionGroup.getOption(code);
	}

	@Override
	public Option getOption(String group, Integer code) {
		return code == null ? null : getOption(group, code.toString());
	}

	@Override
	public String getName(String group, String code) {
		Option opt = getOption(group, code);
		return opt == null ? "" : opt.getName();
	}

	@Override
	public String getName(String group, Integer code) {
		return code == null ? "" : getName(group, code.toString());
	}

	@Override
	public String getCode(String group, String name) {
		OptionGroup optionGroup = getOptionGroup(group);
		return optionGroup == null ? null : optionGroup.getCodeByName(name);
	}

	@Override
	public String getMultiName(String group, String... codes) {
		OptionGroup optionGroup = getOptionGroup(group);
		return optionGroup == null ? "" : optionGroup.getMultiName(codes);
	}

	@Override
	public List<Option> getMultiOption(String group, String... codes) {
		OptionGroup optionGroup = getOptionGroup(group);
		return null == optionGroup ? null : optionGroup.getMultiOption(codes);
	}

	@Override
	public String getOptionExt(String group, String code, String extName) {
		Option opt = getOption(group, code);
		return opt == null ? "" : opt.getExt(extName);
	}

	@Override
	public String getOptionExt(String group, Integer code, String extName) {
		return code == null ? "" : getOptionExt(group, code.toString(), extName);
	}
}
