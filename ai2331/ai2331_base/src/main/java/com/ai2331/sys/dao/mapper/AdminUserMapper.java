package com.ai2331.sys.dao.mapper;

import org.springframework.stereotype.Repository;

import com.ai2331.sys.entity.AdminUser;

@Repository
public interface AdminUserMapper{

	AdminUser findOneByUsername(String userName);

}
