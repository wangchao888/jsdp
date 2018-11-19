package com.joinsoft.platform.global.service;

public interface ISessionRemoveService {

	/**
	 * 删除用户
	 * @param sessionId
	 */
	public void removeSessionUser(String sessionId);
	
	/**
	 * 删除机构
	 * @param sessionId
	 */
	public void removeSessionOrg(String sessionId);
	
	/**
	 * 删除顶级菜单
	 * @param sessionId
	 */
	public void removeSessionNavMenu(String sessionId);
	
	/**
	 * 删除左侧菜单
	 * @param sessionId
	 */
	public void removeSessionLeftMenu(String sessionId);
}
