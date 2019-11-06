package com.ai2331.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ai2331.common.entity.PageX;
import com.ai2331.dao.T1DAO;
import com.ai2331.entity.T1;

@Service
public class T1ServiceImpl implements T1Service {

	@Autowired
	private T1DAO dao;

	@Override
	public Page<T1> list(PageX pr) {
		Sort.by(Direction.DESC, "createTime");
		Pageable pager = PageRequest.of(pr.getPageNumber(), pr.getPageSize(), Sort.by(Direction.DESC, "createTime"));
		return dao.findAll(pager);
	}

	@Override
	public T1 insert(T1 t) {
		return dao.save(t);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
