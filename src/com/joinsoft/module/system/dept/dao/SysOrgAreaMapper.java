/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.dept.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.dept.entity.SysOrgArea;
import com.joinsoft.platform.global.entity.CacheOrgArea;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysOrgAreaMapper{
	List<Map<String, Object>> getSysOrgArea(Map<String, Object> map);
	void insertSysOrgArea(SysOrgArea entity);
	void updateSysOrgArea(SysOrgArea entity);
	void deleteSysOrgArea(Map<String,Object> map);
	List<Map<String, Object>> pageSysOrgArea(Map<String, Object> map);
	Long countSysOrgArea(Map<String, Object> map);
	void batchdeleteSysOrgArea(List<String> ids);
	//缓存专用
	List<CacheOrgArea> queryListByCache(Map<String,Object> map);
}
