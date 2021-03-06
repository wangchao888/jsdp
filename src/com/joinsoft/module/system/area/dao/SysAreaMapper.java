/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.area.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.platform.global.entity.CacheArea;


/**
 * {功能简述}
 * 
 * @author LZX
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysAreaMapper{
	List<CacheArea> queryListByCache(Map<String,Object> map);
}
