package com.dao;

import java.util.List;
import java.util.Map;

public class TestDao extends AbstaractHibernateTemplate {
	public List getList(String sql,Map map,Class clazz){
		return loadTransformedObjectWithNativeSQL(sql, map, clazz);
	}

}
