package com.joinsoft.module.system.userrole.web;

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
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.HfmpConstants;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.role.service.SysRbacRoleService;
import com.joinsoft.module.system.user.service.SysRbacUserService;
import com.joinsoft.module.system.userrole.entity.SysUserRole;
import com.joinsoft.module.system.userrole.service.SysUserRoleService;
import com.joinsoft.platform.global.entity.CacheRbacOrg;

/**
 * 用户角色
 * @author scy
 * @since  2018年3月30日
 */

@Controller
@RequestMapping(value="/system/userrole/")
public class SysUserRoleController {	
	@Autowired
	private SysRbacRoleService sysRbacRoleService;
	@Autowired
	private SysRbacUserService sysRbacUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private IUserService userService;
		
	/**
     * 
     * 功能: 进入给用户赋角色界面
     * 
     * @param model 
     * @param request
     * @return system/dept/edit
     *         作者：scy
     */
  	@RequestMapping(value="initrole")
  	public String initrole(Model model,HttpServletRequest request,String sid){
  		CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
  		Map<String,Object> whereid = new HashMap<String,Object>();
  		whereid.put("sid", sid);
  		Map<String, Object> userlist = sysRbacUserService.getEntityMap(whereid);
  		model.addAttribute("username",userlist.get("USERNAME"));
  		//判断用户当前有无角色
  		Map<String,Object> where = new HashMap<String,Object>();
  		where.put("userid", sid);
  		List<Map<String, Object>> role = sysUserRoleService.getEntityByList(where);
  		//若用户当前没有任何角色
  		if(role==null || role.size()<1){
  			Map<String,Object> params = new HashMap<String,Object>();
  			params.put("delflag", HfmpConstants.EDOCUMENT_DELETED_EXIST);
  			params.put("orgno", rbacOrg.getOrgNo());
  	  		List<Map<String, Object>> roleMap = sysRbacRoleService.getEntityByList(params);
  	  		 		 
  	  		model.addAttribute("roleMap",roleMap);
  	  		model.addAttribute("add", "add");
  	  		model.addAttribute("userid", sid);//用户的id
  	  		return "system/user/role";}
  		//若用户已存在已有角色
  		else{
  			Map<String,Object> params = new HashMap<String,Object>();
  			params.put("USER_ROLEuserid",sid);
  			params.put("delflag", HfmpConstants.EDOCUMENT_DELETED_EXIST);
  			params.put("orgno", rbacOrg.getOrgNo());
  			Map<String,Object> paramsn = new HashMap<String,Object>();
  			paramsn.put("userid","'"+sid+"'" );
  			paramsn.put("delflag", HfmpConstants.EDOCUMENT_DELETED_EXIST);
  			paramsn.put("orgno", rbacOrg.getOrgNo());
  	  		List<Map<String, Object>> roleMap2 = sysRbacRoleService.getEntityByListy(params);//已存在角色
  	  		List<Map<String, Object>> roleMap = sysRbacRoleService.getEntityByListn(paramsn);//未存在角色
  	  		model.addAttribute("roleMap2",roleMap2);
  	  		model.addAttribute("roleMap",roleMap);
	  		model.addAttribute("add", "update");
	  		model.addAttribute("userid", sid);//用户的id  			
  			return "system/user/role";
  		} 		
  	}
  	/**
     * 
     * 功能: 保存用户的角色信息
     * 
     * @param model
     * @param entity
     * @param request
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public String add(SysUserRole entity, Model model,HttpServletRequest request) { 
    	String errMsg = "";
    	String role = request.getParameter("roleval");
    	String[] rolelist = request.getParameter("roleval").split(",");//获取角色id
    	//页面是否有传递角色
    	if(!StringUtils.isEmpty(role)){
    		try {    	    		
        			sysUserRoleService.addEntitys(entity,rolelist);			
        			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);  		
    			} catch (Exception e) {			
    				errMsg = e.getMessage();
    				return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    			} 
    		}else{
    				return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);  		
    		}   	
    }	
    /**
     * 
     * 功能: 保存修改后用户的角色信息
     * 
     * @param model
     * @param entity
     * @param request
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(SysUserRole entity, Model model,HttpServletRequest request) { 
    	String errMsg = "";
    	String role = request.getParameter("roleval");
    	String[] rolelist = request.getParameter("roleval").split(",");//获取角色id
    	Map<String, Object> userid = new HashMap<String, Object>();
		userid.put("userid", entity.getUserId());
    	//页面是否有传递角色
    	if(!StringUtils.isEmpty(role)){
    		try {     			
        		sysUserRoleService.deleteUserOrRole(userid);//当前用户的删除所有记录
        		sysUserRoleService.addEntitys(entity,rolelist);			
        		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);  		   		
    		} catch (Exception e) {
    			errMsg = e.getMessage();
    			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    		} 
    	}else{
    		sysUserRoleService.deleteUserOrRole(userid);
    		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);  		
    	}
    	
    	
    }	
}
