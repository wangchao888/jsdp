/**
 * 文   件  名   : JsonUtils.java
 * 版          本   : 
 * 创建日期   : 2012-12-26
 *
 * Copyright © 1995-2013 JoinSoft Co.Ltd. All right reserved. 
 */

package com.joinsoft.core.utils.json;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;





public class JsonUtils {
    
    /**
     * 
     * 功能: 生成对象的json格式数据
     * 
     * @param obj   传入参数 map list array bean
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     *
     * 作者：
     */
    public static String ObjectToJson(Object obj) throws JsonGenerationException, JsonMappingException, IOException{
        String json = "";
        ObjectMapper objectMapper = new ObjectMapper();
        json = objectMapper.writeValueAsString(obj);
        return json;
    }
    
    /**
     * 
     * 功能: 生成JSON类型的字符串，并替换原属性,也可以追加指定属性
     * @param object 待转换的对象
     * @param properties 传入null代表不转换
     *        封装数据格式为Map<"originalProperty","destinationProperty">
     *        originalProperty是待转换的bean属性,该属性必须声实现getter()/setter()
     *        destinationProperty为转换后的bean属性
     * @param newPropertiesAndValue 传入null代表不追加
     *        封装数据格式为Map<"newProperties",value>
     *        newProperties是新追加的bean属性
     *        value为新追加的bean属性值
     * @return 属性转换后的JSON字符串
     * 作者：王源
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IntrospectionException 
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonGenerationException 
     */
    @SuppressWarnings("unchecked")
    public static <T> String propertyConverter(Object object, 
            Map<String,String> properties,
            Map<String,Object> newPropertiesAndValue) throws InstantiationException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException,
            IntrospectionException, JsonGenerationException, JsonMappingException, IOException{
        StringBuffer jsonStr = new StringBuffer();
        Map<String, Object> beanMap = new HashMap<String, Object>();
        jsonStr.append("{");
        Class<T> clazz = (Class<T>) object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz); 
            Method method = pd.getReadMethod();
            beanMap.put(field.getName(), ObjectToJson(method.invoke(object)));
        }
        if(newPropertiesAndValue != null){
            Iterator<String> newPropertiesIterator = newPropertiesAndValue.keySet().iterator();
            while(newPropertiesIterator.hasNext()){
                String newProperty = (String) newPropertiesIterator.next();
                Object value = newPropertiesAndValue.get(newProperty);
                beanMap.put(newProperty, ObjectToJson(value));
            }
        }
        if(properties != null){
            Iterator<String> propertiesIterator = properties.keySet().iterator();
            while(propertiesIterator.hasNext()){
                String originalProperty = (String) propertiesIterator.next();
                String destinationProperty = properties.get(originalProperty);
                if(beanMap.containsKey(originalProperty)){
                    Object value = beanMap.get(originalProperty);
                    beanMap.remove(originalProperty);
                    beanMap.put(destinationProperty, ObjectToJson(value));
                }
            }
        }
        Iterator<String> beanIterator = beanMap.keySet().iterator();
        while(beanIterator.hasNext()){
            String property = beanIterator.next();
            Object value = beanMap.get(property);
            jsonStr.append("\"")
                   .append(property)
                   .append("\":")
                   .append(value)
                   .append(",");
        }
        return jsonStr.toString().substring(0,jsonStr.toString().length()-1) + "}";
    }
   
}
