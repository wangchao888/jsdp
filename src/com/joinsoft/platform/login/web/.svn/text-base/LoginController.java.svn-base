/**
 * 文 件 名：LoginController.java
 * 创建日期：2014-08-07
 * Copyright @ 1995-2014 joinsoft Co.Ltd. all right reserved.
 */

package com.joinsoft.platform.login.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinsoft.SessionConstants;
import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.encrypt.EncryptUtils;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.core.utils.json.JsonUtils;
import com.joinsoft.dubbo.service.ICommonService;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.menu.service.SysUserShortcutService;
import com.joinsoft.module.system.notice.service.SysNoticeinfoService;
import com.joinsoft.module.system.sysinfo.service.SysInfoService;
import com.joinsoft.module.system.user.entity.SysRbacUser;
import com.joinsoft.module.system.user.entity.SysRbacUserlog;
import com.joinsoft.module.system.user.service.SysRbacUserService;
import com.joinsoft.module.system.user.service.SysRbacUserlogService;
import com.joinsoft.platform.global.entity.CacheAuth;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacMenu;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.ISessionReadService;
import com.joinsoft.platform.login.service.LoginService;

@Controller
public class LoginController{
	
	private static final Logger log = LoggerFactory
			.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private ISessionReadService sessionReadService;
	@Autowired
	private SysRbacUserService sysRbacUserService;
	@Autowired
	private SysInfoService sysInfoService;
	@Autowired
	private IUserService userService;
	@Autowired
	private SysNoticeinfoService noticeinfoService;
	@Autowired
	private ICommonService commonService;
	@Autowired
	private SysRbacUserlogService sysRbacUserlogService;
	@Autowired
	private SysUserShortcutService userShortcutService;
	
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		model.addAttribute("testFlag", sysInfoService.getEntity(null).get("TESTFLAG"));
		Map<String, Object> dataMap = sysInfoService.getEntity(null);
		model.addAttribute("sysName", dataMap.get("SYSNAME"));
		
