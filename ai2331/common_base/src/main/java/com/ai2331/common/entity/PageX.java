package com.ai2331.common.entity;

public class PageX {
	private Integer pageSize = 10;
	private Integer pageNumber = 1;
	private Long totalNumber = 0l;
	
	private String order;
	private String filed;
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public int getOffSite() {
		return (this.pageNumber - 1) * this.pageSize;
	}

	public long getTotalPage() {
		return (this.totalNumber + this.pageSize - 1) / this.pageSize;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}
	
	
}
