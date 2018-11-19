package com.joinsoft.platform.login.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.joinsoft.SessionConstants;
import com.joinsoft.core.bean.BeanUtils;
import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.exception.ServiceException;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.json.JsonUtils;
import com.joinsoft.module.HfmpConstants;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.application.service.SysRbacApplicationService;
import com.joinsoft.module.system.dept.dao.SysOrgAreaMapper;
import com.joinsoft.module.system.dept.dao.SysRbacOrgMapper;
import com.joinsoft.module.system.menu.dao.SysRbacMenuMapper;
import com.joinsoft.module.system.user.entity.SysRbacUser;
import com.joinsoft.platform.global.entity.CacheOrgArea;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalUpdateService;
import com.joinsoft.platform.global.service.ISessionReadService;
import com.joinsoft.platform.global.service.ISessionRemoveService;
import com.joinsoft.platform.global.service.ISessionWriteService;

/**
 * 登陆service层
 * @author gx
 * @since 2017-05-23
 *
 */
@Service
public class LoginService {
	
	@Autowired
	private SysRbacMenuMapper sysRbacMenuMapper;
	@Autowired
	private SysRbacOrgMapper sysRbacOrgMapper;
	@Autowired
	private SysOrgAreaMapper sysOrgAreaMapper;
	@Autowired
	private ISessionWriteService sessionWriteService;
	@Autowired
	private ISessionReadService sessionReadService;
	@Autowired
	private ISessionRemoveService sessionRemoveService;
	@Autowired
	private IGlobalUpdateService globalUpdateService;
	@Autowired
	private SysRbacApplicationService applicationService;
	
	private static final Logger log = LoggerFactory
			.getLogger(LoginService.class);
	
	/**
	 * 通过当前登陆用户的编号获取该用户对应的菜单项
	 * @param string loginname
	 * @param string pid
	 * @return
	 */
	public List<CacheRbacMenu> getSysRbacMenuByRole(String loginname, String pid){
		if(!StringUtils.isEmpty(loginname)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("loginname", loginname);
//			if(!StringUtils.isEmpty(pid)){
//				params.put("pid", pid);
//			}else{
//				params.put("isroot", "0");
//			}
			params.put("order", "to_number(MENU.menuno) asc");
			List<CacheRbacMenu> menuList = sysRbacMenuMapper.getSysRbacMenuByRole(params);
			return menuList;
		}
		return null;
	}
	/**
	 * 登录时放入当前用户的缓存信息
	 * @param loginname
	 * @author LZX 2017-05-26
	 */
	public void setCacheinfo(SysRbacUser user, String sessionId) throws Exception{
		CacheRbacUser cacheUser = new CacheRbacUser();
		BeanUtils.copy(user, cacheUser);
		//放入用户信息
		sessionWriteService.addSessionUser(sessionId, cacheUser);
		//放入用户权限信息
		List<CacheRbacMenu> menuList = getSysRbacMenuByRole(user.getLoginname(), null);
		if(menuList != null && menuList.size() > 0){
			List<CacheRbacMenu> navMenuList = new ArrayList<CacheRbacMenu>(); //定义头部菜单
			List<CacheRbacMenu> leftMenuList = new ArrayList<CacheRbacMenu>();//定义左侧菜单
			//将用户的头部和左侧菜单信息进行分离
			for(int t=0;t<menuList.size();t++){
				CacheRbacMenu menu = menuList.get(t);
				if(menu.getPid() == null){
					navMenuList.add(menu);
				}else{
					leftMenuList.add(menu);
				}
			}
			sessionWriteService.addSessionNavMenu(sessionId, navMenuList);
			sessionWriteService.addSessionLeftMenu(sessionId, leftMenuList);
		} else {
			//删除顶级和左侧菜单 scy	2018/07/03
			sessionRemoveService.removeSessionNavMenu(sessionId);
			sessionRemoveService.removeSessionLeftMenu(sessionId);
			
			throw new ServiceException(JsdpConstants.HFMP_RESULT_FAIL+"_登录失败，用户无任何菜单权限!");
		}
		//放入用户机构信息
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", user.getOrgid());
		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
		List<CacheRbacOrg> orgList = sysRbacOrgMapper.queryListByCache(params);
		if(CollectionUtils.isNotEmpty(orgList)) {
			CacheRbacOrg orgEntity = orgList.get(0);
			if (orgEntity != null) {
				// 内部机构才进行处理
				if (JsdpConstants.RBAC_ORG_FLAG_INSIDE.equals(orgEntity.getOrgflag())) {
					params.clear();
					// 如果是一级机构（上级机构是空），非一级机构（上级机构不为空），需要递归查询顶级机构对象
					params.put("orgid", orgEntity.getSid());
					if (orgEntity.getPid() != null) {
						List<CacheRbacOrg> rootOrgList = sysRbacOrgMapper.getRootCacheOrg(params);
						if (rootOrgList != null && rootOrgList.size() > 0) {
							CacheRbacOrg rootOrg = rootOrgList.get(0);
							if (rootOrg != null) {
								if (rootOrg.getOrgNo() != null) {
									orgEntity.setOrgNo(rootOrg.getOrgNo());
								}
								if (rootOrg.getOrgname() != null) {
									orgEntity.setRootOrgname(rootOrg.getOrgname());
								}
								if (rootOrg.getSid() != null) {
									orgEntity.setRootOrgid(rootOrg.getSid());
								}
							}
						}
					} else {
						orgEntity.setRootOrgname(orgEntity.getOrgname());
						orgEntity.setRootOrgid(orgEntity.getSid());
					}
					// 查询数据权限
					List<CacheOrgArea> orgAreaList = sysOrgAreaMapper.queryListByCache(params);
					if(orgAreaList != null && orgAreaList.size() > 0){
						orgEntity.setAreaList(orgAreaList);
					}
				}else {
					orgEntity.setRootOrgname(orgEntity.getOrgname());
					orgEntity.setRootOrgid(orgEntity.getSid());
				}
				
//				userBean.setOrgname(orgEntity.getOrgname());
			}
			sessionWriteService.addSessionOrg(sessionId, orgEntity);
		}else {
			throw new ServiceException(JsdpConstants.HFMP_RESULT_FAIL+"_登录失败，登录用户查询不到所属单位!");
		}
	}
	
