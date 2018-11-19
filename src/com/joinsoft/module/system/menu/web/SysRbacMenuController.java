package com.joinsoft.module.system.menu.web;

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
import com.joinsoft.core.token.annotation.FormToken;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.actormenu.entity.SysActorMenu;
import com.joinsoft.module.system.application.service.SysRbacApplicationService;
import com.joinsoft.module.system.menu.entity.SysRbacMenu;
import com.joinsoft.module.system.menu.entity.SysUserShortcut;
import com.joinsoft.module.system.menu.service.SysRbacMenuService;
import com.joinsoft.module.system.menu.service.SysUserShortcutService;
import com.joinsoft.platform.global.entity.CacheRbacUser;
/**
 * 菜单
 * @author scy
 * @since  2018年3月30日
 */
@Controller
@RequestMapping(value="/system/menu/")
public class SysRbacMenuController {
	private String url ="system/menu/";
	private String menuid = "";
	@Autowired
	private SysRbacMenuService sysRbacMenuService;
	@Autowired
	private SysRbacApplicationService sysRbacApplicationService;
	@Autowired
	private IUserService userService;
	@Autowired
	private SysUserShortcutService userShortcutService;
	
	/**
     * 
     * 功能: 进入菜单管理
     * 
     * @param model 
     * @param request
     * @return tree
     *         作者：scy
     */
	@RequestMapping(value="list")
	public String list(Model model, String menuid){
		this.menuid = menuid;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("order", "lpad(menuno,2,0)");
		List<ZTree> menuTree = sysRbacMenuService.getMenuTree(params);
		model.addAttribute("treelist", menuTree);
		model.addAttribute("menuid", menuid);
		return url+"list";
	}
	/**
     * 
     * 功能: 进入增加菜单界面
     * 
     * @param model 
     * @param request
     * @return system/menu/edit
     * 作者：scy
     * @author gx  统一页面 menuName sid menuNo 2017-06-08
     */
  	@RequestMapping(value="initAdd")
  	public String initAdd(Model model,String pid){
  		Map<String,Object> params = new HashMap<String,Object>();
  		params.put("order", "sid");
  		//上级菜单
  		Map<String, Object> conditions = new HashMap<String, Object>();
  		//上级菜单不为空
  		if(!StringUtils.isBlank(pid)){
  			conditions.put("sid", pid);
  			Map<String, Object> pidMap = sysRbacMenuService.getEntity(conditions);
  			//规范页面统一使用 sid menuname
  			if(CollectionUtils.isNotEmpty(pidMap)){
  				String sid = pidMap.get("SID").toString();
  				String menuName = pidMap.get("MENUNAME").toString();
  				model.addAttribute("pid", sid);
  				model.addAttribute("menuName", menuName);
  			}
  			model.addAttribute("pidMap", pidMap);
  			//规范页面统一menuNo
  			Map<String, Object> menunoParams = new HashMap<String, Object>();
  			menunoParams.put("pid", pid);
  			String menuNo = sysRbacMenuService.queryEntityByList(menunoParams);
  			model.addAttribute("menuNo", menuNo);
  		}
  		//上级菜单为空
  		if(StringUtils.isBlank(pid)){
  			//规范页面统一menuNo
  			String menunoPid = sysRbacMenuService.queryMaxPidMenuno();
  			model.addAttribute("menuNo", menunoPid);
  		}
  		//上级菜单和所属应用
  		List<ZTree> menuTree = sysRbacMenuService.getMenuTree(params);
  		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
  		List<Map<String, Object>> appList = sysRbacApplicationService.getEntityByList(params);
  		model.addAttribute("treelist", menuTree);
  		model.addAttribute("appList", appList);
  		model.addAttribute("menuid", menuid);
  		model.addAttribute("add", "add");
  		return url+"edit";
  	}  		
  	
