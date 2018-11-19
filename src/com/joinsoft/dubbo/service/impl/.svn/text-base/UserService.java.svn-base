package com.joinsoft.dubbo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.system.user.dao.SysRbacUserMapper;
import com.joinsoft.module.system.userrole.dao.SysUserRoleMapper;
import com.joinsoft.platform.global.entity.CacheAuth;
import com.joinsoft.platform.global.entity.CacheOrgArea;
import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.entity.CacheUserBean;
import com.joinsoft.platform.global.service.IGlobalReadService;
import com.joinsoft.platform.global.service.ISessionReadService;

public class UserService implements IUserService {

	private ISessionReadService sessionReadService;
	private IGlobalReadService globalReadService;
	
	@Override
	public CacheRbacUser getRbacUserBySessionid(String sessionId) {
		return getSessionReadService().getSessionUserByFilter(sessionId);
	}

	@Override
	public CacheRbacOrg getRbacOrgBySessionid(String sessionId) {
		return getSessionReadService().getSessionOrg(sessionId);
	}

	@Override
	public List<CacheRbacRole> getRbacRoleBySessionid(String sessionId) {
		return getSessionReadService().getSessionRole(sessionId);
	}

	@Override
	public List<CacheRbacMenu> getRbacMenuBySessionid(String sessionId) {
		List<CacheRbacMenu> navList = getSessionReadService().getSessionNavMenu(sessionId);
		if(navList != null && navList.size() > 0){
			return navList;
		}
		return null;
	}
	
	@Override
	public List<CacheRbacUser> getAllRbacUser() {
		return getGlobalReadService().getGlobalUser();
	}
	
	@Override
	public CacheAuth getAuthBySessionid(String sessionId) {
		if (sessionId != null && !"".equals(sessionId)) {
			CacheRbacUser user = getSessionReadService().getSessionUserByFilter(sessionId);
			CacheRbacOrg org = getSessionReadService().getSessionOrg(sessionId);
			CacheAuth cacheAuth = new CacheAuth();
			cacheAuth.setOrgno(org.getOrgNo());
			cacheAuth.setUsertype(user.getUsertype());
			cacheAuth.setCorpno(org.getCorpno());
			cacheAuth.setCorptype(org.getCorpType());
			List<String> zonecodes = new ArrayList<String>();
			List<CacheOrgArea> orgAreas = org.getAreaList();
			if (orgAreas != null && orgAreas.size() > 0) {
				for (CacheOrgArea cacheOrgArea : orgAreas) {
					zonecodes.add(cacheOrgArea.getZonecode());
				}
				cacheAuth.setZonecodes(zonecodes);
			} 
			return cacheAuth;
		}
		return null;
	}
	
	@Override
	public CacheUserBean getUserBeanBySessionid(String sessionId) {
		if (sessionId != null && !"".equals(sessionId)) {
			CacheRbacUser user = getSessionReadService().getSessionUserByFilter(sessionId);
			CacheRbacOrg org = getSessionReadService().getSessionOrg(sessionId);
			CacheUserBean userBean = new CacheUserBean();
			if (user != null) {
				if (user.getSid() != null) {
					userBean.setSid(user.getSid());
				}
				if (user.getLoginname() != null) {
					userBean.setLoginname(user.getLoginname());
				}
				if (user.getUsername() != null) {
					userBean.setUsername(user.getUsername());
				}
				if (user.getUsertype() != null) {
					userBean.setUsertype(user.getUsertype());
				}
			}
			if (org != null) {
				if (org.getSid() != null) {
					userBean.setOrgid(org.getSid());
				}
				if (org.getOrgname() != null) {
					userBean.setOrgname(org.getOrgname());
				}
				if (org.getOrgNo() != null) {
					userBean.setRootorgno(org.getOrgNo());
				}
				if (org.getRootOrgname() != null) {
					userBean.setRootorgname(org.getRootOrgname());
				}
				if (org.getCorpType() != null) {
					userBean.setCorptype(org.getCorpType());
				}
			}
			return userBean;
		}
		return null;
	}

	public ISessionReadService getSessionReadService() {
		return sessionReadService;
	}

	public void setSessionReadService(ISessionReadService sessionReadService) {
		this.sessionReadService = sessionReadService;
	}

	public IGlobalReadService getGlobalReadService() {
		return globalReadService;
	}

	public void setGlobalReadService(IGlobalReadService globalReadService) {
		this.globalReadService = globalReadService;
	}

}
