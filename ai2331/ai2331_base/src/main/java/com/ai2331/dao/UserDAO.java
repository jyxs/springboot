package com.ai2331.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ai2331.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>{

	User findByUname(String username);

}
