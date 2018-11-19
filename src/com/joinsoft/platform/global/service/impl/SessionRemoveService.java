package com.joinsoft.platform.global.service.impl;

import com.joinsoft.CacheConstants;
import com.joinsoft.platform.global.service.ISessionRemoveService;
import com.joinsoft.platform.global.service.IXmemCacheService;

public class SessionRemoveService implements ISessionRemoveService {

	private IXmemCacheService xmemCacheService;
	
	@Override
	public void removeSessionUser(String sessionId) {
		xmemCacheService.delete(String.format(CacheConstants.SESSION_SYS_RBAC_USER_KEY, new Object[] { sessionId }));
	}
	
	@Override
	public void removeSessionOrg(String sessionId){
		xmemCacheService.delete(String.format(CacheConstants.SESSION_SYS_RBAC_ORG_KEY, new Object[] { sessionId }));
	}

	@Override
	public void removeSessionNavMenu(String sessionId) {
		xmemCacheService.delete(String.format(CacheConstants.SESSION_SYS_RBAC_MENU_TOP_KEY, new Object[] { sessionId }));
	}

	@Override
	public void removeSessionLeftMenu(String sessionId) {
		xmemCacheService.delete(String.format(CacheConstants.SESSION_SYS_RBAC_MENU_LEFT_KEY, new Object[] { sessionId }));
	}

	public IXmemCacheService getXmemCacheService() {
		return xmemCacheService;
	}

	public void setXmemCacheService(IXmemCacheService xmemCacheService) {
		this.xmemCacheService = xmemCacheService;
	}

}
