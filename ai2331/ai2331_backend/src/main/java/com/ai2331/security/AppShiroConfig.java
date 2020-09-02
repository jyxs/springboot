package com.ai2331.security;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class AppShiroConfig {
	private Logger log = LoggerFactory.getLogger(AppShiroConfig.class);

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager); // 设置安全管理器
		shiroFilterFactoryBean.setLoginUrl("/login");// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setSuccessUrl("/"); // 登录成功后要跳转的链接
		shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");// 未授权界面;
		Map<String, String> filterMap = new LinkedHashMap<String, String>();
		filterMap.put("/css/**", "anon");
		filterMap.put("/images/**", "anon");
		filterMap.put("/js/**", "anon");
		filterMap.put("/fonts/**", "anon");
		filterMap.put("/login", "anon");
		filterMap.put("/error/400", "anon");
		filterMap.put("/error/403", "anon");
		filterMap.put("/error/500", "anon");
		//swagger
		filterMap.put("/doc.html", "anon");
		filterMap.put("/doc.html#/**", "anon");
		filterMap.put("/swagger-ui/index.html", "anon");
		filterMap.put("/swagger-ui/**", "anon");
		filterMap.put("/swagger-ui/**/**", "anon");
		filterMap.put("/swagger-ui/index.html#/**", "anon");
		filterMap.put("/swagger-resources/**", "anon");
		filterMap.put("/v2/api-docs", "anon");
		filterMap.put("/v3/api-docs", "anon");
		filterMap.put("/webjars/**", "anon");
		filterMap.put("/swagger-ui.html", "anon");
		
		filterMap.put("/t1/thymleaftest", "anon");
		filterMap.put("/t2/thymleaftest", "anon");
		filterMap.put("/logout", "logout");// 配置退出 过滤器,其中的具体的退出代码Shiro已经实现
		filterMap.put("/**", "authc");// 过滤链定义，从上向下顺序执行，一般将/**放在最为下边

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm()); // 设置realm.
		// 注入缓存管理器
		securityManager.setCacheManager(ehCacheManager());
		// 注入Cookie(记住我)管理器(remenberMeManager)
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了 ）
	 * 
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);// 散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}

	@Bean
	public AppRealmConfig myShiroRealm() {
		AppRealmConfig myShiroRealm = new AppRealmConfig();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean(name = "simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");// 数据库异常处理
		mappings.setProperty("UnauthorizedException", "/error/403");
		r.setExceptionMappings(mappings); // None by default
		r.setDefaultErrorView("error"); // No default
		r.setExceptionAttribute("exception"); // Default is "exception"
		// r.setWarnLogCategory("example.MvcLogger"); // No default
		return r;
	}

	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:config/ehcache.xml");
		return cacheManager;
	}

	/**
	 * cookie管理器;
	 * 
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		byte[] cipherKey = Base64.decode("9E9uBV19JjhIVjsOA+5vqQ==");
		cookieRememberMeManager.setCipherKey(cipherKey);
		cookieRememberMeManager.setCookie(rememberMeCookie());
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>rememberMeManager");
		return cookieRememberMeManager;
	}

	public SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// 如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
		simpleCookie.setHttpOnly(true);
		// 记住我cookie生效时间,默认30天 ,单位秒
		simpleCookie.setMaxAge(7 * 24 * 60 * 60);
		return simpleCookie;
	}

	private String aesKey() throws NoSuchAlgorithmException {
		// rememberme cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度（128 256 512 位），通过以下代码可以获取
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecretKey deskey = keygen.generateKey();
		String key = Base64.encodeToString(deskey.getEncoded());
		System.out.println(key);
		return key;

	}
	
	/**
	 * shiro对thyeleaf的支持
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		AppShiroConfig c = new AppShiroConfig();
		c.aesKey();
	}

}
