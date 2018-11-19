/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.dynfield.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.module.system.dynfield.dao.DdynfieldMapper;
import com.joinsoft.module.system.dynfield.entity.Ddynfield;



/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@Service
public class DdynfieldService {

    @Autowired
    private DdynfieldMapper ddynfieldMapper;

    /**
     * 得到单个实体。
     * @param params 参数MAP
     * @return 符合条件的实体对象
     * @author 仝玉锐
     */
    public Map<String, Object> getEntity(Map<String,Object> params) {
        List<Map<String, Object>> list= getEntityByList(params);
        if(CollectionUtils.isNotEmpty(list))
            return list.get(0);
        return null;
    }
    
    /**
     * 得到实体列表并分页
     * @param params 参数MAP
     * @return 分页数据列表
     * @author 仝玉锐
     */
    public List<Map<String, Object>> getEntityByPage(Map<String,Object> params){
        return ddynfieldMapper.pageDdynfield(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 仝玉锐
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
        List<Map<String, Object>> list= ddynfieldMapper.getDdynfield(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }
    
    /**
     * 增加实体记录   字段定义转换大写
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void addEntity(Ddynfield entity){
    	entity.setFieldname(entity.getFieldname().toUpperCase());      	
        ddynfieldMapper.insertDdynfield(entity);
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void updateEntity(Ddynfield entity){
        ddynfieldMapper.updateDdynfield(entity);
    }
    
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: 仝玉锐
     */
    public void deleteEntity(String id){
        ddynfieldMapper.deleteDdynfield(id);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：仝玉锐
     */
    public void batchDelEntity(List<String> ids){
        ddynfieldMapper.batchdeleteDdynfield(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：仝玉锐
     */
    public Long countEntity(Map<String,Object> params){
        return ddynfieldMapper.countDdynfield(params);
    }
    //动态查询字段信息
    @SuppressWarnings("rawtypes")
	public List<Map> getMapByList(Map<String,Object> params){
        List<Map> DdynfieldList= new ArrayList<Map>();
        DdynfieldList = ddynfieldMapper.queryDdynFiemap(params);        
        if(CollectionUtils.isNotEmpty(DdynfieldList))
            return DdynfieldList;
        return null;
    }    
}
