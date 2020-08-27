package com.ai2331.sys.entity;

import java.util.Date;

public class Corp {
	private String code;// 公司编码（唯一）
	private String pcode;// 总公司编码，顶级为0
	private String name;// 公司名称
	private String fullname;// 公司全称
	private String taxno;// 纳税号
	private String busiRange;// 业务范围
	private String phone;// 公司电话
	private String address;// 公司地址
	private String responsible;// 责任人
	private String responsiblePhone;// 责任人电话
	private Integer status;// 0失效，1可用
	private String checkStatus;// 审核状态1申请，2通过，9不通过
	private Integer checker;// 审核人
	private String checkReason;// 审核原因
	private String suitCode;// 公司套装code，多个以英文逗号分隔
	private String suitCodeExtra;// 增值套装，多个以英文逗号分隔
	private Date createTime;// 创建时间
	private Integer creator;// 创建人
	private Date updateTime;// 更新人
	private Integer updator;// 更新时间

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getTaxno() {
		return taxno;
	}

	public void setTaxno(String taxno) {
		this.taxno = taxno;
	}

	public String getBusiRange() {
		return busiRange;
	}

	public void setBusiRange(String busiRange) {
		this.busiRange = busiRange;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getResponsiblePhone() {
		return responsiblePhone;
	}

	public void setResponsiblePhone(String responsiblePhone) {
		this.responsiblePhone = responsiblePhone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getChecker() {
		return checker;
	}

	public void setChecker(Integer checker) {
		this.checker = checker;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public String getSuitCode() {
		return suitCode;
	}

	public void setSuitCode(String suitCode) {
		this.suitCode = suitCode;
	}

	public String getSuitCodeExtra() {
		return suitCodeExtra;
	}

	public void setSuitCodeExtra(String suitCodeExtra) {
		this.suitCodeExtra = suitCodeExtra;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

}
