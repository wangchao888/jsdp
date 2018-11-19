package com.joinsoft.platform.global.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;

public interface IGlobalUpdateService {

	/**
	 * 获取应用列表
	 * Key: GLOBAL_SYS_RBAC_APPLICATION_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 */
	public List<CacheRbacApplication> updateGlobalApplication();
	/**
	 * 获取应用MAP
	 * Key: sid
	 * Value: CacheRbacApplication
	 * 
	 */
	public Map<String, Object> getApplicationMap();
	
	/**
	 * 获取机构列表
	 * Key: GLOBAL_SYS_RBAC_ORG_LIST_KEY
	 * Value: 应用列表List集合
	 */
	public List<CacheRbacOrg> updateGlobalOrg();
	
	/**
	 * 获取用户列表
	 * Key: GLOBAL_SYS_RBAC_USER_LIST_KEY
	 * Value: 应用列表List集合
	 */
	public List<CacheRbacUser> updateGlobalUser();
	
	/**
	 * 获取角色列表
	 * Key: GLOBAL_SYS_RBAC_ROLE_LIST_KEY
	 * Value: 应用列表List集合
	 */
	public List<CacheRbacRole> updateGlobalRole();
	
	/**
	 * 获取字典信息
	 * Key: GLOBAL_SYS_DICT_CONTENT_DICTNO_s%  s%是字典类别的占位符
	 * Value: 字典类别对应的字典明细List集合
	 */
//	public List<CacheDicType> updateGlobalDicType();
//	public List<CacheDictContent> updateGlobalDictContent(String dictno);
	
	/**
	 * 获取参数列表
	 * Key: GLOBAL_SYS_PARAM_LIST_KEY
	 * Value: 应用列表List集合
	 */
//	public List<CacheParam> updateGlobalParam();
	
	/**
	 * 写入全局的用户Map信息
	 * @return
	 * 
	 * @author LZX 2017-06-12 0926
	 */
	public HashMap<String, String> addGlobalUserMap(String userSid, String sessionId);
	public HashMap<String, String> deleteGlobalUserMap(String userSid, String sessionId);
	
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
	public void addUserIdAndSessionId(String userSid, String sessionId);
	public void deleteUserIdAndSessionId(String userSid);
	public String getSessionIdByUserId(String userId);
	
	/**
	 * 获取行政区代码列表
	 * Key: GLOBAL_SYS_AREA_LIST_KEY
	 * Value: 应用列表List集合
	 */
//	public List<CacheArea> updateGlobalArea();
	
	/**
	 * 获取自然幢类型列表
	 * Key: GLOBAL_D_BLDTYPE_LIST_KEY
	 * Value: 应用列表List集合
	 */
//	public List<CacheBldType> updateGlobalBldType();
	
	/**
	 * 获取核心应用
	 * @return
	 *
	 * @author LZX
	 * @since 2017年12月7日下午5:36:11
	 */
	public CacheRbacApplication getCoreCacheApp();
	
}
