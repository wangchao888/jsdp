package com.joinsoft.dubbo.service;

import com.joinsoft.module.system.dept.entity.SysRbacOrg;
import com.joinsoft.platform.global.entity.CacheActorMenu;
import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.HfmpCorpUser;
import java.util.List;
import java.util.Map;

/**
 * 外部单位公用接口
 * @author LZX 2017-07-21 1323
 *
 */
public interface ICorpinfoService {

	/**
	 * 添加外部单位用户
	 *     需要添加机构，第一次添加企业的用户时添加机构。
	 *     初始化用户角色权限信息，如果是企业管理员需要添加角色信息；如果是企业非管理员需要添加用户对应的权限。
	 * @param entity
	 * @return
	 */
	public String insertUser(HfmpCorpUser entity);
	
	/**
	 * 更新外部单位用户信息
	 * @param entity
	 * @return
	 */
	public String updateUser(HfmpCorpUser entity);
	
	/**
	 * 删除外部单位用户信息
	 * @param entity
	 * @return
	 * @author GX 
	 */
	public String deleteUser(HfmpCorpUser entity);
	
	/**
	 * 获取外部单位用户信息
	 * @param id
	 * @return
	 */
	public HfmpCorpUser getUser(String id);
	
	/**
	 * 通过用户类型获取用户信息
	 * @param usertype
	 * @return
	 * @author GX
	 */
	public List<HfmpCorpUser> getUserList(String corpno);
	
	/**
	 * 重置用户密码
	 * @param id
	 * @return
	 */
	public String updateUserPasswd(String id);
	
	/**
	 * 仅限于修改企业名称
	 * @param entity
	 * @return
	 */
	public String updateCorpinfo(HfmpCorpUser entity);
	
	/**
	 * 根据用户类型获取登录名
	 * @param usertype
	 * @return
	 */
	public String queryMaxLoginName(String usertype);
	
	/**
	 * 根据单位编号获取菜单列表
	 * @param corpno
	 * @return
	 * @author GX
	 */
	public List<CacheRbacMenu> queryMenuListBycorpno(String corptype);
	
	/**
	 * 通过userid查找菜单权限表中的menuids
	 * @param userid
	 * @return
	 * @author GX
	 */
	public List<CacheActorMenu> queryActorMenuListByuserid(String userid);
	
	/**
	 * 单位用户授权
	 * @param sid
	 * @param menuids
	 * @author GX
	 */
	public void corpUserAutho(String userid,String[] menuids);
	
	/**
	 * 单位注册维护到组织机构表中
	 * @return
	 *
	 * @author cxh
	 * @since 2018-4-28下午2:21:34
	 */
	public String addReference(HfmpCorpUser entity);
	
	/**
	 * 获取用户信息
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getUserInfo(Map<String,Object> params);
	
	/**
	 * 根据用户id获取用户角色信息
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getRoleByuserid(Map<String,Object> params);
}
