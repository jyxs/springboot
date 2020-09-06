package com.ai2331.common.dict.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/*
Dict的值-名称对
*/
public class DictItem {
	private Integer id; /*  */
	private String groupCode; /* Group代码 */
	private String value; /* 值 */
	private String name; /* 名称 */
	private String ext1; /* 扩展1 */
	private String ext2; /* 扩展2 */
	private String ext3; /* 扩展3 */
	private Integer sortOrder; /* 排序（小靠前） */

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
}
