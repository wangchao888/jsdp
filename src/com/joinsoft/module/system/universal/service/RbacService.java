package com.joinsoft.module.system.universal.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.user.dao.SysRbacUserMapper;

@Service
public class RbacService {

	@Autowired
    private SysRbacUserMapper sysRbacUserMapper;
	
	/**
     * 查询用户类型的最大登陆名(工号)
     * @param params
     * @return
     */
    public String queryMaxLoginName(String usertype){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("delflag", JsdpConstants.HFMP_Delflag_N);
    	params.put("usertype", usertype);
    	String maxLoginName = sysRbacUserMapper.queryMaxLoginName(params);
    	if(CollectionUtils.isEmpty(maxLoginName)){
    		//如果登陆名为空,默认一个登陆名
    		return "1";
    	}
    	long LoginName = Long.parseLong(maxLoginName);
    	return String.valueOf(LoginName+1);
    }
}
