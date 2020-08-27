package com.ai2331.sys.service;

import com.ai2331.common.entity.GridX;
import com.ai2331.common.entity.PageX;
import com.ai2331.corp.entity.CorpStaff;

public interface CorpStaffService {

	CorpStaff saveAdminUser(CorpStaff user);

	GridX<CorpStaff> queryAdminUser(CorpStaff user, PageX pager);

}
