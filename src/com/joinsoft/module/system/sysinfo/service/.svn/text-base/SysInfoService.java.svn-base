
/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2017
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.module.system.sysinfo.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.module.system.sysinfo.dao.SysInfoMapper;
import com.joinsoft.module.system.sysinfo.entity.SysInfo;


/**
 * 系统信息
 * @author scy
 * @since  2018年3月27日
 */

@Service
public class SysInfoService {

    @Autowired
    private SysInfoMapper sysInfoMapper;

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
        return sysInfoMapper.pageSysInfo(params);
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
        List<Map<String, Object>> list= sysInfoMapper.getSysInfo(params);
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
    public void addEntity(SysInfo entity){
        sysInfoMapper.insertSysInfo(entity);
    }
    
	 /**
	 * 
	 * 静态修改实体记录
     * @param 实体对象
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public void updateEntity(SysInfo entity,MultipartFile multipartFile,String fulepaths){
        sysInfoMapper.updateSysInfo(entity);
        String filename = multipartFile.getOriginalFilename();
        if (!StringUtils.isEmpty(filename)) {
        	 String suffix = filename.substring(filename.lastIndexOf("."));/* 文件后缀 */
             File dir = new File(fulepaths);
             if (!dir.exists())
                 dir.mkdirs();
             String fileNames = fulepaths + suffix;
             File file = new File(fileNames);
             try {
             	multipartFile.transferTo(file);
      		} catch (Exception e) {
      		}
        }
    }

	 /**
	 * 
	 * 删除单个实体
     * @param id 对象id
	 * 
	 * @author 孙晨阳
	 * @since 
	 */
    public void deleteEntity(String id){
        sysInfoMapper.deleteSysInfo(id);
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
        sysInfoMapper.batchdeleteSysInfo(ids);
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
        return sysInfoMapper.countSysInfo(params);
    }
    /**
     * 替换系统图片
     * @param multipartFile
     * @param fulepaths
     *
     * @author scy
     * @since 2018年3月28日上午10:28:49
     */
   public void uploadImg(MultipartFile multipartFile,String fulepaths){
       
       
       
   }    
}
