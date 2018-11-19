package com.joinsoft.platform.global.service;

import java.util.List;

import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacUser;

/**
 * 登录用户相关缓存信息写入接口
 * @author LZX 20170609 1526
 *
 */
public interface ISessionWriteService {

	/**
	 * 存入sessionId对应的登录用户信息
	 * @param sessionId
	 * @return
	 */
	public void addSessionUser(String sessionId, CacheRbacUser cacheUser);
	
	/**
	 * 存入sessionId对应的登录用户机构信息
	 * @param sessionId
	 * @return
	 */
	public void addSessionOrg(String sessionId, CacheRbacOrg cacheOrg);
	
	/**
	 * 存入sessionId对应的登录用户顶级菜单
	 * @param sessionId
	 * @return
	 */
	public void addSessionNavMenu(String sessionId, List<CacheRbacMenu> list);
	
	/**
	 * 存入sessionId对应的登录用户左侧菜单
	 * @param sessionId
	 * @return
	 */
	public void addSessionLeftMenu(String sessionId, List<CacheRbacMenu> list);
}
