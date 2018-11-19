/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： Dialect.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： Dialect.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.persistence.dialect;

/**
 * 类似hibernate的Dialect。
 * @author wkd
 * @since  2014年8月14日
 */
public interface Dialect {

	/**
	 * 
	 * 是否支持当前的分页查询方式。
	 * <p>数据库本身是否支持分页当前的分页查询方式,如果数据库不支持的话，则不进行数据库分页</p>
	 * @return
	 *
	 * @author wkd
	 * @since 2014年8月14日下午1:15:13
	 */
	public boolean supportsLimit();
	
	/**
	 * 
	 * 将sql转换为分页SQL，分别调用分页sql。
	 * @param sql      SQL语句
	 * @param offset   开始条数
	 * @param limit    每页显示多少纪录条数
	 * @return         分页查询的sql
	 *
	 * @author  wkd
	 * @since 2014年8月14日下午1:16:51
	 */
	public String getLimitString(String sql, int offset, int limit);
}

