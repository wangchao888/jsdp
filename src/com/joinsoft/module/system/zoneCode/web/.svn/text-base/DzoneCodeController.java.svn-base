/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： DzoneCodeController.java
 *              (C) Copyright shandong joinsoft Corporation 2018
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.module.system.zoneCode.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.joinsoft.module.HfmpConstants;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dept.service.SysRbacOrgService;
import com.joinsoft.module.system.dictcontent.service.SysDictContentService;
import com.joinsoft.module.system.zoneCode.entity.DzoneCode;
import com.joinsoft.module.system.zoneCode.service.DzoneCodeService;
import com.joinsoft.platform.global.entity.CacheRbacUser;
/**
 * 区县代码
 * @author scy
 * @since  2018年3月23日
 */
@Controller		
@RequestMapping(value = "/system/zoneCode/")
public class DzoneCodeController {
	public String url = "/system/zoneCode/";
	private String menuid = "";
	@Autowired
	private DzoneCodeService dzonecodeService;
	@Autowired
	private SysDictContentService sysDictContentService;
	@Autowired
	private IUserService userService;
	@Autowired
	private SysRbacOrgService rbacOrgService;

	/**
	 * 进入区县代码列表
	 * @param model
	 * @param request
	 * @param menuid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月23日上午11:03:32
	 */
	@RequestMapping(value = "list")
	public String list(Model model, HttpServletRequest request, String menuid) {
		this.menuid = menuid;
		model.addAttribute("menuid", menuid);
		return url + "list";
	}
	/**
	 * 数据显示并分页
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月23日上午11:03:52
	 */
	@RequestMapping(value = "show")
	@ResponseBody
	public String show(HttpServletRequest request) {
		Page page = PageTools.getPageByParam(request);
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("delflag", JsdpConstants.HFMP_Delflag_N);
		conditions.put("page", page);
		conditions.put("order", "zonecode");
		List<Map<String, Object>> dataList = dzonecodeService.getEntityByPage(conditions);
		return JsonTools.ToDatatableJson(dataList, StringUtils.toString(page.getDraw()),
				StringUtils.toString(page.getTotalResult()));
	}
	/**
	 * 进入新增界面
	 * @param model
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月23日上午11:04:10
	 */
	@FormToken(save=true)
	@RequestMapping(value = "add")
	public String initAdd(Model model) {
		// 区域类型
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dictno", "92");
		params.put("state", "1");
		List<Map<String, Object>> dictList = sysDictContentService.getEntityByList(params);
		params.clear();
		params.put("order", "sid");
		List<ZTree> zoneTree = rbacOrgService.getAreaTree(params);
  		model.addAttribute("zoneTree", zoneTree);//上级
		model.addAttribute("dictList", dictList);
		model.addAttribute("menuid", menuid);
		return url + "add";
	}

	/**
	 * 保存新增信息
	 * 
	 * @param model
	 * @param request
	 * @param entity
	 * @return
	 * 
	 *  @author scy
	 * @since 2018年3月23日上午11:04:10
	 */
	@FormToken(remove=true)
	@RequestMapping(value = "save")
	@ResponseBody
	public String add(Model model, HttpServletRequest request, DzoneCode entity, HttpSession session) {
		String errMsg = "";
		// 获取当前登陆用户
		String userId = (String) session.getAttribute(SessionConstants.JSDP_USER_SESSIONID);
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid(userId);
		// 验证区域代码唯一性
		if (this.validateZoneCode(entity.getZonecode())) {
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "区域编码已存在！");
		}
		try {
			if (StringUtils.isEmpty(entity.getPid())) {
				entity.setPid("0");
			}
			entity.setSid(UUIDUtils.get32UUID());
			entity.setCreateby(rbacUser.getLoginname());
			entity.setCreatetime(DateUtils.getTimeNow());
			entity.setDelflag(JsdpConstants.HFMP_Delflag_N);
			dzonecodeService.addEntity(entity);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, JsdpConstants.AJAX_RESULT_MSG_F);
	}

	/**
	 * 验证区域编码是否存在
	 * 
	 * @param appname
	 * @return
	 * 
	 * @author scy
	 * @since 2018年3月23日上午11:04:10
	 */
	public boolean validateZoneCode(String zoneCode) {
		if (!StringUtils.isBlank(zoneCode)) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("zonecode", zoneCode);
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			Map<String, Object> dataMap = dzonecodeService.getEntity(params);
			if (dataMap != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 进入编辑界面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * 
	 * @author scy
	 * @since 2018年3月23日上午11:04:10 
	 */
	@FormToken(save=true)
	@RequestMapping(value = "edit")
	public String initUpdate(String id, Model model) {
		//上级
		Map<String, Object> params = new HashMap<String, Object>();
		params.clear();
		params.put("order", "sid");
		List<ZTree> zoneTree = rbacOrgService.getAreaTree(params);
  		model.addAttribute("zoneTree", zoneTree);
  		
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("sid", id);
		Map<String, Object> dataMap = dzonecodeService.getEntity(conditions);
		model.addAttribute("dataMap", dataMap);
		if (!"0".equals(StringUtils.getString(dataMap, "PID"))) {
			conditions.put("sid", dataMap.get("PID"));
			Map<String, Object> pDataMap = dzonecodeService.getEntity(conditions);
			model.addAttribute("pDataMap", pDataMap);
		}
		model.addAttribute("menuid", menuid);
		return url + "edit";
	}

	/**
	 * 更新数据
	 * 
	 * @param model
	 * @param request
	 * @param entity
	 * @param session
	 * @return
	 * 
	 * @author scy
	 * @since 2018年3月23日上午11:04:10 
	 */
	@FormToken(remove=true)
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(Model model, HttpServletRequest request, DzoneCode entity, HttpSession session) {
		String errMsg = "";
		// 获取当前登陆用户
		String userId = (String) session.getAttribute(SessionConstants.JSDP_USER_SESSIONID);
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid(userId);
		try {
			if (StringUtils.isEmpty(entity.getPid())) {
				entity.setPid("0");
			}
			entity.setUpdateby(rbacUser.getLoginname());
			entity.setUpdatetime(DateUtils.getTimeNow());
			entity.setDelflag(JsdpConstants.HFMP_Delflag_N);
			dzonecodeService.updateEntity(entity);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}

	/**
	 * 删除实体
	 * 
	 * @param id
	 * @param entity
	 * @return
	 * 
	 * @author scy
	 * @since 2018年3月23日上午11:04:10 
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(String id, DzoneCode entity) {
		String errMsg = "";
		try {
			entity.setSid(id);
			entity.setDelflag(JsdpConstants.HFMP_Delflag_Y);
			dzonecodeService.updateEntity(entity);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}

}

