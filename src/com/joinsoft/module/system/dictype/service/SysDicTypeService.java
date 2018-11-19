/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.dictype.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dictcontent.dao.SysDictContentMapper;
import com.joinsoft.module.system.dictcontent.entity.SysDictContent;
import com.joinsoft.module.system.dictype.dao.SysDicTypeMapper;
import com.joinsoft.module.system.dictype.entity.SysDicType;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@Service
public class SysDicTypeService {

    @Autowired
    private SysDicTypeMapper sysDicTypeMapper;
    @Autowired
    private SysDictContentMapper sysDictContentMapper;

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
        return sysDicTypeMapper.pageSysDicType(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 仝玉锐
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
    	List<Map<String, Object>> list= sysDicTypeMapper.getSysDicType(params);
    	if(CollectionUtils.isNotEmpty(list))
    		return list;
    	return null;
    }
    
    
    /**
     * 增加实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void addEntity(SysDicType entity){
        sysDicTypeMapper.insertSysDicType(entity);
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void updateEntity(SysDicType entity){
        sysDicTypeMapper.updateSysDicType(entity);
    }
    
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: 仝玉锐
     */
    public void deleteEntity(String id){
        sysDicTypeMapper.deleteSysDicType(id);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：仝玉锐
     */
    public void batchDelEntity(List<String> ids){
        sysDicTypeMapper.batchdeleteSysDicType(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：仝玉锐
     */
    public Long countEntity(Map<String,Object> params){
        return sysDicTypeMapper.countSysDicType(params);
    }
    /**
     * 删除字典类别和明细
     * @param entity
     *
     * @author scy
     * @since 2018年4月8日下午6:35:51
     */
    public void updateDelte(SysDicType entity){
        sysDicTypeMapper.updateSysDicType(entity);
        SysDictContent Content = new SysDictContent();
        Content.setDictno(entity.getDictno());
        Content.setDelflag(JsdpConstants.HFMP_Delflag_Y);
        sysDictContentMapper.updateSysDictContent(Content);
    }
}
