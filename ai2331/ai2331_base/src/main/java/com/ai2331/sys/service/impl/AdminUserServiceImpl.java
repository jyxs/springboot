package com.ai2331.sys.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai2331.AppConstants;
import com.ai2331.common.entity.GridX;
import com.ai2331.common.entity.PageX;
import com.ai2331.mybatis.sys.dao.AdminUserDAO;
import com.ai2331.sys.entity.AdminUser;
import com.ai2331.sys.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDAO dao;

	@Override
	public GridX<AdminUser> queryAdminUser(AdminUser user, PageX pager) {
		if (StringUtils.isBlank(pager.getFiled())) {
			pager.setFiled("create_time");
		}
		if (StringUtils.isBlank(pager.getOrder())) {
			pager.setOrder("DESC");
		}
		return new GridX<AdminUser>(pager, dao.findAll(user, pager));
	}

	@Override
	public AdminUser saveAdminUser(AdminUser user) {
		initInsertAdminUser(user);
		dao.insert(user);
		return user;
	}

	private void initInsertAdminUser(AdminUser user) {
		user.setCreateTime(new Date());
		user.setEnabled(AppConstants.YES);
		user.setIsSuper(AppConstants.NO);
	}
}
