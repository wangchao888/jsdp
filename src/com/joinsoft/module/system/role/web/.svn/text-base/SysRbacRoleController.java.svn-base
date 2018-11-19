package com.joinsoft.module.system.role.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinsoft.SessionConstants;
import com.joinsoft.core.bean.ZTree;
import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.token.annotation.FormToken;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.core.utils.page.PageTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.actormenu.entity.SysActorMenu;
import com.joinsoft.module.system.actormenu.service.SysActorMenuService;
import com.joinsoft.module.system.dept.service.SysRbacOrgService;
import com.joinsoft.module.system.menu.entity.SysRbacMenu;
import com.joinsoft.module.system.menu.service.SysRbacMenuService;
import com.joinsoft.module.system.role.entity.SysRbacRole;
import com.joinsoft.module.system.role.service.SysRbacRoleService;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalWriteService;
/**
 * 角色
 * @author scy
 * @since  2018年3月30日
 */

@Controller
@RequestMapping(value="/system/role/")
public class SysRbacRoleController {
	
	private String url ="system/role/";
	
	@Autowired
	private SysActorMenuService sysActorMenuService;
	
	@Autowired
	private SysRbacMenuService sysRbacMenuService;
	
	@Autowired
	private SysRbacRoleService sysRbacRoleService;
	
	@Autowired
	private IGlobalWriteService  globalWriteService;
	
	@Autowired
	private IUserService userService;
	@Autowired
	private SysRbacOrgService sysRbacOrgService;
	
	private String menuid = "";
	
	/**
	 * 进入列表界面
	 * @param model
	 * @param menuid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月26日下午5:34:08
	 */
	@RequestMapping(value="list")
	public String list(Model model,String menuid){
		this.menuid = menuid;
		model.addAttribute("menuid", menuid);
		return url+"list";
	}
	
