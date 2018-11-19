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

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.user.dao.SysRbacUserlogMapper;
import com.joinsoft.module.system.user.entity.SysRbacUser;
import com.joinsoft.module.system.user.entity.SysRbacUserlog;


/**
 * 用户登录日志业务逻辑层
 * 
 * @author 李中新
 * @since 2018-05-02
 */

@Service
public class SysRbacUserlogService {

    @Autowired
    private SysRbacUserlogMapper sysRbacUserlogMapper;

    /**
     * 得到单个实体。
     * @param params 参数MAP
     * @return 符合条件的实体对象
     * @author 李中新
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
     * @author 李中新
     */
    public List<Map<String, Object>> getEntityByPage(Map<String,Object> params){
        return sysRbacUserlogMapper.pageSysRbacUserlog(params);
    }
    
    /**
     * 得到实体列表
     * @param 参数列表
     * @return 符合条件的实体列表
     * @author 李中新
     *
     */
    public List<Map<String, Object>> getEntityByList(Map<String,Object> params){
        List<Map<String, Object>> list= sysRbacUserlogMapper.getSysRbacUserlog(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }
    
    /**
     * 增加实体记录
     * @param 实体对象
     * 作者: 李中新
     */
    public void addEntity(SysRbacUserlog entity){
        sysRbacUserlogMapper.insertSysRbacUserlog(entity);
    }
    
    /**
     * 静态修改实体记录
     * @param 实体对象
     * 作者: 李中新
     */
    public void updateEntity(SysRbacUserlog entity){
        sysRbacUserlogMapper.updateSysRbacUserlog(entity);
    }
    
    /**
     * 删除单个实体
     * @param id 对象id
     * 作者: 李中新
     */
    public void deleteEntity(String id){
        sysRbacUserlogMapper.deleteSysRbacUserlog(id);
    }
    
    /**
     * 采用in的形式批量删除实体
     * @param ids 对象id列表
     * 作者：李中新
     */
    public void batchDelEntity(List<String> ids){
        sysRbacUserlogMapper.batchdeleteSysRbacUserlog(ids);
    }
    
    /**
     * 查找总纪录数目
     * @param entity 待查找的对象
     * @return 符合条件的纪录数
     * 作者：李中新
     */
    public Long countEntity(Map<String,Object> params){
        return sysRbacUserlogMapper.countSysRbacUserlog(params);
    }
    
    /**
     * 用户登录时保存日志记录
     * @param 实体对象
     * 作者: 李中新
     */
    public void saveEntityByLogin(SysRbacUserlog entity, SysRbacUser userEntity){
    	if (userEntity != null && userEntity.getSid() != null) {
    		entity.setSid(UUIDUtils.get32UUID());
        	entity.setUserid(userEntity.getSid());
        	entity.setOpertime(DateUtils.getTimeNow());
            addEntity(entity);
		}
    }
    
    /**
     * 用户退出时保存日志记录
     * @param 实体对象
     * 作者: 李中新
     */
    public void saveEntityByLogout(String userid, String remark, String ip){
    	SysRbacUserlog entity = new SysRbacUserlog();
    	entity.setSid(UUIDUtils.get32UUID());
    	entity.setUserid(userid);
    	entity.setOpertime(DateUtils.getTimeNow());
    	entity.setLogtype(JsdpConstants.RULE_ONE); // 退出
    	entity.setOperip(ip);
//    	entity.setOpermac("");
    	entity.setRemark(remark);
        addEntity(entity);
    }
}
