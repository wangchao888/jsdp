/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.user.dao.SysRbacUserMapper;
import com.joinsoft.module.system.user.entity.SysRbacUser;
import com.joinsoft.module.system.userrole.dao.SysUserRoleMapper;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@Service
public class SysRbacUserService {

    @Autowired
    private SysRbacUserMapper sysRbacUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 得到单个实体MAP。
     * @param params 参数MAP
     * @return 符合条件的实体对象
     * @author 仝玉锐
     */
    public Map<String, Object> getEntityMap(Map<String,Object> params) {
        List<Map<String, Object>> list= getEntityByList(params);
        if(CollectionUtils.isNotEmpty(list))
            return list.get(0);
        return null;
    }
    
    /**
     * 得到单个实体。
     * @param params 参数MAP
     * @return 符合条件的实体对象
     * @author LZX 2017-05-26
     */
    public SysRbacUser getEntity(Map<String,Object> params){
    	return sysRbacUserMapper.getEntity(params);
    }
    
    /**
     * 得到实体列表并分页
     * @param params 参数MAP
     * @return 分页数据列表
     * @author 仝玉锐
     */
    public List<Map<String, Object>> getEntityByPage(Map<String,Object> params){
        return sysRbacUserMapper.pageSysRbacUser(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 仝玉锐
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
        List<Map<String, Object>> list= sysRbacUserMapper.getSysRbacUser(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }
    
    
    /**
     * 增加实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void addEntity(SysRbacUser entity){
        sysRbacUserMapper.insertSysRbacUser(entity);
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void updateEntity(SysRbacUser entity){
        sysRbacUserMapper.updateSysRbacUser(entity);
    }
    
    /**
     * 删除用户和用户角色
     * @param sid
     * 作者 gx
     */
    public void updateFlagorDelRole(String sid){
    	if(!StringUtils.isEmpty(sid)){
    		Map<String,Object> params = new HashMap<String,Object>();
        	SysRbacUser userEntity = new SysRbacUser();
    		userEntity.setDelflag(JsdpConstants.HFMP_Delflag_Y);
    		userEntity.setSid(sid);
    		this.updateEntity(userEntity);
    		params.put("userid", sid);
    		sysUserRoleMapper.deleteSysUserRole(params);
    	}
    }
    
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: 仝玉锐
     */
    public void deleteEntity(String id){
        sysRbacUserMapper.deleteSysRbacUser(id);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：仝玉锐
     */
    public void batchDelEntity(List<String> ids){
        sysRbacUserMapper.batchdeleteSysRbacUser(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：仝玉锐
     */
    public Long countEntity(Map<String,Object> params){
        return sysRbacUserMapper.countSysRbacUser(params);
    }
    /**
     * 得到指定用户列表
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：scy
     */
    public List<Map<String, Object>> show(String pid){
		pid = StringUtils.clean(pid);
		Map<String, Object> where = new HashMap<String, Object>();
		where.put("where", "and orgid = '"+pid+"'");
		List<Map<String, Object>> userlist = getEntityByList(where);
		
		return userlist;
	}
    /**
     * 消息通知中根据机构递归获取用户
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 仝玉锐
     *
     */
    public List<Map<String, Object>> getOrgUser(Map<String,Object> params){
        List<Map<String, Object>> list= sysRbacUserMapper.getOrgUser(params);
            return list;
    }
    
}
