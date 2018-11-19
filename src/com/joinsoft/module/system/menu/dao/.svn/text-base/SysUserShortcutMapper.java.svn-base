/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2017
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.menu.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.menu.entity.SysUserShortcut;

/**
 * 用户快捷菜单关系表
 * @author scy
 * @since  2018年10月19日
 */

@MyBatisDao
public interface SysUserShortcutMapper{
	List<Map<String, Object>> getSysUserShortcut(Map<String, Object> map);
	void insertSysUserShortcut(SysUserShortcut entity);
	void updateSysUserShortcut(SysUserShortcut entity);
	void deleteSysUserShortcut(String id);
	List<Map<String, Object>> pageSysUserShortcut(Map<String, Object> map);
	Long countSysUserShortcut(Map<String, Object> map);
	void batchdeleteSysUserShortcut(List<String> ids);
	/**
	 * 获取首页所用数据
	 * @param map
	 * @return
	 *
	 * @author scy
	 * @since 2018年10月21日下午4:28:23
	 */
	List<Map<String, Object>> getShortcutAndMenu(Map<String, Object> map);
	/**
	 * 获取最大排序号
	 * @param map
	 * @return
	 *
	 * @author scy
	 * @since 2018年10月22日下午4:51:48
	 */
	List<Map<String, Object>> getMaxOrderno(Map<String, Object> map);
	
	
}
