/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： DzoneCodeMapper.java
 *              (C) Copyright shandong joinsoft Corporation 2018
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.module.system.zoneCode.dao;

import java.util.List;
import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.zoneCode.entity.DzoneCode;

@MyBatisDao
public interface DzoneCodeMapper {
	List<Map<String, Object>> pageZoneCode(Map<String, Object> map);
	void insertZoneCode(DzoneCode entity);
	List<Map<String, Object>> getZoneCode(Map<String, Object> map);
	void updateZoneCode(DzoneCode entity);
	
	List<DzoneCode> queryListDzoneCode(Map<String, Object> map);
}

