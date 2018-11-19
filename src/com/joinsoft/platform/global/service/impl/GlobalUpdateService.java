package com.joinsoft.platform.global.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joinsoft.CacheConstants;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalReadService;
import com.joinsoft.platform.global.service.IGlobalUpdateService;
import com.joinsoft.platform.global.service.IGlobalWriteService;
import com.joinsoft.platform.global.service.IXmemCacheService;

public class GlobalUpdateService implements IGlobalUpdateService {

	private IXmemCacheService xmemCacheService;
	private IGlobalReadService globalReadService;
	private IGlobalWriteService globalWriteService;

	@Override
	public List<CacheRbacApplication> updateGlobalApplication() {
		List<String> ids = globalReadService.getApplicationIds();
		List<CacheRbacApplication> list = new ArrayList<CacheRbacApplication>();
		if (ids == null || ids.size() < 0) {
			list = globalWriteService.writeGlobalApplication();
		} else {
			for (String id : ids) {
				CacheRbacApplication entity = globalReadService.getApplicationById(id);
				list.add(entity);
			}
		}
		return list;
	}
	
	
	@Override
	public Map<String, Object> getApplicationMap(){
		List<CacheRbacApplication> list = updateGlobalApplication();
		if(list != null && list.size() > 0){
			Map<String, Object> entityMap = new HashMap<String, Object>();
			for(CacheRbacApplication entity : list){
				entityMap.put(entity.getSid(), entity);
			}
			return entityMap;
		}
		return null;
	}


	@Override
	public List<CacheRbacOrg> updateGlobalOrg() {
		List<String> ids = globalReadService.getMainOrgIds();
		List<CacheRbacOrg> list = new ArrayList<CacheRbacOrg>();
		if(ids == null || ids.size() < 0){
			list = globalWriteService.writeGlobalOrg();
		} else {
			for (String id : ids) {
				CacheRbacOrg entity = globalReadService.getOrgById(id);
				list.add(entity);
			}
		}
		return list;
	}


	@Override
	public List<CacheRbacUser> updateGlobalUser() {
		List<String> ids = globalReadService.getMainUserIds();
		List<CacheRbacUser> list = new ArrayList<CacheRbacUser>();
		if(list == null || list.size() < 0){
			list = globalWriteService.writeGlobalUser();
		} else {
			for (String id : ids) {
				CacheRbacUser entity = globalReadService.getUserById(id);
				list.add(entity);
			}
		}
		return list;
	}


	@Override
	public List<CacheRbacRole> updateGlobalRole() {
		List<String> ids = globalReadService.getRoleIds();
		List<CacheRbacRole> list = new ArrayList<CacheRbacRole>();
		if(list == null || list.size() < 0){
			list = globalWriteService.writeGlobalRole();
		} else {
			for (String id : ids) {
				CacheRbacRole entity = globalReadService.getRoleById(id);
				list.add(entity);
			}
		}
		return list;
	}


//	@Override
//	public List<CacheDicType> updateGlobalDicType() {
//		List<CacheDicType> list = globalReadService.getGlobalDicType();
//		if(list == null || list.size() < 0){
//			list = globalWriteService.writeGlobalDicType();
//		}
//		return list;
//	}
//
//
//	@Override
//	public List<CacheDictContent> updateGlobalDictContent(String dictno) {
//		if(dictno != null && !"".equals(dictno)){
//			List<CacheDictContent> list = globalReadService.getGlobalDictContent(dictno);
//			if(list == null || list.size() < 0){
//				list = globalWriteService.writeGlobalDictContentByDictno(dictno);
//			}
//			return list;
//		}
//		return null;
//	}


//	@Override
//	public List<CacheParam> updateGlobalParam() {
//		List<CacheParam> list = globalReadService.getGlobalParam();
//		if(list == null || list.size() < 0){
//			list = globalWriteService.writeGlobalParam();
//		}
//		return list;
//	}
	
	
	public HashMap<String, String> addGlobalUserMap(String userSid, String sessionId){
		HashMap<String, String> userMap = globalReadService.getGlobalUserMap();
		if(userMap == null){
			userMap = new HashMap<String, String>();
		}
		userMap.put(userSid, sessionId);
		globalWriteService.writeGlobalUserMap(userMap);
		return userMap;
	}
	
	public HashMap<String, String> deleteGlobalUserMap(String userSid, String sessionId){
		HashMap<String, String> userMap = globalReadService.getGlobalUserMap();
		if(userMap != null){
			userMap = new HashMap<String, String>();
			userMap.remove(userSid);
			globalWriteService.writeGlobalUserMap(userMap);
			return userMap;
		}
		return null;
	}
	
	/**
	 * 
	 * 缓存写入用户主键和登录sessionId的对应关系
	 * 用于解决同一用户只能在一台电脑、一个浏览器登录的问题
	 * @param userId
	 * @param sessionId
	 *
	 * @author LZX
	 * @since 2017年12月8日下午4:25:54
	 */
	@Override
	public void addUserIdAndSessionId(String userId, String sessionId) {
		xmemCacheService.set(String.format(CacheConstants.GLOBAL_SYS_RBAC_USER_SESSION_KEY, new Object[] { userId }), sessionId);
	}
	@Override
	public void deleteUserIdAndSessionId(String userId) {
		xmemCacheService.delete(String.format(CacheConstants.GLOBAL_SYS_RBAC_USER_SESSION_KEY, new Object[] { userId }));
	}
	@Override
	public String getSessionIdByUserId(String userId){
		return (String)xmemCacheService.get(String.format(CacheConstants.GLOBAL_SYS_RBAC_USER_SESSION_KEY, new Object[] { userId }));
	}
	
//	@Override
//	public List<CacheArea> updateGlobalArea() {
//		List<CacheArea> list = globalReadService.getGlobalArea();
//		if(list == null || list.size() < 0){
//			list = globalWriteService.writeGlobalArea();
//		}
//		return list;
//	}
//	
//	@Override
//	public List<CacheBldType> updateGlobalBldType() {
//		List<CacheBldType> list = globalReadService.getGlobalBldType();
//		if(list == null || list.size() < 0){
//			list = globalWriteService.writeGlobalBldType();
//		}
//		return list;
//	}
	
	@Override
	public CacheRbacApplication getCoreCacheApp() {
		return globalReadService.getCoreCacheApp();
	}
	
	public IGlobalReadService getGlobalReadService() {
		return globalReadService;
	}


	public void setGlobalReadService(IGlobalReadService globalReadService) {
		this.globalReadService = globalReadService;
	}


	public IGlobalWriteService getGlobalWriteService() {
		return globalWriteService;
	}


	public void setGlobalWriteService(IGlobalWriteService globalWriteService) {
		this.globalWriteService = globalWriteService;
	}


	public IXmemCacheService getXmemCacheService() {
		return xmemCacheService;
	}


	public void setXmemCacheService(IXmemCacheService xmemCacheService) {
		this.xmemCacheService = xmemCacheService;
	}

}
