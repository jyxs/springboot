package com.ai2331.corp.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai2331.AppConstants;
import com.ai2331.common.entity.GridX;
import com.ai2331.common.entity.PageX;
import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.corp.service.CorpStaffService;
import com.ai2331.mybatis.corp.dao.CorpStaffDAO;

@Service
public class CorpStaffServiceImpl implements CorpStaffService {

	@Autowired
	private CorpStaffDAO dao;

	@Override
	public GridX<CorpStaff> queryAdminUser(CorpStaff user, PageX pager) {
		if (StringUtils.isBlank(pager.getFiled())) {
			pager.setFiled("create_time");
		}
		if (StringUtils.isBlank(pager.getOrder())) {
			pager.setOrder("DESC");
		}
		return new GridX<CorpStaff>(pager, dao.findAll(user, pager));
	}

	@Override
	public CorpStaff saveAdminUser(CorpStaff user) {
		initInsertAdminUser(user);
		dao.insert(user);
		return user;
	}

	private void initInsertAdminUser(CorpStaff user) {
		user.setCreateTime(new Date());
		user.setEnabled(AppConstants.YES);
		user.setIsSuper(AppConstants.NO);
	}
}
