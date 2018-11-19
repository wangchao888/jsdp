/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： PaginationInterceptor.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.persistence.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.utils.ReflectionUtils;

/**
 * mybatis分页插件。
 * 
 * @author wkd
 * @since 2014年8月14日
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

	private static String dialect = ""; // 数据库方言
	private static String pageSqlId = ""; // mapper.xml中需要拦截的ID(正则匹配)

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object intercept(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectionUtils.getFieldValue(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectionUtils.getFieldValue(delegate, "mappedStatement");
			if (mappedStatement.getId().matches(pageSqlId)) { // 拦截需要分页的SQL
				System.out.println("mappedStatement.getId():" + mappedStatement.getId());
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();// 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				if (parameterObject == null) {
					throw new NullPointerException("parameterObject尚未实例化！");
				} else {
					Connection connection = (Connection) ivk.getArgs()[0];
					String sql = boundSql.getSql();
					String countSql = generateCountSql(sql); // 记录统计
					PreparedStatement countStmt = connection.prepareStatement(countSql);
					BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
					setParameters(countStmt, mappedStatement, countBS, parameterObject);
					ResultSet rs = countStmt.executeQuery();
					int count = 0;
					if (rs.next()) {
						count = rs.getInt(1);
					}
					rs.close();
					countStmt.close();
					Page page = null;
					if (parameterObject instanceof Page) { // 参数就是Page实体
						page = (Page) parameterObject;
						page.setEntityOrField(true);
						page.setTotalResult(count);
					} else { // 参数为Map
						Map<String, Object> map = (Map) parameterObject;
						page = (Page) map.get("page");
						if (page == null) {
							page = new Page();
							page.setEntityOrField(false);
							map.put("page", page);
						}
						page.setTotalResult(count);
					}
					String pageSql = generatePageSql(sql, page);
					ReflectionUtils.setFieldValue(boundSql, "sql", pageSql); // 将分页sql语句反射回BoundSql.
				}
			}
		}
		return ivk.proceed();
	}

	/**
	 * 
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter。
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 *
	 * @author wkd
	 * @since 2014年8月14日下午2:43:34
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	/**
	 * 
	 * 根据数据库方言，生成特定的计算总记录数sql。
	 * @param sql
	 * @return
	 *
	 * @author wkd
	 * @since 2014年8月14日下午2:43:58
	 */
	private String generateCountSql(String sql) {
		if (!StringUtils.isEmpty(dialect)) {
			StringBuffer pageSql = new StringBuffer();
			if ("oracle".equals(dialect)) {
				pageSql.append("select count(0) from (");
				pageSql.append(sql);
				pageSql.append(") ");
			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	/**
	 * 
	 * 根据数据库方言，生成特定的分页sql。
	 * @param sql
	 * @param page
	 * @return
	 *
	 * @author wkd
	 * @since 2014年8月14日下午2:44:20
	 */
	private String generatePageSql(String sql, Page page) {
		//add by zfx 201411029
		int showCount = page.getShowCount();
		if(showCount <=0){
			page.setShowCount(10);
		}
		if (!StringUtils.isEmpty(dialect)) {
			StringBuffer pageSql = new StringBuffer();
			if ("oracle".equals(dialect)) {
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(sql);
				pageSql.append(") tmp_tb where ROWNUM<=");
				pageSql.append(page.getCurrentResult() + page.getShowCount());
				pageSql.append(") where row_id>");
				pageSql.append(page.getCurrentResult());
			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (StringUtils.isEmpty(dialect)) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (StringUtils.isEmpty(pageSqlId)) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}

}
