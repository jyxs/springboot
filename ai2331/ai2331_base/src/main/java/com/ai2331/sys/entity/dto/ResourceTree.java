package com.ai2331.sys.entity.dto;

import java.util.List;

import com.ai2331.sys.entity.Resource;

public class ResourceTree extends Resource {

	public ResourceTree(Resource resource) {
		this.setCreateTime(resource.getCreateTime());
		this.setEnabled(resource.getEnabled());
		this.setIcon(resource.getIcon());
		this.setId(resource.getId());
		this.setName(resource.getName());
		this.setPid(resource.getPid());
		this.setPrivCode(resource.getPrivCode());
		this.setRemark(resource.getRemark());
		this.setResourceType(resource.getResourceType());
		this.setSortOrder(resource.getSortOrder());
		this.setTabName(resource.getTabName());
		this.setUrl(resource.getUrl());
	}

	private List<ResourceTree> children;

	public List<ResourceTree> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceTree> children) {
		this.children = children;
	}

}
