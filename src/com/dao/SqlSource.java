/************************************************************************
 *    Copyright (C) 2007 General Electric Company. All rights reserved   *
 *            File Name: SqlSource.java                              *
 *            Author Name:Tata Consultancy Services, Limited.            *
 *  Confidential and proprietary information of General Electric Company *
 ************************************************************************/
package com.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlSource {
	String[] value() default "/sql.xml";
}
