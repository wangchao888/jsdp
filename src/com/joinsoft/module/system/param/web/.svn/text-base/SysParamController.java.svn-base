package com.joinsoft.module.system.param.web;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.joinsoft.module.system.param.entity.SysParam;
import com.joinsoft.module.system.param.service.SysParamService;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalWriteService;

/**
 * 系统核心参数
 * @author GX
 * @since 2017-05-22
 */
@Controller
@RequestMapping(value = "/system/param/")
public class SysParamController {
	
	public String url = "system/param/";
	@Autowired
	private SysParamService sysParamService;
	@Autowired
	private IGlobalWriteService  globalWriteService;
	@Autowired
	private IUserService  userService;
	private String menuid = "";
	
	/**
	 * 进入系统参数列表
	 * @param model
	 * @param request
	 * @param menuid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:23:58
	 */
	@RequestMapping(value = "list")
	public String list(Model model,HttpServletRequest request,String menuid){
		this.menuid = menuid;
		model.addAttribute("menuid",menuid);
		return url+"list";
	}
	/**
	 * 数据显示
	 * @param entity
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:24:13
	 */
	@RequestMapping(value = "show")
	@ResponseBody
	public String show(SysParam entity,HttpServletRequest request){
		Page page = PageTools.getPageByParam(request);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("page", page);
		params.put("delflag", JsdpConstants.HFMP_Delflag_N);
		params.put("order", "Createtime desc");
		List<Map<String,Object>> dataList = sysParamService.getEntityByPage(params);
		return JsonTools.ToDatatableJson(dataList, StringUtils.toString(page.getDraw()), StringUtils.toString(page.getTotalResult()));
	}
	
	/**
	 * 进入增加页面
	 * @param model
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:24:22
	 */
	@FormToken(save=true)	
	@RequestMapping(value = "initAdd")
	public String add(Model model,HttpServletRequest request){
		model.addAttribute("menuid", menuid);
		return url+"add";
	}
	/**
	 * 新增数据保存
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param model
	 * @param request
	 * @param entity
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:25:06
	 */
	@FormToken(remove=true)
	@RequestMapping(value = "add")
	@ResponseBody
	public String save(Model model,HttpServletRequest request,SysParam entity){
		//获取当前登陆用户
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	String errMsg = "";
		try {
			entity.setSid(UUIDUtils.get32UUID());
			entity.setCreateby(rbacUser.getUsername());
			entity.setCreatetime(DateUtils.getTimeNow());
			entity.setDelflag(JsdpConstants.HFMP_Delflag_N);
			sysParamService.addEntity(entity);
			 globalWriteService.writeGlobalParam();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	
	/**
	 * 进入修改页面
	 * @param model
	 * @param request
	 * @param sid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:25:18
	 */
	@FormToken(save=true)
	@RequestMapping(value = "initUpdate")
	public String edit(Model model,HttpServletRequest request,String sid){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sid", sid);
		Map<String,Object> dataMap = sysParamService.getEntity(params);
		model.addAttribute("dataMap", dataMap);
		model.addAttribute("menuid", menuid);
		return url+"edit";
	}
	
	/**
	 * 修改数据保存
	 * @param model
	 * @param request
	 * @param entity
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:25:34
	 */
	@FormToken(remove=true)
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Model model,HttpServletRequest request,SysParam entity){
		//获取当前登陆用户
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	String errMsg = "";
		try {
			entity.setUpdateby(rbacUser.getUsername());
			entity.setUpdatetime(DateUtils.getTimeNow());
			sysParamService.updateEntity(entity);
			 globalWriteService.writeGlobalParam();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	/**
	 * 系统核心参数删除
	 * @param model
	 * @param request
	 * @param sid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:25:57
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public String del(Model model,HttpServletRequest request, String sid){
		String errMsg = "";
		try {
			SysParam entity = new SysParam();
			entity.setDelflag(JsdpConstants.HFMP_Delflag_Y);
			entity.setSid(sid);
			sysParamService.updateEntity(entity);
			globalWriteService.writeGlobalParam();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	
}
