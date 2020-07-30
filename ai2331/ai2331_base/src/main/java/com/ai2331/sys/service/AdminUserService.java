package com.ai2331.sys.service;

import com.ai2331.common.entity.GridX;
import com.ai2331.common.entity.PageX;
import com.ai2331.sys.entity.AdminUser;

public interface AdminUserService {

	AdminUser saveAdminUser(AdminUser user);

	GridX<AdminUser> queryAdminUser(AdminUser user, PageX pager);

}
