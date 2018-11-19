/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.dictcontent.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.bean.ZTree;
import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.dictcontent.entity.SysDictContent;
import com.joinsoft.platform.global.entity.CacheDictContent;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@MyBatisDao
public interface SysDictContentMapper{
	//缓存专用，不要随意修改 @note by LZX
	List<CacheDictContent> queryListByCache(Map<String, Object> map);
	
	List<Map<String, Object>> getSysDictContent(Map<String, Object> map);
	void insertSysDictContent(SysDictContent entity);
	void updateSysDictContent(SysDictContent entity);
	void deleteSysDictContent(String id);
	List<Map<String, Object>> pageSysDictContent(Map<String, Object> map);
	Long countSysDictContent(Map<String, Object> map);
	void batchdeleteSysDictContent(List<String> ids);
	/**
	 * 行政区域树
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param map
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月23日下午3:26:32
	 */
	List<ZTree>getAreaTree(Map<String, Object> map);
}
