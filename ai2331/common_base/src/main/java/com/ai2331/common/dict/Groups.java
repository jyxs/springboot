package com.ai2331.common.dict;

import java.util.LinkedHashMap;
import java.util.Map;

public class Groups {
	private Map<String, String> m = new LinkedHashMap<String, String>();
	
	public void addGroup(String groupCode, String lookupType) {
		m.put(groupCode, lookupType);
	}
	
	public void addGroups(Groups o){
		if(o!=null) m.putAll(o.m);
	}
	
	public Map<String, String> getCodeToTypeMapping() {
		return m;
	}
}
