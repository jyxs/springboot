package com.ai2331.jpa.master.sys.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ai2331.sys.entity.Resource;

public interface ResourceDAO extends JpaRepository<Resource, Integer> {

	@Query(nativeQuery = true, value = "select r.* from t_role_resource rr,t_resource r where rr.resource_id=r.id and r.enabled=1 and rr.role_id in(?1)")
	List<Resource> findByRoleIds(Set<Integer> roleIds);
}
