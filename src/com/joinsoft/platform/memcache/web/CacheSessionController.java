package com.joinsoft.platform.memcache.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joinsoft.SessionConstants;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalReadService;
import com.joinsoft.platform.global.service.ISessionReadService;

@Controller
@RequestMapping(value="/cache/session/")
public class CacheSessionController {
	@Autowired
	private ISessionReadService sessionReadService;
	@Autowired
	private IGlobalReadService globalReadService;
	@Autowired
	private IUserService userService;
	
	private String url ="cache/session/";
	private String menuid = "";
	
	@RequestMapping(value = "list")
	public String list(Model model,HttpServletRequest request,String menuid){
		//获取当前登陆用户
    	this.menuid = menuid;
    	//用户信息
    	String sessionId = (String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID);
    	CacheRbacUser userEnity = sessionReadService.getSessionUser(sessionId);
    	//机构信息
    	List<CacheRbacOrg> orgList = globalReadService.getGlobalOrg();
    	for (CacheRbacOrg cacheRbacOrg : orgList) {
			if(userEnity.getOrgid().equals(cacheRbacOrg.getSid())){
				model.addAttribute("orgEntity", cacheRbacOrg);
			}
		}
    	//顶级菜单
    	List<CacheRbacMenu> navMenuList = sessionReadService.getSessionNavMenu(sessionId);
    	model.addAttribute("navMenuList", navMenuList);
    	model.addAttribute("userEntity", userEnity);
		return url + "list";
	}
	
	/**
	 * 子菜单
	 * @param model
	 * @param pid
	 * @param menuid
	 * @return
	 */
	@RequestMapping(value = "childMenuList")
	public String childMenuList(Model model,String pid,String menuid,HttpServletRequest request){
		//获取当前登陆用户
		String sessionId = (String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID);
    	//左侧菜单所有
    	List<CacheRbacMenu> leftMenuList = sessionReadService.getSessionLeftMenu(sessionId);
    	//左侧一级菜单
    	List<CacheRbacMenu> leftFirstMenuList = new ArrayList<CacheRbacMenu>();
    	//左侧二级菜单
    	List<CacheRbacMenu> LeftSecondMenuList = new ArrayList<CacheRbacMenu>();
    	List<CacheRbacMenu> menuSList = new ArrayList<CacheRbacMenu>();
    	for (CacheRbacMenu cacheRbacMenu : leftMenuList) {
			if(pid.equals(cacheRbacMenu.getPid())){
				leftFirstMenuList.add(cacheRbacMenu);
			}
		}
    	for (CacheRbacMenu leftFirstMenu : leftFirstMenuList) {
    		for (CacheRbacMenu leftMenu : leftMenuList) {
				if(leftFirstMenu.getSid().equals(leftMenu.getPid())){
					LeftSecondMenuList.add(leftMenu);
				}
			}
    	}
    	//将顶级菜单下的一级,二级菜单同时放入一个List
    	for (CacheRbacMenu cacheRbacMenu : leftFirstMenuList) {
			menuSList.add(cacheRbacMenu);
		}
    	for (CacheRbacMenu cacheRbacMenu : LeftSecondMenuList) {
			menuSList.add(cacheRbacMenu);
		}
    	model.addAttribute("leftMenuList", menuSList);
    	return url + "leftMenuList";
	}
}
