/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： UUIDUtils.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.utils;

import java.util.UUID;

/**
 * 生成uuid。
 * @author 
 * @since  2014年8月18日
 */

public class UUIDUtils {
    

    /**
     * 
     * 生成36位UUID。
     * @return
     *
     * @author wkd
     * @since 2014年8月18日下午5:30:29
     */
    public static String get36UUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    
    /**
     * 
     * 生成32位UUID。
     * @return
     *
     * @author wkd
     * @since 2014年8月18日下午5:30:57
     */
    public static String get32UUID(){
        return get36UUID().replace("-", "");
    }

}


