/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2017
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.sysinfo.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.sysinfo.entity.SysInfo;
/**
 * 
 * 系统信息
 * @author scy
 * @since  2018年3月27日
 */
@MyBatisDao
public interface SysInfoMapper{
	List<Map<String, Object>> getSysInfo(Map<String, Object> map);
	void insertSysInfo(SysInfo entity);
	void updateSysInfo(SysInfo entity);
	void deleteSysInfo(String id);
	List<Map<String, Object>> pageSysInfo(Map<String, Object> map);
	Long countSysInfo(Map<String, Object> map);
	void batchdeleteSysInfo(List<String> ids);
}
