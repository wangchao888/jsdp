package com.joinsoft.module.system.application.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinsoft.SessionConstants;
import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.token.annotation.FormToken;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.core.utils.page.PageTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.application.entity.SysRbacApplication;
import com.joinsoft.module.system.application.service.SysRbacApplicationService;
import com.joinsoft.module.system.menu.service.SysRbacMenuService;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalWriteService;

/**
 * 
 * 应用管理
 * @author LZX
 * @since  2017-05-10
 */
@Controller
@RequestMapping(value="/system/application/")
public class SysRbacApplicationController {

	@Autowired
	private SysRbacApplicationService sysRbacApplicationService;
	@Autowired
	private SysRbacMenuService sysRbacMenuService;
	@Autowired
	private IGlobalWriteService globalWriteService;
	@Autowired
	private IUserService userService;
	
	private String url ="system/application/";
	private String menuid = "";
	/**
	 * 进入应用列表
	 * @param model
	 * @param request
	 * @param menuid
	 * @return
	 *
	 * @author lzx
	 * @since 2018年3月29日下午4:08:52
	 */
	@RequestMapping(value="list")
	public String list(Model model,HttpServletRequest request, String menuid){
		this.menuid = menuid;
		model.addAttribute("menuid", menuid);
		return url+"list";
	}
	/**
	 * 列表数据显示
	 * @param entity
	 * @param request
	 * @return
	 *
	 * @author lzx
	 * @since 2018年3月29日下午4:09:10
	 */
	@RequestMapping(value = "/show", produces="application/json;charset=UTF-8")
    @ResponseBody
    public String show(SysRbacApplication entity, HttpServletRequest request) {
        Page page = PageTools.getPageByParam(request);
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("page", page);
        conditions.put("delFlag", "0");
        conditions.put("order", "createTime desc");
        List<Map<String, Object>> dataList = sysRbacApplicationService.getEntityByPage(conditions);
        return JsonTools.ToDatatableJson(dataList, StringUtils.toString(page.getDraw()), StringUtils.toString(page.getTotalResult()));
    }
	/**
	 * 进入新加应用界面
	 * @param model
	 * @param request
	 * @return
	 *
	 * @author lzx
	 * @since 2018年3月29日下午4:09:27
	 */
	@FormToken(save=true)
	@RequestMapping(value="initAdd")
	public String initAdd(Model model,HttpServletRequest request){
		model.addAttribute("menuid", menuid);
		return url+"add";
	}
	/**
	 * 新增数据保存
	 * @param model
	 * @param request
	 * @param entity
	 * @return
	 *
	 * @author lzx
	 * @since 2018年3月29日下午4:09:52
	 */
	@FormToken(remove=true)
	@RequestMapping(value="add")
	@ResponseBody
	public String add(Model model,HttpServletRequest request, SysRbacApplication entity){
    	//获取当前登陆用户
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		String errMsg = "";
		//验证应用名称唯一
		if(this.validateAppname(entity.getAppname(),"")){
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "应用名已存在！");
		}
		try {
			entity.setSid(UUIDUtils.get32UUID());
			entity.setCreateBy(rbacUser.getUsername());
			entity.setCreateTime(DateUtils.getTimeNow());
			entity.setDelFlag(JsdpConstants.HFMP_Delflag_N);
			sysRbacApplicationService.addEntity(entity);
			globalWriteService.writeGlobalApplication();//更新缓存
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}   
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	
	/**
	 * 验证应用名称是否存在
	 * @param appname
	 * @return
	 */
	public boolean validateAppname(String appname,String sid){
		if(!StringUtils.isBlank(sid)){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("notsid", sid);
			params.put("appname", appname);
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			Map<String, Object> dataMap = sysRbacApplicationService.getEntity(params);
			if(dataMap != null){
				return true;
			}
		}else{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("appname", appname);
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			Map<String, Object> dataMap = sysRbacApplicationService.getEntity(params);
			if(dataMap != null){
				return true;
			}			
		}
		return false;
	}
	/**
	 * 进入修改界面
	 * @param model
	 * @param request
	 * @param sid
	 * @return
	 *
	 * @author lzx
	 * @since 2018年3月29日下午4:10:16
	 */
	@FormToken(save=true)
	@RequestMapping(value="initUpdate")
	public String initUpdate(Model model,HttpServletRequest request, String sid){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		Map<String, Object> dataMap = sysRbacApplicationService.getEntity(params);
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("menuid", menuid);
		return url+"edit";
	}
	/**
	 * 保存修改数据
	 * @param model
	 * @param request
	 * @param entity
	 * @return
	 *
	 * @author lzx
	 * @since 2018年3月29日下午4:10:33
	 */
	@FormToken(remove=true)
	@RequestMapping(value="update")
	@ResponseBody
	public String update(Model model,HttpServletRequest request, SysRbacApplication entity){
    	//获取当前登陆用户
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	//验证应用名称唯一
    	if(this.validateAppname(entity.getAppname(),entity.getSid())){
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "应用名已存在！");
		}    		    	   	
		String errMsg = "";
		try {
			entity.setUpdateBy(rbacUser.getUsername());
			entity.setUpdateTime(DateUtils.getTimeNow());
			sysRbacApplicationService.updateEntity(entity);
			globalWriteService.writeGlobalApplication();//更新缓存
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}   
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	/**
	 * 删除应用
	 * @param model
	 * @param request
	 * @param sid
	 * @return
	 *
	 * @author lzx
	 * @since 2018年3月29日下午4:10:48
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public String delete(Model model,HttpServletRequest request, String sid){
		String errMsg = "";
		Map<String,Object> params = new HashMap<String,Object>();
		/*应用删除时查找sys_rbac_menu中的appid个数,个数超过0 无法删除*/
		params.put("appid", sid);
		Long countAppid = sysRbacMenuService.countEntity(params);
		if(countAppid >0){
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "菜单信息中存在该应用,"+JsdpConstants.AJAX_RESULT_MSG_F);
		}
		try {
			SysRbacApplication entity = new SysRbacApplication();
			entity.setDelFlag(JsdpConstants.HFMP_Delflag_Y);
			entity.setSid(sid);
			sysRbacApplicationService.updateEntity(entity);
			globalWriteService.writeGlobalApplication();//更新缓存
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}   
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	
}
