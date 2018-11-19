/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.userrole.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.module.system.actormenu.entity.SysActorMenu;
import com.joinsoft.module.system.userrole.dao.SysUserRoleMapper;
import com.joinsoft.module.system.userrole.entity.SysUserRole;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@Service
public class SysUserRoleService {

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
        return sysUserRoleMapper.pageSysUserRole(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 仝玉锐
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
        List<Map<String, Object>> list= sysUserRoleMapper.getSysUserRole(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }
    
    /**
     * 增加实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void addEntity(SysUserRole entity){
        sysUserRoleMapper.insertSysUserRole(entity);
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void updateEntity(SysUserRole entity){
        sysUserRoleMapper.updateSysUserRole(entity);
    }
    
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: 仝玉锐
     */
    public void deleteEntity(Map<String,Object> params){
        sysUserRoleMapper.deleteSysUserRole(params);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：仝玉锐
     */
    public void batchDelEntity(List<String> ids){
        sysUserRoleMapper.batchdeleteSysUserRole(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：仝玉锐
     */
    public Long countEntity(Map<String,Object> params){
        return sysUserRoleMapper.countSysUserRole(params);
    }
    /**
     * 循环增加实体记录
     * @param 实体对象
     * 作者: scy
     */
    public void addEntitys(SysUserRole entity,String[] rolelist){
    	for (int i = 0; i < rolelist.length; i++) {
    		String RoleId = rolelist[i].trim();
    		
    		entity.setSid(UUIDUtils.get32UUID());
    		entity.setRoleId(RoleId);
    		entity.setRemark("备注");
    		sysUserRoleMapper.insertSysUserRole(entity);
    	}      
    }
    /**
     * 按照用户或者角色删除
     * @param id 对象id
     * 作者: scy
     */
    public void deleteUserOrRole(Map<String,Object> params){
        sysUserRoleMapper.deleteSysUserOrRole(params);
    }
  
}
