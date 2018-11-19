/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： StringUtils.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.ConvertUtils;


/**
 * 处理字符串的工具类。
 * @author wkd
 * @since  2014年8月14日
 */

public class StringUtils {

	/**
	 * 
	 * String类型转换为其他类型。
	 * @param value   待转换字符串
	 * @param toType  目标类型
	 * @return
	 *
	 * @author wkd
	 * @since 2014年8月14日下午2:11:23
	 */
    public static Object fromString(String value, Class<?> toType) {
        try {
            return ConvertUtils.convert(value, toType);
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
    
    /**
     * 
     * 其他类型转换为string类型。
     * @param object    其他类型
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:16:10
     */
    public static String toString(Object object) {
        try {
            return ConvertUtils.convert(object);
        } catch (Exception e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }
    
    /**
     * 
     * 对string 进行trim,如果值为null返回"",其他情况string.trim()。
     * @param str    待处理的字符串
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:17:35
     */
    public static String clean(String str) {
        return org.apache.commons.lang.StringUtils.trimToEmpty(str);
    }

    /**
     * 
     * 返回str.trim(),如果值为null或string.trim()为"",则返回指定的默认值。
     * @param str
     * @param defaultValue 默认值
     * @return
     *
     * @author tyr
     * @since 2014-9-22下午4:19:27
     */
    public static String cleanDefault(String str,String defaultValue) {
        if (isEmpty(clean(str))) {
            return defaultValue;
        }else {
            return clean(str);
        }
    }
    
    /**
     * 
     * 判断字符串是否为空。
     * <p>严格判断字符串是否为空。为空的标准  str==null or str.length()==0</p>
     * @param str
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:18:52
     */
    public static boolean isEmpty(String str) {
        return org.apache.commons.lang.StringUtils.isEmpty(str);
    }

    /**
     * 
     * 判断字符串是否为空。
     * <p>宽松判断字符串是否为空。为空的标准  [str==null or str.length()==0 && 空格]</p>
     * @param str
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:19:32
     */
    public static boolean isBlank(String str) {
    	if("null".equals(str)){//add by zfx
    		return true;
    	}
        return org.apache.commons.lang.StringUtils.isBlank(str);
    }

    /**
     * 
     * 字符串转换为大写形式。
     * @param str
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:21:24
     */
    public static String upperCase(String str) {
        return org.apache.commons.lang.StringUtils.upperCase(str);
    }

    /**
     * 
     * 字符串转换为小写形式。
     * @param str
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:21:47
     */
    public static String lowerCase(String str) {
        return org.apache.commons.lang.StringUtils.lowerCase(str);
    }

    /**
     * 
     *  对字符串进行反转。
     * @param str
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:22:09
     */
    public static String reverse(String str) {
        return org.apache.commons.lang.StringUtils.reverse(str);
    }

    /**
     * 
     * 在字符串中移除指定的字符串  (eg: remove("abc","c")-->"ab")。
     * @param str      待处理的字符串
     * @param remove   需要移除的字符串
     * @return         处理后的字符串
     *
     * @author wkd
     * @since 2014年8月14日下午2:22:30
     */
    public static String remove(String str, String remove) {
        return org.apache.commons.lang.StringUtils.remove(str, remove);
    }

    /**
     * 
     * 判断字符串每位是否为数值 [eg:"abc"-->false "a13"-->false "123"-->true "12.11"-->false]。
     * @param str
     * @return
     *
     * @author 
     * @since 2014年8月14日下午2:23:32
     */
    public static boolean isNumeric(String str) {
        return org.apache.commons.lang.StringUtils.isNumeric(str);
    }

    /**
     * 
     * 用指定的字符串补齐足够的长度。
     * <p>%方法详述（简单方法可不必）%</p>
     * @param str       需要补齐的字符串
     * @param addstr    补齐字符
     * @param lenstr    补齐长度
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:24:59
     */
    public static String addZero(String str,String addstr, int lenstr) {
        String value = clean(str);
        while (value.length() < lenstr) {
            value = addstr + value;
        }
        return value;
    }

    /**
     * 
     * 截取字符串左边的零。
     * @param teststr
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:25:49
     */
    public static String removeZero(String teststr) {
        String str = clean(teststr);
        if ("".equals(str)) {
            return "";
        }
        String tmpStr = str.substring(0, 1);
        while ("0".equals(tmpStr) && str.length() > 0) {
            str = str.substring(1, str.length());
            tmpStr = str.substring(0, 1);
        }
        return str;
    }

    /**
     * 
     * 将str用多个分隔符进行切分。
     * <p>将str用多个分隔符进行切分
     *    示例：StringTokenizerUtils.split("1,2;3 4"," ,;")
     *    返回: ["1","2","3","4"]
     * </p>
     * @param str
     * @param seperators
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:26:21
     */
    public static String[] split(String str, String seperators) {
        StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
        List<Object> result = new ArrayList<Object>();
        while (tokenlizer.hasMoreElements()) {
            Object s = tokenlizer.nextElement();
            result.add(s);
        }
        return (String[]) result.toArray(new String[result.size()]);
    }

    /**
     * 
     * byte[] 转为string。
     * @param bytes
     * @param charsetName
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:27:17
     */
    public static String newString(byte[] bytes, String charsetName) {
        if (bytes == null)
            return null;
        try {
            return new String(bytes, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 
     * 输入一个字符串,判断是否为空,如果为空设定默认值。
     * <p>
     *     输入一个字符串,判断是否为空(为空的标准 str==null or str.length()==0 or 字符串为空格
     * </p>
     * @param str
     * @param defaultstr
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午2:28:09
     */

	public static String defaultIfBlank(String str, String defaultstr) {
		return org.apache.commons.lang3.StringUtils.defaultIfBlank(str,
				defaultstr);
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
    public static String getString(Map tmpMap, String key) {
        String val = "";
        Object a = tmpMap.get(key);
        if (tmpMap == null || key == null || a == null) {
			return "";
		}
        try {
            if (a instanceof Integer) {
                val = String.valueOf((Integer) a);
            } else if (a instanceof String) {
                val = (String) a;
            } else if (a instanceof Double) {
                val = String.valueOf((Double) a);
            } else if (a instanceof BigDecimal) {
                val = String.valueOf(((BigDecimal) a));
            }
        } catch (Exception e) {
        }
        return val.trim();
    }

    /**
     * 
     * 把数组中的值 转换成sql in需要的形式 ["a","b"]--->('a','b')。
     * @param str
     * @return
     *
     * @author 
     * @since 2014年8月14日下午2:38:40
     */
    public static String toInString(String[] str){
        StringBuffer buffer=new StringBuffer();
        buffer.append("(");
        for (int i = 0; i < str.length-1; i++) {
            buffer.append("'"+str[i]+"',");
        }
        buffer.append("'"+str[str.length-1]+"'");
        buffer.append(")");
        return buffer.toString();
    }
    
    /**
     * 
     * 功能: 对字符串进行前置补0。
     * <p>%方法详述（简单方法可不必）%</p>
     * @param str
     * @param lenstr
     * @return
     *
     * @author 
     * @since 2014-9-5下午4:25:25
     */
    public static String addZero(String str, int lenstr) {
        String value = clean(str);
        while (value.length() < lenstr) {
            value = "0" + value;
        }
        return value;
    }
    
    /**
     * 
     * 功能: 返回不以科学计算法为显示的double值
     * 
     * @param bytes
     * @param charsetName
     * @return
     *
     * 作者：仝玉锐
     */
    public static String getFomartDouble(double value) {
        double out = 0.00;
        DecimalFormat df = new DecimalFormat("0.00");
        if (StringUtils.isEmpty(value + "")) {
            out = 0d;
        } else {
            out = new Double(value);
        }
        String str = df.format(out);
        return str;
    }
    public static String getFomartDouble(String str) {
        double out = 0.00;
        if(StringUtils.isEmpty(str)){
            out = 0d;
        } else {
            out = new Double(Double.parseDouble(str));
        }
        return  getFomartDouble(out);
        
    }


}


