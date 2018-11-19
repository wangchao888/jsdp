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

import com.joinsoft.core.bean.ZTree;
import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.dept.entity.SysRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacOrg;


/**
 * {功能简述}
 * 
 * @author wkd
 * @since 2014年8月15日
 */

@MyBatisDao
public interface SysRbacOrgMapper{
	List<Map<String, Object>> getSysRbacOrg(Map<String, Object> map);
	void insertSysRbacOrg(SysRbacOrg entity);
	void updateSysRbacOrg(SysRbacOrg entity);
	void deleteSysRbacOrg(String id);
	List<Map<String, Object>> pageSysRbacOrg(Map<String, Object> map);
	Long countSysRbacOrg(Map<String, Object> map);
	void batchdeleteSysRbacOrg(List<String> ids);
	List<ZTree>getOrgTree(Map<String, Object> map);
	List<ZTree>getAreaTree(Map<String, Object> map);
	
	//缓存专用，不要随意修改 @note by LZX
	List<CacheRbacOrg> queryListByCache(Map<String, Object> map);
	// 用户登录时查询用户所属机构的顶级机构
	List<CacheRbacOrg> getRootCacheOrg(Map<String, Object> map);
	/**
	 * 获取最大的机构编号
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月22日下午7:11:24
	 */
	String getMaxOrgNO(Map<String, Object> map);
}
