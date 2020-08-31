package com.ai2331.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai2331.AppConstants;
import com.ai2331.corp.entity.CorpStaff;
import com.ai2331.mybatis.corp.dao.CorpResourceDAO;
import com.ai2331.mybatis.corp.dao.CorpRoleDAO;
import com.ai2331.mybatis.corp.dao.CorpStaffDAO;
import com.ai2331.mybatis.sys.dao.CorpDAO;
import com.ai2331.sys.entity.Corp;
import com.ai2331.sys.entity.Resource;
import com.alibaba.druid.support.json.JSONUtils;

public class AppRealmConfig extends AuthorizingRealm {
	private Logger loger = LoggerFactory.getLogger(AppRealmConfig.class);
	@Autowired
	private CorpStaffDAO userDAO;
	@Autowired
	private CorpRoleDAO corpRoleDAO;
	@Autowired
	private CorpResourceDAO resourceDAO;
	@Autowired
	private CorpDAO corpDAO;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		loger.debug("权限配置-->AppRealmConfig.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 如果身份认证的时候没有传入User对象，这里只能取到userName
		// 也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
		CorpStaff user = (CorpStaff) principals.getPrimaryPrincipal();

		Corp corp = corpDAO.findOne(user.getCorpCode());
		// 获取公司所属角色
		String suitCode = corp.getSuitCode();
		String suitCodeExtra = corp.getSuitCodeExtra();
		Set<String> suitCodes = new HashSet<String>();
		if (StringUtils.isNotBlank(suitCode)) {
			suitCodes.addAll(Arrays.asList(suitCode.split(",")));
		}
		if (StringUtils.isNotBlank(suitCodeExtra)) {
			suitCodes.addAll(Arrays.asList(suitCodeExtra.split(",")));
		}

		// 获取用户角色
		List<com.ai2331.corp.entity.Role> roles = corpRoleDAO.findRolesByUsername(user.getUsername(), user.getCorpCode());
		List<String> roleCodes = new ArrayList<String>();
		// 初始化角色，权限
		for (com.ai2331.corp.entity.Role role : roles) {
			authorizationInfo.addRole(role.getCode());
			roleCodes.add(role.getCode());
		}

		// 公司所具有的所有资源
		List<Resource> resources = resourceDAO.findBySuitCodes(suitCodes);
		// 获取员工所具有的资源
		List<Resource> userResources = resourceDAO.findByRoleCodesAndCorpCode(roleCodes, user.getCorpCode());
		// 取公司和员工资源交集
		resources.retainAll(userResources);

		authorizationInfo.addStringPermissions(resources.stream().filter(item -> null != item.getPrivCode()).map(Resource::getPrivCode).collect(Collectors.toSet()));
		loger.debug("角色集合：" + JSONUtils.toJSONString(authorizationInfo.getRoles()));
		loger.debug("权限集合：" + JSONUtils.toJSONString(authorizationInfo.getStringPermissions()));

		return authorizationInfo;
	}

	public static void main(String[] args) {
		List<Resource> resources = new ArrayList<Resource>();
		Resource r1 = new Resource();
		r1.setId(1);
		r1.setPrivCode("a");
		Resource r2 = new Resource();
		r2.setId(2);
		r2.setPrivCode("b");
		Resource r3 = new Resource();
		r3.setId(3);
		r3.setPrivCode("c");
		resources.add(r1);
		resources.add(r2);
		resources.add(r3);

		List<Resource> resources2 = new ArrayList<Resource>();
		Resource r11 = new Resource();
		r11.setId(1);
		r11.setPrivCode("a");
		Resource r12 = new Resource();
		r12.setId(2);
		r12.setPrivCode("b");
		resources2.add(r11);
		resources2.add(r12);

		boolean a = resources.retainAll(resources2);
		System.out.println(a);
		System.out.println(resources.size());
		resources.stream().forEach(item -> System.out.println(item.getId()));
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		loger.debug("登录认证-->AppRealmConfig.doGetAuthenticationInfo()");
		// 获取用户的输入的账号.
		String userName = (String) token.getPrincipal();
		System.out.println(token.getCredentials());
		// 通过username从数据库中查找 User对象.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		CorpStaff user = userDAO.findOneByUsername(userName);
		if (user == null) {
			return null;
		}
		if (user.getEnabled() == AppConstants.NO) {
			throw new LockedAccountException();
		}
		// 传入用户信息，对用户验证
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getUsername()), getName());
		return authenticationInfo;
	}

}