		//获取业务系统请求地址
    	model.addAttribute("address", loginService.address());
		return "login";
	}
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test";
    }
    
    /**
     * 登录方法
     *   根据用户名查询用户信息表，比对密码
     *   查询用户权限放入缓存 
     * @param model
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
	public Map<String,Object> login(Model model,HttpServletRequest request,HttpSession session){
    	Map<String,Object> retMap = new HashMap<String, Object>();
    	retMap.put("state", "false");
    	retMap.put("message", "登录错误！");
    	try {
        	Map<String,Object> params = new HashMap<String, Object>();
        	String loginname = StringUtils.clean(request.getParameter("username"));
        	String password = StringUtils.clean(request.getParameter("password"));
        	params.put("loginname", loginname);
        	params.put("delflag", JsdpConstants.HFMP_Delflag_N);
        	SysRbacUser userEntity = sysRbacUserService.getEntity(params);
        	// 日志记录 
        	SysRbacUserlog userLogEntity = new SysRbacUserlog();
        	userLogEntity.setLogtype(JsdpConstants.RULE_ZERO); // 0，登录；1，退出
        	if(userEntity != null){
        		//判断用户状态是否停用
        		String state = userEntity.getState();
        		if(!StringUtils.isBlank(state) && state.equals(JsdpConstants.HFMP_State_Y)){
        			String passwd = userEntity.getPasswd();
        			if(!StringUtils.isEmpty(password) && !StringUtils.isEmpty(passwd)){
        				password = EncryptUtils.sha1(password);
        				if(password.equals(passwd)){
        					//密码验证通过，查询权限
        					String sessionId = request.getSession(true).getId();
        					loginService.writeUserIdAndSessionId(userEntity.getSid(), sessionId); // 和下边的代码（setCacheinfo）不要置换位置，负责登录会出错
        					loginService.setCacheinfo(userEntity, sessionId);
        					retMap.put("sessionId", sessionId);
        					retMap.put("state", "true");
        					retMap.put("message", "登录成功！");
        					session.setAttribute(SessionConstants.JSDP_USER_SESSIONID, sessionId);
        					session.setAttribute("userid", userEntity.getSid());
        					session.setAttribute("loginname", userEntity.getLoginname());
        					// 日志相关
        					userLogEntity.setResult(JsdpConstants.RULE_ONE); // 0，失败；1，成功
        				}else{
        					// 日志相关
        					userLogEntity.setResult(JsdpConstants.RULE_ZERO); // 0，失败；1，成功
        					retMap.put("message", "用户输入密码不匹配！");
        				}
        			}
        		}else{
        			// 日志相关
    				userLogEntity.setResult(JsdpConstants.RULE_ZERO); // 0，失败；1，成功
        			retMap.put("message", "该用户已被停用！");
        		}
        	}
        	// 记录用户登录操作日志
        	userLogEntity.setPasswd(password);
        	userLogEntity.setOperip(getRemoteIP(request));
        	sysRbacUserlogService.saveEntityByLogin(userLogEntity, userEntity);
		} catch (Exception e) {
			e.printStackTrace();
			//捕获自己封装的登录异常内容
			String[] split = e.getMessage().split("_");
			if (CollectionUtils.isNotEmpty(split)&&split.length==2&&(JsdpConstants.HFMP_RESULT_FAIL).equals(split[0])) {
				retMap.put("message", split[1]);
			}
		}
    	
    	
    	return retMap;
    }
    
    @SuppressWarnings("finally")
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, Model model){
    	model.addAttribute("testFlag", sysInfoService.getEntity(null).get("TESTFLAG"));
    	Map<String, Object> dataMap = sysInfoService.getEntity(null);
		model.addAttribute("sysName", dataMap.get("SYSNAME"));
		String userid = request.getParameter("userid");
    	try {
//    		UserBean userBean = (UserBean)request.getSession().getAttribute(JsdpConstants.USER_SESSION);
    		if (request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID) == null) {
    			// 记录用户登录操作日志
            	sysRbacUserlogService.saveEntityByLogout(userid, "Session失效", getRemoteIP(request));
        		return "login";
    		}
        	String sessionId = (String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID);
        	CacheRbacUser userBean = sessionReadService.getSessionUser(sessionId);
        	if (userBean == null) {
        		// 记录用户登录操作日志
            	sysRbacUserlogService.saveEntityByLogout(userid, "缓存失效", getRemoteIP(request));
        		return "login";
    		}
        	log.info("退出用户为：" + sessionId);
        	delete(userBean.getSid(), sessionId);
            
            request.getSession(false).removeAttribute(JsdpConstants.USER_SESSION);
            
            request.getSession(false).invalidate();
            // 记录用户登录操作日志
        	sysRbacUserlogService.saveEntityByLogout(userid, "正常退出", getRemoteIP(request));
        	
        	//获取业务系统请求地址
        	model.addAttribute("address", loginService.address());
		} catch (Exception e) {
			// 记录用户登录操作日志
        	sysRbacUserlogService.saveEntityByLogout(userid, "异常退出", getRemoteIP(request));
			e.printStackTrace();
		} finally {
			return "login";
		}
	}
    
    private void delete(String sid, String sessionId){
    	loginService.removeCache(sid, sessionId);
    }
    
    @RequestMapping(value = "/main")
    public String main(Model model, HttpServletRequest request){
//    	UserBean userBean = (UserBean) request.getSession().getAttribute(JsdpConstants.USER_SESSION);
    	model.addAttribute("testFlag", sysInfoService.getEntity(null).get("TESTFLAG"));
    	if (request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID) == null) {
    		return "login";
		}
    	String sessionId = (String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID);
    	CacheRbacUser userBean = sessionReadService.getSessionUser(sessionId);
    	model.addAttribute("sessionId", sessionId);
    	if (userBean == null) {
    		return "login";
		}
    	try {
    		//登陆成功后获取该用户的菜单项
    		List<CacheRbacMenu> navList = (List<CacheRbacMenu>)sessionReadService.getSessionNavMenu(sessionId);
    		String navMenus = JsonUtils.ObjectToJson(navList);
    		model.addAttribute("navMenus", navMenus);
//    	List<String> leftMenus = loginService.getLeftMenu(userBean.getSessionId(), (String)request.getServletContext().getAttribute("ctx"));
    		String leftMenus = loginService.getLeftMenuOfJson(sessionId, (String)request.getServletContext().getAttribute("ctx"));//sessionid
    		model.addAttribute("leftMenus", leftMenus);
    		//获取显示系统名称以及测试环境标识
    		Map<String, Object> dataMap = sysInfoService.getEntity(null);
    		model.addAttribute("sysName", dataMap.get("SYSNAME"));
    		model.addAttribute("testFlag", dataMap.get("TESTFLAG"));
    		//用户相关数据 右上角展示
    		CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    		model.addAttribute("userName", rbacUser.getUsername());
    		CacheRbacOrg rbacOrg = userService.getRbacOrgBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    		model.addAttribute("rootOrgName", rbacOrg.getRootOrgname());
    		Map<String,Object> params = new HashMap<String, Object>();
    		params.put("sid", rbacUser.getSid());
    		model.addAttribute("icoPath", sysRbacUserService.getEntityMap(params).get("ICOPATH"));
    		//用户消息部分（显示通知条数）
    		params.clear();
    		params.put("userid", rbacUser.getSid());
    		model.addAttribute("count", noticeinfoService.countInfo(params));
    		model.addAttribute("userid", rbacUser.getSid());
    		// 获取核心应用的URL
    		CacheRbacApplication coreApp = commonService.getCoreCacheApp();
    		String coreAppURL = null;
	    	if(coreApp != null && coreApp.getAppip() != null && coreApp.getAppport() != null){
	    		coreAppURL = "http://" + coreApp.getAppip() + ":" + coreApp.getAppport() + "/" + coreApp.getWebview();
	            model.addAttribute("coreAppURL", coreAppURL);
	    	}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "main";
    }
    
    @RequestMapping(value = "/welcome")
    public String welcome(Model model,HttpServletRequest request,HttpSession session){
    	return "default";
    }
    
    @RequestMapping(value = "/right")
    public String right(){
    	return "right";
    }
    
    /**
     * 过滤器，没有权限时跳转
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/nologin")
    public String nologin(Model model,HttpServletRequest request){
    	return "nologin";
    }
    
    /**
     * 
     * 分割div。
     * <p>%方法详述（简单方法可不必）%</p>
     * @return
     *
     * @author 
     * @since 2014-10-16下午4:36:13
     */
    @RequestMapping(value = "/splits")
    public String splits(){
    	return "splits";
    }
    
    /**
     * 
     * %当前位置信息%。
     * @return
     *
     * @author 仝玉锐
     * @since 2015-8-3上午10:23:13
     */
    @RequestMapping(value = "/siteinfo")
    public String siteinfo(Model model,HttpServletRequest request){
        String sid = StringUtils.clean(request.getParameter("sid"));//菜单主键
        return "siteinfo";
    }
    
    /**
     * 获取客户端的真实IP
     *   
     * @param request
     * @return
     *
     * @author LZX 
     * @since 2018年5月2日上午11:04:41
     */
    public String getRemoteIP(HttpServletRequest request) {
    	if (request.getHeader("x-forwarded-for") == null) { 
    		return request.getRemoteAddr();
    	}
    	// 有可能是一串，目前只粗略截取，不进行精准截取 
    	String ip = request.getHeader("x-forwarded-for"); 
    	if (ip != null && !"".equals(ip)) {
			if (ip.length() > 30) {
				return ip.substring(0, 29);
			}
		}
    	return ip;
    }
    /**
     * 跳转至首页
     *   
     * @param request
     * @return
     *
     * @author 孙晨阳
     * @since 2018年9月21日上午09:00:41
     */
    @RequestMapping(value = "/index")
    public String index(Model model,HttpServletRequest request){
    	CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	CacheAuth auth = userService.getAuthBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
		List<String> zonecodes = auth.getZonecodes();
		if(CollectionUtils.isNotEmpty(zonecodes)){
			String zonecode = "(";
			for (int i = 0; i < zonecodes.size(); i++) {
				if(i == zonecodes.size()-1){
					zonecode += "'"+zonecodes.get(i)+"'";
				}else{
					zonecode += "'"+zonecodes.get(i)+"',";
				}
			}
			model.addAttribute("zonecodes", zonecode+")");
		}
		model.addAttribute("compno", auth.getOrgno());
    	Map<String,Object> params = new HashMap<String, Object>();
    	params.put("sid", rbacUser.getSid());
    	model.addAttribute("icoPath", sysRbacUserService.getEntityMap(params).get("ICOPATH"));
		model.addAttribute("userName", rbacUser.getUsername());
		model.addAttribute("userid", rbacUser.getSid());
		model.addAttribute("date", DateUtils.getChineseDay());
		model.addAttribute("WeekOfDate", DateUtils.getWeekOfDate());
		model.addAttribute("address", loginService.address());
		params.clear();
		params.put("userid", rbacUser.getSid());
		model.addAttribute("shortcutList", userShortcutService.getShortcutAndMenu(params));
		String sessionId = (String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID);
		List<CacheRbacMenu> navList = (List<CacheRbacMenu>)sessionReadService.getSessionNavMenu(sessionId);
		String navMenus;
		try {
			navMenus = JsonUtils.ObjectToJson(navList);
			model.addAttribute("navMenus", navMenus);
			String leftMenus = loginService.getLeftMenuOfJson(sessionId, (String)request.getServletContext().getAttribute("ctx"));//sessionid
    		model.addAttribute("leftMenus", leftMenus);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		String [] strArr = {"生活因为有阳光而绚丽多彩","忙碌的你，有着迷人的身影","从今天起做一个幸福的人，读书、旅行、努力工作、保持好的心情","南山得心跳仍在继续，我们的脚步也没有停息，梦想在前方，未来也在前方","灵感全然不是漂亮地挥着手，而是如健牛般竭尽全力工作的心理状态","擦亮眼睛，点燃新的希望，放飞新的梦想"};
		//暂时先不要显示  以上句子
		String [] strArr = {""};
		int index=(int)(Math.random()*strArr.length);
		model.addAttribute("indexText", strArr[index]);
		Map<String, Object> dataMap = sysInfoService.getEntity(null);
		model.addAttribute("sysName", dataMap.get("SYSNAME"));
    	return "index";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}