/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.bean.ZTree;
import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.actormenu.dao.SysActorMenuMapper;
import com.joinsoft.module.system.menu.dao.SysRbacMenuMapper;
import com.joinsoft.module.system.menu.entity.SysRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacMenu;


/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

@Service
public class SysRbacMenuService {

    @Autowired
    private SysRbacMenuMapper sysRbacMenuMapper;
    @Autowired
    private SysActorMenuMapper sysActorMenuMapper;
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
        return sysRbacMenuMapper.pageSysRbacMenu(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 仝玉锐
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
        List<Map<String, Object>> list= sysRbacMenuMapper.getSysRbacMenu(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }
    
    /**
     * 得到菜单序号
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author scy
     * @author gx   修改返回map为String 2017-06-08
     */
    public String queryEntityByList(Map<String,Object> params){
        Map<String, Object> map= sysRbacMenuMapper.querySysRbacMenu(params);
        String menuNo = "";
        if(CollectionUtils.isNotEmpty(map)){
        	menuNo = map.get("MENUNO").toString();
        	return menuNo;
        }else{
        	menuNo = "1";
        	return menuNo;
        }
                  
    }
    
    /**
     * 得到pid为空的顶级菜单序号
     * @return
     */
    public String queryMaxPidMenuno(){
    	return sysRbacMenuMapper.queryMaxPidMenuno();
    }
    
    
    /**
     * 增加实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void addEntity(SysRbacMenu entity){
        sysRbacMenuMapper.insertSysRbacMenu(entity);
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: 仝玉锐
     */
    public void updateEntity(SysRbacMenu entity){
        sysRbacMenuMapper.updateSysRbacMenu(entity);
    }
    
   /**
    * 删除菜单
    * 删除前验证存在状态正常的子菜单 ,存在 无法删除,不存在删除
    * @param sid
    * @return
    * 作者：gx
    */
    public String updateSysMenuDelFlag(String sid){
    	String errMsg = "";
    	//删除前该菜单是否存在正常的子菜单
		Map<String,Object> validationParams = new HashMap<String,Object>();
		validationParams.put("pid", sid);
		validationParams.put("state", JsdpConstants.HFMP_State_Y);
		validationParams.put("delflag", JsdpConstants.HFMP_Delflag_N);
		List<Map<String,Object>> menuMap = sysRbacMenuMapper.getSysRbacMenu(validationParams);
		if(CollectionUtils.isNotEmpty(menuMap)){
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "该菜单下存在状态正常的子菜单,"+JsdpConstants.AJAX_RESULT_MSG_F);
		}
    	try {
    		//父节点
        	SysRbacMenu pidEntity = new SysRbacMenu();
        	pidEntity.setDelflag(JsdpConstants.HFMP_Delflag_Y);
        	pidEntity.setSid(sid);
        	//子节点
        	SysRbacMenu entity = new SysRbacMenu();
        	entity.setDelflag(JsdpConstants.HFMP_Delflag_Y);
        	entity.setPid(sid);
        	//更新父节点删除标志
        	sysRbacMenuMapper.updateSysRbacMenu(pidEntity);
        	Map<String,Object> pidparams = new HashMap<String,Object>();
        	pidparams.put("menuid", pidEntity.getSid());
        	//删除父节点相应的菜单权限表中的相关记录
        	sysActorMenuMapper.deleteActorOrMenu(pidparams);
        	//更新子节点删除标志
            sysRbacMenuMapper.updateSysMenuDelFlag(entity);
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("pid", entity.getPid());
            List<Map<String,Object>> sysRbacMenu = sysRbacMenuMapper.getSysRbacMenu(params);
            if(!CollectionUtils.isEmpty(sysRbacMenu)){
            	for (Map<String, Object> map : sysRbacMenu) {
            		String menuid = map.get("SID").toString();
            		pidparams.clear();
            		pidparams.put("menuid", menuid);
            		//删除子节点相应的菜单权限表中的相关记录
            		sysActorMenuMapper.deleteActorOrMenu(pidparams);
            	}
            }
            return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
    
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: 仝玉锐
     */
    public void deleteEntity(String id){
        sysRbacMenuMapper.deleteSysRbacMenu(id);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：仝玉锐
     */
    public void batchDelEntity(List<String> ids){
        sysRbacMenuMapper.batchdeleteSysRbacMenu(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：仝玉锐
     */
    public Long countEntity(Map<String,Object> params){
        return sysRbacMenuMapper.countSysRbacMenu(params);
    }
    /**
     * 获取树
     * @param params
     * @return
     */
    public List<ZTree> getMenuTree(Map<String,Object> params){
    	return sysRbacMenuMapper.getMenuTree(params);
    }
    
    /**
     * 查找菜单序号最大值
     * @param params
     * @return
     */
    public Long queryMaxMenuno(Map<String,Object> params){
    	return sysRbacMenuMapper.queryMaxMenuno(params);
    }
    /**
     * 根据单位类型获取对应菜单树
     * @param params
     * @return
     */
    public List<CacheRbacMenu> getMenuTreeCorp(Map<String,Object> params){
    	return sysRbacMenuMapper.queryMenuTreeCorp(params);
    }
}
