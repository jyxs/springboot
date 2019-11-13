package com.ai2331.sys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ai2331.sys.entity.Role;

@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {

	@Query(nativeQuery = true, value = "select r.* from t_admin_role ar,t_role r where ar.role_id=r.id and ar.admin_id=?1")
	List<Role> findRolesByUserId(Integer uid);
}