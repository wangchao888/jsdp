/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.actormenu.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.actormenu.entity.SysActorMenu;
import com.joinsoft.platform.global.entity.CacheActorMenu;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysActorMenuMapper{
	List<Map<String, Object>> getSysActorMenu(Map<String, Object> map);
	List<CacheActorMenu> getSysActorMenuEntity(Map<String, Object> map);
	void insertSysActorMenu(SysActorMenu entity);
	void updateSysActorMenu(SysActorMenu entity);
	void deleteSysActorMenu(String id);
	void deleteActorOrMenu(Map<String, Object> map);
	List<Map<String, Object>> pageSysActorMenu(Map<String, Object> map);
	Long countSysActorMenu(Map<String, Object> map);
	void batchdeleteSysActorMenu(List<String> ids);
	/**
	 * 删除用户直接关联菜单并且以失去角色的数据
	 * @param map
	 *
	 * @author scy
	 * @since 2018年11月16日上午10:55:53
	 */
	void deleteUserActorNotRole(Map<String, Object> map);
}
