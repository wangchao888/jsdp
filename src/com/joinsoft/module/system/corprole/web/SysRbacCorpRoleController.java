package com.joinsoft.module.system.corprole.web;

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
import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.core.utils.page.PageTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.HfmpConstants;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.corprole.entity.SysRbacCorprole;
import com.joinsoft.module.system.corprole.service.SysRbacCorproleService;
import com.joinsoft.module.system.dept.service.SysRbacOrgService;
import com.joinsoft.module.system.dictcontent.service.SysDictContentService;
import com.joinsoft.module.system.role.service.SysRbacRoleService;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacUser;

/**
 * 单位类别角色管理
 * @author gx
 * time 2017-07-21 1328
 *
 */
@Controller
@RequestMapping(value = "/system/corprole/")
public class SysRbacCorpRoleController {
	@Autowired
	private SysRbacRoleService sysRbacRoleService;
	@Autowired
	private SysRbacCorproleService sysRbacCorproleService;
	@Autowired
	private SysRbacOrgService sysRbacOrgService;
	@Autowired
	private SysDictContentService sysDictContentService;
	@Autowired
	private IUserService userService;
	private String url ="system/corprole/";
	private String menuid = "";
	
	
	/**
	 * 列表
	 * @param request
	 * @param model
	 * @param menuid
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月8日下午3:33:13
	 */
	@RequestMapping(value = "/list")
    public String show(HttpServletRequest request,Model model,String menuid) {
		this.menuid = menuid;
		//获取登录用户机构信息	实现过滤单位
		CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("delflag", JsdpConstants.HFMP_Delflag_N);
		if(!"00".equals(rbacUser.getUsertype())){//超级管理员不过滤
			conditions.put("orgno", rbacOrg.getOrgNo());  
		}
        List<Map<String, Object>> roleList = sysRbacCorproleService.getEntityByList(conditions);//单位的角色
        if (CollectionUtils.isNotEmpty(roleList)) {
        conditions.clear();
        conditions.put("dictno", "75");
        conditions.put("dictlabelIn", sysDictContentService.dictlabelCorp(roleList,"rol"));
        conditions.put("state", "1");
        List<Map<String, Object>> dictContentList = sysDictContentService.getEntityByList(conditions);//字典中单位类型
        Map<String,String> dataMap = new HashMap<String,String>();
        	for (int i = 0; i < dictContentList.size(); i++) {
            	StringBuffer sb = new StringBuffer();
            	Map<String, Object> dicMap = dictContentList.get(i);
            	String dicCorptype = (String)dicMap.get("DICTLABEL");
            	for (int j = 0; j < roleList.size(); j++) {
            		String corptype = roleList.get(j).get("CORPTYPE").toString();
            		if(dicCorptype.equals(corptype)){
            			sb.append(roleList.get(j).get("ROLENAME").toString());
            			sb.append(",");
            		}else{
            			continue;
            		}
            	}
            	if(sb.length()>0){
            		dataMap.put(dicCorptype, sb.substring(0, sb.length()-1));
            	}
    		}
        	model.addAttribute("dataMap", dataMap);
            model.addAttribute("dictContentList", dictContentList);
            conditions.clear();
            conditions.put("dictno", "75");
            conditions.put("delflag", JsdpConstants.HFMP_Delflag_N);
            conditions.put("state", "1");
            if (sysDictContentService.getEntityByList(conditions).size() == dictContentList.size()) {
            	model.addAttribute("flag", "1");
            } 
            model.addAttribute("dictlabelCorp", sysDictContentService.dictlabelCorp(dictContentList,"corp"));
        }
        return url+"list";
    }
	/**
	 * 进入新增界面
	 * @param model
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月8日上午11:39:40
	 */
	@RequestMapping(value = "initAdd")
	public String initAdd(Model model,HttpServletRequest request,String dictlabelCorp){
		CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
  		Map<String,Object> params = new HashMap<String,Object>();
  		params.put("orgno", rbacOrg.getOrgNo());  
  		params.put("delflag", JsdpConstants.HFMP_Delflag_N);  
  		List<Map<String, Object>> roleMap = sysRbacRoleService.getEntityByList(params);
  		params.clear();
  		params.put("order", "sid");
  		List<ZTree> zoneTree = sysRbacOrgService.getAreaTree(params);
  		model.addAttribute("roleMap",roleMap);
  		model.addAttribute("orgno",rbacOrg.getOrgNo());
  		model.addAttribute("zoneTree", zoneTree);
  		model.addAttribute("add", "add");
		model.addAttribute("menuid", menuid);
		//显示单位类型
		params.clear();
		params.put("dictno", "75");
        params.put("dictlabelCorp", dictlabelCorp); 
        params.put("state", HfmpConstants.RULE_ONE);
        List<Map<String, Object>> dictContentList = sysDictContentService.getEntityByList(params);//字典中单位类型
        model.addAttribute("dictContentList", dictContentList);
		return url+"addCorprole";
	}
	/**
	 * 保存单位角色数据
	 * @param entity
	 * @param model
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月8日下午1:29:57
	 */
	@RequestMapping(value = "add")
    @ResponseBody
    public String add(SysRbacCorprole entity, Model model,HttpServletRequest request) { 
    	String errMsg = "";
    	String role = request.getParameter("roleval");
    	String[] rolelist = request.getParameter("roleval").split(",");//获取角色id
    	//页面是否有传递角色
    	if(!StringUtils.isEmpty(role)){
    		try {    	  
    			  sysRbacCorproleService.addEntity(entity,rolelist);
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
	 * 进入修改界面
	 * @param corptype
	 * @param model
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月8日下午4:30:11
	 */
	@RequestMapping(value="initUpdate")
  	public String initUpdate(String corptype,Model model,String dictlabelCorp,HttpServletRequest request){
		CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
  		//角色信息
  		Map<String, Object> conditions = new HashMap<String, Object>();
  		conditions.put("corptype", corptype);
  		conditions.put("delflag", JsdpConstants.HFMP_Delflag_N);
  		List<Map<String, Object>> dataMap = sysRbacCorproleService.getEntityByList(conditions);
  		conditions.clear();
  		conditions.put("orgno", rbacOrg.getOrgNo());  
  		conditions.put("delflag", JsdpConstants.HFMP_Delflag_N);  
  		conditions.put("notsidIn",sysDictContentService.dictlabelCorp(dataMap,""));
  		List<Map<String, Object>> roleMap = sysRbacRoleService.getEntityByList(conditions);
  		conditions.clear();
  		List<String> roleids = new ArrayList<String>();
  		if(CollectionUtils.isNotEmpty(dataMap)){
  			for (Map<String, Object> map : dataMap) {
  				String roleid = map.get("ROLEID").toString();
  				roleids.add(roleid);
  			}
  			List<Map<String,Object>> seledRoleMap = sysRbacRoleService.getEntityByListByRoleids(roleids);
  			model.addAttribute("seledRoleMap", seledRoleMap);
  		}
  		conditions.clear();
  		conditions.put("order", "sid");
  		List<ZTree> zoneTree = sysRbacOrgService.getAreaTree(conditions);
  		if(CollectionUtils.isNotEmpty(dataMap)){
  			model.addAttribute("dataMap", dataMap.get(0));
  		}
  		model.addAttribute("roleMap", roleMap);
  		model.addAttribute("zoneTree", zoneTree);
  		model.addAttribute("add", "update");
  		model.addAttribute("menuid", menuid);
  		//显示单位类型
  		conditions.clear();
  		conditions.put("dictno", "75");
  		conditions.put("dictlabelCorp", dictlabelCorp); 
  		conditions.put("state", "1");
        List<Map<String, Object>> dictContentList = sysDictContentService.getEntityByList(conditions);//字典中单位类型
        model.addAttribute("dictContentList", dictContentList);
        model.addAttribute("corptype", corptype);//默认的单位类型
        conditions.clear();
        conditions.put("dictno", "75");
        conditions.put("dictlabel", corptype); 
        conditions.put("state", HfmpConstants.RULE_ONE);
        model.addAttribute("corpName", sysDictContentService.getEntityByList(conditions).get(0).get("DICTVALUE"));
        model.addAttribute("orgno",rbacOrg.getOrgNo());
  		return url+"addCorprole";
  	}
	/**
	 * 保存修改的数据
	 * @param entity
	 * @param model
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年4月8日下午4:58:39
	 */
	@RequestMapping(value = "update")
    @ResponseBody
    public String update(SysRbacCorprole entity, Model model,HttpServletRequest request,String oldcorptype) { 
    	String errMsg = "";
    	String role = request.getParameter("roleval");
    	String[] rolelist = request.getParameter("roleval").split(",");//获取角色id
    	//页面是否有传递角色
    	if(!StringUtils.isEmpty(role)){
    		try {   
    			sysRbacCorproleService.updateCorprole(entity,rolelist,oldcorptype);
        		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);  		   		
    		} catch (Exception e) {
    			errMsg = e.getMessage();
    			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    		} 
    	}else{
    		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, JsdpConstants.AJAX_RESULT_MSG_F);  		
    	}
    }
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(String corptype){
		String errMsg = "";
		Map<String,Object> params = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(corptype)){
			try {
				params.put("corptype", corptype);
				sysRbacCorproleService.updateDelflag(params);
				return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
			} catch (Exception e) {
				errMsg = e.getMessage();
			}
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}

}