	/**
	 * 数据显示
	 * @param role
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月26日下午5:33:57
	 */
	@RequestMapping(value = "/show")
    @ResponseBody
    public String show(SysRbacRole role, HttpServletRequest request) {	
		String rolename = StringUtils.clean(request.getParameter("rolename"));
	    Page page = PageTools.getPageByParam(request);
        Map<String, Object> conditions = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(rolename)){
        	conditions.put("rolename", rolename);
        }
        //超级管理员不过滤
        CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
        if(!"00".equals(rbacUser.getUsertype())){
        	CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
        	conditions.put("orgno", rbacOrg.getOrgNo());
        }
        conditions.put("page", page);
        conditions.put("delflag", JsdpConstants.HFMP_Delflag_N);
        conditions.put("order", "createTime desc");
        List<Map<String, Object>> roleList = sysRbacRoleService.getEntityByPage(conditions);
        return JsonTools.ToDatatableJson(roleList, StringUtils.toString(page.getDraw()), StringUtils.toString(page.getTotalResult()));
    }
	
	/**
	 * 角色增加页面
	 * @param model
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月26日下午4:57:02
	 */
	@FormToken(save=true)
	@RequestMapping(value="initAdd")
	public String initAdd(Model model, HttpServletRequest request){
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
  		List<Map<String, Object>> orgNameList = new ArrayList<Map<String, Object>>();
  		Map<String, Object> params = new HashMap<String, Object>();
  		if(!"00".equals(rbacUser.getUsertype())){//超级管理员不过滤机构
  			CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
  			params.put("sid", rbacOrg.getRootOrgid());
  			orgNameList  = sysRbacOrgService.getEntityByList(params);
  		}else{
  			params.put("istop", "y");//顶级机构
  			params.put("orgflag", "1");//内部机构
  			orgNameList  = sysRbacOrgService.getEntityByList(params);
  		}
  		model.addAttribute("orgNameList", orgNameList);
		model.addAttribute("add", "add");
		model.addAttribute("menuid", menuid);
		return url+"add";
	}
	/**
	 * 保存角色数据
	 * @param request
	 * @param entity
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月26日下午4:57:23
	 */
	@FormToken(remove=true)
	@RequestMapping(value="add")
	@ResponseBody
	public String add(HttpServletRequest request, SysRbacRole entity){
		String errMsg = "";
    	//获取当前登陆用户
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("rolename", entity.getRolename());
		//验证角色名称唯一
		if(this.validateRolename(entity.getRolename(),"")){
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "角色名已存在！");
		}
		try {
			entity.setSid(UUIDUtils.get32UUID());
			entity.setCreateBy(rbacUser.getUsername());
			entity.setCreateTime(DateUtils.getTimeNow());
			entity.setDelFlag(JsdpConstants.HFMP_Delflag_N);
			sysRbacRoleService.addEntity(entity);
			globalWriteService.writeGlobalRole();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	
	/**
	 * 验证角色名称是否存在
	 * @param rolename
	 * @param sid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月26日下午5:34:20
	 */
	public boolean validateRolename(String rolename,String sid){
		if(!StringUtils.isEmpty(sid)){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("notsid", sid);
			params.put("rolename", rolename);
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			Map<String,Object> dataMap = sysRbacRoleService.getEntity(params);
			if(dataMap != null){
				return true;
			}
		}else{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("rolename", rolename);
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			Map<String,Object> dataMap = sysRbacRoleService.getEntity(params);
			if(dataMap != null){
				return true;
			}
		}
		return false;
	}
	
	 /**
     * 
     * 功能: 进入角色编辑界面
     * 
     * @param model 
     * @param request
     * @param page
     * @return system/dept/edit
     *         作者：scy
     */
	@FormToken(save=true)
  	@RequestMapping(value="initUpdate")
  	public String initUpdate(String sid,Model model,HttpServletRequest request){
  		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
  		List<Map<String, Object>> orgNameList = new ArrayList<Map<String, Object>>();
  		Map<String, Object> params = new HashMap<String, Object>();
  		if(!"00".equals(rbacUser.getUsertype())){//超级管理员不过滤机构
  			CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
  			params.put("sid", rbacOrg.getRootOrgid());
  			orgNameList  = sysRbacOrgService.getEntityByList(params);
  		}else{
  			params.put("istop", "y");//顶级机构
  			params.put("orgflag", "1");//内部机构
  			orgNameList  = sysRbacOrgService.getEntityByList(params);
  		}
  		model.addAttribute("orgNameList", orgNameList);
  		//角色信息
  		Map<String, Object> conditions = new HashMap<String, Object>();
  		conditions.put("sid", sid);
  		Map<String, Object> rolelist = sysRbacRoleService.getEntity(conditions);
  				
  		model.addAttribute("rolelist", rolelist);	
  		model.addAttribute("add", "update");
  		model.addAttribute("menuid", menuid);
  		return url+"add";
  		
  	}
  	
  	/**
     * 
     * 功能: 保存角色编辑信息
     * 
     * @param model 
     * @param request
     * @param entity
     * @return map
     *         作者：scy
     */
	@FormToken(remove=true)
  	@RequestMapping(value="update")
	@ResponseBody
	public String update(HttpServletRequest request, SysRbacRole entity){
  		String errMsg = "";
    	//获取当前登陆用户
  		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	//验证角色名称唯一
		if(this.validateRolename(entity.getRolename(),entity.getSid())){
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "角色名已存在！");
		}  	
		try {
			entity.setUpdateBy(rbacUser.getUsername());
			entity.setUpdateTime(DateUtils.getTimeNow());
			entity.setDelFlag(JsdpConstants.HFMP_Delflag_N);
			sysRbacRoleService.updateEntity(entity);
			globalWriteService.writeGlobalRole();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
			
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
    /**
     * 
     * 功能: 删除角色
     * 
     * @param entity 
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(String sid,SysRbacRole entity) {
    	String errMsg = "";
	    try {
	    	//逻辑删除角色
	    	sysRbacRoleService.deleteEntityPlus(entity,sid);
	    	 globalWriteService.writeGlobalRole();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		} 
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
    /**
     * 
     * 功能: 进入角色授权界面
     * 
     * @param model 
     * @param request
     * @param page
     * @return system/dept/edit
     *         作者：scy
     */
    @FormToken(save=true)
  	@RequestMapping(value="initautho")
  	public String initautho(String sid,Model model,Page page, HttpServletRequest request){
  		//检查选中角色授权情况
  		Map<String,Object> memuparams = new HashMap<String,Object>();
  		memuparams.put("actorid", sid);
  		//查询菜单权表
  		List<Map<String, Object>> menulist = sysActorMenuService.getEntityByList(memuparams);
  		//若角色没有任何授权
  		if(menulist==null || menulist.size()<1){
  			Map<String,Object> params = new HashMap<String,Object>();
  	  		params.put("order", "lpad(menuno,2,0)");
  	  		List<ZTree> menuTree = sysRbacMenuService.getMenuTree(params);//获取整个菜单树
  	  		 		
  	  		model.addAttribute("treelist", menuTree);
  	  		model.addAttribute("sid", sid);	
  	  		model.addAttribute("add", "autho");
  	  		model.addAttribute("menuid", menuid);
  	  		return url+"autho";
  	  	}
  			//若角色已有授权
  			else{
  				//获取角色已授权的菜单   	menuids为菜单id			
  				ArrayList<String>  menuids = new ArrayList<String>();
  				for (int i = 0; i < menulist.size(); i++) {
  					String menuid = (String) menulist.get(i).get("MENUID");
  					menuids.add(menuid);		
				}
  				//获取菜单树
  				Map<String,Object> params = new HashMap<String,Object>();
  	  	  		params.put("order", "lpad(menuno,2,0)");
  	  	  		List<ZTree> menuTree = sysRbacMenuService.getMenuTree(params);
  	  	  		//为菜单树拼接已授权菜单的选中 	  	  		
  	  	  		for (int i = 0; i < menuids.size(); i++) {
  	  	  			String a = menuids.get(i); 	  	  		
  	  	  			for (int j = 0; j < menuTree.size(); j++) {
  	  	  			    if(a.equals(menuTree.get(j).getId())){
  	  	  			    	menuTree.get(j).setChecked(true);
  	  	  			    }
					}
				} 	  	  		 		
  	  	  		model.addAttribute("treelist", menuTree);
  	  	  		model.addAttribute("sid", sid);	
  	  	  		model.addAttribute("add", "authoupdate");
  	  	  		model.addAttribute("menuid", menuid);
  	  	  		return url+"autho";
  		} 		
  	}
  	/**
     * 
     * 功能: 保存角色授权信息
     * 
     * @param model 
     * @param request
     * @param page
     * @return system/dept/edit
     *         作者：scy
     */
    @FormToken(remove=true)
  	@RequestMapping(value="autho")
  	@ResponseBody
  	public String autho(HttpServletRequest request,String sid,Model model,SysRbacMenu entity,SysActorMenu amenu){
  		String errMsg = "";
  		String Menuid =  request.getParameter("menuids");

  		//用户输入值为空时跳过
  		if(!StringUtils.isEmpty(Menuid)){  			
  			String[] Menuids = request.getParameter("menuids").split(",");
  			try {
  				amenu.setActortype(JsdpConstants.ACTORTYPE_ROLE);
  				amenu.setActorid(sid);
  				amenu.setRemark("备注");
  				sysActorMenuService.addEntitys(amenu,Menuids);
  				
  				return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
  			} catch (Exception e) {
  				errMsg = e.getMessage();
  			}
  			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
  		}else{
  			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
  		}	
  	}
  	/**
     * 
     * 功能: 保存修改后角色授权信息
     * 
     * @param model 
     * @param request
     * @param page
     * @return system/dept/edit
     *         作者：scy
     */
    @FormToken(remove=true)
  	@RequestMapping(value="authoupdate")
  	@ResponseBody
  	public String authoupdate(HttpServletRequest request,String sid,Model model,SysRbacMenu entity,SysActorMenu amenu){
  		String errMsg = "";
		String Menuid =  request.getParameter("menuids");
		String[] Menuids = request.getParameter("menuids").split(",");
		//授权菜单有值时添加无值时跳过
		if(!StringUtils.isEmpty(Menuid)){
			try {
				sysActorMenuService.saveAutho(amenu, Menuids,sid);
				return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
			} catch (Exception e) {
				errMsg = e.getMessage();
			}
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);	
		}else{	
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);	
		}			
  	}
    /**
     * 联想数据获取
     * @param request
     * @param rolename
     * @return
     *
     * @author scy
     * @since 2018年4月2日上午10:29:39
     */
    @RequestMapping(value="autocomplete")
	@ResponseBody
	public String autocomplete(HttpServletRequest request,String rolename){
		String errMsg = "";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rolename", rolename);
		try {
			List<Map<String, Object>> List = sysRbacRoleService.getAutocomplete(params);
			return JsonTools.ToDatatableJson(List, StringUtils.toString(1), StringUtils.toString(10));
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}

}
