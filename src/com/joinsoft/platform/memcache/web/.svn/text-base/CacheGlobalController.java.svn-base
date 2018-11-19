package com.joinsoft.platform.memcache.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalReadService;
import com.joinsoft.platform.global.service.IGlobalUpdateService;

@Controller
@RequestMapping(value="/cache/global/")
public class CacheGlobalController {
	
	@Autowired
	private IGlobalReadService globalReadService;
	@Autowired
	private IGlobalUpdateService globalUpdateService;
	private String url ="cache/global/";
	private String menuid = "";
	
	/**
	 * 全局缓存列表
	 * @param model
	 * @param request
	 * @param menuid
	 * @return
	 */
	@RequestMapping(value="list")
	public String list(Model model,HttpServletRequest request, String menuid){
		this.menuid = menuid;
		List<CacheRbacApplication>  appList = globalReadService.getGlobalApplication();
		List<CacheRbacRole> rbacRoleList = globalReadService.getGlobalRole();
		List<CacheRbacOrg> rbacOrgList = globalReadService.getGlobalOrg();
		List<CacheRbacUser> rbacUserList = globalReadService.getGlobalUser();
		model.addAttribute("appList", appList);
		model.addAttribute("roleList", rbacRoleList);
		model.addAttribute("orgList", rbacOrgList);
		model.addAttribute("userList", rbacUserList);
		model.addAttribute("menuid", menuid);
		return url+"list";
	}
	
	/**
	 * 字典内容明细
	 * @param model
	 * @param menuid
	 * @param dictno
	 * @return
	 */
	@RequestMapping(value = "dictContentList")
	public String dictContentList(Model model,String menuid,String dictno){
//		if(!StringUtils.isEmpty(dictno)){
//			List<CacheDictContent> dictContentList = globalReadService.getGlobalDictContent(dictno);
//			model.addAttribute("dictContentList", dictContentList);
//		}
		model.addAttribute("menuid", menuid);
		return url + "dictContentList";
	}
	
	/**
	 * 机构下所属用户
	 * @param model
	 * @param menuid
	 * @param orgid
	 * @return
	 */
	@RequestMapping(value = "userList")
	public String userList(Model model,String menuid,String orgid){
		List<CacheRbacUser> rbacUserList = globalReadService.getGlobalUser();
		List<CacheRbacUser> userList = new ArrayList<CacheRbacUser>();
		if(!StringUtils.isEmpty(orgid)){
			for (CacheRbacUser cacheRbacUser : rbacUserList) {
				if(orgid.equals(cacheRbacUser.getOrgid())){
					userList.add(cacheRbacUser);
				}
			}
		}
		model.addAttribute("userList", userList);
		return url + "userList";
	}
	
	/**
	 * 缓存刷新
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cacheFlash")
	@ResponseBody
	public String cacheFlash(Model model,HttpServletRequest request){
		String errMsg = "";
		try {
			globalUpdateService.updateGlobalApplication();//应用更新
			globalUpdateService.updateGlobalOrg();//机构更新
//			globalUpdateService.updateGlobalParam();//参数更新
			globalUpdateService.updateGlobalRole();//角色更新
			globalUpdateService.updateGlobalUser();//用户更新
//			List<CacheDicType> dicTypeList = globalUpdateService.updateGlobalDicType();//字典类别更新
//			for (CacheDicType cacheDicType : dicTypeList) {
//				globalUpdateService.updateGlobalDictContent(cacheDicType.getDictno());//字典内容更新
//			}
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getLocalizedMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, JsdpConstants.AJAX_RESULT_MSG_F);
	}
	
}
