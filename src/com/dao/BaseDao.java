/************************************************************************
 *    Copyright (C) 2007 General Electric Company. All rights reserved   *
 *            File Name:BaseDao.java                				 	 *
 *            Author Name:Tata Consultancy Services, Limited.            *
 *  Confidential and proprietary information of General Electric Company *
 ************************************************************************/
package com.dao;

/* Java imports */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.stereotype.Repository;



@Repository
public class BaseDao {

	@Autowired
	private DataSource datasource;

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private HibernateTransactionManager transactionManager;

	// Throws exception if insert fails
	public <T> void save(Class<T> entity) {

		sessionFactory.getCurrentSession().save(entity);
	}

	// Throws exception if row does not exists
	public <T> void update(Class<T> entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	// Does not throw exception, does one or the other
	public <T> void saveOrUpdate(Class<T> entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	// Throws exception (HibernateException) if the row does not exists
	public <T> void delete(Class<T> entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	/*
	 * Force the Session to flush. Must be called at the end of a unit of work,
	 * before committing the transaction and closing the session
	 * (Transaction.commit() calls this method
	 */
	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Class<T> type) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (query.list());
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Object value, Class<T> type) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, value);

		return (query.list());
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findByNamedNativeQuery(String namedQueryName, String paramName, Object value) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery(namedQueryName);

		query.setParameter(paramName, value);

		return (query.list());

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(String sql) {
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setCacheable(true);
		return (query.list());
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(Class<T> type) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(type);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> type, Serializable id) {
		return ((T) sessionFactory.getCurrentSession().get(type, id));
	}

	/**
	 * Load an object with a particular type knowing one of the unique
	 * attributes.
	 * 
	 * @param <T>
	 * @param type
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T loadByKey(Class<T> type, String attributeName, Long attributeValue) {

		String hql = "select " + type.getSimpleName() + " from " + type.getName() + " " + type.getSimpleName() + " where " + type.getSimpleName() + "." + attributeName + " = ?";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, attributeValue);

		return ((T) query.uniqueResult());
	}

	/**
	 * Load an object with a particular type knowing one of the unique
	 * attributes.
	 * 
	 * @param <T>
	 * @param type
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T loadByKey(Class<T> type, String attributeName, String attributeValue) {

		String hql = "select " + type.getSimpleName() + " from " + type.getName() + " " + type.getSimpleName() + " where " + type.getSimpleName() + "." + attributeName + " = ?";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, attributeValue);

		return (T) query.uniqueResult();
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return the hibernateTemplate
	 */
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * @param hibernateTemplate
	 *            the hibernateTemplate to set
	 */
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * transactionManager Getters
	 * 
	 * @return Returns the transactionManager
	 */
	public HibernateTransactionManager getTransactionManager() {
		return transactionManager;
	}

	/**
	 * transactionManager Setters
	 * 
	 * @param transactionManager
	 *            the transactionManager to set
	 */
	public void setTransactionManager(HibernateTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * dataSource Getters
	 * 
	 * @return Returns the dataSource
	 */
	public DataSource getDataSource() {
		return transactionManager.getDataSource();
	}

	/**
	 * connection Getters
	 * 
	 * @return Returns the connection
	 */
	public Connection getConnection() throws SQLException {
		return (Connection) getDataSource().getConnection();
	}

	/**
	 * 
	 * @author Miao Sep 4, 2012 7:21:23 PM
	 * 
	 *         search by Sql with one parameter
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryListBySql(String strsql, Object value) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(strsql);
		query.setParameter(0, value);
		return query.list();
	}

	/**
	 * 
	 * @author Miao Sep 4, 2012 7:21:28 PM
	 * 
	 *         search by Sql with many parameters
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryListBySql(String strsql, Object... values) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(strsql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.list();
	}

	/**
	 * 
	 * @author Miao Sep 4, 2012 7:21:32 PM
	 * 
	 *         search by Hql with many parameters
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Object... values) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}

		return (query.list());
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Object[] values, Class<T> type) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}

		return (query.list());
	}

	/**
	 * 
	 * @author Miao Sep 4, 2012 7:21:36 PM
	 * 
	 *         execute sql update
	 */
	public int executeSql(String sql, Object... values) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		try {
			return query.executeUpdate();
		} catch (SQLGrammarException sqlException) {
			sqlException.printStackTrace();
			return 0;
		} catch (Exception exception) {
			exception.printStackTrace();
			return 0;
		}

	}

	public int executeSqlNew(String sql, Object... values) {
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.executeUpdate();
	}

	// result transformer
	@SuppressWarnings( { "rawtypes", "unused", "unchecked" })
	public List loadTransformedObjectWithNativeSQL(String sql, Map map, Object[] parameters, Class className) {
		List result = null;
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);

		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, org.hibernate.type.Type> entry = (Map.Entry<String, org.hibernate.type.Type>) it.next();
			sqlQuery.addScalar(entry.getKey(), entry.getValue());
		}

		for (int i = 0; i < parameters.length; ++i) {
			sqlQuery.setParameter(i, parameters[i]);
		}
		sqlQuery.setResultTransformer(Transformers.aliasToBean(className));

		return sqlQuery.list();
	}

	// result transformer
	@SuppressWarnings( { "rawtypes", "unused", "unchecked" })
	public List loadTransformedObjectWithNativeSQL(String sql, Map map, Object[] parameters, Class className, int fetchSize) {
		List result = null;
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);

		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, org.hibernate.type.Type> entry = (Map.Entry<String, org.hibernate.type.Type>) it.next();
			sqlQuery.addScalar(entry.getKey(), entry.getValue());
		}

		for (int i = 0; i < parameters.length; ++i) {

			sqlQuery.setParameter(i, parameters[i]);

		}
		sqlQuery.setFetchSize(fetchSize);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(className));

		return sqlQuery.list();
	}


	@SuppressWarnings( { "unchecked" })
	public List loadTransformedObjectWithNativeSQL(String sql, Map map, Class className) {

		List result = null;

		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);

		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, org.hibernate.type.Type> entry = (Map.Entry<String, org.hibernate.type.Type>) it.next();
			sqlQuery.addScalar(entry.getKey(), entry.getValue());
		}

		sqlQuery.setResultTransformer(Transformers.aliasToBean(className));

		result = sqlQuery.list();
		return result;
	}

	/**
	 * @return the datasource
	 */
	public DataSource getDatasource() {
		return datasource;
	}

	/**
	 * @param datasource
	 *            the datasource to set
	 */
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

}
