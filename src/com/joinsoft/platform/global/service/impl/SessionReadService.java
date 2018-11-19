package com.joinsoft.platform.global.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joinsoft.CacheConstants;
import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.ISessionReadService;
import com.joinsoft.platform.global.service.IXmemCacheService;

public class SessionReadService implements ISessionReadService {

	private IXmemCacheService xmemCacheService;
	private static final Logger log = LoggerFactory.getLogger(SessionReadService.class);
	
	@Override
	public CacheRbacUser getSessionUser(String sessionId) {
		return (CacheRbacUser)getXmemCacheService().get(String.format(CacheConstants.SESSION_SYS_RBAC_USER_KEY, new Object[] { sessionId }));
	}
	
	@Override
	public CacheRbacUser getSessionUserByFilter(String sessionId){
		CacheRbacUser cacheUser = getSessionUser(sessionId);
		if(cacheUser != null){
			getXmemCacheService().touch(String.format(CacheConstants.SESSION_SYS_RBAC_USER_KEY, new Object[] { sessionId }), CacheConstants.SESSION_TIMEOUT);
			return cacheUser;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CacheRbacMenu> getSessionNavMenu(String sessionId) {
		return (List<CacheRbacMenu>)getXmemCacheService().get(String.format(CacheConstants.SESSION_SYS_RBAC_MENU_TOP_KEY, new Object[] { sessionId }));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CacheRbacMenu> getSessionLeftMenu(String sessionId) {
		return (List<CacheRbacMenu>)getXmemCacheService().get(String.format(CacheConstants.SESSION_SYS_RBAC_MENU_LEFT_KEY, new Object[] { sessionId }));
	}
	

	@Override
	public CacheRbacOrg getSessionOrg(String sessionId) {
		return (CacheRbacOrg)getXmemCacheService().get(String.format(CacheConstants.SESSION_SYS_RBAC_ORG_KEY, new Object[] { sessionId }));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CacheRbacRole> getSessionRole(String sessionId) { 
		return (List<CacheRbacRole>)getXmemCacheService().get(String.format(CacheConstants.SESSION_SYS_RBAC_ROLE_KEY, new Object[] { sessionId }));
	}
	
	public IXmemCacheService getXmemCacheService() {
		return xmemCacheService;
	}

	public void setXmemCacheService(IXmemCacheService xmemCacheService) {
		this.xmemCacheService = xmemCacheService;
	}
	
}
