/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： CollectionUtils.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.collection;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.joinsoft.core.utils.ReflectionUtils;

/**
 * 集合对象的处理。
 * 
 * @author Administrator
 * @since 2014年8月15日
 */

public class CollectionUtils {
	private CollectionUtils() {

	}

	/**
	 * 
	 * 得到两个数据集合的差集。
	 * 
	 * @param source
	 * @param target
	 * @return
	 * 
	 * @author wkd
	 * @since 2014年8月15日下午7:36:19
	 */
	public static <T> List<T> subtract(final Collection<T> source, final Collection<T> target) {
		ArrayList<T> list = new ArrayList<T>(source);
		for (Iterator<T> it = target.iterator(); it.hasNext();) {
			list.remove(it.next());
		}
		return list;
	}

	/**
	 * 
	 * 取出数据集的指定属性，封装成list。
	 * 
	 * @param collection
	 * @param propertyName
	 * @return
	 * 
	 * @author
	 * @since 2014年8月15日下午7:37:16
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List extractToList(final Collection collection, final String propertyName) {
		List list = new ArrayList();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 
	 * 取出集合对象的指定属性，拼接成字符串。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param collection
	 * @param propertyName
	 * @param separator
	 * @return
	 *
	 * @author 
	 * @since 2014年8月15日下午7:38:38
	 */

	@SuppressWarnings("rawtypes")
	public static String extractToString(final Collection collection, final String propertyName, final String separator) {
		List list = extractToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 
	 * 判断传入的对象是否为空。
	 * <p>%方法详述（简单方法可不必）%</p>
	 * @param o
	 * @return
	 *
	 * @author wkd
	 * @since 2014年8月15日下午7:39:44
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object o) {
		if (o == null)
			return true;

		if (o instanceof String) {
			if (((String) o).length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * 
	 * 判断String Collection Array Map是否为空,为空返回 false。
	 * @param o
	 * @return
	 *
	 * @author wkd
	 * @since 2014年8月15日下午7:43:50
	 */
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
	
	/**
	 * 
	 * 功能同isEmpty去掉了 String = ""的判断。
	 * @param o
	 * @return
	 *
	 * @author 
	 * @since 2014年8月15日下午7:42:22
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isBank(Object o) {
		if (o == null)
			return true;
		if (o instanceof Collection) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
	
	/**
	 * 
	 * 判断String Collection Array Map是否为空,为空返回 false。
	 * @param o
	 * @return
	 *
	 * @author wkd
	 * @since 2014年8月15日下午7:43:50
	 */
	public static boolean isNotBank(Object o) {
		return !isBank(o);
	}

	
	/**
	 * 
	 * 实现bean到map的转换。
	 * @param bean
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 *
	 * @author wkd
	 * @since 2014年8月15日下午7:44:41
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> BeanToMap(Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return BeanUtils.describe(bean);
	}
}
