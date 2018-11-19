package com.joinsoft.core.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joinsoft.platform.global.service.IGlobalInitService;

/**
 * 容器启动时初始化全局变量和写入缓存字典信息、参数信息
 * @author LZX
 * @version 201705091056
 * 业务的数据信息不在核心应用中记录
 * @author LZX
 * @version 201712071125
 */
public class Initializer implements ServletContextListener {

	private static IGlobalInitService globalInitService;
	
	private static final Logger log = LoggerFactory
			.getLogger(Initializer.class);

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent envent)  { 
    	ServletContext servletContext = envent.getServletContext();
        servletContext.setAttribute("ctx", servletContext.getContextPath());
        //核心应用
        servletContext.setAttribute("jsdp", servletContext.getContextPath());
        
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        globalInitService = (IGlobalInitService)ctx.getBean("globalInitService");
        log.info("my listener Initializer");
        //写入应用信息
        initApplication();
        //写入机构信息
        initOrg();
        //写入角色信息
        initRole();
        //写入用户信息
        initUser();
        //写入字典信息
//        initDic();
        //写入参数信息
//        initParam();
        //写入行政区代码信息
//        initArea();
        //写入自然幢类型信息
//        initBldType();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent envent)  { 
    	ServletContext servletContext = envent.getServletContext();
    	servletContext.removeAttribute("ctx");
    }
    
    /**
     * 初始化应用信息列表
     * @param ctx
     * @param servletContext
     * @author LZX
     */
    private void initApplication(){
    	log.info("初始化应用开始");
    	globalInitService.initApplication();
    	log.info("初始化应用结束");
    }
    
    /**
     * 初始化机构信息列表
     * @param ctx
     * @param servletContext
     * @author LZX
     */
    private void initOrg(){
    	log.info("初始化机构开始");
    	globalInitService.initOrg();
    	log.info("初始化机构结束");
    }
    
    /**
     * 初始化角色信息列表
     * @param ctx
     * @param servletContext
     * @author LZX
     */
    private void initRole(){
    	log.info("初始化角色开始");
    	globalInitService.initRole();
    	log.info("初始化角色结束");
    }
    
    /**
     * 初始化用户信息列表
     * @param ctx
     * @param servletContext
     * @author LZX
     */
    private void initUser(){
    	log.info("初始化用户开始");
    	globalInitService.initUser();
    	log.info("初始化用户结束");
    }
    
    /**
     * 初始化字典信息
     * @param ctx
     * @param servletContext
     */
    private void initDic(){
    	log.info("初始化字典开始");
    	globalInitService.initDic();
    	log.info("初始化字典结束");
    }
    
    /**
     * 初始化参数信息
     * @param ctx
     * @param servletContext
     */
    private void initParam(){
    	log.info("初始化参数开始");
    	globalInitService.initParam();
    	log.info("初始化参数结束");
    }
    
    /**
     * 初始化行政区代码信息
     * @param ctx
     * @param servletContext
     */
    private void initArea(){
    	log.info("初始化行政区代码开始");
    	globalInitService.initArea();
    	log.info("初始化行政区代码结束");
    }
    
    /**
     * 初始化自然幢类型信息
     * @param ctx
     * @param servletContext
     */
    private void initBldType(){
    	log.info("初始化自然幢类型开始");
    	globalInitService.initBldType();
    	log.info("初始化自然幢类型结束");
    }
	
}
