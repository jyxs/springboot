package com.ai2331.dao.mapper;

import org.springframework.stereotype.Repository;

import com.ai2331.entity.T1;

@Repository
public interface T1Mapper{
	T1 findById(Integer id);
}