  	/**
     * 
     * 功能: 动态更新菜单序号
     * 
     * @param model 
     * @param request
     * @param entity
     * @return 
     *  作者：scy
     *  @author gx menuno类型 2017-06-08
     */
    @RequestMapping(value = "menuno")
    @ResponseBody
    public String  menuno(String pid) {
    	try {
    		//菜单序号
    		Map<String, Object> menunos = new HashMap<String, Object>();
    		menunos.put("delflag", JsdpConstants.HFMP_Delflag_N);
    		menunos.put("pid", pid);
    		String menuno = sysRbacMenuService.queryEntityByList(menunos);
            return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, menuno);			
		} catch (Exception e) {
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, JsdpConstants.AJAX_RESULT_MSG_F);
		}		
    }	
  	
  	/**
     * 
     * 功能: 保存增加的菜单
     * 
     * @param model 
     * @param request
     * @param entity
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public String  add(HttpServletRequest request,SysRbacMenu entity, Model model) {
    	//获取当前登陆用户
    	CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	//去除下拉树传值带来的逗号
    	entity.setPid(entity.getPid().replaceAll(",", ""));
    	String errMsg = "";
    	try {  		 
    		entity.setSid(UUIDUtils.get32UUID());
			entity.setDelflag(JsdpConstants.HFMP_Delflag_N);//默认
			entity.setState(JsdpConstants.HFMP_State_Y);
			entity.setCreateby(rbacUser.getUsername());
			entity.setCreatetime(DateUtils.getTimeNow());//创建时间
			entity.setUpdatetime("");
			entity.setRemark("");
			sysRbacMenuService.addEntity(entity);	
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
		    errMsg = e.getMessage();
		} 
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
    
    
    /**
     * 
     * 功能: 进入菜单编辑界面
     * 
     * @param model 
     * @param request
     * @param page
     * @return system/dept/edit
     *  作者：scy
     *  @author gx  统一页面menuName sid menuNo 2017-06-08
     */
  	@RequestMapping(value="initUpdate")
  	public String initUpdate(String pid,String name,Model model,Page page, HttpServletRequest request){
  		//菜单信息
  		Map<String, Object> conditions = new HashMap<String, Object>();
  		conditions.put("sid", name);
  		Map<String, Object> menuMap = sysRbacMenuService.getEntity(conditions);
  		
  		 //上级菜单和所属应用
  		Map<String,Object> params = new HashMap<String,Object>();
  		params.put("order", "sid");
  		List<ZTree>orgTree = sysRbacMenuService.getMenuTree(params);
  		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
  		List<Map<String, Object>> appList = sysRbacApplicationService.getEntityByList(params);
  		//规范页面统一使用 menuName,sid
  		if(CollectionUtils.isNotEmpty(menuMap)){
  			String menuNo = menuMap.get("MENUNO").toString();
  			//上级菜单的pid
  			String sid = (String)menuMap.get("PID");
  			if(!StringUtils.isBlank(sid)){
  				model.addAttribute("pid", sid);
  			}
  			String menuName = (String)menuMap.get("PNAME");
  			if(!StringUtils.isBlank(menuName)){
  				model.addAttribute("menuName", menuName);
  			}
  			model.addAttribute("menuNo", menuNo);
  		}
  		model.addAttribute("menuMap", menuMap);
  		model.addAttribute("treelist", orgTree);
  		model.addAttribute("appList", appList);
  		model.addAttribute("menuid", menuid);
  		model.addAttribute("add", "update");
  		model.addAttribute("sub", "<button type='button' onclick='del()'><strong>删除</strong></button>");
  		return "system/menu/edit"; 		
  	}
  	
  	/**
     * 
     * 功能: 保存编辑的菜单信息
     * 
     * @param model
     * @param entity
     * @param request
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public String update(SysRbacMenu entity, Model model,HttpServletRequest request) { 
    	//获取当前登陆用户
    	CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	String errMsg = "";
    	try {  		 
    		entity.setDelflag(JsdpConstants.HFMP_Delflag_N);//默认
			entity.setUpdateby(rbacUser.getUsername());
			entity.setUpdatetime(DateUtils.getTimeNow());//修改时间
			entity.setRemark("");
			sysRbacMenuService.updateEntity(entity);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		} 
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
    
    /**
     * 
     * 功能: 删除菜单
     * 
     * @param entity 
     * @return 
     *  作者：scy
     *  作者：modify by gx  time 2017-06-08
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(String sid) { 
    	return sysRbacMenuService.updateSysMenuDelFlag(sid);
    }
    
    
    /**
     * 菜单图标
     * @param model
     * @param request
     * @return
     * @author gx
     */
    @RequestMapping(value = "menuIco")
    public String menuIco(Model model,HttpServletRequest request){
    	return url+"menuIco";
    }
  	/**
  	 * 保存添加快捷菜单
  	 * @param request
  	 * @param entity
  	 * @return
  	 *
  	 * @author scy
  	 * @since 2018年10月21日下午3:46:33
  	 */
  	@RequestMapping(value="shortSave")
  	@ResponseBody
  	public String shortSave(HttpServletRequest request,SysUserShortcut entity){
  		String menuid =  request.getParameter("menuid");
  		//用户输入值为空时跳过
  		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));  
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userid", rbacUser.getSid());
		params.put("menuid", menuid);
		if (CollectionUtils.isEmpty(userShortcutService.getShortcutAndMenu(params))) {
  			try {
  				entity.setUserid(rbacUser.getSid());
  				entity.setSid(UUIDUtils.get32UUID());
  				if (CollectionUtils.isEmpty(userShortcutService.getMaxOrderno(params).get(0))) {
  					entity.setOrderno("1");
  				}else{
  					entity.setOrderno(StringUtils.getString(userShortcutService.getMaxOrderno(params).get(0), "MAXO"));
  				}
  				userShortcutService.addEntity(entity);
  				return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
  			} catch (Exception e) {
  				
  			}
  			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, JsdpConstants.AJAX_RESULT_MSG_F);
  		}else{
  			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, "已存在当前菜单，请检查！");
  		}	
  	}  	
  	/**
  	 * 修改快捷入口
  	 * @param request
  	 * @param menuids
  	 * @return
  	 *
  	 * @author scy
  	 * @since 2018年10月23日上午9:21:09
  	 */
  	@RequestMapping(value="shortUpdate")
  	@ResponseBody
  	public String shortUpdate(HttpServletRequest request,String menuid){
  		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));  
  		if (JsdpConstants.HFMP_RESULT_SUCCESS.equals(userShortcutService.updateShort(menuid, rbacUser))) {
  			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
  		} else{
  			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, JsdpConstants.AJAX_RESULT_MSG_T);
  		}
  	}  	
}
