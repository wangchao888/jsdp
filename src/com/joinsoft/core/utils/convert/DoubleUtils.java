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
import java.text.DecimalFormat;
import java.util.Map;

import org.apache.commons.beanutils.converters.DoubleConverter;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.ReflectionUtils;



/**
 * 
 * 实现double和其他对象间的转换。
 * @author 
 * @since  2014-9-5
 */
public class DoubleUtils {
    
    /**
     * 
     * 功能: Double转换为其他类型
     * 
     * @param value  待转换的double数值
     * @param toType 需要转换的类型 [String.class]
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:03:09
     */
    public static Object fromDouble(double value, Class<?> toType) {
        try {
            DoubleConverter convert = new DoubleConverter();
            return convert.convert(toType, value);
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
    
    /**
     * 
     * 功能: 其他类型转换为 double类型
     * 
     * @param value  待转换的类型
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:03:20
     */
    public static Object toInteger(Object value) {
        try {
            DoubleConverter convert = new DoubleConverter();
            return convert.convert(Double.class, value);
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
    
    
    /**
     * 
     * 功能: 对指定数值转换为百分比的形式
     * 
     * @param number  需要转换的 double数值
     * @param number
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:03:30
     */
    public static String percen(Double number) {
        DecimalFormat df = new DecimalFormat("##.00%");
        if(number != null) {
            if(number == 0.0) {
                return "00.00%";
            }
            return df.format(number);
        }
        return null;
    }
    
    /**
     * 
     * 功能:  将传入的字符串转化成整型(若字符串非法,取默认值)
     * 
     * @param strInt 转换值
     * @param defaultInt 默认值
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:03:42
     */
    public static Double convertDouble(String strInt, double defaultInt) {
        Double outInt = new Double(defaultInt);
        if ((strInt != null) && (!strInt.equals(""))) {
            try {
                outInt = new Double(Double.parseDouble(strInt));
            } catch (Exception e) {
                outInt = new Double(defaultInt);
            }
        }
        if (outInt <= 0) {
            outInt = new Double(defaultInt);
        }
        return outInt;
    }
    
    /**
     * 
     * 功能: 过滤Double类型(返回默认值）。
     * <p>%方法详述（简单方法可不必）%</p>
     * @param outInt
     * @param defaultVal
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:03:55
     */
    public static double convertDouble(Double outInt, double defaultVal) {
        if (outInt != null) {
            return outInt.doubleValue();
        }
        return defaultVal;
    }

    /**
     * 
     * 功能:  根据map中的key值，返回map中对应的value(double类型)
     * 
     * @param tmpMap
     * @param key
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:04:04
     */
    @SuppressWarnings("rawtypes")
	public static double getDouble(Map tmpMap, String key) {
		double val = 0;
		Object a = tmpMap.get(key);
		try {
			if (a instanceof Integer) {
				val = (Integer) a;
			} else if (a instanceof String) {
				val = Double.parseDouble((String) a);
			} else if (a instanceof Double) {
				val = (Double) a;
			} else if (a instanceof BigDecimal) {
				val =((BigDecimal) a).doubleValue();
			} else if(CollectionUtils.isEmpty(a)){//如果是空的话，默认为0。
				val = 0d;
			}
		} catch (Exception e) {
			val = 0d;
		}
		return val;
	}
}
