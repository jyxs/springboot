package com.ai2331.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ai2331.sys.entity.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {

	@Query(nativeQuery = true, value = "select r.* from t_role_resource rr,t_resource r where rr.resource_id=r.id and r.enabled=1 and rr.role_id in(?1)")
	List<Role> findRolesByUserId(Integer uid);
}