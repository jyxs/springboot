package com.ai2331.common.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 任意扩展内容.
 * Map<String, Object>
 */

public class PropertyX implements java.io.Serializable {
	private static final long serialVersionUID = 7861924284847242766L;
	
	@JsonIgnore
	private Map<String, Object> m;
	
	public PropertyX() {
		super();
	}
	public PropertyX(Map<String, Object> m) {
		super();
		this.m = m;
	}
	
	public static PropertyX create(String key, Object value) {
		return new PropertyX().set(key, value);
	}
	
	@JsonIgnore
	public boolean isEmpty() {
		return m==null || m.isEmpty();
	}
	
	@JsonAnySetter
	public PropertyX set(String key, Object value) {
		if(m==null) m = new HashMap<String, Object>();
		m.put(key, value);
		return this;	//便于连写
	}
	public PropertyX setIfNN(String key, Object value) {
		if(key==null||key.length()==0||value==null) return this;
		
		if(m==null) m = new HashMap<String, Object>();
		m.put(key, value);
		return this;	//便于连写
	}
	public PropertyX append(Map<String, Object> o) {
		if(o==null) return this;
		
		if(m==null) m = new HashMap<String, Object>();
		m.putAll(o);
		return this;
	}
	public Object get(String key) {
		return m==null?null:m.get(key);
	}
	
	@JsonAnyGetter
	public Map<String, Object> data() {
		if(m==null) m = new HashMap<String, Object>();
		return m;
	}
	
	public void data(Map<String, Object> m) {
		this.m = m;
	}

	/**
	 * 取字符串请求参数(缺省值"").
	 */
	public String getString(String key, String defValue){
		if(m==null) return defValue;
		Object obj = m.get(key);
		if(obj==null) return defValue;
		else return obj.toString();
	}
	public String getString(String key){
		return getString(key, "");
	}

	/**
	 * 取整数请求参数(缺省值0).
	 */
	public int getInt(String key, int defValue){
		if(m==null) return defValue;
		Object obj = m.get(key);
		if(obj==null) return defValue;
		String s = obj.toString();
		
		if(s.length() == 0){
			return defValue;
		}else{
			return Integer.parseInt(s);
		}
	}
	public int getInt(String key){
		return getInt(key,0);
	}

	/**
	 * 取长整数请求参数(缺省值0).
	 */
	public long getLong(String key, long defValue){
		if(m==null) return defValue;
		Object obj = m.get(key);
		if(obj==null) return defValue;
		String s = obj.toString();
		
		if(s.length() == 0){
			return defValue;
		}else{
			return Long.parseLong(s);
		}
	}
	public long getLong(String key){
		return getLong(key,0);
	}

	/**
	 * 取浮点数请求参数(缺省值0.0f).
	 */
	public float getFloat(String key, float defValue){
		if(m==null) return defValue;
		Object obj = m.get(key);
		if(obj==null) return defValue;
		String s = obj.toString();
		
		if(s.length() == 0){
			return defValue;
		}else{
			return Float.parseFloat(s);
		}
	}
	public float getFloat(String key){
		return getFloat(key,0.0f);
	}

	/**
	 * 取双精度浮点数请求参数(缺省值0.0f).
	 */
	public double getDouble(String key, double defValue){
		if(m==null) return defValue;
		Object obj = m.get(key);
		if(obj==null) return defValue;
		String s = obj.toString();
		
		if(s.length() == 0){
			return defValue;
		}else{
			return Double.parseDouble(s);
		}
	}
	public double getDouble(String key){
		return getDouble(key,0.0d);
	}	
}
