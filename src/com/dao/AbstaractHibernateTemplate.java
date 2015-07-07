/**
 * Project:      IcamTechRef
 * Component:    AbstaractHibernateTemplate.java
 * Date-Written: Jun 28, 2012
 * Security:     GE-Confidential
 * Restrictions: GE PROPRIETARY INFORMATION, FOR GE USE ONLY
 *
 *     ****************************************************
 *     *  Copyright (C) 2012 General Electric Company     *
 *     *           All rights reserved                    *
 *     ****************************************************
 *
 */
package com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import oracle.jdbc.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public abstract class AbstaractHibernateTemplate extends BaseDao {

	private static Map<String, String> sqlMap;

	/** Static block, no changes required for any thread. */
	private static XPathExpression expr;
	private static DocumentBuilder docBuilder;
	static {
		expr = null;
		docBuilder = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// dbf.setValidating(true);
			docBuilder = dbf.newDocumentBuilder();

			XPathFactory xpf = XPathFactory.newInstance();
			XPath xpath = xpf.newXPath();
			expr = xpath.compile("//sql");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * getting the SQL from the xml file .
	 * 
	 * @return the sql xml file
	 */
	protected String[] getSqlXml() {
		SqlSource sqlXml = getClass().getAnnotation(SqlSource.class);
		String[] result = null;
		if (sqlXml != null) {
			result = sqlXml.value();
		}
		return result;
	}

	/**
	 * load the sql content.
	 */
	@PostConstruct
	public void loadSqls() {
		if (sqlMap == null)
			sqlMap = new HashMap<String, String>();
		String[] sqlXml = getSqlXml();
		if (sqlXml == null || sqlXml.length == 0) {
			return;
		}
		for (String location : sqlXml) {
			loadSqlFrom(location);
		}
		dumpLoadedSqls();
	}

	/**
	 * get the sql xml file from the path.
	 * 
	 * @param location
	 *            the file path
	 */
	private final void loadSqlFrom(final String location) {

		final URL xmlURL = AbstaractHibernateTemplate.class.getResource(location);
		if (xmlURL == null) {
			return;
		}
		InputStream instream = null;
		try {
			instream = xmlURL.openStream();
			Document doc = docBuilder.parse(instream);
			NodeList sqls = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			if (sqls == null || sqls.getLength() == 0) {
				return;
			}
			Element elm = null;
			String id = null;
			String sql = null;
			for (int i = 0; i < sqls.getLength(); i++) {
				elm = (Element) sqls.item(i);
				sql = elm.getTextContent();
				id = elm.getAttribute("id");
				if (! StringUtils.hasText(id) || ! StringUtils.hasText(sql)) {
					continue;
				}
				sqlMap.put(id.trim(), shrink(sql));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} finally {
			if (instream != null) {
				try {
					instream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @return return the sqls content
	 */
	protected String dumpLoadedSqls() {
		StringBuilder buf = new StringBuilder();
		Map.Entry<String, String> entry = null;
		Iterator<Map.Entry<String, String>> iter = null;
		for (iter = sqlMap.entrySet().iterator(); iter.hasNext();) {
			entry = iter.next();
			buf.append(entry.getKey());
			buf.append("\t => ");
			buf.append(trimToLine(entry.getValue()));
			buf.append("\r\n");
		}
		return buf.toString();
	}

	/**
	 * 
	 * @param sqlId
	 *            the sql id
	 * @return return the related query by the sql id
	 */
	protected String getSql(String sqlId) {
		if (! StringUtils.hasText(sqlId)) {
			throw new IllegalArgumentException("invalid sqlId: " + sqlId);
		}
		if (sqlMap == null) {
			loadSqls();
		}
		if (sqlMap != null && ! sqlMap.isEmpty()) {
			return sqlMap.get(sqlId);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param str
	 *            the query content
	 * @return return the query
	 */
	private static String trimToLine(final String str) {
		if (null == str) {
			return str;
		}
		return str.replace("\n", "").replace("\r", "").trim();
	}

	private static String shrink(String input) {
		if (input == null || input.equals("")) {
			return input;
		}
		return input.replaceAll("[\\r\\n\\s]+", " ").trim();
	}
	protected ARRAY registerOracleArray(String typeName, Connection connection,
			Object[] object) throws SQLException {
		OracleConnection oracleConnection = null;
		if (connection.isWrapperFor(OracleConnection.class)) {
			oracleConnection = connection.unwrap(OracleConnection.class);
		}
		String inTypeName = "TBCAM." + typeName.toUpperCase();
		ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor(
				inTypeName, oracleConnection);
		return new ARRAY(arrayDescriptor, oracleConnection, object);
	}
}
