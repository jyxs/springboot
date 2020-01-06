package com.ai2331.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ai2331.AppConstants;
import com.ai2331.common.entity.PageX;
import com.ai2331.jpa.master.sys.dao.AdminUserDAO;
import com.ai2331.sys.entity.AdminUser;
import com.ai2331.sys.service.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminUserDAO dao;

	@Override
	public Page<AdminUser> queryAdminUser(AdminUser user, PageX pager) {
		Specification<AdminUser> specification = createCondition(user);
		Pageable pageable = PageRequest.of(pager.getPageNumber(), pager.getPageSize(), Sort.by(Direction.DESC, "createTime"));
		return dao.findAll(specification, pageable);
	}

	private Specification<AdminUser> createCondition(AdminUser user) {
		return new Specification<AdminUser>() {
			private static final long serialVersionUID = -8495666732034345700L;

			@Override
			public Predicate toPredicate(Root<AdminUser> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				String tmp = user.getUsername();
				if (StringUtils.isNotEmpty(tmp)) {
					Predicate username = builder.like(root.get("username"), "%" + user.getUsername() + "%");
					predicates.add(username);
				}
				tmp = user.getMobilephone();
				if (StringUtils.isNotEmpty(tmp)) {
					Predicate mobilephone = builder.equal(root.get("mobilephone"), user.getUsername());
					predicates.add(mobilephone);
				}

				return builder.and(predicates.toArray(new Predicate[] {}));
			}
		};
	}

	@Override
	public AdminUser saveAdminUser(AdminUser user) {
		initInsertAdminUser(user);
		return dao.save(user);
	}

	private void initInsertAdminUser(AdminUser user) {
		user.setCreateTime(new Date());
		user.setEnabled(AppConstants.YES);
		user.setIsSuper(AppConstants.NO);
	}
}
