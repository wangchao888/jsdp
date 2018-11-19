/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.dictype.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.dictype.entity.SysDicType;
import com.joinsoft.platform.global.entity.CacheDicType;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysDicTypeMapper{
	List<Map<String, Object>> getSysDicType(Map<String, Object> map);
	void insertSysDicType(SysDicType entity);
	void updateSysDicType(SysDicType entity);
	void deleteSysDicType(String id);
	List<Map<String, Object>> pageSysDicType(Map<String, Object> map);
	Long countSysDicType(Map<String, Object> map);
	void batchdeleteSysDicType(List<String> ids);
	
	//缓存专用，不要随意修改 @note by LZX
	List<CacheDicType> queryListByCache(Map<String, Object> map);
}
