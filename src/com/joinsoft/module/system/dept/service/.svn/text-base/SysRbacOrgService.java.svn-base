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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joinsoft.core.bean.ZTree;
import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dept.dao.SysOrgAreaMapper;
import com.joinsoft.module.system.dept.dao.SysRbacOrgMapper;
import com.joinsoft.module.system.dept.entity.SysOrgArea;
import com.joinsoft.module.system.dept.entity.SysRbacOrg;
import com.joinsoft.module.system.user.dao.SysRbacUserMapper;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacUser;

/**
 * {功能简述}
 * 
 * @author LZX
 * @since 2014年8月15日
 */

@Service
public class SysRbacOrgService {

    @Autowired
    private SysRbacOrgMapper sysRbacOrgMapper;
    @Autowired
    private SysRbacUserMapper sysRbacUserMapper;
    @Autowired
    private SysOrgAreaMapper sysOrgAreaMapper;
    /**
     * 得到单个实体。
     * @param params 参数MAP
     * @return 符合条件的实体对象
     * @author wkd
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
     * @author wkd
     */
    public List<Map<String, Object>> getEntityByPage(Map<String,Object> params){
        return sysRbacOrgMapper.pageSysRbacOrg(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author wkd
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
    	List<Map<String, Object>> list= sysRbacOrgMapper.getSysRbacOrg(params);
    	if(CollectionUtils.isNotEmpty(list))
    		return list;
    	return null;
    }
    
    /**
     * 增加实体记录
     * @param 实体对象
     * 作者: LZX
     */
    public void addEntity(SysRbacOrg entity){
        sysRbacOrgMapper.insertSysRbacOrg(entity);
    }
    
    /**
     * 机构添加,机构数据范围添加
     * @param entity
     * @param userBean
     * @param dataZone
     * 作者 ：gx   2017-06-08
     * @return
     */
    public void addEntityandOrgArea(SysRbacOrg entity, CacheRbacUser rbacUser, String dataZone,CacheRbacOrg rbacOrg){  
    	//若为顶级机构添加机构编号
    	if(StringUtils.isEmpty(entity.getPid())){
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put("orgno", rbacOrg.getZonecode()+"00");//这里应该为01因为历史数据都是00 所以先为00！~！！
    		String orgno = StringUtils.clean(sysRbacOrgMapper.getMaxOrgNO(params));
    		if(StringUtils.isEmpty(orgno)){
    			orgno = rbacOrg.getZonecode()+"000001";//这里应该为010001因为历史数据都是00 所以先为00！~！！
    		}
    		entity.setOrgNo(orgno);
//    		entity.setOrgNo("650100000001");
    	}
		String sid = UUIDUtils.get32UUID();
		entity.setSid(sid);
		entity.setDelFlag(JsdpConstants.HFMP_Delflag_N);//默认正常
		entity.setCreateBy(rbacUser.getLoginname());
		entity.setCreateTime(DateUtils.getTimeNow());//创建时间
		entity.setOrgflag(JsdpConstants.RBAC_ORG_FLAG_INSIDE); //1 内部机构 2 外部机构
		sysRbacOrgMapper.insertSysRbacOrg(entity);
		//将数据范围添加到sys_org_area表中
		if(!StringUtils.isEmpty(dataZone)){
			String [] zonecodes = dataZone.split(",");
    		for (String string : zonecodes) {
    			String zonecode = string;
    			SysOrgArea orgAreaEntity = new SysOrgArea();
    			orgAreaEntity.setSid(UUIDUtils.get32UUID());
    			orgAreaEntity.setZonecode(zonecode);
    			orgAreaEntity.setOrgid(sid);
    			sysOrgAreaMapper.insertSysOrgArea(orgAreaEntity);
    		}
		}   
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: LZX
     */
    public void updateEntity(SysRbacOrg entity){
        sysRbacOrgMapper.updateSysRbacOrg(entity);
    }
    
    /**
     * 修改机构信息和机构权限
     * @param entity
     * @param dataZone
     */
    public void updateOrgandOrgArea(SysRbacOrg entity,String dataZone){
    	sysRbacOrgMapper.updateSysRbacOrg(entity);
    	//更新sys_org_area时先删除表中原有数据
    	Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgid", entity.getSid());
		sysOrgAreaMapper.deleteSysOrgArea(params);
		if(!StringUtils.isEmpty(dataZone)){
			String [] zonecodes = dataZone.split(",");
    		for (String zonecode : zonecodes) {
    			SysOrgArea orgAreaEntity = new SysOrgArea();
    			orgAreaEntity.setSid(UUIDUtils.get32UUID());
    			orgAreaEntity.setZonecode(zonecode);
    			orgAreaEntity.setOrgid(entity.getSid());
    			sysOrgAreaMapper.insertSysOrgArea(orgAreaEntity);
    		}
		}    	
    }
    
    /**
     * 机构删除 更新删除标志,删除对应数据范围
     * @param entity
     * @param orgid
     */
    public void updateFlagandDelorgArea(SysRbacOrg entity,String orgid){
    	Map<String,Object> params = new HashMap<String,Object>();
    	sysRbacOrgMapper.updateSysRbacOrg(entity);
    	if(!StringUtils.isEmpty(orgid)){
    		params.put("orgid", orgid);
    		sysOrgAreaMapper.deleteSysOrgArea(params);
    	}
    }
    
    
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: LZX
     */
    public void deleteEntity(String id){
        sysRbacOrgMapper.deleteSysRbacOrg(id);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：wkd
     */
    public void batchDelEntity(List<String> ids){
        sysRbacOrgMapper.batchdeleteSysRbacOrg(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：wkd
     */
    public Long countEntity(Map<String,Object> params){
        return sysRbacOrgMapper.countSysRbacOrg(params);
    }
    
    /**
     * 获取树
     * @param params
     * @return
     */
    public List<ZTree> getOrgTree(Map<String,Object> params){
    	return sysRbacOrgMapper.getOrgTree(params);
    }
    /**
     * 
     * 功能: 拼接用户信息
     * 
     * @param List<ZTree>    
     * @return
     *         作者：scy
     */
    public List<ZTree> getuserTree(List<ZTree> org){
    	int size = org.size();   	
    	for (int i = 0; i < size; i++) {
    		ZTree user = new ZTree();
    		user.setName("用户信息");
    		user.setId(UUIDUtils.get32UUID());
    		user.setPid(org.get(i).getId());   		
		    org.add(user);
		}  	
    	return org;
    }
    
    /**
     * 获取机构用户信息树
     *   用other来区分是否机构还是用户，org-机构 user-用户信息
     * @param params
     * @return
     * @author LZX 2017-05-24 1744
     */
    public List<ZTree> getOrgUserTree(Map<String,Object> params){
    	List<ZTree> orgTrees = sysRbacOrgMapper.getOrgTree(params);
    	List<ZTree> dataList = new ArrayList<ZTree>();
    	if(CollectionUtils.isNotEmpty(orgTrees)){
    		for(ZTree entity : orgTrees){
    			ZTree user = new ZTree();
        		user.setName("用户信息");
        		user.setId(UUIDUtils.get32UUID());
        		user.setPid(entity.getId());
        		user.setOther("user");
        		dataList.add(entity);
        		dataList.add(user);
    		}
    	}
    	 //为用户信息排序
    	 Collections.sort(dataList, new Comparator<ZTree>(){	     
	     public int compare(ZTree o1, ZTree o2) {
		     ZTree z1=(ZTree)o1;  
		     ZTree z2=(ZTree)o2;
		     return z2.getOther().compareTo(z1.getOther());
	     }		
    	});   	
    	return dataList;
    }

    /**
     * 获取树 行政区
     * @param params
     * @return
     */
    public List<ZTree>getAreaTree(Map<String,Object> params){
    	return sysRbacOrgMapper.getAreaTree(params);
    }
    
    
    /**
     * 根据部门编号获取该部门的顶级部门sid
     * @param orgId
     * @return
     */
    public String getPid(String orgId){
    	String Pid = "";
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put("where", " and pid is null  START WITH  sid='"+orgId+"' CONNECT BY PRIOR pid = sid");
		Map<String,Object> orgMap = this.getEntity(params);
		if(CollectionUtils.isBank(orgMap.get("SID"))){
			Pid = (String) orgMap.get("SID");
		}
		return Pid;
    }
    
 
    /**
     * 验证机构可否被删除
     * @param sid
     * @return
     */
    public boolean validation(String sid){
    	Map<String,Object> params = new HashMap<String,Object>();
	    	params.put("sid", sid);
	    	params.put("delflag", JsdpConstants.HFMP_Delflag_N);
	    	try {
	    		Map<String,Object> sysRbacOrg = this.getEntity(params);
		    	if(!CollectionUtils.isEmpty(sysRbacOrg)){
		    		params.clear();
		    		params.put("pid", sid);
		    		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
		    		List<Map<String,Object>> sysRbacOrgList = this.getEntityByList(params);
		    		//该节点下含有子节点并且个数大于0,不允许删除
		    		if(!CollectionUtils.isEmpty(sysRbacOrgList)&&sysRbacOrgList.size()>0){
		    			return false;
		    		}
		    		params.clear();
		    		params.put("orgid", sid);
		    		params.put("state", "1");
		    		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
		    		//该节点下存在状态正常的用户信息,不允许删除
		    		Long countUser = sysRbacUserMapper.countSysRbacUser(params);
		    		if(countUser > 0){
		    			return false;
		    		}
		    	}
			} catch (Exception e) {
				return false;
			}	
    	return true;
    }

}