	/**
	 * 放入全局用户信息
	 * @param userSid
	 * @param sessionId
	 */
	public void setGlobalUserMap(String userSid, String sessionId){
		globalUpdateService.addGlobalUserMap(userSid, sessionId);
	}
	
	/**
	 * 
	 * 放入全局用户登录和会话对应关系
	 * @param userSid
	 * @param sessionId
	 *
	 * @author LZX
	 * @since 2017年12月13日下午1:57:57
	 */
	public void writeUserIdAndSessionId(String userSid, String sessionId){
		String sid = globalUpdateService.getSessionIdByUserId(userSid);
		if (sid != null) {// 如果存在以前的对应关系，那么需要删除旧的关系
			sessionRemoveService.removeSessionUser(sid);
			sessionRemoveService.removeSessionOrg(sid);
			sessionRemoveService.removeSessionNavMenu(sid);
			sessionRemoveService.removeSessionLeftMenu(sid);
		}
		globalUpdateService.addUserIdAndSessionId(userSid, sessionId);
	}
	
	public String getLeftMenuOfJson(String sessionId, String ctx) throws JsonGenerationException, JsonMappingException, IOException{
		//从缓存中拿到当前用户的顶部和左侧菜单
		List<CacheRbacMenu> navMenuList = (List<CacheRbacMenu>)sessionReadService.getSessionNavMenu(sessionId);
		List<CacheRbacMenu> leftMenuList = (List<CacheRbacMenu>)sessionReadService.getSessionLeftMenu(sessionId);
		List<Map<String, Object>> leftMapList = new ArrayList<Map<String, Object>>();
		//从缓存中将应用信息拿出来，在拼接菜单的时候使用 2017-06-08 1516
		Map<String, Object> appMap = globalUpdateService.getApplicationMap();
		
		//查询顶部菜单信息 
		if (CollectionUtils.isNotBank(navMenuList)) {
			for(CacheRbacMenu navMenu : navMenuList){
				Map<String, Object> leftMap = new HashMap<String, Object>();
				leftMap.put(navMenu.getSid(), iterateMenusOfJson(leftMenuList, navMenu.getSid(), sessionId, ctx, appMap));
				leftMapList.add(leftMap);
			}
		}
		return JsonUtils.ObjectToJson(leftMapList);
	}
	
