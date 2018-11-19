/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.role.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.actormenu.dao.SysActorMenuMapper;
import com.joinsoft.module.system.role.dao.SysRbacRoleMapper;
import com.joinsoft.module.system.role.entity.SysRbacRole;
import com.joinsoft.module.system.userrole.dao.SysUserRoleMapper;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@Service
public class SysRbacRoleService {

    @Autowired
    private SysRbacRoleMapper sysRbacRoleMapper;
    @Autowired
    private SysActorMenuMapper sysActorMenuMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

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
        return sysRbacRoleMapper.pageSysRbacRole(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 仝玉锐
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
    	List<Map<String, Object>> list= sysRbacRoleMapper.getSysRbacRole(params);
    	if(CollectionUtils.isNotEmpty(list))
    		return list;
    	return null;
    }
    
    /**
     * 通过roleids查找用户角色信息
     * @param roleids
     * @return
     */
    public List<Map<String,Object>> getEntityByListByRoleids(List<String> roleids){
    	List<Map<String,Object>> list = sysRbacRoleMapper.getEntityByListByRoleids(roleids);
    	if(CollectionUtils.isNotEmpty(list))
    		return list;
    	return null;
    }
    
    /**
     * 得到用户已有的角色
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author scy
     *
     */
    public List<Map<String, Object>> getEntityByListy(Map<String,Object> params){
        List<Map<String, Object>> list= sysRbacRoleMapper.getSysRbacRoleusery(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }
    /**
     * 得到用户没有的角色
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author scy
     *
     */
    public List<Map<String, Object>> getEntityByListn(Map<String,Object> params){
        List<Map<String, Object>> list= sysRbacRoleMapper.getSysRbacRoleusern(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }
    
    /**
     * 增加实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void addEntity(SysRbacRole entity){
        sysRbacRoleMapper.insertSysRbacRole(entity);
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void updateEntity(SysRbacRole entity){
        sysRbacRoleMapper.updateSysRbacRole(entity);
    }
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: 仝玉锐
     */
    public void deleteEntity(Map<String,Object> params){
        sysRbacRoleMapper.deleteSysRbacRole(params);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：仝玉锐
     */
    public void batchDelEntity(List<String> ids){
        sysRbacRoleMapper.batchdeleteSysRbacRole(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：仝玉锐
     */
    public Long countEntity(Map<String,Object> params){
        return sysRbacRoleMapper.countSysRbacRole(params);
    }
    /**
     * 逻辑删除角色
     * @param 实体对象
     * 作者: scy
     */
    public void deleteEntityPlus(SysRbacRole entity,String sid){
    	entity.setSid(sid);
    	entity.setDelFlag(JsdpConstants.HFMP_Delflag_Y);
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("roleid", sid);
    	params.put("actorid", sid);  	
    	//更新删除标识
        sysRbacRoleMapper.updateSysRbacRole(entity);
        //删除菜单权限表信息
        sysActorMenuMapper.deleteActorOrMenu(params);
        //删除用户角色表信息
        sysUserRoleMapper.deleteSysUserOrRole(params);
    }
    /**
     * 获取联想的数据
     * @param params
     * @return
     *
     * @author scy
     * @since 2018年4月2日上午10:23:24
     */
    public List<Map<String, Object>> getAutocomplete(Map<String,Object> params){
    	List<Map<String, Object>> list= sysRbacRoleMapper.getAutocomplete(params);
    	return list;
    }
    
}
