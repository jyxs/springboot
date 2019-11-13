package com.ai2331.common.entity;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResultX {
	private String code;
	private String message;
	private Object datas;

	private static String SUCCESS = "成功";

	public ResultX() {
		code = ResultXCode.SUCCESS.getCode();
		message = SUCCESS;
	}

	public ResultX(ResultXCode code) {
		this.code = code.getCode();
		this.message = "";
	}

	public ResultX(ResultXCode code, String message) {
		this.code = code.getCode();
		this.message = message;
	}

	public ResultX(ResultXCode code, String message, Object datas) {
		this.code = code.getCode();
		this.message = message;
		this.datas = datas;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonInclude(Include.NON_NULL)
	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}

	public int isDataEmpty() {
		return null == datas || ObjectUtils.isEmpty(datas) ? 1 : 0;
	}

	public enum ResultXCode {
		SUCCESS("1"), FAIL("0"), ERROR("500"), UNAUTHORIZED("403"), NOTFOUND("400");
		private String code;

		ResultXCode(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

	}
}
