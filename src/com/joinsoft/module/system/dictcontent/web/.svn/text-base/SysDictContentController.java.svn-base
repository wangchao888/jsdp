package com.joinsoft.module.system.dictcontent.web;

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
import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.token.annotation.FormToken;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.core.utils.page.PageTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dictcontent.entity.SysDictContent;
import com.joinsoft.module.system.dictcontent.service.SysDictContentService;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalWriteService;
/**
 * 字典内容
 * @author scy
 * @since  2018年3月29日
 */
@Controller
@RequestMapping(value="/system/dictcontent/")
public class SysDictContentController {
	
	private String url ="system/dictcontent/";
	@Autowired
	private SysDictContentService sysDictContentService;
	@Autowired
	private IGlobalWriteService globalWriteService;
	@Autowired
	private IUserService userService;
	private String menuid = "";

	/**
	 * 进入字典内容列表
	 * @param model
	 * @param dictno
	 * @param menuid
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午5:22:05
	 */
	@RequestMapping(value="list")
	public String list(Model model,String dictno,String menuid){
		model.addAttribute("dictno", dictno);
		this.menuid = menuid;
		model.addAttribute("menuid", menuid);
		return url+"list";
	}
	/**
	 * 数据显示
	 * @param request
	 * @param dictno
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午5:22:15
	 */
	@RequestMapping(value = "/show")
    @ResponseBody
    public String show(HttpServletRequest request,String dictno) {
        Page page = PageTools.getPageByParam(request);
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("page", page);
        conditions.put("order", "DICTLABEL");
        conditions.put("dictno", dictno);
        conditions.put("delflag",JsdpConstants.HFMP_Delflag_N);
        List<Map<String, Object>> dataList = sysDictContentService.getEntityByPage(conditions);
        return JsonTools.ToDatatableJson(dataList, StringUtils.toString(page.getDraw()), StringUtils.toString(page.getTotalResult()));
    }
	/**
	 * 进入增加界面
	 * @param model
	 * @param dictno
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午5:23:00
	 */
	@FormToken(save=true)
	@RequestMapping(value="initAdd")
	public String initAdd(Model model,String dictno){
		model.addAttribute("dictno", dictno);
		model.addAttribute("add", "add");
		model.addAttribute("menuid", menuid);
		return url+"edit";
	}
	
	/**
	 * 保存增加的信息
	 * @param model
	 * @param request
	 * @param entity
	 * @return
	 *
	 * @author scy
	 * @since 2018年3月29日下午5:23:13
	 */
	@FormToken(remove=true)
	@RequestMapping(value="add")
	@ResponseBody
	public String add(Model model,HttpServletRequest request, SysDictContent entity){
		String errMsg = "";
    	//获取当前登陆用户
		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		try {
			entity.setSid(UUIDUtils.get32UUID());
			entity.setCreateby(rbacUser.getUsername());
			entity.setCreatetime(DateUtils.getTimeNow());
			entity.setDelflag(JsdpConstants.HFMP_Delflag_N);
			entity.setZonecode("");
			sysDictContentService.addEntity(entity);
			globalWriteService.writeGlobalDictContentByDictno(entity.getDictno());
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
  	public String initUpdate(String sid,Model model,HttpServletRequest request){
  		//角色信息
  		Map<String, Object> conditions = new HashMap<String, Object>();
  		conditions.put("sid", sid);
  		Map<String, Object> rolelist = sysDictContentService.getEntity(conditions);
  				
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
  	@RequestMapping(value="update")
	@ResponseBody
	@FormToken(remove=true)
	public String update(Model model,HttpServletRequest request, SysDictContent entity){
  		String errMsg = "";
    	//获取当前登陆用户
  		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		try {
			entity.setUpdateby(rbacUser.getUsername());
			entity.setUpdatetime(DateUtils.getTimeNow());
			entity.setDelflag(JsdpConstants.HFMP_Delflag_N);		
			sysDictContentService.updateEntity(entity);
			globalWriteService.writeGlobalDictContentByDictno(entity.getDictno());
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
	}
	
	
  	/**
     * 
     * 功能: 删除字典内容
     * 
     * @param entity 
     * @return 
     *         作者：scy
     */
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(SysDictContent entity) {
    	String errMsg = "";
    		 try {
    			 entity.setDelflag(JsdpConstants.HFMP_Delflag_Y);
    			 sysDictContentService.updateEntity(entity);
    			 globalWriteService.writeGlobalDictContentByDictno(entity.getDictno());
    			 return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
			} catch (Exception e) {
				 errMsg = e.getMessage();
			} 
    		 return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }
	
	
}
