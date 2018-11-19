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

import org.apache.commons.beanutils.converters.FloatConverter;

import com.joinsoft.core.utils.ReflectionUtils;


/**
 * 
 * 实现 float和其他类型间的转换
 * @author 
 * @since  2014-9-5
 */
public class FloatUtils {
    
    /**
     * 
     * 功能: float类型转换为其他类型
     * 
     * @param value   待转换的float数据
     * @param toType  目标类型
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:00:32
     */
    public static Object fromFloat(float value, Class<?> toType) {
        try {
            FloatConverter convert = new FloatConverter();
            return convert.convert(toType, value);
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
    
    
    /**
     * 
     * 功能: 其他类型转换为float类型
     * 
     * @param value   待转换的数据
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:00:44
     */
    public static Object toFloat(Object value) {
        try {
            FloatConverter convert = new FloatConverter();
            return convert.convert(Float.class, value);
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }

}
