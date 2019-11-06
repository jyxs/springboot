package com.ai2331.service;

import org.springframework.data.domain.Page;

import com.ai2331.common.entity.PageX;
import com.ai2331.entity.T1;

public interface T1Service {
	T1 insert(T1 t);
	
	void delete(Integer id);


	Page<T1> list(PageX pr);


}
