package com.joinsoft.platform.global.service;

import java.util.List;

import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;

public interface ISessionReadService {

	/**
	 * 获取sessionId对应的登录用户信息
	 * @param sessionId
	 * @return
	 */
	public CacheRbacUser getSessionUser(String sessionId);
	
	/**
	 * 获取sessionId对应的登录用户信息，实现轮滑机制
	 * @param sessionId
	 * @return
	 */
	public CacheRbacUser getSessionUserByFilter(String sessionId);
	
	/**
	 * 获取sessionId对应的登录用户顶级菜单
	 * @param sessionId
	 * @return
	 */
	public List<CacheRbacMenu> getSessionNavMenu(String sessionId);
	
	/**
	 * 获取sessionId对应的登录用户左侧菜单
	 * @param sessionId
	 * @return
	 */
	public List<CacheRbacMenu> getSessionLeftMenu(String sessionId);
	
	/**
	 * 获取sessionId对应的登录用户机构信息
	 * @param sessionId
	 * @return
	 */
	public CacheRbacOrg getSessionOrg(String sessionId);
	
	/**
	 * 获取sessionId对应的登录用户角色信息
	 * @param sessionId
	 * @return
	 */
	public List<CacheRbacRole> getSessionRole(String sessionId);
	
}
