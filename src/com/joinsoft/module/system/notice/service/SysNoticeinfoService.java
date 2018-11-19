
/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2017
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.module.HfmpConstants;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.notice.dao.SysNoticedetailMapper;
import com.joinsoft.module.system.notice.dao.SysNoticeinfoMapper;
import com.joinsoft.module.system.notice.entity.SysNoticedetail;
import com.joinsoft.module.system.notice.entity.SysNoticeinfo;
import com.joinsoft.platform.global.entity.CacheRbacOrg;

/**
 * 通知消息
 * @author scy
 * @since  2018年3月30日
 */
@Service
public class SysNoticeinfoService {

    @Autowired
    private SysNoticeinfoMapper sysNoticeinfoMapper;
    @Autowired
    private SysNoticedetailMapper sysNoticedetailMapper;

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
        return sysNoticeinfoMapper.pageSysNoticeinfo(params);
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
        List<Map<String, Object>> list= sysNoticeinfoMapper.getSysNoticeinfo(params);
        if(CollectionUtils.isNotEmpty(list))
            return list;
        return null;
    }

	 /**
	  * 保存
	  * @param entity
	  *
	  * @author scy
	  * @since 2018年4月1日下午1:19:44
	  */
    public void addEntity(SysNoticeinfo entity){
        sysNoticeinfoMapper.insertSysNoticeinfo(entity);
    }
    
	 /**
	 * 
	 * 静态修改实体记录
     * @param 实体对象
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public void updateEntity(SysNoticeinfo entity){
        sysNoticeinfoMapper.updateSysNoticeinfo(entity);
    }

    /**
     * 修改数据
     * @param id
     * @param entity
     * @param users
     * @param rbacOrg
     *
     * @author scy
     * @since 2018年4月2日下午5:44:54
     */
    public void updateInfo(String id,SysNoticeinfo entity,String users,CacheRbacOrg rbacOrg){
        sysNoticeinfoMapper.deleteSysNoticeinfo(id);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("noticeid", id);
        sysNoticedetailMapper.batchdeleteSysNoticedetail(params);
        this.saveEntity(entity, users, rbacOrg);
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
        sysNoticeinfoMapper.batchdeleteSysNoticeinfo(ids);
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
        return sysNoticeinfoMapper.countSysNoticeinfo(params);
    }
    /**
     * 获取用户的消息（关联sys_noticeDetail）
     * @param params
     * @return
     *
     * @author scy
     * @since 2018年4月3日下午2:31:43
     */
    public List<Map<String, Object>> getUserMessage(Map<String,Object> params){
        List<Map<String, Object>> list= sysNoticeinfoMapper.getUserMessage(params);
       return list;
    }
    /**
     * 获取用户接收的消息条数
     * @param params
     * @return
     *
     * @author scy
     * @since 2018年4月9日下午5:03:01
     */
    public Long countInfo(Map<String,Object> params){
        return sysNoticeinfoMapper.countInfo(params);
    }
    /**
     * 获取用户的消息并分页（关联sys_noticeDetail）
     * @param params
     * @return
     *
     * @author scy
     * @since 2018年4月3日下午2:31:43
     */
    public List<Map<String, Object>> getUserMessageByPage(Map<String,Object> params){
        List<Map<String, Object>> list= sysNoticeinfoMapper.pageGetUserMessage(params);
       return list;
    }
    /**
     * 增加消息表和用户详情表的数据
     * @param entity
     * @param users
     * @param rbacOrg
     *
     * @author scy
     * @since 2018年4月10日上午11:07:01
     */
   public void saveEntity(SysNoticeinfo entity,String users,CacheRbacOrg rbacOrg){
   	entity.setSid(UUIDUtils.get32UUID());
		entity.setState(JsdpConstants.NOTICE_N);
		entity.setRectime(DateUtils.getTimeNow());
       sysNoticeinfoMapper.insertSysNoticeinfo(entity);
       SysNoticedetail detai = new SysNoticedetail();
       detai.setSid(UUIDUtils.get32UUID());
       detai.setNoticeid(entity.getSid());
       detai.setViewflag(JsdpConstants.VIEWFLAG_N);
       Map<String,Object> params = new HashMap<String,Object>();
       params.put("noticeid", entity.getSid());
       params.put("viewflag", JsdpConstants.VIEWFLAG_N);
       params.put("orgno", rbacOrg.getOrgNo());
       //所有用户都添加
       if (JsdpConstants.NOTICE_USERTYPE_00.equals(entity.getUsertype())) { 
       	sysNoticedetailMapper.insertAll(params);
       } else if (JsdpConstants.NOTICE_USERTYPE_01.equals(entity.getUsertype())) { 
			String[] userids = users.split("\\|");
			for (int i = 0; i < userids.length; i++) {
				detai.setUserid(userids[i]);
				sysNoticedetailMapper.insertSysNoticedetail(detai);
			}
		}else if (JsdpConstants.NOTICE_USERTYPE_02.equals(entity.getUsertype())) { 
			String[] userids = users.split("\\|");//这里不是用户id 是企业类型
			String corptype = StringUtils.toInString(userids);//in类型数据
			params.put("corptype", corptype);
			params.put("usertype", "02");
			sysNoticedetailMapper.insertAll(params);
		} else if (JsdpConstants.NOTICE_USERTYPE_03.equals(entity.getUsertype())) { 
			String[] userids = users.split("\\|");
			for (int i = 0; i < userids.length; i++) {
				detai.setUserid(userids[i]);
				sysNoticedetailMapper.insertSysNoticedetail(detai);
			}
		}
   }
}
