/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.role.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.role.entity.SysRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacRole;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysRbacRoleMapper{
	//缓存专用，不要随意修改 @note by LZX
	List<CacheRbacRole> queryListByCache(Map<String, Object> map);
	
	List<Map<String, Object>> getSysRbacRole(Map<String, Object> map);
	List<Map<String, Object>> getEntityByListByRoleids(List<String> roleids);
	List<Map<String, Object>> getSysRbacRoleusery(Map<String, Object> map);
	List<Map<String, Object>> getSysRbacRoleusern(Map<String, Object> map);
	void insertSysRbacRole(SysRbacRole entity);
	void updateSysRbacRole(SysRbacRole entity);
	void deleteSysRbacRole(Map<String,Object> map);
	List<Map<String, Object>> pageSysRbacRole(Map<String, Object> map);
	Long countSysRbacRole(Map<String, Object> map);
	void batchdeleteSysRbacRole(List<String> ids);
	/**
	 * 联想数据
	 * @param map
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月2日上午10:24:54
	 */
	List<Map<String, Object>> getAutocomplete(Map<String, Object> map);
}
