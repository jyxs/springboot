package com.ai2331.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_resource")
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer pid;// 父ID
	@Column(name = "priv_code")
	private String privCode;// 权限代码
	private String icon;// 图标css类名
	private String name;// 功能名称
	@Column(name = "resource_type")
	private Integer resourceType;// 分类：0 菜单；1 功能
	@Column(name = "sort_order")
	private Integer sortOrder;// 排序号
	private String url;// 访问地址
	@Column(name = "tab_name")
	private String tabName;// 存在url的菜单，显示在Tab上的名称
	@Column(name = "create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;// 创建时间
	private String remark;// 备注
	private Integer enabled;// 是否生效：0 否；1 是

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPrivCode() {
		return privCode;
	}

	public void setPrivCode(String privCode) {
		this.privCode = privCode;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
