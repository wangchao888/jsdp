package com.joinsoft.module.system.user.web;

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
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.core.utils.page.PageTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.user.entity.SysRbacUser;
import com.joinsoft.module.system.user.service.SysRbacUserlogService;
import com.joinsoft.platform.global.entity.CacheRbacUser;

/**
 * 用户登录日志查询
 * @author LZX 2018-05-03
 *
 */
@Controller
public class SysRbacUserlogController {

	@Autowired
	private IUserService userService;
	@Autowired
	private SysRbacUserlogService sysRbacUserlogService;
	
	private String url ="system/user/";
	private String menuid = "";
	
	/**
	 * 进入用户日志列表
	 * @param model
	 * @param request
	 * @return
	 * @author LZX 2018-05-03 1002
	 */
	@RequestMapping(value="/system/user/loglist")
	public String list(Model model, String menuid, HttpServletRequest request){
		this.menuid = menuid;
		return url + "loglist";
	}
	/**
	 * 数据显示
	 * @param entity
	 * @param request
	 * @return
	 *
	 * @author LZX
	 * @since 2018-05-03 1002
	 */
	@RequestMapping(value = "/system/user/logshow")
    @ResponseBody
    public String show(SysRbacUser entity, HttpServletRequest request) { 
//		String userId = (String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID);
//		CacheRbacUser rbacUser = userService.getRbacUserBySessionid(userId);
		String loginname = request.getParameter("loginname");
		String username = request.getParameter("username");
        Page page = PageTools.getPageByParam(request);
        Map<String, Object> conditions = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(loginname)) {
			conditions.put("loginname", StringUtils.clean(loginname));
		}
        if (!StringUtils.isEmpty(username)) {
			conditions.put("username", StringUtils.clean(username));
		}
        conditions.put("page", page);
        conditions.put("order", "operTime desc");
        List<Map<String, Object>> dataList = sysRbacUserlogService.getEntityByPage(conditions);
        return JsonTools.ToDatatableJson(dataList, StringUtils.toString(page.getDraw()), StringUtils.toString(page.getTotalResult()));
    }
	
}
