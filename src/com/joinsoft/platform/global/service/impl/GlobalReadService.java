package com.joinsoft.platform.global.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.joinsoft.CacheConstants;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalReadService;
import com.joinsoft.platform.global.service.IXmemCacheService;

/**
 * 缓存读取
 * @author LZX 2017-06-07 1402
 *
 */
public class GlobalReadService implements IGlobalReadService {
	
	private IXmemCacheService xmemCacheService;
	/**
	 * 重写此方法，先获取主键列表，再循环获取内容 （应用）
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月9日下午2:32:34
	 */
	@Override
	public List<CacheRbacApplication> getGlobalApplication() {
		List<CacheRbacApplication> appList = new ArrayList<CacheRbacApplication>();
		List<String>  appIds = this.getApplicationIds();
		for (int i = 0; i < appIds.size(); i++) {
			appList.add(this.getApplicationById(appIds.get(i)));
		}
		return appList;
	}
	/**
	 * 重写此方法，先获取主键列表，再循环获取内容  （机构）
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月9日下午2:36:50
	 */
	@Override
	public List<CacheRbacOrg> getGlobalOrg() {
		List<CacheRbacOrg> orgList = new ArrayList<CacheRbacOrg>();
		List<String> orgIds = this.getMainOrgIds();
		for (int i = 0; i < orgIds.size(); i++) {
			orgList.add(this.getOrgById(orgIds.get(i)));
		}
		return orgList;
	}
	/**
	 * 重写此方法，先获取主键列表，再循环获取内容  （用户）
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月9日下午2:39:13
	 */
	@Override
	public List<CacheRbacUser> getGlobalUser() {
		List<CacheRbacUser> userList = new ArrayList<CacheRbacUser>();
		List<String> userIds = this.getMainUserIds();
		for (int i = 0; i < userIds.size(); i++) {
			userList.add(this.getUserById(userIds.get(i)));
		}
		return userList;
	}
	/**
	 * 重写此方法，先获取主键列表，再循环获取内容  （角色）
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月9日下午2:39:23
	 */
	@Override
	public List<CacheRbacRole> getGlobalRole() {
		List<CacheRbacRole> roleList = new ArrayList<CacheRbacRole>();
		List<String> roleIds = this.getRoleIds();
		for (int i = 0; i < roleIds.size(); i++) {
			roleList.add(this.getRoleById(roleIds.get(i)));
		}
		return roleList;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CacheDicType> getGlobalDicType() {
//		return (List<CacheDicType>)getXmemCacheService().get(CacheConstants.GLOBAL_SYS_DIC_TYPE_LIST_KEY);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CacheDictContent> getGlobalDictContent(String dictno) {
//		return (List<CacheDictContent>)getXmemCacheService().get(String.format(CacheConstants.GLOBAL_SYS_DICT_CONTENT_DICTNO, new Object[] { dictno }));
//	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<CacheParam> getGlobalParam() {
//		return (List<CacheParam>)getXmemCacheService().get(CacheConstants.GLOBAL_SYS_PARAM_LIST_KEY);
//	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getGlobalUserMap(){
		return (HashMap<String, String>)getXmemCacheService().get(CacheConstants.GLOBAL_USER_MAP_KEY);
	}
	
//	@Override
//	public List<CacheArea> getGlobalArea() {
//		return (List<CacheArea>)getXmemCacheService().get(CacheConstants.GLOBAL_SYS_AREA_LIST_KEY);
//	}
//	
//	@Override
//	public List<CacheBldType> getGlobalBldType() {
//		return (List<CacheBldType>)getXmemCacheService().get(CacheConstants.GLOBAL_D_BLDTYPE_LIST_KEY);
//	}

	@Override
	public List<String> getApplicationIds() {
		return (List<String>)getXmemCacheService().get(CacheConstants.GLOBAL_SYS_RBAC_APPLICATION_LIST_IDS_KEY);
	}

	@Override
	public List<String> getMainOrgIds() {
		return (List<String>)getXmemCacheService().get(CacheConstants.GLOBAL_SYS_RBAC_ORG_LIST_IDS_KEY);
	}

	@Override
	public List<String> getMainUserIds() {
		return (List<String>)getXmemCacheService().get(CacheConstants.GLOBAL_SYS_RBAC_USER_LIST_IDS_KEY);
	}

	@Override
	public List<String> getRoleIds() {
		return (List<String>)getXmemCacheService().get(CacheConstants.GLOBAL_SYS_RBAC_ROLE_LIST_IDS_KEY);
	}

	@Override
	public CacheRbacApplication getApplicationById(String id) {
		return (CacheRbacApplication)getXmemCacheService().get(String.format(CacheConstants.GLOBAL_SYS_RBAC_APPLICATION_KEY, new Object[] { id }));
	}

	@Override
	public CacheRbacOrg getOrgById(String id) {
		return (CacheRbacOrg)getXmemCacheService().get(String.format(CacheConstants.GLOBAL_SYS_RBAC_ORG_KEY, new Object[] { id }));
	}

	@Override
	public CacheRbacUser getUserById(String id) {
		return (CacheRbacUser)getXmemCacheService().get(String.format(CacheConstants.GLOBAL_SYS_RBAC_USER_KEY, new Object[] { id }));
	}

	@Override
	public CacheRbacRole getRoleById(String id) {
		return (CacheRbacRole)getXmemCacheService().get(String.format(CacheConstants.GLOBAL_SYS_RBAC_ROLE_KEY, new Object[] { id }));
	}
	
	@Override
	public CacheRbacApplication getCoreCacheApp() {
		return (CacheRbacApplication)getXmemCacheService().get(CacheConstants.GLOBAL_CORE_APPLICATION_KEY);
	}
	
	public IXmemCacheService getXmemCacheService() {
		return xmemCacheService;
	}

	public void setXmemCacheService(IXmemCacheService xmemCacheService) {
		this.xmemCacheService = xmemCacheService;
	}

}
