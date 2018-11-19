/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.dept.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.module.system.dept.dao.SysOrgAreaMapper;
import com.joinsoft.module.system.dept.entity.SysOrgArea;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@Service
public class SysOrgAreaService {

    @Autowired
    private SysOrgAreaMapper sysOrgAreaMapper;

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
        return sysOrgAreaMapper.pageSysOrgArea(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 仝玉锐
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
        List<Map<String, Object>> list= sysOrgAreaMapper.getSysOrgArea(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }
    
    /**
     * 查找区域名称
     * @param params
     * @return
     * 作者：gx
     */
    public Map<String,Object> queryZoneNameandZoneCode(Map<String,Object> params){
    	Map<String,Object> zoneMap = new HashMap<String,Object>();
    	List<Map<String, Object>> list= sysOrgAreaMapper.getSysOrgArea(params);
    	String zoneName = "";
    	String zoneCode = "";
    	StringBuffer zoneNameBuffer = new StringBuffer();
    	StringBuffer zoneCodeBuffer = new StringBuffer();
  		if(CollectionUtils.isNotEmpty(list)){
  			//多个zonename，zonecode拼接
  			if(list.size()>1){
  				for (Map<String, Object> map : list) {
					zoneNameBuffer.append(map.get("ZONENAME").toString());
					zoneNameBuffer.append(",");
					zoneCodeBuffer.append(map.get("ZONECODE").toString());
					zoneCodeBuffer.append(",");
				}
  				zoneName = zoneNameBuffer.substring(0, zoneNameBuffer.length()-1);//去除最后一个逗号
  				zoneCode = zoneCodeBuffer.substring(0,zoneCodeBuffer.length()-1);
  			}else{
  				zoneName = list.get(0).get("ZONENAME").toString();
  				zoneCode = list.get(0).get("ZONECODE").toString();
  			}
  		}
  		zoneMap.put("ZONENAME", zoneName);
  		zoneMap.put("ZONECODE", zoneCode);
  		return zoneMap;
    }
    
    /**
     * 增加实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void addEntity(SysOrgArea entity){
        sysOrgAreaMapper.insertSysOrgArea(entity);
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void updateEntity(SysOrgArea entity){
        sysOrgAreaMapper.updateSysOrgArea(entity);
    }
    
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: 仝玉锐
     */
    public void deleteEntity(Map<String,Object> params){
        sysOrgAreaMapper.deleteSysOrgArea(params);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：仝玉锐
     */
    public void batchDelEntity(List<String> ids){
        sysOrgAreaMapper.batchdeleteSysOrgArea(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：仝玉锐
     */
    public Long countEntity(Map<String,Object> params){
        return sysOrgAreaMapper.countSysOrgArea(params);
    }
}
