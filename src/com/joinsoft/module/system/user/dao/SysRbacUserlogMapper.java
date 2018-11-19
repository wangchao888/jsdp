/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.user.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.user.entity.SysRbacUserlog;


/**
 * 用户登录日志映射器
 * 
 * @author 李中新
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysRbacUserlogMapper{
	List<Map<String, Object>> getSysRbacUserlog(Map<String, Object> map);
	void insertSysRbacUserlog(SysRbacUserlog entity);
	void updateSysRbacUserlog(SysRbacUserlog entity);
	void deleteSysRbacUserlog(String id);
	List<Map<String, Object>> pageSysRbacUserlog(Map<String, Object> map);
	Long countSysRbacUserlog(Map<String, Object> map);
	void batchdeleteSysRbacUserlog(List<String> ids);
}
