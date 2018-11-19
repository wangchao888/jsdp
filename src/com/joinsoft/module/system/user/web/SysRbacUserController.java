package com.joinsoft.module.system.user.web;


import java.io.IOException;
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
import com.joinsoft.core.encrypt.EncryptUtils;
import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.core.utils.json.JsonUtils;
import com.joinsoft.core.utils.page.PageTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.HfmpConstants;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dept.service.SysRbacOrgService;
import com.joinsoft.module.system.universal.service.RbacService;
import com.joinsoft.module.system.user.entity.SysRbacUser;
import com.joinsoft.module.system.user.service.SysRbacUserService;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalWriteService;
/**
 * 用户
 * @author scy
 * @since  2018年3月29日
 */
@Controller
@RequestMapping(value="/system/user/")
public class SysRbacUserController {
	
	@Autowired
	private RbacService rbacService;
	@Autowired
	private SysRbacUserService sysRbacUserService;
	@Autowired
	private SysRbacOrgService sysRbacOrgService;
	@Autowired
	private IGlobalWriteService  globalWriteService;
	@Autowired
	private IUserService userService;
	
	private String url ="system/user/";
	private String menuid = "";
	/**
	 * 进入用户列表
	 * @param model
	 * @param request
	 * @return
	 * @author LZX 2017-05-24 1801
	 */
	@RequestMapping(value="list")
	public String list(Model model,String menuid,HttpServletRequest request){
		String orgid = request.getParameter("orgid");
		this.menuid = menuid;
		model.addAttribute("orgid", orgid);
		model.addAttribute("menuid", menuid);
		return url + "list";
	}
	/**
	 * 数据显示
	 * @param entity
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午8:17:24
	 */
	@RequestMapping(value = "/show")
    @ResponseBody
    public String show(SysRbacUser entity, HttpServletRequest request) {		
        Page page = PageTools.getPageByParam(request);
        Map<String, Object> conditions = new HashMap<String, Object>();
        if(entity.getOrgid() != null){
        	conditions.put("orgid", entity.getOrgid());
        	conditions.put("loginname", entity.getLoginname());
        	conditions.put("username", entity.getUsername());
        }
        conditions.put("page", page);
        conditions.put("delflag", JsdpConstants.HFMP_Delflag_N);
        conditions.put("order", "createTime desc");
        List<Map<String, Object>> dataList = sysRbacUserService.getEntityByPage(conditions);
        return JsonTools.ToDatatableJson(dataList, StringUtils.toString(page.getDraw()), StringUtils.toString(page.getTotalResult()));
    }
	
