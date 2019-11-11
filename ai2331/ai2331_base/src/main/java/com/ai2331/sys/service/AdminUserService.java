package com.ai2331.sys.service;

import org.springframework.data.domain.Page;

import com.ai2331.common.entity.PageX;
import com.ai2331.sys.entity.AdminUser;

public interface AdminUserService {

	AdminUser saveAdminUser(AdminUser user);

	Page<AdminUser> queryAdminUser(AdminUser user, PageX pager);

}
