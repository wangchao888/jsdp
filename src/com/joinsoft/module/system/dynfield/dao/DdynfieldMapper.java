/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.dynfield.dao;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.dynfield.entity.Ddynfield;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface DdynfieldMapper{
	List<Map<String, Object>> getDdynfield(Map<String, Object> map);
	void insertDdynfield(Ddynfield entity);
	void updateDdynfield(Ddynfield entity);
	void deleteDdynfield(String id);
	List<Map<String, Object>> pageDdynfield(Map<String, Object> map);
	Long countDdynfield(Map<String, Object> map);
	void batchdeleteDdynfield(List<String> ids);
	
	@SuppressWarnings("rawtypes")
	List<Map> queryDdynFiemap(Map map);
	
	/*获取动态表的主键*/
	List<Ddynfield> getEntityKeyId(@Param(value="extid")String extid);

	
}
