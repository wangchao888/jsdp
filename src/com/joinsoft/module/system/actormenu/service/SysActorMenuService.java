/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.actormenu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.actormenu.dao.SysActorMenuMapper;
import com.joinsoft.module.system.actormenu.entity.SysActorMenu;
import com.joinsoft.module.system.corprole.dao.SysRbacCorproleMapper;
import com.joinsoft.module.system.user.dao.SysRbacUserMapper;
import com.joinsoft.module.system.userrole.dao.SysUserRoleMapper;
import com.joinsoft.module.system.userrole.entity.SysUserRole;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@Service
public class SysActorMenuService {

    @Autowired
    private SysActorMenuMapper sysActorMenuMapper;
    @Autowired
    private SysRbacCorproleMapper rbacCorproleMapper;
    @Autowired
    private SysRbacUserMapper rbacUserMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysActorMenuMapper actorMenuMapper;

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
        return sysActorMenuMapper.pageSysActorMenu(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 仝玉锐
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
        List<Map<String, Object>> list= sysActorMenuMapper.getSysActorMenu(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }
    
    /**
     * 增加实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void addEntity(SysActorMenu entity){
        sysActorMenuMapper.insertSysActorMenu(entity);
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void updateEntity(SysActorMenu entity){
        sysActorMenuMapper.updateSysActorMenu(entity);
    }
    
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: 仝玉锐
     */
    public void deleteEntity(String id){
        sysActorMenuMapper.deleteSysActorMenu(id);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：仝玉锐
     */
    public void batchDelEntity(List<String> ids){
        sysActorMenuMapper.batchdeleteSysActorMenu(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：仝玉锐
     */
    public Long countEntity(Map<String,Object> params){
        return sysActorMenuMapper.countSysActorMenu(params);
    }
    /**
     * 循环增加实体记录
     * @param 实体对象
     * 作者: scy
     */
    public void addEntitys(SysActorMenu entity,String[] Menuids){
    	for (int i = 0; i < Menuids.length; i++) {
    		String Menuid = Menuids[i];
    		
    		entity.setSid(UUIDUtils.get32UUID());
    		entity.setMenuid(Menuid);
    		sysActorMenuMapper.insertSysActorMenu(entity);
    	}      
    }
    /**
     * 按照角色或菜单删除
     * @param id 对象id
     * 作者: scy
     */
    public void deleteActorOrMenu(Map<String, Object> map){
        sysActorMenuMapper.deleteActorOrMenu(map);
    }
    /**
     * 保存授权 判断若当前角色关联企业，则要对企业下用户进行数据同步
     * @param entity
     * @param Menuids
     *
     * @author scy
     * @since 2018年11月16日下午2:55:00
     */
    public void saveAutho(SysActorMenu entity,String[] Menuids,String sid){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("actorid", sid);
    	sysActorMenuMapper.deleteActorOrMenu(params);
    	entity.setActortype(JsdpConstants.ACTORTYPE_ROLE);
    	entity.setActorid(sid);
    	for (int i = 0; i < Menuids.length; i++) {
    		String Menuid = Menuids[i];
    		entity.setSid(UUIDUtils.get32UUID());
    		entity.setMenuid(Menuid);
    		sysActorMenuMapper.insertSysActorMenu(entity);
    	} 
    	params.clear();
    	params.put("roleid", sid);
    	List<Map<String, Object>> corpRoleList = rbacCorproleMapper.getSysRbacCorprole(params);
    	for (int i = 0; i < corpRoleList.size(); i++) {
    		updateUser(StringUtils.getString(corpRoleList.get(i), "CORPTYPE"), sid);
		}
    }
    /**
	 * 对单位下用户进行数据同步
	 * @param corpType
	 * @param rolelist
	 *
	 * @author scy
	 * @since 2018年11月16日下午2:41:02
	 */
	public void updateUser(String corpType,String roleid){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("corptype", corpType);
    	List<String> useridList = rbacUserMapper.getUserId(params);//所有参数单位类型下的用户
    	if (CollectionUtils.isNotEmpty(useridList)) {
    	String userids = "(";
    	for (int i = 0; i < useridList.size(); i++) {
    		userids += "'"+useridList.get(i)+"',";
    	}
    	//删除此类型单位下的用户的在当前授权角色之外的菜单
    	params.clear();
    	params.put("userids", userids.substring(0,userids.length() - 1)+")");
    	params.put("roleids", "('"+roleid+"')");
    	actorMenuMapper.deleteUserActorNotRole(params);
    	}
	}
	
	
	
	
}
