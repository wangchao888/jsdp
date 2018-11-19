/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.core.utils.convert;

import java.math.BigDecimal;

import org.apache.commons.beanutils.converters.BigDecimalConverter;

import com.joinsoft.core.utils.ReflectionUtils;



/**
 * 
 * 实现BigDecimal类型和其他类型的相互转换[除String外]。
 * @author as
 * @since  2014-9-3
 */
public class BigDecimaUtils {
    
    /**
     * 
     * 功能: BigDecimal类型转换为其他指定类型
     * 
     * @param value    BigDecimal类型数据
     * @param toType   指定的转换类型
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:08:48
     */
    public static Object fromBigDecimal(BigDecimal value, Class<?> toType) {
        try {
            BigDecimalConverter convert = new BigDecimalConverter();
            return convert.convert(toType, value);
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
    
    /**
     * 
     * 功能: 实现其他类型转换为BigDecimal类型
     * @param value
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:09:02
     */
    public static Object toBigDecimal(Object value) {
        try {
            BigDecimalConverter convert = new BigDecimalConverter();
            return convert.convert(BigDecimal.class, value);
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
    
}
