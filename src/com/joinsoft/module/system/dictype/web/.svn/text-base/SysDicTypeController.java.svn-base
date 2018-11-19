package com.joinsoft.module.system.dictype.web;

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
import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.token.annotation.FormToken;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.core.utils.page.PageTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dictcontent.service.SysDictContentService;
import com.joinsoft.module.system.dictype.entity.SysDicType;
import com.joinsoft.module.system.dictype.service.SysDicTypeService;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalWriteService;
/**
 * 字典类型
 * @author scy
 * @since  2018年3月29日
 */
@Controller
@RequestMapping(value="/system/dictype/")
public class SysDicTypeController {

	@Autowired
	private SysDicTypeService sysDicTypeService;
	@Autowired
	private IGlobalWriteService globalWriteService;
	@Autowired
	private IUserService userService;
	@Autowired
	private SysDictContentService dictContentService;
	
	private String url ="system/dictype/";
	
	private String menuid = "";
	
	
	/**
	 * 进入字典类型列表
	 * @param model
	 * @param menuid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:31:29
	 */
	@RequestMapping(value="list")
	public String list(Model model,String menuid){
		this.menuid = menuid;
		model.addAttribute("menuid", menuid);
		return url+"list";
	}
	/**
	 * 数据显示
	 * @param request
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:31:48
	 */
	@RequestMapping(value = "/show")
    @ResponseBody
    public String show(HttpServletRequest request) {
        Page page = PageTools.getPageByParam(request);
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("delflag",JsdpConstants.HFMP_Delflag_N);
        conditions.put("page", page);
        conditions.put("order", "dictno");
        List<Map<String, Object>> dataList = sysDicTypeService.getEntityByPage(conditions);
        return JsonTools.ToDatatableJson(dataList, StringUtils.toString(page.getDraw()), StringUtils.toString(page.getTotalResult()));
        
    }
	
	/**
	 * 进入增加界面
	 * @param model
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:31:56
	 */
	@FormToken(save=true)
	@RequestMapping(value="initAdd")
	public String initAdd(Model model){
		model.addAttribute("add", "add");
		model.addAttribute("menuid", menuid);
		return url+"edit";
	}
	/**
	 * 验证字典标识是否存在
	 * @param dictNo
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:32:27
	 */
	public boolean validateDictNo(String dictNo){
		if(!StringUtils.isBlank(dictNo)){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dictno", dictNo);
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			Map<String, Object> dataMap = sysDicTypeService.getEntity(params);
			if(dataMap != null){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 保存增加的信息
	 * %方法功能的一句话概括%。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param model
	 * @param request
	 * @param entity
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午4:32:37
	 */
	@FormToken(remove=true)
	@RequestMapping(value="add")
	@ResponseBody
	public String add(Model model,HttpServletRequest request, SysDicType entity){
		String errMsg = "";
    	//获取当前登陆用户
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	//验证字典标识唯一性
    	if(this.validateDictNo(entity.getDictno())){
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, "字典标识已存在！");
		}
		try {
			entity.setSid(UUIDUtils.get32UUID());
			entity.setCreateby(rbacUser.getUsername());
			entity.setCreatetime(DateUtils.getTimeNow());
			entity.setDelflag(JsdpConstants.HFMP_Delflag_N);
			sysDicTypeService.addEntity(entity);
			globalWriteService.writeGlobalDicType();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	/**
     * 
     * 功能: 进入字典内容编辑界面
     * 
     * @param model 
     * @param request
     * @param page
     * @return system/dept/edit
     *         作者：scy
     */
	@FormToken(save=true)
  	@RequestMapping(value="initUpdate")
  	public String initUpdate(String sid,Model model){
  		//角色信息
  		Map<String, Object> conditions = new HashMap<String, Object>();
  		conditions.put("sid", sid);//已修改
  		Map<String, Object> rolelist = sysDicTypeService.getEntity(conditions);
  				
  		model.addAttribute("rolelist", rolelist);	
  		model.addAttribute("add", "update");
  		model.addAttribute("menuid", menuid);
  		return url+"edit";
  		
  	}	
	
  	/**
     * 
     * 功能: 保存字典内容编辑信息
     * 
     * @param model 
     * @param request
     * @param entity
     * @return map
     *         作者：scy
     */
	@FormToken(remove=true)
  	@RequestMapping(value="update")
	@ResponseBody
	public String update(Model model,HttpServletRequest request, SysDicType entity){
  		String errMsg = "";
    	//获取当前登陆用户
  		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		try {
			entity.setUpdateby(rbacUser.getUsername());
			entity.setUpdatetime(DateUtils.getTimeNow());
			entity.setDelflag(JsdpConstants.HFMP_Delflag_N);		
			sysDicTypeService.updateEntity(entity);
			globalWriteService.writeGlobalDicType();
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
  	
  	/**
     * 
     * 功能: 删除字典类别
     * 
     * @param entity 
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(SysDicType entity) {
    	String errMsg = "";
    		 try {
    			 entity.setDelflag(JsdpConstants.HFMP_Delflag_Y);
    			 sysDicTypeService.updateDelte(entity);
    			 globalWriteService.writeGlobalDicType();
    			 return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
			} catch (Exception e) {
				
			} 
    		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
    /**
     * 验证是否有明细数据
     * %方法功能的一句话概括%。
     * <p>%方法详述（简单方法可不必）%</p>
     * @param request
     * @param dictno
     * @return
     *
     * @author scy
     * @since 2018年4月8日下午6:22:44
     */
  	@RequestMapping(value="validation")
	@ResponseBody
	public String validation(HttpServletRequest request, String dictno){
  		String errMsg = "";
  		Map<String, Object> params = new HashMap<String, Object>();
  		params.put("dictno", dictno);
  		params.put("delflag",JsdpConstants.HFMP_Delflag_N);
  		params.put("state", "1");
		try {
			if (CollectionUtils.isEmpty(dictContentService.getEntityByList(params))) {
				return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, "是否删除当前记录？");
			} else {
				return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, "存在明细信息，是否删除当前记录？");
			}
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}	
	
}
