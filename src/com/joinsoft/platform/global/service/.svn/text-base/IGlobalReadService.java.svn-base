package com.joinsoft.platform.global.service;

import java.util.HashMap;
import java.util.List;

import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;

/**
 * 缓存读取接口
 * @author LZX 2017-06-07 1402
 *
 */
public interface IGlobalReadService {

	/**
	 * 获取应用列表
	 * Key: GLOBAL_SYS_RBAC_APPLICATION_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 */
	public List<CacheRbacApplication> getGlobalApplication();
	
	/**
	 * 获取机构列表
	 * Key: GLOBAL_SYS_RBAC_ORG_LIST_KEY
	 * Value: 应用列表List集合
	 */
	public List<CacheRbacOrg> getGlobalOrg();
	
	/**
	 * 获取用户列表
	 * Key: GLOBAL_SYS_RBAC_USER_LIST_KEY
	 * Value: 应用列表List集合
	 */
	public List<CacheRbacUser> getGlobalUser();
	
	/**
	 * 获取角色列表
	 * Key: GLOBAL_SYS_RBAC_ROLE_LIST_KEY
	 * Value: 应用列表List集合
	 */
	public List<CacheRbacRole> getGlobalRole();
	
	/**
	 * 获取字典信息
	 * Key: GLOBAL_SYS_DICT_CONTENT_DICTNO_s%  s%是字典类别的占位符
	 * Value: 字典类别对应的字典明细List集合
	 */
//	public List<CacheDicType> getGlobalDicType();
//	public List<CacheDictContent> getGlobalDictContent(String dictno);
	
	/**
	 * 获取参数列表
	 * Key: GLOBAL_SYS_PARAM_LIST_KEY
	 * Value: 应用列表List集合
	 */
//	public List<CacheParam> getGlobalParam();
	
	/**
	 * 获取全局的用户Map信息
	 * @return
	 */
	public HashMap<String, String> getGlobalUserMap();
	
	/**
	 * 获取行政区代码列表
	 * Key: GLOBAL_SYS_AREA_LIST_KEY
	 * Value: 应用列表List集合
	 */
//	public List<CacheArea> getGlobalArea();
	
	/**
	 * 获取自然幢类型列表
	 * Key: GLOBAL_D_BLDTYPE_LIST_KEY
	 * Value: 应用列表List集合
	 */
//	public List<CacheBldType> getGlobalBldType();
	
	/**
	 * 
	 * 获取应用主键列表
	 * Key: GLOBAL_SYS_RBAC_APPLICATION_IDS_KEY
	 * @return
	 *
	 * @author LZX
	 * @since 2017年12月7日下午4:52:38
	 */
	public List<String> getApplicationIds();
	/**
	 * 
	 * 获取内部机构主键列表
	 * Key: GLOBAL_SYS_RBAC_ORG_IDS_KEY
	 * @return
	 *
	 * @author LZX
	 * @since 2017年12月7日下午4:52:38
	 */
	public List<String> getMainOrgIds();
	/**
	 * 
	 * 获取内部机构用户主键列表
	 * Key: GLOBAL_SYS_RBAC_USER_IDS_KEY
	 * @return
	 *
	 * @author LZX
	 * @since 2017年12月7日下午4:52:38
	 */
	public List<String> getMainUserIds();
	/**
	 * 
	 * 获取角色主键列表
	 * Key: GLOBAL_SYS_RBAC_ROLE_IDS_KEY
	 * @return
	 *
	 * @author LZX
	 * @since 2017年12月7日下午4:52:38
	 */
	public List<String> getRoleIds();
	/**
	 * 
	 * 获取应用对象
	 * Key: GLOBAL_SYS_RBAC_APPLICATION_KEY_%s
	 * @return
	 *
	 * @author LZX
	 * @since 2017年12月7日下午4:52:38
	 */
	public CacheRbacApplication getApplicationById(String id);
	/**
	 * 
	 * 获取机构对象
	 * Key: GLOBAL_SYS_RBAC_ORG_KEY_%s
	 * @return
	 *
	 * @author LZX
	 * @since 2017年12月7日下午4:52:38
	 */
	public CacheRbacOrg getOrgById(String id);
	/**
	 * 
	 * 获取用户对象
	 * Key: GLOBAL_SYS_RBAC_USER_KEY_%s
	 * @return
	 *
	 * @author LZX
	 * @since 2017年12月7日下午4:52:38
	 */
	public CacheRbacUser getUserById(String id);
	/**
	 * 
	 * 获取角色对象
	 * Key: GLOBAL_SYS_RBAC_ROLE_KEY_%s
	 * @return
	 *
	 * @author LZX
	 * @since 2017年12月7日下午4:52:38
	 */
	public CacheRbacRole getRoleById(String id);
	/**
	 * 
	 * 获取核心应用
	 * Key: GLOBAL_CORE_APPLICATION_KEY
	 * @return
	 *
	 * @author LZX
	 * @since 2017年12月7日下午4:52:38
	 */
	public CacheRbacApplication getCoreCacheApp();
	
}
