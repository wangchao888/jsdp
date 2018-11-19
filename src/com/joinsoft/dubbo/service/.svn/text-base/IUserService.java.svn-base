package com.joinsoft.dubbo.service;

import java.util.List;
import java.util.Map;

import com.joinsoft.platform.global.entity.CacheAuth;
import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.entity.CacheUserBean;

public interface IUserService {

	/**
	 * 获取sessionId对应的登录用户信息
	 * @param sessionId
	 * @return
	 * @author LZX 20170609 0814
	 */
	public CacheRbacUser getRbacUserBySessionid(String sessionId);
	
	
	/**
	 * 获取sessionId对应的登录用户机构信息
	 * @param sessionId
	 * @return
	 * @author LZX 20170615 1448
	 */
	public CacheRbacOrg getRbacOrgBySessionid(String sessionId);
	
	/**
	 * 获取sessionId对应的登录用户角色信息
	 * @param sessionId
	 * @return
	 * @author LZX 20170615 1448
	 */
	public List<CacheRbacRole> getRbacRoleBySessionid(String sessionId);
	
	/**
	 * 获取sessionId对应的登录用户权限信息
	 * @param sessionId
	 * @return
	 * @author LZX 20170615 1448
	 */
	public List<CacheRbacMenu> getRbacMenuBySessionid(String sessionId);
	
	/**
	 * 
	 * 获取所有的管理单位用户
	 * @return CacheRbacUser
	 *
	 * @author LZX
	 * @since 2017年11月28日下午2:34:46
	 */
	public List<CacheRbacUser> getAllRbacUser();
	
	/**
	 * 获取当前登录用户的数据权限信息
	 *   
	 * @param sessionId
	 * @return
	 *
	 * @author LZX
	 * @since 2018年3月20日下午5:06:40
	 */
	public CacheAuth getAuthBySessionid(String sessionId);
	
	/**
	 * 获取当前登录用户的相关信息
	 *   
	 * @param sessionId
	 * @return
	 *
	 * @author LZX
	 * @since 2018年3月22日下午5:17:06
	 */
	public CacheUserBean getUserBeanBySessionid(String sessionId);
	
}