	/**
     * 
     * 功能: 进入增加用户界面
     * 
     * @param model 
     * @param request
     * @return system/dept/edit
     *  作者：scy
     *  @author gx 统一页面中所属部门
     */
  	@RequestMapping(value="initAdd")
  	public String initAdd(Model model,String orgid,HttpServletRequest request){
  		//CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
  		Map<String,Object> params = new HashMap<String,Object>();
  		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
  		List<ZTree> orgTree = sysRbacOrgService.getOrgTree(params);
  		if(!StringUtils.isEmpty(orgid)){
  			params.clear();
  			params.put("sid", orgid);
  			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
  			Map<String,Object> rbacOrgMap = sysRbacOrgService.getEntity(params);
  			model.addAttribute("userType", "0"+rbacOrgMap.get("ORGFLAG"));
  			//统一页面中所属部门
  			String orgName = (String)rbacOrgMap.get("ORGNAME");
  			String orgId = (String)rbacOrgMap.get("SID");
  			model.addAttribute("orgName", orgName);
  			model.addAttribute("orgId", orgId);
  			//默认的登录名
  			try {
				String loginName = this.queryMaxLoginName("01");
				model.addAttribute("loginName", loginName);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
  		}
  		model.addAttribute("treelist", orgTree);
  		model.addAttribute("add", "add");
  		model.addAttribute("menuid", menuid);
  		model.addAttribute("update", "0");
  		return url+"edit";
  	}
  	/**
     * 
     * 功能: 保存增加用户
     * 
     * @param model 
     * @param request
     * @param entity
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public String add(HttpServletRequest request,SysRbacUser entity, Model model) {
    	//获取当前登陆用户
    	CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	String errMsg = "";
    	try {  		 
    		if(this.validateLoginName(entity.getLoginname())){
    			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "应用名已存在！");
    		}
    		entity.setSid(UUIDUtils.get32UUID());
			entity.setDelflag(JsdpConstants.HFMP_Delflag_N);  //默认正常，0_正常 1_删除
			entity.setCreateby(rbacUser.getUsername());
			entity.setCreatetime(DateUtils.getTimeNow());//创建时间
			entity.setPasswd(EncryptUtils.sha1(JsdpConstants.PASS_INIT_VAL)); //密码初始值为：666666
			entity.setHeadicon(JsdpConstants.USER_HEADICON);
			entity.setIcopath(JsdpConstants.USER_ICOPATH);
			sysRbacUserService.addEntity(entity);
			globalWriteService.writeGlobalUser();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);		
		} catch (Exception e) {
			errMsg = e.getMessage();
		}   
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
    /**
     * 验证登录名是否重复
     * @param appname
     * @param sid
     * @return
     *
     * @author scy
     * @since 2018年4月3日下午5:22:55
     */
    public boolean validateLoginName(String loginname){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("loginname", loginname);
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			Map<String, Object> dataMap = sysRbacUserService.getEntityMap(params);
			if(dataMap != null){
				return true;
			}
		return false;
	}
	
    /**
     * 
     * 功能: 进入用户编辑界面
     * 
     * @param model 
     * @param request
     * @param page
     * @return system/dept/edit
     *  作者：scy
     *  @author gx 统一页面中所属部门
     */
  	@RequestMapping(value="initUpdate")
  	public String initUpdate(String pid,String sid,Model model,Page page, HttpServletRequest request){
  		//用户信息
  		Map<String, Object> conditions = new HashMap<String, Object>();
  		conditions.put("sid", sid);
  		Map<String, Object> entityMap = sysRbacUserService.getEntityMap(conditions);
  		//统一页面中所属部门
  		String orgName = entityMap.get("ORGNAME").toString();
  		String orgId = entityMap.get("ORGID").toString();
  		 //机构树
  		Map<String,Object> params = new HashMap<String,Object>();
  		params.put("order", "sid");
  		List<ZTree> orgTree = sysRbacOrgService.getOrgTree(params);
  		model.addAttribute("entityMap", entityMap);
  		model.addAttribute("orgName", orgName);
  		model.addAttribute("orgId", orgId);
  		model.addAttribute("treelist", orgTree);
  		model.addAttribute("add", "update");
  		model.addAttribute("menuid", menuid);
  		model.addAttribute("userType", entityMap.get("USERTYPE"));
  		model.addAttribute("update", "1");
  		return url+"edit";
  	}
  	
  	/**
     * 
     * 功能: 保存编辑的用户信息
     * 
     * @param model
     * @param entity
     * @param request
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(SysRbacUser entity, Model model,HttpServletRequest request) { 
    	//获取当前登陆用户
    	CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	String errMsg = "";
    	try {  	
    		if (StringUtils.isEmpty(entity.getSid())) {//修改头像
    			entity.setSid(rbacUser.getSid());
    		}
    		if (!StringUtils.isEmpty(entity.getPasswd())) {//修改密码
    			entity.setPasswordmodifytime(DateUtils.getTimeNow());//密码修改时间
    			entity.setPasswd(EncryptUtils.sha1(entity.getPasswd()));//加密
    			//验证原密码
    			Map<String,Object> params = new HashMap<String,Object>();
    			params.put("sid", entity.getSid());
    			sysRbacUserService.getEntity(params);
    			if(!StringUtils.getString(sysRbacUserService.getEntityMap(params), "PASSWD").equals(EncryptUtils.sha1(request.getParameter("originalPass")))){
    				return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "原密码验证失败！");
    			}
    		}
			entity.setUpdateby(rbacUser.getUsername());
			entity.setUpdatetime(DateUtils.getTimeNow());//修改时间
			sysRbacUserService.updateEntity(entity);
			globalWriteService.writeGlobalUser();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {			
			errMsg = e.getMessage();
		}   
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }	
    
    /**
     * 
     * 功能: 删除用户
     * 
     * @param entity 
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(String sid) {     	
    	String errMsg = "";
		try {
			sysRbacUserService.updateFlagorDelRole(sid);
			 globalWriteService.writeGlobalUser();
			 return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		} 
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }	
    
    /**
     * 停用启用
     * @param sid
     * @return
     */
    @RequestMapping(value = "updateState")
    @ResponseBody
    public String updateState(String sid, String state){
    	String errMsg = "";
    	try {
    		SysRbacUser entity =  new SysRbacUser();
    		entity.setSid(sid);
    		if(JsdpConstants.HFMP_State_N.equals(state)){
    			entity.setState(JsdpConstants.HFMP_State_Y);
    		}else if(JsdpConstants.HFMP_State_Y.equals(state)){
    			entity.setState(JsdpConstants.HFMP_State_N);
    		}
			sysRbacUserService.updateEntity(entity);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
    
    /**
     * 重置密码
     * @param sid
     * @return
     */
    @RequestMapping(value = "resetPsswd")
    @ResponseBody
    public String resetPsswd(String sid,HttpServletRequest request){
    	String errMsg = "";
    	CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	try {
    		SysRbacUser entity =  new SysRbacUser();
    		entity.setSid(sid);
    		entity.setPasswd(EncryptUtils.sha1(JsdpConstants.PASS_INIT_VAL)); //密码初始值为：666666
    		entity.setPasswordmodifytime(DateUtils.getTimeNow());
    		entity.setUpdateby(rbacUser.getUsername());
			entity.setUpdatetime(DateUtils.getTimeNow());//修改时间
			sysRbacUserService.updateEntity(entity);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
    
    /**
     * 查询用户类型的最大登陆名(工号)
     * @param usertype
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "queryMaxLoginName")
    @ResponseBody
    public String queryMaxLoginName(String usertype) throws IOException{
    	String maxLoginName = rbacService.queryMaxLoginName(usertype);
    	return JsonUtils.ObjectToJson(maxLoginName);
    }
    /**
     * 在man页面点击用户头像
     * @param model
     * @param request
     * @return
     *
     * @author scy
     * @since 2018年3月27日下午3:51:42
     */
	@RequestMapping(value="userHeader")
	public String userHeader(Model model,HttpServletRequest request){
		return url + "userHeader";
	}
	/**
	 * main中修改用户密码
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param model
	 * @param orgid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月28日下午5:15:09
	 */
  	@RequestMapping(value="editPass")
  	public String esitPass(Model model,HttpServletRequest request){
  		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		model.addAttribute("userName", rbacUser.getUsername());
		model.addAttribute("sid", rbacUser.getSid());
		model.addAttribute("loginName", rbacUser.getLoginname());
		model.addAttribute("passwd", rbacUser.getPasswd());
		CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		model.addAttribute("rootOrgName", rbacOrg.getRootOrgname());
  		return url+"editPass";
  	}
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
}
