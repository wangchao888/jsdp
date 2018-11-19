
/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2017
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.menu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.menu.dao.SysUserShortcutMapper;
import com.joinsoft.module.system.menu.entity.SysUserShortcut;
import com.joinsoft.platform.global.entity.CacheRbacUser;
/**
 * 用户快捷菜单关系表
 * @author scy
 * @since  2018年10月19日
 */

@Service
public class SysUserShortcutService {

    @Autowired
    private SysUserShortcutMapper sysUserShortcutMapper;
    

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
        return sysUserShortcutMapper.pageSysUserShortcut(params);
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
        List<Map<String, Object>> list= sysUserShortcutMapper.getSysUserShortcut(params);
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
    public void addEntity(SysUserShortcut entity){
        sysUserShortcutMapper.insertSysUserShortcut(entity);
    }
    
	 /**
	 * 
	 * 静态修改实体记录
     * @param 实体对象
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public void updateEntity(SysUserShortcut entity){
        sysUserShortcutMapper.updateSysUserShortcut(entity);
    }

	 /**
	 * 
	 * 按照用户删除
     * @param id 对象id
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public void deleteEntity(String id){
        sysUserShortcutMapper.deleteSysUserShortcut(id);
    }
    
	 /**
	 * 
	 * 采用in的形式批量删除实体
     * @param ids 对象id列表
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public void batchDelEntity(List<String> ids){
        sysUserShortcutMapper.batchdeleteSysUserShortcut(ids);
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
        return sysUserShortcutMapper.countSysUserShortcut(params);
    }
    /**
     * 获取首页数据所用
     * @param params
     * @return
     *
     * @author scy
     * @since 2018年10月21日下午4:29:05
     */
    public List<Map<String, Object>> getShortcutAndMenu(Map<String,Object> params){
        return sysUserShortcutMapper.getShortcutAndMenu(params);
    }
    /**
     * 获取最大排序号
     * @param params
     * @return
     *
     * @author scy
     * @since 2018年10月22日下午4:51:21
     */
    public List<Map<String, Object>> getMaxOrderno(Map<String,Object> params){
        return sysUserShortcutMapper.getMaxOrderno(params);
    }
    /**
     * 快捷入口修改
     * @param entity
     *
     * @author scy
     * @since 2018年10月23日上午9:32:14
     */
    public String updateShort(String menuid,CacheRbacUser rbacUser){
    	this.deleteEntity(rbacUser.getSid());
  		String[] menuids = menuid.split(","); 
  		for (int i = 0; i < menuids.length; i++) {
  			try {
  				SysUserShortcut entity = new SysUserShortcut();
  				entity.setUserid(rbacUser.getSid());
  				entity.setSid(UUIDUtils.get32UUID());
  				entity.setOrderno(i+1+"");
  				entity.setMenuid(menuids[i]);
  				this.addEntity(entity);
  			} catch (Exception e) {
  				return JsdpConstants.HFMP_RESULT_FAIL;
  			}
		}
  		return JsdpConstants.HFMP_RESULT_SUCCESS;
    	
    	
    }
}
