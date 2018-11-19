
/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2017
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.module.system.notice.dao.SysNoticedetailMapper;
import com.joinsoft.module.system.notice.entity.SysNoticedetail;
/**
 * 通知用户详情
 * @author scy
 * @since  2018年3月30日
 */
@Service
public class SysNoticedetailService {

    @Autowired
    private SysNoticedetailMapper sysNoticedetailMapper;

	 /**
	 * 
	 * 得到实体列表
	 * @return 符合条件的实体对象
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public Map<String, Object> getEntity(Map<String,Object> params) {
        List<Map<String, Object>> list= getEntityByList(params);
        if(CollectionUtils.isNotEmpty(list))
            return list.get(0);
        return null;
    }
    
	 /**
	 * 
	 * 得到实体列表并分页
	 * @param params 参数MAP
	 * @return 分页数据列表
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public List<Map<String, Object>> getEntityByPage(Map<String,Object> params){
        return sysNoticedetailMapper.pageSysNoticedetail(params);
    }
    
	 /**
	 * 
	 * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
        List<Map<String, Object>> list= sysNoticedetailMapper.getSysNoticedetail(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }

	 /**
	 * 
	 * 增加实体记录
     * @param 实体对象
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public void addEntity(SysNoticedetail entity){
        sysNoticedetailMapper.insertSysNoticedetail(entity);
    }
    
	 /**
	 * 
	 * 静态修改实体记录
     * @param 实体对象
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public void updateEntity(SysNoticedetail entity){
        sysNoticedetailMapper.updateSysNoticedetail(entity);
    }

	 /**
	 * 
	 * 删除单个实体
     * @param id 对象id
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public void deleteEntity(String id){
        sysNoticedetailMapper.deleteSysNoticedetail(id);
    }
    
	 /**
	 * 
	 * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public Long countEntity(Map<String,Object> params){
        return sysNoticedetailMapper.countSysNoticedetail(params);
    }
    /**
     * 获取已选择的企业类型
     * @param params
     * @return
     *
     * @author scy
     * @since 2018年4月2日下午5:21:01
     */
    public List<Map<String, Object>> getDistin(Map<String,Object> params){
        List<Map<String, Object>> list= sysNoticedetailMapper.getDistin(params);
            return list;
    }
}
