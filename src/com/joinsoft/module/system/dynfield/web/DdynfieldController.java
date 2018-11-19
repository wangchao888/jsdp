package com.joinsoft.module.system.dynfield.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.token.annotation.FormToken;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.core.utils.page.PageTools;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dynfield.entity.Ddynfield;
import com.joinsoft.module.system.dynfield.service.DdynfieldService;
/**
 * 动态字段
 * %类功能的概括%。
 * @author scy
 * @since  2018年3月29日
 */
@Controller
@RequestMapping(value="/system/dynfield/")
public class DdynfieldController {
	
	private String url ="system/dynfield/";
	
	@Autowired
	private DdynfieldService ddynfieldService;
	private String menuid = "";
	/**
	 * 进入字段列表
	 * @param model
	 * @param request
	 * @param extid
	 * @param menuid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午7:26:52
	 */
	@RequestMapping(value="list")
	public String list(Model model,HttpServletRequest request,String extid,String menuid){
		this.menuid = menuid;
		model.addAttribute("menuid", menuid);
		model.addAttribute("extid", extid);
		return url+"list";
	}
	/**
	 * 数据显示
	 * @param request
	 * @param extid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午7:27:10
	 */
	@RequestMapping(value = "/show")
    @ResponseBody
    public String show(HttpServletRequest request,String extid) {
        Page page = PageTools.getPageByParam(request);
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("page", page);
        conditions.put("extid", extid);
        conditions.put("order", "ORDERNO");
        List<Map<String, Object>> dataList = ddynfieldService.getEntityByPage(conditions);
        return JsonTools.ToDatatableJson(dataList, StringUtils.toString(page.getDraw()), StringUtils.toString(page.getTotalResult()));
    }
	/**
	 * 进入增加页面
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午7:27:17
	 */
	@FormToken(save=true)
	@RequestMapping(value="initAdd")
	public String initAdd(Model model,HttpServletRequest request,String id){
		model.addAttribute("menuid", menuid);
		model.addAttribute("extid", id);
		return url+"add";
	}
	/**
	 * 保存增加数据
	 * @param model
	 * @param request
	 * @param entity
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午7:27:35
	 */
	@RequestMapping(value="add")
	@ResponseBody
	@FormToken(remove=true)
	public String add(Model model,HttpServletRequest request, Ddynfield entity){
		String errMsg = "";
		try {
			entity.setId(UUIDUtils.get32UUID());
			ddynfieldService.addEntity(entity);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}   
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	/**
	 * 进入修改界面
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午7:27:48
	 */
	@FormToken(save=true)
	@RequestMapping(value="initUpdate")
	public String initUpdate(Model model,HttpServletRequest request, String id){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("where", "and id = '"+id+"'");
		Map<String, Object> dataMap = ddynfieldService.getEntity(params);
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
	 * @author scy
	 * @since 2018年3月29日下午7:28:05
	 */
	@FormToken(remove=true)
	@RequestMapping(value="update")
	@ResponseBody
	public String update(Model model,HttpServletRequest request, Ddynfield entity){
		String errMsg = "";
		try {
			ddynfieldService.updateEntity(entity);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}   
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	
	/**
	 * 删除
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午7:28:14
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public String delete(Model model,HttpServletRequest request, String id){
		String errMsg = "";
		try {
			ddynfieldService.deleteEntity(id);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}   
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	

}
