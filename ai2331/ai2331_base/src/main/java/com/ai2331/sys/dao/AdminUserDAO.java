package com.ai2331.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ai2331.sys.entity.AdminUser;

@Repository
public interface AdminUserDAO extends JpaRepository<AdminUser, Integer>,JpaSpecificationExecutor<AdminUser>{

	AdminUser findOneByUsername(String userName);

}
