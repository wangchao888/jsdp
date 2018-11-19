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
import com.joinsoft.module.system.notice.entity.SysNoticeinfo;

/**
 * 通知消息
 * @author scy
 * @since  2018年3月30日
 */
@MyBatisDao
public interface SysNoticeinfoMapper{
	List<Map<String, Object>> getSysNoticeinfo(Map<String, Object> map);
	void insertSysNoticeinfo(SysNoticeinfo entity);
	void updateSysNoticeinfo(SysNoticeinfo entity);
	void deleteSysNoticeinfo(String id);
	List<Map<String, Object>> pageSysNoticeinfo(Map<String, Object> map);
	Long countSysNoticeinfo(Map<String, Object> map);
	void batchdeleteSysNoticeinfo(List<String> ids);
	
	/**
	 * 获取用户的消息（关联sys_noticeDetail）
	 * @param map
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月3日下午2:23:49
	 */
	List<Map<String, Object>> getUserMessage(Map<String, Object> map);
	/**
	 * 获取用户接收的消息条数
	 * @param map
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月9日下午5:02:29
	 */
	Long countInfo(Map<String, Object> map);
	/**
	 * 获取用户的消息并分页（关联sys_noticeDetail）
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param map
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月10日上午9:53:48
	 */
	List<Map<String, Object>> pageGetUserMessage(Map<String, Object> map);
}