	public List<Map<String, Object>> iterateMenusOfJson(List<CacheRbacMenu> menuList, String parentId, String sessionId, String ctx, Map<String, Object> appMap){
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for(CacheRbacMenu entity : menuList){
			String sid = entity.getSid();
			String pid = entity.getPid();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			if(!StringUtils.isEmpty(pid)){
				if(pid.equals(parentId)){  
					dataMap.put("sid", sid);
					dataMap.put("zonecode", entity.getZonecode());
					dataMap.put("appid", entity.getAppid());
					dataMap.put("menuname", entity.getMenuname());
					dataMap.put("pid", pid);
					dataMap.put("menuno", entity.getMenuno());
					dataMap.put("menuico", entity.getMenuico());
                    if(isleaf(menuList, sid)){ //叶子节点，需要拼接url http://192.168.0.147:8080/
                    	if(entity.getMenuurl() == null || "".equals(entity.getMenuurl()) || "#".equals(entity.getMenuurl())){
                    		dataMap.put("menuurl", "#");
                    	}else{
                    		String url = getAppUrlBySid(entity.getAppid(), appMap);
                    		String menuid = "";
                    		if (entity.getMenuurl().indexOf("?") > 0) {
                    			menuid = "&menuid=" + entity.getSid();
							} else {
								menuid = "?menuid=" + entity.getSid();
							}
                    		dataMap.put("menuurl", url + entity.getMenuurl() + menuid);
                    		dataMap.put(SessionConstants.SESSIONID, sessionId);
                    	}
                    	dataMap.put("menuid", entity.getSid());
                    }else{ //非叶子节点
                    	dataMap.put("menuurl", "#");
                    	dataMap.put("childmenu", iterateMenusOfJson(menuList, sid, sessionId, ctx, appMap));
                    }
                }  
				if (dataMap != null && dataMap.size() > 0) {
					dataList.add(dataMap);
				}
			}
		}
		return dataList;
	}
	
	/**
	 * 获取左侧的菜单
	 * @param pid
	 * @param sessionId
	 * @param ctx
	 * @return
	 * @author LZX 
	 * @version 1.0版：点击顶部菜单，异步获取左侧菜单，这样存在一个问题，影响多标签页的实现功能。 
	 * @date 2017-05-26 1530
	 * @version 1.1版：登录时到main页面，该用户的菜单全部生成，点击顶部菜单使用隐藏的方式实现。 
	 * @date 2017-06-02 0838
	 * @date 2017-06-08 1512 添加应用URL拼接
	 */
	public List<String> getLeftMenu(String sessionId, String ctx){
		//从缓存中拿到当前用户的顶部和左侧菜单
		List<CacheRbacMenu> navMenuList = (List<CacheRbacMenu>)sessionReadService.getSessionNavMenu(sessionId);
		List<CacheRbacMenu> leftMenuList = (List<CacheRbacMenu>)sessionReadService.getSessionLeftMenu(sessionId);
		//从缓存中将应用信息拿出来，在拼接菜单的时候使用 2017-06-08 1516
		Map<String, Object> appMap = globalUpdateService.getApplicationMap();
		
		//查询顶部菜单信息 
		List<String> leftMenus = new ArrayList<String>();
		for(CacheRbacMenu navMenu : navMenuList){
			StringBuilder menuStr = new StringBuilder();
			if(navMenu.getMenuname().equals("开发平台")){
				String retStr = menuStr.append(iterateMenusByAdminlte(leftMenuList, navMenu.getSid(), sessionId, ctx, appMap)).toString();
				leftMenus.add(retStr);
			}
		}
		return leftMenus;
	}
	
