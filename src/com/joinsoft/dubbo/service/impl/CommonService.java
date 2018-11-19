package com.joinsoft.dubbo.service.impl;

import java.util.List;


import com.joinsoft.dubbo.service.ICommonService;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalUpdateService;

public class CommonService implements ICommonService {
	
	private IGlobalUpdateService globalUpdateService; 
	
//	@Override
//	public List<CacheArea> getCacheAreaList() {
//		return getGlobalUpdateService().updateGlobalArea();
//	}
//	
//	@Override
//	public List<CacheBldType> getBldtypeAreaList() {
//		return getGlobalUpdateService().updateGlobalBldType();
//        
//	}

	/**
	 * 返回核心应用
	 * @author LZX 2017-07-19 1735
	 */
	@Override
	public CacheRbacApplication getCoreCacheApp() {
		return getGlobalUpdateService().getCoreCacheApp();
	}

	@Override
	public List<CacheRbacRole> getCacheRbacRoleList() {
		return getGlobalUpdateService().updateGlobalRole();
	}

	@Override
	public List<CacheRbacUser> getCacheRbacUserList() {
		return getGlobalUpdateService().updateGlobalUser();
	}
	
//	@Override
//	public List<CacheParam> getCacheParamList() {
//		return getGlobalUpdateService().updateGlobalParam();
//	}
	
	/**
	 * 根据参数编号获取参数对象
	 * @param paramno
	 * @return
	 * @author LZX
	 */
//	@Override
//	public CacheParam getCacheParam(String paramno) {
//		if (paramno != null && !"".equals(paramno)) {
//			List<CacheParam> list = getCacheParamList();
//			for (CacheParam entity : list) {
//				if (paramno.equals(entity.getParamno())) {
//					return entity;
//				}
//			}
//		}
//		return null;
//	}
	
	public IGlobalUpdateService getGlobalUpdateService() {
		return globalUpdateService;
	}

	public void setGlobalUpdateService(IGlobalUpdateService globalUpdateService) {
		this.globalUpdateService = globalUpdateService;
	}

}
