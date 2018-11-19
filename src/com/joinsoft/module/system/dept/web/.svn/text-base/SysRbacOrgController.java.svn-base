package com.joinsoft.module.system.dept.web;

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
import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dept.entity.SysRbacOrg;
import com.joinsoft.module.system.dept.service.SysOrgAreaService;
import com.joinsoft.module.system.dept.service.SysRbacOrgService;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalWriteService;
/**
 * 
 * 组织机构管理
 * @author as
 * @since  2014-9-1
 */
@Controller
@RequestMapping(value="/system/dept/")
public class SysRbacOrgController {

	@Autowired
	private SysRbacOrgService sysRbacOrgService;
	@Autowired
	private SysOrgAreaService sysOrgAreaService;
	@Autowired
	private IGlobalWriteService globalWriteService;		
	@Autowired
	private IUserService userService;
	
	private String url ="system/dept/";
	private String menuid = "";
	

	/**
     * 
     * 功能: 进入机构管理
     * 
     * @param model 
     * @param request
     * @return tree
     *         作者：scy
     */
	@RequestMapping(value="tree")
	public String tree(Model model, HttpServletRequest request, String menuid){
		this.menuid = menuid;
		//获取登录用户机构信息	实现机构过滤
		CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		String admin = "1";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
		params.put("order", "orgtype,sid");
		params.put("orgid", rbacOrg.getSid());
		params.put("orgno", rbacOrg.getOrgNo());
		if(!"00".equals(rbacUser.getUsertype())){//超级管理员不过滤机构
			params.put("isAdmin", "isAdmin");
			admin = "0";
		}
		List<ZTree> orgTree = sysRbacOrgService.getOrgUserTree(params);
		model.addAttribute("treelist", orgTree);
		model.addAttribute("menuid", menuid);
		model.addAttribute("admin", admin);
		return url+"tree";
	}
	/**
     * 
     * 功能: 进入增加机构界面
     * 
     * @param model 
     * @param request
     * @return system/dept/edit
     *         作者：scy
     */
  	@RequestMapping(value="initAdd")
  	public String initAdd(Model model,HttpServletRequest request,String pid){
  		//上级机构
  		if(!StringUtils.isBlank(pid)){
  		Map<String, Object> conditions = new HashMap<String, Object>();
  		conditions.put("sid", pid);
  		Map<String, Object> orgvMap = sysRbacOrgService.getEntity(conditions);
  		//规范页面统一格式  pidnName pid
  		if(CollectionUtils.isNotEmpty(orgvMap)){
  			String orgName = (String)orgvMap.get("ORGNAME");
  			if(!StringUtils.isBlank(orgName)){
  				model.addAttribute("pidName", orgName);
  			}
  			String sid = orgvMap.get("SID").toString();
  			model.addAttribute("pid", sid);
  		}
  		model.addAttribute("orgvMap", orgvMap);
  		}
	
  		Map<String,Object> params = new HashMap<String,Object>();
  		params.put("order", "sid");
  		List<ZTree> orgTree = sysRbacOrgService.getOrgTree(params);
  		List<ZTree> zoneTree = sysRbacOrgService.getAreaTree(params);
  		 		
  		model.addAttribute("orgTree", orgTree);
  		model.addAttribute("zoneTree", zoneTree);
  		model.addAttribute("add", "add");
  		model.addAttribute("menuid", menuid);
  		return url + "edit";
  	}
	