	/**
	 * 
	 * @param menuList
	 * @param parentId
	 * @param sessionId
	 * @param ctx
	 * @param appMap
	 * @return
	 * @author LZX 2017-09-14 修改左侧菜单显示图标
	 */
	public StringBuilder iterateMenus(List<CacheRbacMenu> menuList, String parentId, String sessionId, String ctx, Map<String, Object> appMap){
		StringBuilder menuStr = new StringBuilder("");
		for(CacheRbacMenu entity : menuList){
			String sid = entity.getSid();
			String pid = entity.getPid();
			if(!StringUtils.isEmpty(pid)){
				if(pid.equals(parentId)){  
                    if(isleaf(menuList, sid)){ //叶子节点，需要拼接url http://192.168.0.147:8080/
                    	if(entity.getMenuurl() == null || "".equals(entity.getMenuurl()) || "#".equals(entity.getMenuurl())){
                    		menuStr.append("<li name=\"").append(pid).append("\"><a class=\"J_menuItem\" href='#'");
                    	}else{
                    		String url = getAppUrlBySid(entity.getAppid(), appMap);
                    		menuStr.append("<li name=\"").append(pid).append("\"><a class=\"J_menuItem\" href='").append(url).append(entity.getMenuurl());
                    		if (entity.getMenuurl().contains("?")) {
                    			menuStr.append("&menuid=");
                    		} else {
                    			menuStr.append("?menuid=");
                    		}
                    		menuStr.append(entity.getSid())
                    			.append("&").append(SessionConstants.SESSIONID).append("=")
                        		.append(sessionId)
                        		.append("'");
                    	}
                    	menuStr.append(" menuid='").append(entity.getSid()).append("'>")
                    	.append("<i class=\"fa ").append(entity.getMenuico()).append(" fa-lg\"></i>")
                    	.append("<span>").append(entity.getMenuname()).append("</sapn>")
                    	.append("</a></li>");
                    }else{ //非叶子节点
                    	menuStr.append("<li name=\"").append(pid).append("\"><a href='#'><i class=\"fa ").append(entity.getMenuico()).append(" fa-lg\"></i><span class=\"nav-label\">")
                    	  .append(entity.getMenuname())
                    	  .append("</span><span class=\"fa arrow\"></span></a>\n");
                    	menuStr.append("<ul class=\"nav nav-second-level\">");
                    	menuStr.append(iterateMenus(menuList, sid, sessionId, ctx, appMap));
                    	menuStr.append("</ul></li>");
                    }
                }  
			}
		}
		return menuStr;
	}
	/**
	 *   菜单结构：
	 *   <li class="active treeview menu-open">
     *     <a href="#">
     *       <i class="fa fa-dashboard"></i> <span>Dashboard</span>
     *       <span class="pull-right-container">
     *         <i class="fa fa-angle-left pull-right"></i>
     *       </span>
     *     </a>
     *     <ul class="treeview-menu">
     *       <li><a href="index.html"><i class="fa fa-circle-o"></i> Dashboard v1</a></li>
     *       <li class="active"><a href="index2.html"><i class="fa fa-circle-o"></i> Dashboard v2</a></li>
     *     </ul>
     *   </li>
	 * @param menuList
	 * @param parentId
	 * @param sessionId
	 * @param ctx
	 * @param appMap
	 * @return
	 */
	public StringBuilder iterateMenusByAdminlte(List<CacheRbacMenu> menuList, String parentId, String sessionId, String ctx, Map<String, Object> appMap){
		StringBuilder menuStr = new StringBuilder("");
		for(CacheRbacMenu entity : menuList){
			String sid = entity.getSid();
			String pid = entity.getPid();
			if(!StringUtils.isEmpty(pid)){
				if(pid.equals(parentId)){  
                    if(isleaf(menuList, sid)){ //叶子节点，需要拼接url http://192.168.0.147:8080/
                    	if(entity.getMenuurl() == null || "".equals(entity.getMenuurl()) || "#".equals(entity.getMenuurl())){
                    		menuStr.append("<li name=\"").append(pid).append("\"><a class=\"J_menuItem\" href='#'");
                    	}else{
                    		String url = getAppUrlBySid(entity.getAppid(), appMap);
                    		menuStr.append("<li name=\"").append(pid).append("\"><a class=\"J_menuItem\" href='").append(url).append(entity.getMenuurl());
                    		if (entity.getMenuurl().contains("?")) {
                    			menuStr.append("&menuid=");
                    		} else {
                    			menuStr.append("?menuid=");
                    		}
                    		menuStr.append(entity.getSid())
                        		.append("&").append(SessionConstants.SESSIONID).append("=")
                        		.append(sessionId)
                        		.append("'");
                    	}
                    	menuStr.append(" menuid='").append(entity.getSid()).append("'>")
                    	.append("<i class=\"fa fa-circle-o\"></i>")
                    	.append(entity.getMenuname())
                    	.append("</a></li>");
                    }else{ //非叶子节点
                    	menuStr.append("<li class=\"treeview\" name=\"").append(pid).append("\">")
                    	    .append("<a href='#'>")
                    	    .append("<i class=\"fa ").append(entity.getMenuico()).append("\"></i> <span>").append(entity.getMenuname()).append("</span>")
                    	    .append("<span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span>")
                    	    .append("</a>\n")
                    	    .append("<ul class=\"treeview-menu\">")
                    	    .append(iterateMenusByAdminlte(menuList, sid, sessionId, ctx, appMap))
                    	    .append("</ul>")
                    	    .append("</li>");
                    }
                }  
			}
		}
		return menuStr;
	}
	
