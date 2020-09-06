package com.ai2331.common.dict.api;

import java.util.Collection;
import java.util.List;

import com.ai2331.common.dict.Groups;
import com.ai2331.common.dict.Option;
import com.ai2331.common.dict.OptionGroup;

public interface LookupService {
	String getLookupType(String group);	//db or xml or ...
	void init();
	void reload();
	void reload(String group);
	boolean containsGroup(String group);
	Groups listGroup();	//返回所有的Group Code
	
	OptionGroup getOptionGroup(String group);
	Collection<Option> getOptions(String group);
	Option getOption(String group, String code);
	List<Option> getMultiOption(String group, String ...codes);
	Option getOption(String group, Integer code);
	
	String getName(String group, String code);
	String getName(String group, Integer code);
	String getMultiName(String group, String ... codes);
	
	String getCode(String group, String name);
	
	String getOptionExt(String group, String code, String extName);
	String getOptionExt(String group, Integer code, String extName);
}
