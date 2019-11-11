package com.ai2331.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

import com.ai2331.common.entity.AppConstants;
import com.ai2331.sys.dao.AdminUserDAO;
import com.ai2331.sys.dao.ResourceDAO;
import com.ai2331.sys.dao.RoleDAO;
import com.ai2331.sys.entity.AdminUser;
import com.ai2331.sys.entity.Resource;
import com.ai2331.sys.entity.Role;

public class AppRealmConfig extends AuthorizingRealm {
	private Logger loger = LoggerFactory.getLogger(AppRealmConfig.class);
	@Autowired
	private AdminUserDAO userDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private ResourceDAO resourceDAO;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		loger.debug("权限配置-->AppRealmConfig.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 如果身份认证的时候没有传入User对象，这里只能取到userName
		// 也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
		AdminUser user = (AdminUser) principals.getPrimaryPrincipal();

		// 初始化角色，权限
		List<Role> roles = roleDAO.findRolesByUserId(user.getId());
		Set<Integer> roleIds = new HashSet<Integer>();
		for (Role role : roles) {
			authorizationInfo.addRole(role.getRoleCode());
			roleIds.add(role.getId());
		}
		List<Resource> resources = resourceDAO.findByRoleIds(roleIds);
		authorizationInfo
				.addStringPermissions(resources.stream().map(Resource::getPrivCode).collect(Collectors.toSet()));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		loger.debug("登录认证-->AppRealmConfig.doGetAuthenticationInfo()");
		// 获取用户的输入的账号.
		String userName = (String) token.getPrincipal();
		System.out.println(token.getCredentials());
		// 通过username从数据库中查找 User对象.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		AdminUser user = userDAO.findOneByUsername(userName);
		if (user == null) {
			return null;
		}
		if (user.getEnabled() == AppConstants.NO) {
			throw new LockedAccountException();
		}
		// 传入用户信息，对用户验证
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getUsername()), getName());
		return authenticationInfo;
	}

}
