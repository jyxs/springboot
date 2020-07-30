package com.ai2331.common.entity;

import java.util.List;

public class GridX<T> {

	private PageX pager;
	private List<T> rows;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public GridX(PageX pager) {
		this.pager = pager;
	}

	public GridX(PageX pager, List<T> rows) {
		this.pager = pager;
		this.rows = rows;
	}

	public PageX getPager() {
		return pager;
	}
}
