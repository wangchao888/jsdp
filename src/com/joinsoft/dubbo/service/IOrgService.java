package com.joinsoft.dubbo.service;

import com.joinsoft.platform.global.entity.CacheRbacOrg;

/**
 * 机构接口
 * 主管单位添加的时候需要添加一级机构信息
 * @author LZX 2018-03-16 1401
 */
public interface IOrgService {

	/**
	 * 机构信息添加
	 *   业务系统主管单位添加的时候需要添加一级机构信息;
	 *   1）添加机构表（sys_rbac_org）
	 *   2）添加机构权限表（sys_org_area）
	 *   3）更新缓存信息
	 * @param cacheRbacOrg
	 * @return
	 *
	 * @author LZX
	 * @since 2018年3月16日下午2:02:55
	 */
	public String insertOrg(CacheRbacOrg cacheRbacOrg);
	
	/**
	 * 机构信息更新
	 *   业务系统更新机构信息时调用
	 * @param cacheRbacOrg
	 * @return
	 *
	 * @author LZX
	 * @since 2018年3月16日下午2:04:40
	 */
	public String updateOrg(CacheRbacOrg cacheRbacOrg);
	
	/**
	 * 机构信息删除
	 *   业务系统删除机构信息调用
	 * @param sid
	 * @return
	 *
	 * @author LZX
	 * @since 2018年3月16日下午2:04:40
	 */
	public String deleteOrg(String sid);
}
