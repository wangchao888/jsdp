package com.joinsoft.platform.global.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joinsoft.CacheConstants;
import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.ISessionWriteService;
import com.joinsoft.platform.global.service.IXmemCacheService;

/**
 * 登录用户相关缓存信息写入接口实现
 * @author LZX 20170609 1526
 *
 */
public class SessionWriteService implements ISessionWriteService {

	private IXmemCacheService xmemCacheService;
	private static final Logger log = LoggerFactory.getLogger(SessionWriteService.class);

	@Override
	public void addSessionUser(String sessionId, CacheRbacUser cacheUser) {
		getXmemCacheService().set(String.format(CacheConstants.SESSION_SYS_RBAC_USER_KEY, new Object[] { sessionId }), CacheConstants.SESSION_TIMEOUT, cacheUser);
	}
	
	public void addSessionOrg(String sessionId, CacheRbacOrg cacheOrg){
		getXmemCacheService().set(String.format(CacheConstants.SESSION_SYS_RBAC_ORG_KEY, new Object[] { sessionId }), cacheOrg);
	}

	@Override
	public void addSessionNavMenu(String sessionId, List<CacheRbacMenu> list) {
		getXmemCacheService().set(String.format(CacheConstants.SESSION_SYS_RBAC_MENU_TOP_KEY, new Object[] { sessionId }), list);
	}

	@Override
	public void addSessionLeftMenu(String sessionId, List<CacheRbacMenu> list) {
		getXmemCacheService().set(String.format(CacheConstants.SESSION_SYS_RBAC_MENU_LEFT_KEY, new Object[] { sessionId }), list);
	}

	public IXmemCacheService getXmemCacheService() {
		return xmemCacheService;
	}

	public void setXmemCacheService(IXmemCacheService xmemCacheService) {
		this.xmemCacheService = xmemCacheService;
	}
	
}
