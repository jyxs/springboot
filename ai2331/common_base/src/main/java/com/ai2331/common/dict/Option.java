package com.ai2331.common.dict;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Option implements Serializable {
	private static final long serialVersionUID = 633720115422704897L;

	private String code;
	private String name;
	
	private Map<String, String> ext;

	public String getExt(String extName){
		return ext==null?null:ext.get(extName);
	}
	public void setExt(String extName, String extVal){
		if(ext==null) ext = new HashMap<String, String>();
		ext.put(extName, extVal);
	}
	//getter & setter
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getExt() {
		return ext;
	}

	public void setExt(Map<String, String> ext) {
		this.ext = ext;
	}
	
	
}
