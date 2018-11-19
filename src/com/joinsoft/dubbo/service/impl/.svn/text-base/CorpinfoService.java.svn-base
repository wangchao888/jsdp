package com.joinsoft.dubbo.service.impl;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.encrypt.EncryptUtils;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.dubbo.service.ICorpinfoService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.actormenu.dao.SysActorMenuMapper;
import com.joinsoft.module.system.actormenu.entity.SysActorMenu;
import com.joinsoft.module.system.corprole.dao.SysRbacCorproleMapper;
import com.joinsoft.module.system.dept.dao.SysRbacOrgMapper;
import com.joinsoft.module.system.dept.entity.SysRbacOrg;
import com.joinsoft.module.system.menu.dao.SysRbacMenuMapper;
import com.joinsoft.module.system.universal.service.RbacService;
import com.joinsoft.module.system.user.dao.SysRbacUserMapper;
import com.joinsoft.module.system.user.entity.SysRbacUser;
import com.joinsoft.module.system.userrole.dao.SysUserRoleMapper;
import com.joinsoft.module.system.userrole.entity.SysUserRole;
import com.joinsoft.platform.global.entity.CacheActorMenu;
import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.HfmpCorpUser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外部单位接口实现
 * @author LZX 2017-07-21 1442
 *
 */
public class CorpinfoService implements ICorpinfoService {

