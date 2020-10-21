package com.ai2331.ds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.ai2331.ds.DataSourceHolder.DataSourceKey;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface DataSource {
	DataSourceKey value() default DataSourceKey.MASTER;
}
