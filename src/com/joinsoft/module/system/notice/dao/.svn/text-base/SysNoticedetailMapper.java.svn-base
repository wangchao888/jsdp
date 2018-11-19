/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2017
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.notice.dao;

import java.util.List;

import java.util.Map;

import com.joinsoft.core.persistence.annotation.MyBatisDao;
import com.joinsoft.module.system.notice.entity.SysNoticedetail;
/**
 * 通知用户详情
 * %类功能的概括%。
 * @author scy
 * @since  2018年3月30日
 */
@MyBatisDao
public interface SysNoticedetailMapper{
	List<Map<String, Object>> getSysNoticedetail(Map<String, Object> map);
	void insertSysNoticedetail(SysNoticedetail entity);
	void updateSysNoticedetail(SysNoticedetail entity);
	void deleteSysNoticedetail(String id);
	List<Map<String, Object>> pageSysNoticedetail(Map<String, Object> map);
	Long countSysNoticedetail(Map<String, Object> map);
	void batchdeleteSysNoticedetail(Map<String, Object> map);
	/**
	 * 为所有用户时数据添加
	 * @param entity
	 *
	 * @author scy
	 * @since 2018年4月1日下午4:15:40
	 */
	void insertAll(Map<String, Object> map);
	/**
	 * 获取已选择的企业类型
	 * @param map
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月2日下午5:20:00
	 */
	List<Map<String, Object>> getDistin(Map<String, Object> map);
}
