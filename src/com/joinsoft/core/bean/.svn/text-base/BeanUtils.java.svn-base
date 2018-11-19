/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.core.bean;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;

import com.google.common.collect.Lists;

/**
 * 
 * 对Dozer实现简单封装，实现bean<->bean的转换。
 * @author 
 * @since  2014-9-3
 */
public class BeanUtils {
    private BeanUtils() {

    }

    /* 创建dozer单例，避免重复创建造成资源浪费*/
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 
     * 功能: 对象进行转换。
     * @param source
     * @param destinationClass
     * @return
     *
     * @author 
     * @since 2014-9-3下午7:55:11
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }
    
    /**
     * 
     * 把集合类拆解为指定类型的List。
     * @param sourceList
     * @param destinationClass
     * @return
     *
     * @author 
     * @since 2014-9-3下午7:55:23
     */
    public static <T> List<T> mapList(Collection<T> sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }
    
    
    /**
     * 两个对象的值进行拷贝
     * <p>%方法详述（简单方法可不必）%</p>
     * @param source
     * @param destinationObject
     *
     * @author 
     * @since 2014-9-3下午7:55:38
     */
     
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
    
    /**
     * 
     * 将对象转为map形式,<K,V> K:属性名,V:属性值
     *       若该对象为bean对象,该bean类必须有setter/getter,
     * @param source
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     *
     * @author 
     * @since 2014-9-3下午7:55:50
     */
    public static Map<String, Object> toMap(Object source) throws InstantiationException,
            IllegalAccessException, IntrospectionException, IllegalArgumentException,
            InvocationTargetException {
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        if (source != null) {
            Class<? extends Object> clazz = source.getClass();
            if(clazz.getPackage().getName().contains("com")){
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                    Method method = pd.getReadMethod();
                    result.put(field.getName(), method.invoke(source));
                }
            }else{
                result.put("parameter", source.toString());
            }
        }
        return result;
    }
}
