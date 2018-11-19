/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： ReflectionUtils.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射工具类。
 * <p>提供调用getter/setter方法, 访问私有变量, 调用私有方法, 获取泛型类型Class, 被AOP过的真实类等工具函数</p>
 * @author wkd
 * @since  2014年8月14日
 */

public class ReflectionUtils {

    public static final String CGLIB_CLASS_SEPARATOR = "$$";
	private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);

    private ReflectionUtils() {
    }

    /**
     * 
     * 通过反射调用get方法。
     * @param obj            源对象
     * @param propertyName   需要调用的方法
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午1:50:04
     */
    public static Object invokeGetter(Object obj, String propertyName) {
        String getterMethodName = "get" + StringUtils.capitalize(propertyName);
        return invokeMethod(obj, getterMethodName, new Class[] {}, new Object[] {});
    }

    /**
     * 
     * 调用Setter方法.使用value的Class来查找Setter方法。
     * @param obj            调用的对象
     * @param propertyName   调用的方法
     * @param value          设置方法的值
     *
     * @author wkd
     * @since 2014年8月14日下午1:50:38
     */
    public static void invokeSetter(Object obj, String propertyName, Object value) {
        invokeSetter(obj, propertyName, value, null);
    }
    
    /**
     * 
     * 调用Setter方法。
     * @param obj
     * @param propertyName
     * @param value
     * @param propertyType
     *
     * @author 
     * @since 2014年8月14日下午1:51:41
     */
    public static void invokeSetter(Object obj, String propertyName, Object value,
            Class<?> propertyType) {
        Class<?> type = propertyType != null ? propertyType : value.getClass();
        String setterMethodName = "set" + StringUtils.capitalize(propertyName);
        invokeMethod(obj, setterMethodName, new Class[] { type }, new Object[] { value });
    }
    
    /**
     * 
     * 调用Setter方法。
     * @param obj          目标对象
     * @param fieldName    取值字段
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午1:52:23
     */
    public static Object getFieldValue(final Object obj, final String fieldName) {
        Field field = getAccessibleField(obj, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName
                    + "] on target [" + obj + "]");
        }

        Object result = null;
        try {
            result = field.get(obj);
        } catch (IllegalAccessException e) {
            logger.error("不可能抛出的异常{}", e.getMessage());
        }
        return result;
    }

    /**
     * 
     * 设置对象属性值。
     * <p>直接设置对象属性值, 无视private/protected修饰符, 不经过setter函数.</p>
     * @param obj         目标对象
     * @param fieldName   目标字段
     * @param value       设置的值
     *
     * @author wkd
     * @since 2014年8月14日下午1:53:10
     */
    public static void setFieldValue(final Object obj, final String fieldName, final Object value) {
        Field field = getAccessibleField(obj, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName
                    + "] on target [" + obj + "]");
        }

        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            logger.error("不可能抛出的异常:{}", e.getMessage());
        }
    }

    /**
     * 
     * 调用对象方法。
     * <p>直接调用对象方法, 无视private/protected修饰符</p>
     * @param obj             调用的对象
     * @param methodName      调用的方法
     * @param parameterTypes  方法参数
     * @param args            方法返回
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午1:54:08
     */
    public static Object invokeMethod(final Object obj, final String methodName,
            final Class<?>[] parameterTypes, final Object[] args) {
        Method method = getAccessibleMethod(obj, methodName, parameterTypes);
        if (method == null) {
            throw new IllegalArgumentException("Could not find method [" + methodName
                    + "] on target [" + obj + "]");
        }

        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw convertReflectionExceptionToUnchecked(e);
        }
    }

    /**
     * 
     * 循环向上转型, 获取对象的DeclaredField, 并强制设置为可访问.如向上转型到Object仍无法找到, 返回null。
     * @param obj            需要操作的对象
     * @param fieldName      获取的字段名称
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午1:55:08
     */
    public static Field getAccessibleField(final Object obj, final String fieldName) {
        Validate.notNull(obj, " 对象不能为空 ");
        Validate.notBlank(fieldName, " 字段名不能为空 ");
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                Field field = superClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException e) {// NOSONAR
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 
     * 循环向上转型, 获取对象的DeclaredMethod,并强制设置为可访问. 如向上转型到Object仍无法找到, 返回null。
     * <p>%方法详述（简单方法可不必）%</p>
     * @param obj               操作的对象
     * @param methodName        获取的方法名
     * @param parameterTypes    方法的参数
     * @return
     *
     * @author 
     * @since 2014年8月14日下午1:56:23
     */
    public static Method getAccessibleMethod(final Object obj, final String methodName,
            final Class<?>... parameterTypes) {

        Validate.notNull(obj, " 对象不能为空  ");

        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                Method method = superClass.getDeclaredMethod(methodName, parameterTypes);

                method.setAccessible(true);

                return method;

            } catch (NoSuchMethodException e) {// NOSONAR
                // Method不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 
     * 将check exception转换为uncheck exception。
     * @param e
     * @return
     *
     * @author wkd
     * @since 2014年8月14日下午1:56:56
     */
    public static RuntimeException convertReflectionExceptionToUnchecked(Exception e) {
        if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
                || e instanceof NoSuchMethodException) {
            return new IllegalArgumentException(e);
        } else if (e instanceof InvocationTargetException) {
            return new RuntimeException(((InvocationTargetException) e).getTargetException());
        } else if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        }
        return new RuntimeException("Unexpected Checked Exception.", e);
    }

}

