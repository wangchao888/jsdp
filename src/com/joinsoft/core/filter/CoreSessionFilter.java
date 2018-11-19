package com.joinsoft.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joinsoft.SessionConstants;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.dubbo.service.ICommonService;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacUser;

public class CoreSessionFilter implements Filter {

	private String encoding = "UTF-8";
	private String[] ignoreUrls;
	private FilterConfig filterConfig = null;
	private IUserService userService;
	private ICommonService commonService;
	private static final Logger log = LoggerFactory
			.getLogger(CoreSessionFilter.class);
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletrequest;
	    HttpServletResponse response = (HttpServletResponse)servletresponse;
	    HttpSession session = request.getSession();
	    request.setCharacterEncoding(this.encoding);
	    response.setCharacterEncoding(this.encoding);

	    String ctx = (String)session.getServletContext().getAttribute("ctx");
	    String business_sid = (String)session.getAttribute(SessionConstants.JSDP_USER_SESSIONID);
	    String uri = request.getRequestURI();
	    
	    response.setHeader("Cache-Control", "no-cache");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0L);
	    
	    response.addHeader("Cache-Control", "must-revalidate");
	    
	    /*String sid = request.getParameter(SessionConstants.SESSIONID);
	    if(!StringUtils.isBlank(sid) && !sid.equals(business_sid)){
	    	session.setAttribute(SessionConstants.JSDP_USER_SESSIONID, sid);
	    	business_sid = sid;
	    }*/
	    String jsdp_url = (String)session.getAttribute(SessionConstants.JSDP_URL);// 核心应用的请求地址
	    
	    if (StringUtils.isBlank(jsdp_url)) {
	    	CacheRbacApplication coreApp = getCommonService().getCoreCacheApp();
	    	if(coreApp != null && coreApp.getAppip() != null && coreApp.getAppport() != null){
	    		jsdp_url = "http://" + coreApp.getAppip() + ":" + coreApp.getAppport() + "/" + coreApp.getWebview();
	            session.setAttribute(SessionConstants.JSDP_URL, jsdp_url);
	    	}
	    }
	    /* 非过滤路径 */
//        String[] noFilters = new String[] {"/index.jsp","/static","/nologin.jsp"};
        String reqPath = StringUtils.isEmpty(request.getServletPath()) ? request.getPathInfo() : request.getServletPath();
        for (String s : getIgnoreUrls()) {
            if (reqPath.equals(s.trim()) || reqPath.indexOf(s)!=-1 || reqPath.isEmpty()) {
            	chain.doFilter(request, response);
                return;
            }
        }
        uri = uri.substring(ctx.length()); //从第几个字符开始截取
        if(business_sid == null || "".equals(business_sid) || "null".equals(business_sid)){
        	log.info("还未登录,无法打开[" + uri + "]");
        	response.sendRedirect(ctx + "/nologin");
            return;
        }
        CacheRbacUser rbacUser = getUserService().getRbacUserBySessionid(business_sid);
        if(rbacUser == null){
        	log.info("用户失效,无法打开[" + uri + "],请重新登录");
            response.sendRedirect(ctx + "/nologin");
            return;
        }
        
        /*CacheRbacOrg rbacOrg = getUserService().getRbacOrgBySessionid(business_sid);
        if(rbacOrg == null){
        	log.info("用户无机构信息,无法打开[" + uri + "],请联系管理员配置机构信息");
        	response.sendRedirect(ctx + "/nologin");
            return;
        }
        
        List<CacheRbacMenu> menuList = getUserService().getRbacMenuBySessionid(business_sid);
        if(menuList == null || menuList.size() < 0){
        	log.info("用户无权限信息,无法打开[" + uri + "],请联系管理员配置权限信息");
        	response.sendRedirect(ctx + "/nologin");
            return;
        }*/
        
        chain.doFilter(request, response);
	}
	
	/*private void openLogin(HttpServletRequest request, HttpServletResponse response){
		String loginurl = (String)request.getSession().getAttribute(SessionConstants.JSDP_URL);
		try {
			PrintWriter out = response.getWriter();
			out.print("<script>window.top.location.href='"+loginurl+"'</script>");
			out.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	    String ignore = filterConfig.getInitParameter("ignore");
	    String encoding = filterConfig.getInitParameter("encoding");
	    setIgnoreUrls(StringUtils.split(ignore, ","));
	    if (!StringUtils.isBlank(encoding)) {
	      this.encoding = encoding;
	    }
	    ApplicationContext springCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
	    setUserService((IUserService)springCtx.getBean("userService"));
	    setCommonService((ICommonService)springCtx.getBean("commonService"));
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String[] getIgnoreUrls() {
		return ignoreUrls;
	}

	public void setIgnoreUrls(String[] ignoreUrls) {
		this.ignoreUrls = ignoreUrls;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ICommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(ICommonService commonService) {
		this.commonService = commonService;
	}

}
