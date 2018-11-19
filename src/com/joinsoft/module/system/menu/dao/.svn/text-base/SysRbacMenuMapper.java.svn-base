/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.menu.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.bean.ZTree;
import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.menu.entity.SysRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacMenu;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysRbacMenuMapper{
	List<Map<String, Object>> getSysRbacMenu(Map<String, Object> map);
	Map<String, Object> querySysRbacMenu(Map<String, Object> map);
	String queryMaxPidMenuno();
	void insertSysRbacMenu(SysRbacMenu entity);
	void updateSysRbacMenu(SysRbacMenu entity);
	void updateSysMenuDelFlag(SysRbacMenu entity);
	void deleteSysRbacMenu(String id);
	List<Map<String, Object>> pageSysRbacMenu(Map<String, Object> map);
	Long countSysRbacMenu(Map<String, Object> map);
	Long queryMaxMenuno(Map<String,Object> map); 
	void batchdeleteSysRbacMenu(List<String> ids);
	List<ZTree> getMenuTree(Map<String, Object> map);
	List<CacheRbacMenu> queryMenuTreeCorp(Map<String,Object> map);
	
	/**
	 * 根据角色查询菜单
	 * @param map 
	 *   注：isroot不为空，赋值为字符"1"即可，则代表查询顶级菜单
	 * @return
	 * @author LZX 2017-05-25 1452
	 * @author LZX 2017-05-26 1404 查询全部菜单，存入缓存
	 */
	List<CacheRbacMenu> getSysRbacMenuByRole(Map<String, Object> map);
}
