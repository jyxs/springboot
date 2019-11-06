package com.ai2331.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ai2331.entity.T1;

@Repository
public interface T1DAO extends JpaRepository<T1, Integer>{

}
