/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： DzoneCodeService.java
 *              (C) Copyright shandong joinsoft Corporation 2018
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.module.system.zoneCode.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.module.system.zoneCode.dao.DzoneCodeMapper;
import com.joinsoft.module.system.zoneCode.entity.DzoneCode;

@Service
public class DzoneCodeService {
	@Autowired
	private DzoneCodeMapper dzonecodeMapper;
	
	/**
	 * 得到实体列表并分页
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getEntityByPage(Map<String, Object> params) {
		return dzonecodeMapper.pageZoneCode(params);
	}
	
	/**
	 * 增加实体记录
	 * 
	 * @param entity
	 */
	public void addEntity(DzoneCode entity) {
		dzonecodeMapper.insertZoneCode(entity);
	}
	
	/**
	 * 得到单个实体
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> getEntity(Map<String, Object> params) {
		List<Map<String, Object>> list = getEntityByList(params);
		if (CollectionUtils.isNotEmpty(list))
			return list.get(0);
		return null;
	}

	/**
	 * 得到实体列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getEntityByList(Map<String, Object> params) {
		List<Map<String, Object>> list = dzonecodeMapper.getZoneCode(params);
		if (CollectionUtils.isNotEmpty(list))
			return list;
		return null;
	}
	
	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	public void updateEntity(DzoneCode entity) {
		dzonecodeMapper.updateZoneCode(entity);
	}
}

