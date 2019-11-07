package com.ai2331.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Aspect
@Configuration
public class TransactionConfig {
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.ai2331.service.*.*(..))";

	@Autowired
	private PlatformTransactionManager manager;

	@Bean
	public TransactionInterceptor txAdvice() {
		DefaultTransactionAttribute required = new DefaultTransactionAttribute();
		required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		DefaultTransactionAttribute readonly = new DefaultTransactionAttribute();
		readonly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		readonly.setReadOnly(true);

		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
		source.addTransactionalMethod("add*", required);
		source.addTransactionalMethod("save*", required);
		source.addTransactionalMethod("insert*", required);
		source.addTransactionalMethod("update*", required);
		source.addTransactionalMethod("edit*", required);
		source.addTransactionalMethod("do*", required);
		source.addTransactionalMethod("del*", required);
		source.addTransactionalMethod("remove*", required);

		source.addTransactionalMethod("get*", readonly);
		source.addTransactionalMethod("list*", readonly);
		source.addTransactionalMethod("query*", readonly);
		source.addTransactionalMethod("search*", readonly);
		source.addTransactionalMethod("count*", readonly);
		source.addTransactionalMethod("find*", readonly);
		return new TransactionInterceptor(manager, source);
	}

	@Bean
	public Advisor txAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}
}
