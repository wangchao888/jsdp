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
import com.joinsoft.module.system.user.entity.SysRbacUser;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.entity.HfmpCorpUser;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysRbacUserMapper{
	List<Map<String, Object>> getSysRbacUser(Map<String, Object> map);
	void insertSysRbacUser(SysRbacUser entity);
	void updateSysRbacUser(SysRbacUser entity);
	void updateCorpUser(HfmpCorpUser entity);
	void deleteSysRbacUser(String id);
	List<Map<String, Object>> pageSysRbacUser(Map<String, Object> map);
	Long countSysRbacUser(Map<String, Object> map);
	void batchdeleteSysRbacUser(List<String> ids);
	List<HfmpCorpUser> queryCorpUserList(Map<String,Object> map);
	String queryMaxLoginName(Map<String,Object> map);
	//查询实体信息
	SysRbacUser getEntity(Map<String, Object> map);
	//缓存专用，不要随意修改 @note by LZX
	List<CacheRbacUser> queryListByCache(Map<String,Object> map);
	/**
	 * 递归获取机构下的用户
	 * @param map
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月1日下午2:03:11
	 */
	List<Map<String, Object>> getOrgUser(Map<String, Object> map);
	/**
	 * 根据企业类型获取用户id
	 * @param map
	 * @return
	 *
	 * @author scy
	 * @since 2018年11月16日上午10:09:51
	 */
	List<String> getUserId(Map<String, Object> map);
	
}
