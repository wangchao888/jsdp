/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.dyn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joinsoft.core.bean.SelectBean;
import com.joinsoft.core.persistence.annotation.MyBatisDao;


/**
 * {功能简述}
 * 
 * @author as
 * @since 2014年8月22日
 */

@MyBatisDao
public interface BasedynMapper{
	List<Map<String, Object>> getListBySql(@Param(value="sql") String sql);
	void delBySql(@Param(value="sql") String sql);
	void insertBySql(@Param(value="sql") String sql);
	void updateBySql(@Param(value="sql") String sql);
	Map<String,Object> selectOneBySql(@Param(value="sql")String sql);
	//从字典表中查询下拉框
	List<SelectBean> getSelectBySql(@Param(value="str") String str);
	//根据sql查询String
	String getStrBySql(@Param(value="sql") String sql);
	//获取主管单位信息
	List<Map<String, Object>> getCompeinfoByZonecode(Map<String, Object> map);
	//传入sql返回int 
	Integer getInt(@Param(value="sql")String sql);
	Double getDouble(@Param(value="sql")String sql);
	String getFsupportinfo(Map map);
}
