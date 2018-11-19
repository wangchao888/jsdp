package com.joinsoft.dubbo.service;

import java.util.List;

import com.joinsoft.platform.global.entity.CacheArea;
import com.joinsoft.platform.global.entity.CacheBldType;
import com.joinsoft.platform.global.entity.CacheParam;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;

/**
 * 通用服务类
 * @author LZX 2017-07-14 0958
 *
 */
public interface ICommonService {

	/**
	 * 获取行政区代码列表
	 * @return
	 */
//	public List<CacheArea> getCacheAreaList();
	/**
	 * 获取自然幢类型代码列表
	 * 孙晨阳  2017-07-19
	 */
//	public List<CacheBldType> getBldtypeAreaList();
	
	/**
	 * 获取核心应用
	 * @return
	 * @author LZX 2017-07-19 1658
	 */
	public CacheRbacApplication getCoreCacheApp();
	
	/**
	 * 获取所有角色信息列表
	 * @return
	 */
	public List<CacheRbacRole> getCacheRbacRoleList();
	
	/**
	 * 获取所有用户信息列表
	 * @return
	 */
	public List<CacheRbacUser> getCacheRbacUserList();
	
	/**
	 * 获取系统参数
	 * @return
	 */
//	public List<CacheParam> getCacheParamList();
	
	/**
	 * 根据参数编号获取参数对象
	 * @param paramno
	 * @return
	 * @author LZX
	 */
//	public CacheParam getCacheParam(String paramno);
	
}
