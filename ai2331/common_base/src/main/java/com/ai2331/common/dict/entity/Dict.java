package com.ai2331.common.dict.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/*
Dict
*/
public class Dict {
	private String code; /* Group代码 */
	private String name; /* Group名称 */
	private String note; /* 说明 */
	private Integer status; /* 状态：0 无效，1 有效 */

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
