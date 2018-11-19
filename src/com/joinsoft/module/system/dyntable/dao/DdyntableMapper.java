/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.dyntable.dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.dyntable.entity.Ddyntable;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface DdyntableMapper{
	List<Map<String, Object>> getDdyntable(Map<String, Object> map);
	void insertDdyntable(Ddyntable entity);
	void updateDdyntable(Ddyntable entity);
	void deleteDdyntable(String id);
	List<Map<String, Object>> pageDdyntable(Map<String, Object> map);
	Long countDdyntable(Map<String, Object> map);
	void batchdeleteDdyntable(List<String> ids);
	Ddyntable queryById(@Param(value="id")String id);
}
