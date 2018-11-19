package com.joinsoft.module.system.dyntable.web;

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
import com.joinsoft.module.system.dyntable.entity.Ddyntable;
import com.joinsoft.module.system.dyntable.service.DdyntableService;
/**
 * 动态维护表
 * %类功能的概括%。
 * @author scy
 * @since  2018年3月29日
 */
@Controller
@RequestMapping(value="/system/dyntable/")
public class DdyntableController {
	
	private String url ="system/dyntable/";
	
	@Autowired
	private DdyntableService ddyntableService;
	private String menuid = "";
	/**
	 * 进入动态维护表列表
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param model
	 * @param request
	 * @param menuid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午6:22:56
	 */
	@RequestMapping(value="list")
	public String list(Model model,HttpServletRequest request,String menuid){
		this.menuid = menuid;
		model.addAttribute("menuid", menuid);
		return url+"list";
	}
	/**
	 * 数据显示
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午6:23:07
	 */
	@RequestMapping(value = "/show")
    @ResponseBody
    public String show(HttpServletRequest request) {
        Page page = PageTools.getPageByParam(request);
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("page", page);
        conditions.put("order", "tableId desc");
        List<Map<String, Object>> dataList = ddyntableService.getEntityByPage(conditions);
        return JsonTools.ToDatatableJson(dataList, StringUtils.toString(page.getDraw()), StringUtils.toString(page.getTotalResult()));
    }
	/**
	 * 进入增加页面
	 * @param model
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午6:23:15
	 */
	@FormToken(save=true)
	@RequestMapping(value="initAdd")
	public String initAdd(Model model,HttpServletRequest request){
		model.addAttribute("menuid", menuid);
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
	 * @since 2018年3月29日下午6:24:42
	 */
	@FormToken(remove=true)
	@RequestMapping(value="add")
	@ResponseBody
	public String add(Model model,HttpServletRequest request, Ddyntable entity){
		String errMsg = "";
		try {
			entity.setId(UUIDUtils.get32UUID());
			ddyntableService.addEntity(entity);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}   
    	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	/**
	 * 进入修改页面
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param model
	 * @param request
	 * @param sid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午6:24:53
	 */
	@FormToken(save=true)
	@RequestMapping(value="initUpdate")
	public String initUpdate(Model model,HttpServletRequest request, String sid){
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("where", "and id = '"+sid+"'");
		Map<String, Object> dataMap = ddyntableService.getEntity(params);
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
	 * @since 2018年3月29日下午7:07:39
	 */
	@FormToken(remove=true)
	@RequestMapping(value="update")
	@ResponseBody
	public String update(Model model,HttpServletRequest request, Ddyntable entity){
		String errMsg = "";
		try {
			ddyntableService.updateEntity(entity);
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
	 * @param sid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午7:08:02
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public String delete(Model model,HttpServletRequest request, String sid){
		String errMsg = "";
		try {
			ddyntableService.deleteEntity(sid);
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}   
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	

}