	private SysRbacOrgMapper sysRbacOrgMapper;
	private SysRbacUserMapper sysRbacUserMapper;
	private SysRbacCorproleMapper sysRbacCorproleMapper;
	private SysUserRoleMapper sysUserRoleMapper;
	private SysActorMenuMapper sysActorMenuMapper;
	private SysRbacMenuMapper sysRbacMenuMapper;
	private RbacService rbacService;
	/**
	 * 添加外部单位用户信息
	 */
	@Override
	public String insertUser(HfmpCorpUser entity) {
		if(entity != null){
			//根据企业编号查询机构是否存在，不存在的话需要添加机构
			if(entity.getCorpNo() != null){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("sid", entity.getSid());
//				param.put("delflag", JsdpConstants.HFMP_Delflag_N);
//				Long orgNum = getSysRbacOrgMapper().countSysRbacOrg(param);
//				if(orgNum == null || orgNum.intValue() == 0){
				List<Map<String, Object>> sysRbacOrgMap= sysRbacOrgMapper.getSysRbacOrg(param);
				if (CollectionUtils.isEmpty(sysRbacOrgMap)) {//如果不存在机构信息
					SysRbacOrg sysRbacOrg = new SysRbacOrg(); 
					sysRbacOrg.setSid(entity.getSid());        //单位编号存成主键
					sysRbacOrg.setCorpno(entity.getCorpNo());      //企业编号
					sysRbacOrg.setCorpType(entity.getCorpType());
					sysRbacOrg.setOrgname(entity.getCorpName());
					sysRbacOrg.setDelFlag(JsdpConstants.HFMP_Delflag_N);//默认正常
					sysRbacOrg.setCreateBy(entity.getUserid());
					sysRbacOrg.setCreateTime(DateUtils.getTimeNow());//创建时间
					sysRbacOrg.setOrgflag(JsdpConstants.RBAC_ORG_FLAG_OUTSIDE); //1 内部机构 2 外部机构
					sysRbacOrg.setOrgNo(entity.getCompno());//企业所属主管单位编号
					sysRbacOrgMapper.insertSysRbacOrg(sysRbacOrg);
				}else {//如果机构信息存在
					if (!StringUtils.getString(sysRbacOrgMap.get(0), "DELFLAG").equals(JsdpConstants.HFMP_Delflag_N)) {//如果删除标志 不正常
						SysRbacOrg sysRbacOrg = new SysRbacOrg(); 
						sysRbacOrg.setSid(entity.getSid());        //单位编号存成主键
						sysRbacOrg.setCorpno(entity.getCorpNo());      //企业编号
						sysRbacOrg.setCorpType(entity.getCorpType());//企业类型
						sysRbacOrg.setOrgname(entity.getCorpName()); //企业名称
						sysRbacOrg.setDelFlag(JsdpConstants.HFMP_Delflag_N);//默认正常
						sysRbacOrg.setOrgNo(entity.getCompno());//企业所属主管单位编号
						sysRbacOrg.setUpdateBy(entity.getUpdateby());
						sysRbacOrg.setUpdateTime(entity.getUpdatetime());
						sysRbacOrgMapper.updateSysRbacOrg(sysRbacOrg);
					}
				}
				//添加用户，应该验证Loginname的重复性
				SysRbacUser rbacUser = new SysRbacUser();
				String rbacUserId = UUIDUtils.get32UUID();
				rbacUser.setSid(rbacUserId);
				rbacUser.setZonecode(entity.getZonecode());
				rbacUser.setIsadmin(entity.getIsadmin());
				rbacUser.setUsertype(entity.getCorpType());//用户类型需要与企业类型做个统一
				rbacUser.setOrgid(entity.getSid());
				rbacUser.setLoginname(entity.getLoginname());
				rbacUser.setUsername(entity.getUsername());
				rbacUser.setState(JsdpConstants.HFMP_State_Y);      //默认启用
				rbacUser.setDelflag(JsdpConstants.HFMP_Delflag_N);  //默认正常，0_正常 1_删除
				rbacUser.setCreateby(entity.getUserid());
				rbacUser.setCreatetime(DateUtils.getTimeNow());  //创建时间
				rbacUser.setPasswd(EncryptUtils.sha1(JsdpConstants.PASS_INIT_VAL)); //密码初始值为：666666
				rbacUser.setHeadicon(JsdpConstants.USER_HEADICON);
				rbacUser.setIcopath(JsdpConstants.USER_ICOPATH);
				sysRbacUserMapper.insertSysRbacUser(rbacUser);
				//初始化角色、权限
				insertUserMenu(entity, rbacUserId);
				
				return JsdpConstants.HFMP_RESULT_SUCCESS;
			}
		}
		return JsdpConstants.HFMP_RESULT_FAIL;
	}
	/**
	 * 用户初始化，外部单位管理员初始化角色，非管理员初始化角色对应的用户权限
	 * @param entity
	 * 
	 */
	public void insertUserMenu(HfmpCorpUser entity, String rbacUserId){
		if(entity.getCorpType() != null){
			String corpType = entity.getCorpType();
			//根据企业类型查找角色和权限
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("corptype", corpType);
			param.put("delflag", JsdpConstants.HFMP_Delflag_N);
			List<Map<String, Object>> list = sysRbacCorproleMapper.getSysRbacCorprole(param);
			if(list != null && list.size() > 0){
				if(entity.getIsadmin().equals(JsdpConstants.RULE_ONE)){ //如果是管理员，保存用户和角色关系
					for(int t=0;t<list.size();t++){
						Map<String, Object> corpMap = list.get(t);
						if(corpMap.get("ROLEID") != null){
							String roleId = (String)corpMap.get("ROLEID");
							SysUserRole userRole = new SysUserRole();
							userRole.setSid(UUIDUtils.get32UUID());
							userRole.setRoleId(roleId);
							userRole.setUserId(rbacUserId);
							userRole.setRemark("管理员角色设置");
							sysUserRoleMapper.insertSysUserRole(userRole);
						}
					}
				}else{ //非管理员，保存用户的权限
					for(int t=0;t<list.size();t++){ // TODO 此处可优化，想办法从数据库存储过程解决，循环一条一条插入太笨
						Map<String, Object> corpMap = list.get(t);
						if(corpMap.get("ROLEID") != null){
							String roleId = (String)corpMap.get("ROLEID");
							param.clear();
							param.put("actorid", roleId);
							List<Map<String, Object>> menuList = sysActorMenuMapper.getSysActorMenu(param);
							if(menuList != null && menuList.size() > 0){
								for(int j=0;j<menuList.size();j++){
									Map<String, Object> menuMap = menuList.get(j);
									if(menuMap.get("MENUID") != null){
										SysActorMenu sysActorMenu = new SysActorMenu();
										sysActorMenu.setSid(UUIDUtils.get32UUID());
										sysActorMenu.setActorid(rbacUserId);
										sysActorMenu.setActortype(JsdpConstants.ACTORTYPE_USER);
										sysActorMenu.setMenuid((String)menuMap.get("MENUID"));
										sysActorMenu.setRemark("非管理员权限设置");
										sysActorMenuMapper.insertSysActorMenu(sysActorMenu);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public String updateUser(HfmpCorpUser entity) {
		if(CollectionUtils.isNotEmpty(entity)){
			sysRbacUserMapper.updateCorpUser(entity);
			return JsdpConstants.HFMP_RESULT_SUCCESS; 
		}
		return JsdpConstants.HFMP_RESULT_FAIL;
	}

	@Override
	public String deleteUser(HfmpCorpUser entity) {
		if(CollectionUtils.isNotEmpty(entity)){
			sysRbacUserMapper.updateCorpUser(entity);
			return JsdpConstants.HFMP_RESULT_SUCCESS; 
		}
		return JsdpConstants.HFMP_RESULT_FAIL;
	}

	@Override
	public HfmpCorpUser getUser(String id) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sid", id);
		List<HfmpCorpUser> userList = sysRbacUserMapper.queryCorpUserList(params);
		if(CollectionUtils.isNotEmpty(userList)){
			return userList.get(0);
		}
		return null;
	}
	
	@Override
	public List<HfmpCorpUser> getUserList(String corpno){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgid", corpno);
		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
		params.put("order", "isadmin desc ,loginname");
		List<HfmpCorpUser> userList = sysRbacUserMapper.queryCorpUserList(params);
		return userList;
	}

	@Override
	public String updateUserPasswd(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * 变更企业单位时  更新 机构信息
	 * @param entity
	 * @param rbacUserId
	 *
	 * @author 仝玉锐
	 * @since 2018-8-23下午2:20:10
	 */
	@Override
	public String updateCorpinfo(HfmpCorpUser entity) {
		if(CollectionUtils.isNotEmpty(entity)){
			SysRbacOrg sysRbacOrg = new SysRbacOrg(); 
			sysRbacOrg.setSid(entity.getSid());        //单位编号存成主键
			sysRbacOrg.setCorpno(entity.getCorpNo());      //企业编号
			sysRbacOrg.setCorpType(entity.getCorpType());
			sysRbacOrg.setOrgname(entity.getCorpName());
			sysRbacOrg.setDelFlag(entity.getDelflag());//默认正常
			sysRbacOrg.setOrgNo(entity.getCompno());
			sysRbacOrg.setUpdateBy(entity.getUpdateby());
			sysRbacOrg.setUpdateTime(entity.getUpdatetime());
			sysRbacOrgMapper.updateSysRbacOrg(sysRbacOrg);
			return JsdpConstants.HFMP_RESULT_SUCCESS; 
		}
		return JsdpConstants.HFMP_RESULT_FAIL;
	}
	/**
	 * 根据用户类型获取登录名
	 * @param usertype
	 * @return
	 */
	public String queryMaxLoginName(String usertype){
		return rbacService.queryMaxLoginName(usertype);
	}
	/**
	 * 根据用单位类型获取单位对应的菜单树
	 * @param usertype
	 * @return
	 */
	@Override
	public List<CacheRbacMenu> queryMenuListBycorpno(String corptype){
		Map<String,Object> params = new HashMap<String,Object>();
	  		params.put("order", "sid");
	  		params.put("corptype", corptype);
	  		List<CacheRbacMenu> menuTree = sysRbacMenuMapper.queryMenuTreeCorp(params);
		return menuTree;
	}
	/**
	 * 获取用户是否有菜单权限
	 * @param usertype
	 * @return
	 */
	@Override
	public List<CacheActorMenu> queryActorMenuListByuserid(String userid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("actorid", userid);
		List<CacheActorMenu> actorMenuList = sysActorMenuMapper.getSysActorMenuEntity(params);//获取用户的菜单权限表数据								 
		if(CollectionUtils.isNotEmpty(actorMenuList)){
			return actorMenuList;
		}
		return null;
	}
	
	@Override
	public void corpUserAutho(String userid, String[] menuids) {
		Map<String,Object> params = new HashMap<String,Object>();
		//sid中存放userid
		if(!StringUtils.isEmpty(userid)){
			params.put("actorid", userid);
			sysActorMenuMapper.deleteActorOrMenu(params);
		}
		for (int i = 0; i < menuids.length; i++) {
			SysActorMenu actorMenu = new SysActorMenu();
			actorMenu.setSid(UUIDUtils.get32UUID());
			actorMenu.setActortype(JsdpConstants.ACTORTYPE_USER);
			actorMenu.setActorid(userid);
			actorMenu.setMenuid(menuids[i]);
			actorMenu.setRemark("单位用户授权");
			sysActorMenuMapper.insertSysActorMenu(actorMenu);
		}
	}

	/**
     * 
     * 功能: 登录首页单位注册
     * @param entity
     * @author CXH
     */
	@Override
	public String addReference(HfmpCorpUser entity) {
		if(CollectionUtils.isNotEmpty(entity)){
			SysRbacOrg sysRbacOrg = new SysRbacOrg(); 
			sysRbacOrg.setSid(entity.getSid());                     //单位编号存成主键
			sysRbacOrg.setCorpno(entity.getCorpNo());      			//企业编号
			sysRbacOrg.setCorpType(entity.getCorpType());
			sysRbacOrg.setOrgname(entity.getCorpName());
			sysRbacOrg.setDelFlag(JsdpConstants.HFMP_Delflag_N);    //默认正常
			sysRbacOrg.setCreateBy(entity.getUserid());
			sysRbacOrg.setCreateTime(DateUtils.getTimeNow());       //创建时间
			sysRbacOrg.setOrgflag(JsdpConstants.RBAC_ORG_FLAG_OUTSIDE); //1 内部机构 2 外部机构
			sysRbacOrg.setOrgNo(entity.getCompno());
			sysRbacOrgMapper.insertSysRbacOrg(sysRbacOrg);
			return JsdpConstants.HFMP_RESULT_SUCCESS; 
		}
		return JsdpConstants.HFMP_RESULT_FAIL;
	}
	
	public SysRbacOrgMapper getSysRbacOrgMapper() {
		return sysRbacOrgMapper;
	}

	public void setSysRbacOrgMapper(SysRbacOrgMapper sysRbacOrgMapper) {
		this.sysRbacOrgMapper = sysRbacOrgMapper;
	}

	public SysRbacUserMapper getSysRbacUserMapper() {
		return sysRbacUserMapper;
	}

	public void setSysRbacUserMapper(SysRbacUserMapper sysRbacUserMapper) {
		this.sysRbacUserMapper = sysRbacUserMapper;
	}
	public SysRbacCorproleMapper getSysRbacCorproleMapper() {
		return sysRbacCorproleMapper;
	}
	public void setSysRbacCorproleMapper(SysRbacCorproleMapper sysRbacCorproleMapper) {
		this.sysRbacCorproleMapper = sysRbacCorproleMapper;
	}
	public SysUserRoleMapper getSysUserRoleMapper() {
		return sysUserRoleMapper;
	}
	public void setSysUserRoleMapper(SysUserRoleMapper sysUserRoleMapper) {
		this.sysUserRoleMapper = sysUserRoleMapper;
	}
	public SysActorMenuMapper getSysActorMenuMapper() {
		return sysActorMenuMapper;
	}
	public void setSysActorMenuMapper(SysActorMenuMapper sysActorMenuMapper) {
		this.sysActorMenuMapper = sysActorMenuMapper;
	}
	public RbacService getRbacService() {
		return rbacService;
	}
	public void setRbacService(RbacService rbacService) {
		this.rbacService = rbacService;
	}
	public SysRbacMenuMapper getSysRbacMenuMapper() {
		return sysRbacMenuMapper;
	}
	public void setSysRbacMenuMapper(SysRbacMenuMapper sysRbacMenuMapper) {
		this.sysRbacMenuMapper = sysRbacMenuMapper;
	}
	
	/**
	 * 获取所有用户信息
	 */
	@Override
	public List<Map<String, Object>> getUserInfo(Map<String, Object> params) {
		List<Map<String, Object>> dataList = sysRbacUserMapper.getSysRbacUser(params);
		return dataList;
	}

	/**
	 * 根据用户获取用户角色
	 */
	@Override
	public List<Map<String, Object>> getRoleByuserid(Map<String, Object> params) {
		List<Map<String, Object>> dataList = sysUserRoleMapper.getSysUserRole(params);
		return dataList;
	}
}
