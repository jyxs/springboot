package com.ai2331.sys.service;

import com.ai2331.common.entity.GridX;
import com.ai2331.common.entity.PageX;
import com.ai2331.common.entity.ResultX;
import com.ai2331.sys.entity.Corp;
import com.ai2331.sys.vo.CorpSearchVO;

public interface CorpService {

	GridX<Corp> query(CorpSearchVO vo, PageX pager);

	Corp getOne(String code);

	ResultX insert(Corp corp);

	ResultX update(Corp corp);

	ResultX updateStatus(String code, Integer status, String remark, Integer updator);

	ResultX updateSuit(String code, String suitCode, String suitCodeExtra, String remark, Integer updator);

	ResultX updateCheckStatus(String code, Integer checkStatus, String remark, Integer updator);

}
