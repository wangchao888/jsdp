/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.corprole.service;

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
import com.joinsoft.module.system.corprole.dao.SysRbacCorproleMapper;
import com.joinsoft.module.system.corprole.entity.SysRbacCorprole;
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
public class SysRbacCorproleService {

    @Autowired
    private SysRbacCorproleMapper sysRbacCorproleMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRbacUserMapper rbacUserMapper;
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
        return sysRbacCorproleMapper.pageSysRbacCorprole(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 仝玉锐
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
        List<Map<String, Object>> list= sysRbacCorproleMapper.getSysRbacCorprole(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }
    
    /**
     * 增加实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void addEntity(SysRbacCorprole entity,String[] rolelist){
    	for (int i = 0; i < rolelist.length; i++) {
    		String roleId = rolelist[i].trim();
    		entity.setSid(UUIDUtils.get32UUID());
    		entity.setDelflag(JsdpConstants.HFMP_Delflag_N);
    		entity.setRoleid(roleId);
    		sysRbacCorproleMapper.insertSysRbacCorprole(entity);
    	}
    }
    
    /**
     * 修改单位角色
     * @param entity
     * @param rolelist
     */
	public void updateCorprole(SysRbacCorprole entity,String[] rolelist,String oldcorptype){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("corptype", oldcorptype);
    	/*修改前先删除单位原来的角色信息*/
    	if(!StringUtils.isEmpty(entity.getCorptype())){
    		sysRbacCorproleMapper.deleteSysRbacCorprole(params);
    	}
    	sysRbacCorproleMapper.updateDelflag(params);
    	/*删除掉原来的信息后,添加单位新的角色信息*/
    	this.addEntity(entity, rolelist);
    	updateUser(entity.getCorptype(),rolelist);
    }
	/**
	 * 对单位下用户进行数据同步
	 * @param corpType
	 * @param rolelist
	 *
	 * @author scy
	 * @since 2018年11月16日下午2:41:02
	 */
	public void updateUser(String corpType,String[] rolelist){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("corptype", corpType);
    	List<String> useridList = rbacUserMapper.getUserId(params);
    	userRoleMapper.batchDeleteUser(useridList);//删除类型单位下用户对应的角色关系
    	//添加新的用户角色
    	String userids = "(";
    	String roleids = "(";
    	for (int i = 0; i < useridList.size(); i++) {
    		userids += "'"+useridList.get(i)+"',";
    		SysUserRole userRole = new SysUserRole();
    		userRole.setUserId(useridList.get(i));
    		for (int j = 0; j < rolelist.length; j++) {
    			userRole.setSid(UUIDUtils.get32UUID());
    			userRole.setRoleId(rolelist[j].trim());
    			userRoleMapper.insertSysUserRole(userRole);
			}
		}
    	//删除此类型单位下的用户的在新加角色之外的菜单
    	for (int j = 0; j < rolelist.length; j++) {
    		roleids += "'"+rolelist[j]+"',";
		}
    	params.clear();
    	params.put("userids", userids.substring(0,userids.length() - 1)+")");
    	params.put("roleids", roleids.substring(0,roleids.length() - 1)+")");
    	actorMenuMapper.deleteUserActorNotRole(params);
	}
    /**
     * 更新表状态为删除
     * @param params
     */
    public void updateDelflag(Map<String,Object> params){
    	sysRbacCorproleMapper.updateDelflag(params);
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void updateEntity(SysRbacCorprole entity){
        sysRbacCorproleMapper.updateSysRbacCorprole(entity);
    }
    
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: 仝玉锐
     */
    public void deleteEntity(Map<String,Object> params){
        sysRbacCorproleMapper.deleteSysRbacCorprole(params);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：仝玉锐
     */
    public void batchDelEntity(List<String> ids){
        sysRbacCorproleMapper.batchdeleteSysRbacCorprole(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：仝玉锐
     */
    public Long countEntity(Map<String,Object> params){
        return sysRbacCorproleMapper.countSysRbacCorprole(params);
    }
}
