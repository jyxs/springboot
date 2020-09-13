package com.ai2331.common.entity;

/**
 *  统一异常类，应用层可扩展.
 */
public class ExceptionX extends RuntimeException{
	private static final long serialVersionUID = 8859354787017047396L;
	
	private Integer code;

	public ExceptionX(Integer code) {
		super();
		this.code = code;
	}

	public ExceptionX(Integer code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public ExceptionX(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public ExceptionX(Integer code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public ExceptionX(String message) {
		super(message);
	}

	public ExceptionX(String message, Throwable cause) {
		super(message, cause);
	}

	//setter & getter
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
}
