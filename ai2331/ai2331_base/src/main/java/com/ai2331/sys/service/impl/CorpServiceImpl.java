package com.ai2331.sys.service.impl;

import java.time.Instant;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai2331.AppConstants;
import com.ai2331.common.entity.GridX;
import com.ai2331.common.entity.PageX;
import com.ai2331.common.entity.ResultX;
import com.ai2331.common.entity.ResultX.ResultXCode;
import com.ai2331.sys.dao.CorpDAO;
import com.ai2331.sys.entity.Corp;
import com.ai2331.sys.service.CorpService;
import com.ai2331.sys.vo.CorpSearchVO;

@Service
public class CorpServiceImpl implements CorpService {

	@Autowired
	private CorpDAO dao;

	@Override
	public GridX<Corp> query(CorpSearchVO vo, PageX pager) {
		long count = dao.findCorpsCount(vo);
		GridX<Corp> corps = new GridX<Corp>(pager);
		if (count > 0) {
			pager.setTotalNumber(count);
			corps.setRows(dao.findCorps(vo, pager));
		}
		return corps;
	}

	@Override
	public Corp getOne(String code) {
		return dao.findOne(code);
	}

	@Override
	public ResultX insert(Corp corp) {
		if (StringUtils.isAllBlank(corp.getPcode())) {
			corp.setPcode("0");
		}
		corp.setStatus(AppConstants.NO);
		corp.setCheckStatus(Corp.CHECK_STATUS_DRAFT_1);
		corp.setCreateTime(Date.from(Instant.now()));
		corp.setUpdateTime(corp.getCreateTime());
		int result = dao.insert(corp);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS);
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	@Override
	public ResultX update(Corp corp) {
		if (StringUtils.isBlank(corp.getCode())) {
			return new ResultX(ResultXCode.ERROR, "公司code无效");
		}
		corp.setUpdateTime(Date.from(Instant.now()));
		int result = dao.update(corp);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS);
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	@Override
	public ResultX updateStatus(String code, Integer status, String remark, Integer updator) {
		if (StringUtils.isBlank(code)) {
			return new ResultX(ResultXCode.ERROR, "公司code无效");
		}
		if (null == status) {
			status = AppConstants.NO;
		}
		int result = dao.updateStatus(code, status, remark, Date.from(Instant.now()), updator);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS);
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	@Override
	public ResultX updateSuit(String code, String suitCode, String suitCodeExtra, String remark, Integer updator) {
		if (StringUtils.isBlank(code)) {
			return new ResultX(ResultXCode.ERROR, "公司code无效");
		}
		if (StringUtils.isBlank(suitCode)) {
			return new ResultX(ResultXCode.ERROR, "未选择公司套装");
		}
		int result = dao.updateSuit(code, suitCode, suitCodeExtra, remark, Date.from(Instant.now()), updator);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS);
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

	@Override
	public ResultX updateCheckStatus(String code, Integer checkStatus, String remark, Integer updator) {
		if (StringUtils.isBlank(code)) {
			return new ResultX(ResultXCode.ERROR, "公司code无效");
		}
		if (null == checkStatus) {
			checkStatus = Corp.CHECK_STATUS_FAIL_9;
		}
		int result = dao.updateCheckStatus(code, checkStatus, Date.from(Instant.now()), updator);
		if (result > 0) {
			return new ResultX(ResultXCode.SUCCESS);
		} else {
			return new ResultX(ResultXCode.FAIL, "操作失败");
		}
	}

}