  	/**
     * 
     * 功能: 保存增加机构
     * 
     * @param model 
     * @param request
     * @param entity
     * @return 
     *  作者：scy
     *  @author gx 新增数据范围保存  2017-06-08
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public String add(HttpServletRequest request,SysRbacOrg entity, Model model) {
    	String errMsg = "";
    	//获取当前登陆用户
    	CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	//数据范围
    	String dataZone = StringUtils.clean(request.getParameter("dataZone"));
    	try {
    		sysRbacOrgService.addEntityandOrgArea(entity, rbacUser, dataZone,rbacOrg);
        	globalWriteService.writeGlobalOrg();
        	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();	
		} 	
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
    
    /**
     * 
     * 功能: 进入机构编辑界面
     * 
     * @param model 
     * @param request
     * @param page
     * @return system/dept/edit
     *         作者：scy
     *         @author LZX 机构还是用户在遍历树的时候就分开，不要在这个地方分流。在前台页面向后台发送请求之前就分开。 2017-05-24
     *         @author gx 添加查找该机构的数据范围  2017-06-08
     */
  	@RequestMapping(value="initUpdate")
  	public String initUpdate(String pid,String sid,Model model,Page page, HttpServletRequest request){
  		//机构信息
  		Map<String, Object> conditions = new HashMap<String, Object>();
  		conditions.put("sid", sid);
  		Map<String, Object> orgMap = sysRbacOrgService.getEntity(conditions);
  		//规范页面统一格式 pid
  		if(CollectionUtils.isNotEmpty(orgMap)){
  			String orgMapPid = (String)orgMap.get("PID");
  			if(!StringUtils.isBlank(orgMapPid)){
  				model.addAttribute("pid", orgMapPid);
  			}
  		}
  		//两颗树
  		Map<String,Object> params = new HashMap<String,Object>();
  		params.put("order", "sid");
  		List<ZTree> orgTree = sysRbacOrgService.getOrgTree(params);
  		List<ZTree> zoneTree = sysRbacOrgService.getAreaTree(params);
  		params.clear();
  		params.put("orgid", sid);
  		//从sys_org_area表中查找该机构的数据范围
  		Map<String,Object> zoneMap= sysOrgAreaService.queryZoneNameandZoneCode(params);
  		model.addAttribute("zoneMap", zoneMap);
  		model.addAttribute("orgMap", orgMap);
  		model.addAttribute("orgTree", orgTree);
  		model.addAttribute("zoneTree", zoneTree);	                                                                                                                                                                                                                                                                                                                
  		model.addAttribute("add", "update");
  		model.addAttribute("menuid", menuid);
  		return url+"edit";
  	}
	
  	/**
     * 
     * 功能: 保存编辑的机构信息
     * 
     * @param model
     * @param entity
     * @param request
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(SysRbacOrg entity, Model model,HttpServletRequest request) { 
    	//去除下拉树传值带来的逗号
    	entity.setPid(entity.getPid().replaceAll(",", ""));
    	entity.setZonecode(entity.getZonecode().replaceAll(",", ""));
    	//获取当前登陆用户
    	CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	String dataZone = StringUtils.clean(request.getParameter("dataZone"));
    	String errMsg = "";
    	try {  		 
			entity.setDelFlag(JsdpConstants.HFMP_Delflag_N);//默认
			entity.setUpdateBy(rbacUser.getUsername());
			entity.setUpdateTime(DateUtils.getTimeNow());	
			entity.setOrgflag(JsdpConstants.RBAC_ORG_FLAG_INSIDE);
			sysRbacOrgService.updateOrgandOrgArea(entity, dataZone);
			globalWriteService.writeGlobalOrg();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {			
			errMsg = e.getMessage();
		} 
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
    
    /**
     * 
     * 功能: 删除机构
     * 
     * @param entity 
     * @return 
     * 作者：scy
     * @author gx
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(SysRbacOrg entity) {
    	String errMsg = "";
    	try {
    		if(sysRbacOrgService.validation(entity.getSid())){
        		entity.setDelFlag(JsdpConstants.HFMP_Delflag_Y);
        		sysRbacOrgService.updateFlagandDelorgArea(entity, entity.getSid());
            	globalWriteService.writeGlobalOrg();
            	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
        	}else{
        		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "当前机构不允许被删除");
        	}
		} catch (Exception e) {
			errMsg = e.getMessage();			
		}
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);

    }
    
    /**
     * 
     * 功能: 机构管理，机构信息维护完后刷新节点树
     * 
     * @param model 
     * @param request
     * @return tree
     *         作者：LZX
     */
	@RequestMapping(value="refreshTree")
	@ResponseBody
	public String refreshTree(Model model,HttpServletRequest request){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("order", "createTime,sid");
		CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
		params.put("order", "orgtype,sid");
		params.put("orgid", rbacOrg.getSid());
		params.put("orgno", rbacOrg.getOrgNo());
		if(!"00".equals(rbacUser.getUsertype())){//超级管理员不过滤机构
			params.put("isAdmin", "isAdmin");
		}
		List<ZTree> orgTree = sysRbacOrgService.getOrgUserTree(params);
		//拼接用户信息
//		List<ZTree> userorgTree = sysRbacOrgService.getuserTree(orgTree);			
		return JsonTools.ToZtreeJson(orgTree);
	}
    
	
}
