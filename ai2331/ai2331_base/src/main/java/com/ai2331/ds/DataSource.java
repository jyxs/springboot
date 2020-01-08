package com.ai2331.ds;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.ai2331.ds.DataSourceHolder.DataSourceKey;

@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface DataSource {
	DataSourceKey value() default DataSourceKey.MASTER;
}
