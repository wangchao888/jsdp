/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/

		
package com.joinsoft.module.system.userrole.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.userrole.entity.SysUserRole;



/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysUserRoleMapper{
	List<Map<String, Object>> getSysUserRole(Map<String, Object> map);
	void insertSysUserRole(SysUserRole entity);
	void updateSysUserRole(SysUserRole entity);
	void deleteSysUserRole(Map<String,Object> map);
	void deleteSysUserOrRole(Map<String, Object> map);//按照用户或角色删除
	List<Map<String, Object>> pageSysUserRole(Map<String, Object> map);
	Long countSysUserRole(Map<String, Object> map);
	void batchdeleteSysUserRole(List<String> ids);
	/**
	 * 根据用户批量删除数据
	 * @param ids
	 *
	 * @author scy
	 * @since 2018年11月16日上午10:20:16
	 */
	void batchDeleteUser(List<String> ids);
	/**
	 * 按照用户和角色删除
	 * @param map
	 *
	 * @author scy
	 * @since 2018年11月16日下午3:51:30
	 */
	void batchDeleteUserAndRole(Map<String, Object> map);
	
}
