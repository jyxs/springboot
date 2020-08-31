package com.ai2331.corp.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Role {

	private String code;
	private String name;// 角色名
	private String corpCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;// 创建时间
	private Integer createStaff;
	private String remark;// 备注
	private Integer enabled;// 是否生效：0 否；1 是
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
	public String getCorpCode() {
		return corpCode;
	}
	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateStaff() {
		return createStaff;
	}
	public void setCreateStaff(Integer createStaff) {
		this.createStaff = createStaff;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}