	/**
	 * 根据应用sid获取应用路径，格式为 http://127.0.0.1:8080/jsdp
	 * @param sid
	 * @return
	 */
	public String getAppUrlBySid(String sid, Map<String, Object> entityMap){
		if(!StringUtils.isBlank(sid) && entityMap != null){
			CacheRbacApplication entity = (CacheRbacApplication)entityMap.get(sid);
			String appip = entity.getAppip();
			String appport = entity.getAppport();
			String webview = entity.getWebview();
			if(!StringUtils.isBlank(appip) && !StringUtils.isBlank(appport) && !StringUtils.isBlank(webview)){
				StringBuilder url = new StringBuilder(JsdpConstants.APPLICATION_PROTOCOL);
				url.append(appip).append(":").append(appport).append("/").append(webview).toString();
//				url.append("192.168.0.109:8080/").append(webview).toString();
//				url.append("127.0.0.1:8080/").append(webview).toString();
				return url.toString();
			}
		}
		return "";
	}
	
	/**
	 * 判断是否是叶子节点，如果该sid作为pid不存在记录，则说明是叶子节点
	 * @param menuList
	 * @param sid
	 * @return
	 */
	public boolean isleaf(List<CacheRbacMenu> menuList, String sid){
		for(CacheRbacMenu menu : menuList){
			if(menu.getPid().equals(sid)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 移除登录用户的session信息
	 * @param sid
	 * @param sessionid
	 */
	public void removeCache(String sid, String sessionId){
		globalUpdateService.deleteUserIdAndSessionId(sid);
		sessionRemoveService.removeSessionUser(sessionId);
		sessionRemoveService.removeSessionOrg(sessionId);
		sessionRemoveService.removeSessionNavMenu(sessionId);
		sessionRemoveService.removeSessionLeftMenu(sessionId);
	}
	/**
	 * 拼接维修资金地址
	 * @return
	 *
	 * @author scy
	 * @since 2018年10月17日上午11:06:12
	 */
	public String address(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("appdesc", "维修资金");
		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
		Map<String,Object> entity = applicationService.getEntity(params);
		String addres = "http://"+StringUtils.getString(entity, "APPIP")+":"+StringUtils.getString(entity, "APPPORT")+"/"+StringUtils.getString(entity, "WEBVIEW")+"/";
		return addres;
	}

	
}
