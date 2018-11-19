/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： MyBatisDao.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.token.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.joinsoft.core.token.annotation.FormToken;
import com.joinsoft.core.utils.StringUtils;

/**
 * 页面token验证，防止重复提交，拦截器
 * <p>description: </p> 	
 * @author Administrator
 * @date 2017年3月22日
 */
public class FormTokenInterceptor extends HandlerInterceptorAdapter  {
	
	/**
 	 * 
 	 * 业务请求处理之前，生成formToken,并进行验证表单重复提取
 	 * @param request
 	 * @return
 	 *
 	 * @author 仝玉锐
 	 * @since 2018-8-31上午11:19:21
 	 */
	 @Override  
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
	        if (handler instanceof HandlerMethod) {  
	            HandlerMethod handlerMethod = (HandlerMethod) handler;  
	            Method method = handlerMethod.getMethod();  
	            FormToken annotation = method.getAnnotation(FormToken.class);  
	            if (annotation != null) {  
	                boolean needSaveSession = annotation.save();  
	                if (needSaveSession) {
	                    request.setAttribute("formToken", UUID.randomUUID().toString());
	                }  
	                boolean needRemoveSession = annotation.remove();  
	                if (needRemoveSession) {  
	                    if (!isRepeatSubmit(request)) {//验证表单重复提交
	                        return false;
	                    }  
	                }  
	            }  
	            return true;  
	        } else {  
	            return super.preHandle(request, response, handler);  
	        }  
	   }  
	 
	 	/**
	 	 * 
	 	 * 判断是否重复请求
	 	 * @param request
	 	 * @return
	 	 *
	 	 * @author 仝玉锐
	 	 * @since 2018-8-31上午11:19:21
	 	 */
	    private boolean isRepeatSubmit(HttpServletRequest request) {
	    	String clinetToken = request.getParameter("formToken");  
	        String sessionVal = (String) request.getSession(false).getAttribute(clinetToken);  
	        if (StringUtils.isEmpty(sessionVal)) {//非重复请求
	        	request.getSession(false).setAttribute(clinetToken, "T");
	            return true;  
	        }
	        return false;
	    }
	    
	    /**
	 	 * 
	 	 * 业务请求处理完成返回结果之前，session中清除token
	 	 * @param request
	 	 * @return
	 	 *
	 	 * @author 仝玉锐
	 	 * @since 2018-8-31上午11:19:21
	 	 */
	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	    	if (handler instanceof HandlerMethod) {  
	            HandlerMethod handlerMethod = (HandlerMethod) handler;  
	            Method method = handlerMethod.getMethod(); 
		    	 FormToken annotation = method.getAnnotation(FormToken.class);
		    	 if (annotation != null) {
			    	 boolean needRemoveSession = annotation.remove();  
		             if (needRemoveSession) { 
					    	String clinetToken = request.getParameter("formToken");
					    	if (!StringUtils.isEmpty(clinetToken)) {
					    		request.getSession(false).removeAttribute(clinetToken);  
							}
		             }
	             }
	    	}
	    	// TODO Auto-generated method stub
	    	super.postHandle(request, response, handler, modelAndView);
	    }
	
	
}
