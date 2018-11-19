/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/



package com.joinsoft.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 实现系统业务日志的分离
 * %类功能的概括%。
 * @author 
 * @since  2014-9-5
 */
@Component
public class BusinessLogger {

    private static Logger businessLogger = LoggerFactory.getLogger("business");
    private static Logger   logger = LoggerFactory.getLogger(BusinessLogger.class);
    
    public void log(String user,String ip,String module, String action, String message) {
        try {
            businessLogger.info("{}_{}_{}_{}_{}",user,ip,module,action,message);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
        } catch(Exception e){
        	logger.error(e.getMessage());
        }
    }
    
}
