package com.javatrainingcamp.week07.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源选择--自定义注解
 * @ClassName DataSource
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyDataSource {

	// 默认主表
	DataSourceType value() default DataSourceType.Master;

}
