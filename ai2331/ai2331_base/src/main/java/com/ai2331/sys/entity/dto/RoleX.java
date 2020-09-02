package com.ai2331.sys.entity.dto;

import java.util.List;

import com.ai2331.sys.entity.Resource;
import com.ai2331.sys.entity.Role;

public class RoleX extends Role{

	List<Resource> resources;

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
}
