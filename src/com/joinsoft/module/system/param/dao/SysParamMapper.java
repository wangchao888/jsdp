/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.param.dao;
import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.param.entity.SysParam;
import com.joinsoft.platform.global.entity.CacheParam;



/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysParamMapper{
	List<Map<String, Object>> getSysParam(Map<String, Object> map);
	void insertSysParam(SysParam entity);
	void updateSysParam(SysParam entity);
	void deleteSysParam(String id);
	List<Map<String, Object>> pageSysParam(Map<String, Object> map);
	Long countSysParam(Map<String, Object> map);
	void batchdeleteSysParam(List<String> ids);
	//缓存专用，不要随意修改 @note by LZX
	List<CacheParam> queryListByCache(Map<String,Object> map);
}
