package com.ai2331.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("公司查询参数")
public class CorpSearchVO {
	@ApiModelProperty("公司名")
	private String name;
	@ApiModelProperty("公司code")
	private String code;
	@ApiModelProperty("0失效，1可用")
	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
