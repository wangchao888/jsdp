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
import java.util.Map;

import org.apache.commons.beanutils.converters.IntegerConverter;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.utils.ReflectionUtils;

/**
 * Integer类型和其他类型间的转换
 * %类功能的概括%。
 * @author 
 * @since  2014-9-5
 */
public class IntegerUtils {

    /**
     * 
     * 功能: int转为其他数据类型
     * 
     * @param value    待转换的int值
     * @param toType   目标类型
     * @return
     *
     * @author 
     * @since 2014-9-5下午3:59:44
     */
    public static Object fromInteger(int value, Class<?> toType) {
        try {
            IntegerConverter convert = new IntegerConverter();
            return convert.convert(toType, value);
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
    
    /**
     * 其他类型转为Integer类型
     * <p>%方法详述（简单方法可不必）%</p>
     * @param value
     * @return
     *
     * @author 
     * @since 2014-9-5下午3:59:55
     */
    public static int toInteger(Object value) {
        try {
            IntegerConverter convert = new IntegerConverter();
            return (Integer)convert.convert(Integer.class, value);
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
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
     * @since 2014-9-5下午4:00:10
     */
    public static int convertInteger(String strInt, int defaultInt) {
        Integer outInt = new Integer(defaultInt);
        if ((strInt != null) && (!strInt.equals(""))) {
            try {
                outInt = new Integer(Integer.parseInt(strInt));
            } catch (Exception e) {
                outInt = new Integer(defaultInt);
            }
        }
        if (outInt <= 0) {
            outInt = new Integer(defaultInt);
        }
        return outInt;
    }
    /**
	 * 
	 * 功能/function :将传入的字符串转化成整型(若字符串非法,取默认值)
	 * 
	 * 流程描述/step :
	 * 
	 * 修改记录/revision :
	 * 
	 * 日期 修改人 描述
	 * 
	 * @return int
	 * @author zfx
	 * 
	 */
	public static int convertInt(String strInt, int defaultInt) {
		int outInt = defaultInt;
		if ((strInt == null) || (strInt.equals(""))) {
			outInt = defaultInt;
		} else {
			try {
				outInt = Integer.parseInt(strInt);
			} catch (Exception e) {
				outInt = defaultInt;
			}
		}
		if (outInt <= 0) {
			outInt = defaultInt;
		}
		return outInt;
	}
	
	/**
	 * 
	 * 根据map中的key值，返回map中对应的value(String类型)。
	 * @param tmpMap
	 * @param key
	 * @return
	 *
	 * @author wkd
	 * @since 2014年8月14日下午2:29:46
	 */
    public static int getInteger(Map tmpMap, String key) {
    	int val = 0;
		Object a = tmpMap.get(key);
		try {
			if (a instanceof Integer) {
				val = (Integer) a;
			} else if (a instanceof String) {
				val = Integer.parseInt((String) a);
			} else if (a instanceof Double) {
				val = (Integer) a;
			} else if (a instanceof BigDecimal) {
				val =((BigDecimal) a).intValue();
			} else if(CollectionUtils.isEmpty(a)){//如果是空的话，默认为0。
				val = 0;
			}
		} catch (Exception e) {
			val = 0;
		}
		return val;
    }
	
}
