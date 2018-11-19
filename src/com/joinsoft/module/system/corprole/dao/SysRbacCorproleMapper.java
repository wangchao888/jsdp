/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.corprole.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.corprole.entity.SysRbacCorprole;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysRbacCorproleMapper{
	List<Map<String, Object>> getSysRbacCorprole(Map<String, Object> map);
	void insertSysRbacCorprole(SysRbacCorprole entity);
	void updateSysRbacCorprole(SysRbacCorprole entity);
	void deleteSysRbacCorprole(Map<String,Object> map);
	void updateDelflag(Map<String,Object> map);
	List<Map<String, Object>> pageSysRbacCorprole(Map<String, Object> map);
	Long countSysRbacCorprole(Map<String, Object> map);
	void batchdeleteSysRbacCorprole(List<String> ids);
}
