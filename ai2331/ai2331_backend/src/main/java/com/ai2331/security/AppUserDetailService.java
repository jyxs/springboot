package com.ai2331.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ai2331.dao.UserDAO;
import com.ai2331.entity.User;

@Service
public class AppUserDetailService implements UserDetailsService {

	@Autowired
	private UserDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = dao.findByUname(username);
		if (null == u) {
			throw new UsernameNotFoundException("用户不存在");
		}
		try {
			u.setPwd(new BCryptPasswordEncoder(4,SecureRandom.getInstanceStrong()).encode(u.getPwd()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("user:list"));
		authorities.add(new SimpleGrantedAuthority("user:add"));
		authorities.add(new SimpleGrantedAuthority("user:edit"));
		authorities.add(new SimpleGrantedAuthority("user:delete"));

		return new org.springframework.security.core.userdetails.User(username, u.getPwd(), authorities);
	}
}
