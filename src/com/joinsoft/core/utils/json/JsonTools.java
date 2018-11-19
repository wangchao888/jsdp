/**
 * 文   件  名   : JsonTools.java
 * 版          本   : 
 * 创建日期   : 2013-1-10
 *
 * Copyright © 1995-2013 JoinSoft Co.Ltd. All right reserved. 
 */

package com.joinsoft.core.utils.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.joinsoft.core.utils.ReflectionUtils;
import com.joinsoft.core.utils.json.JsonUtils;
import com.joinsoft.module.JsdpConstants;


public class JsonTools {
    
    //-----------------------------------------tree相关-------------------------------------------------------------
    /**
     * 
     * 功能: 得到规范的ztree需要的json格式
     * 
     * @param obj
     * @return
     *
     * 作者：
     */
    public static String ToZtreeJson(Object obj){
        return ToZtreeJson(obj,"");
    }
    
    /**
     * 
     * 功能: tree具体实现
     * 
     * @param obj  传入参数 map list array bean
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     *
     * 作者：Exodus
     */
    public static String ToZtreeJson(Object obj,String input) {
        String json = "";
        try {
            json = JsonUtils.ObjectToJson(obj).replaceAll("\"parentId\":\"0\",",
                    "\"parentId\":\"0\",\"open\":true,");
        } catch (JsonGenerationException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (JsonMappingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (IOException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
        if(input.equals("input")){
            StringBuilder  nodes = new StringBuilder("{\"treeNodes\":");
            nodes.append(json).append("}");
            return nodes.toString();
        }
        return json;
    }
    
    //-----------------------------------------grid相关---------------------------------------------------------------------------
    /**
     * 
     * 功能: 生成grid列表需要的json格式
     * 
     * @param obj
     * @param pageNo
     * @param totalRows
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     * 
     * @edit 2017-05-16 添加总页数
     *
     * 作者：Exodus
     */
    public static String ToGridJson(Object obj, String pageNo, String totalRows,String totalPage) {
        StringBuilder str = new StringBuilder();
        String json = "";
        str.append("{\"pager.pageNo\":" + pageNo + ",\"pager.totalPage\":" + totalPage
                + ",\"pager.totalRows\":" + totalRows
                + ",\"rows\":");

        try {
            json = JsonUtils.ObjectToJson(obj);
        } catch (JsonGenerationException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (JsonMappingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (IOException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }

        str.append(json);
        str.append("}");
        return str.toString();
    }
    /**
     * 不带总页数
     * @param obj
     * @param pageNo
     * @param totalRows
     * @return
     */
    public static String ToGridJson(Object obj, String pageNo, String totalRows) {
        StringBuilder str = new StringBuilder();
        String json = "";
        str.append("{\"pager.pageNo\":" + pageNo
                + ",\"pager.totalRows\":" + totalRows
                + ",\"rows\":");

        try {
            json = JsonUtils.ObjectToJson(obj);
        } catch (JsonGenerationException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (JsonMappingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (IOException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }

        str.append(json);
        str.append("}");
        return str.toString();
    }
    
    /**
     * datatables 使用
     * @param obj
     * @param pageNo
     * @param totalRows
     * @return
     */
    public static String ToDatatableJson(Object obj, String draw, String recordsTotal) {
        StringBuilder str = new StringBuilder();
        String json = "";
        str.append("{\"draw\":" + draw 
        		+ ",\"recordsTotal\":" + recordsTotal
                + ",\"recordsFiltered\":" + recordsTotal
                + ",\"data\":");

        try {
            json = JsonUtils.ObjectToJson(obj);
        } catch (JsonGenerationException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (JsonMappingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (IOException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }

        str.append(json);
        str.append("}");
        return str.toString();
    }
    
    /**
     * 
     * 功能: 生成grid即时编辑下拉框所需的json格式
     * @param List<?> 待转换的数据,keyCol生成key的字段名,valueCol生成value的字段名
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     *
     * 作者：王源
     */
    public static String ToGridEditSelectJson(List<Object> list,String keyCol,String valueCol) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"list\":");
        String json = "";
        try {
            json = JsonUtils.ObjectToJson(list);
        } catch (JsonGenerationException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (JsonMappingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (IOException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
        sb.append(json);
        sb.append("}");
        return sb.toString().replaceAll(keyCol, "key").replaceAll(valueCol, "value");
    }
    
    /**
     * 
     * 功能: 生成grid列表中columns需要的JSON格式
     * 
     * @param obj
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     *
     * @author 李中新  2013-02-22
     * 版本：1.0
     */
    public static String ToGridColumnsJson(Object obj) {
        StringBuilder str = new StringBuilder();
        
        String json = "";
        String optStr = "function(rowdata, rowindex, value){return \"<a onclick=\'endEdit(\"+rowindex+\")\'><span class=\'icon_ok\'>提交</span></a>&nbsp;<a onclick=\'cancelEdit(\"+rowindex+\")\'><span class=\'icon_no\'>取消</span></a>\";}";
        try {
            json = JsonUtils.ObjectToJson(obj);
            if(json.indexOf("editor") != -1){
                json = json.replaceAll("\"editor\"", "editor");
            }
            if(json.indexOf("align") != -1){
                json = json.replaceAll("\"align\"", "align");
            }
            if(json.indexOf("name") != -1){
                json = json.replaceAll("\"name\"", "name");
            }
            if(json.indexOf("display") != -1){
                json = json.replaceAll("\"display\"", "display");
            }
            if(json.indexOf("isSort") != -1){
                json = json.replaceAll("\"isSort\"", "isSort");
            }
            if(json.indexOf("width") != -1){
                json = json.replaceAll("\"width\"", "width");
            }
            if(json.indexOf("render") != -1){
                json = json.replaceAll("\"render\"", "render");
            }
            if(json.indexOf("false") != -1){
                json = json.replaceAll("\"false\"", "false");
            }
            if(json.indexOf("{type:\'text\'}") != -1){
                json = json.replaceAll("\"\\{type:\'text\'\\}\"", "\\{type:\"text\"\\}");
            }
            if(json.indexOf("{type:\'select\'}") != -1){
                json = json.replaceAll("\"\\{type:\'select\'\\}\"", "\\{type:\"select\"\\}");
            }
            
            if(!StringUtils.isBlank(json)){
                json = json.substring(0, json.length()-1);
                StringBuilder sb = new StringBuilder(",{display:\"操作\",isSort:false,width:\"110px\",render:");
                sb.append(optStr).append("}]");
                json = json + sb.toString();
            }
        } catch (JsonGenerationException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (JsonMappingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (IOException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }

        str.append(json);
        return str.toString();
    }
    
    //-----------------------------------------双向选择器相关------------------------------------------------------------------------
    /**
     * 功能: 生成listener(双向选择框)需要的json格式
     * @param obj
     * @return 王超
     */
    public static String ToSelectListerFrom (Object obj) {
        String role = "";
        try {
            role = JsonUtils.ObjectToJson(obj);
        } catch (JsonGenerationException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (JsonMappingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (IOException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
        if(obj != "") {
            StringBuilder nodes = new StringBuilder("{\"fromList\":");
            nodes.append(role).append(",\"toList\":[]}");
            return nodes.toString();
        }
        return role;
    }
    
    //-----------------------------------------表单form元素相关--------------------------------------------------------------------------
    /**
     * 
     * 功能: 得到规范的select需要的json格式
     * 
     * @param obj
     * @return
     *
     * 作者：
     */
    public static String ToSelectJson(Object obj){
        return ToSelectJson(obj,"select");
    }
    /**
     * 
     * 功能: select具体实现
     * @param obj  传入参数 map list array bean
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     *
     * 作者：王源
     */
    public static String ToSelectJson(Object obj,String input) {
        String json = "";
        try {
            json = JsonUtils.ObjectToJson(obj);
        } catch (JsonGenerationException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (JsonMappingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (IOException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
        if("select".equals(input)){
            StringBuilder  nodes = new StringBuilder("{\"list\":");
            nodes.append(json).append("}");
            return nodes.toString();
        }
        return json;
    }
    
    
    /**
     * 
     * 功能: 得到规范的left需要的json格式
     * 
     * @param obj
     * @return
     *
     * 作者：王超
     */
    public static String ToLeftJson(Object obj ) {
    	return ToLeftJson(obj,"");
    }
    
    /**
     * 
     * 功能: left具体实现
     * @param obj  传入参数 map list array bean
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     *
     * 作者：王超
     */
    public static String ToLeftJson(Object obj,String input) {
    	String json = "";
        try {
            json = JsonUtils.ObjectToJson(obj);
//            json = JsonUtils.ObjectToJson(obj).replaceAll("\"parentId\":\"0\",",
//                    "\"parentId\":\"0\",\"open\":true,");
        } catch (JsonGenerationException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (JsonMappingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (IOException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
        if(input.equals("input")){
            StringBuilder  nodes = new StringBuilder("[{");
            nodes.append(json).append("}]");
            return nodes.toString();
        }
        return json;
    }
    
  //-----------------------------------------文件相关--------------------------------------------------------------------------
    /**
     * 
     * 功能: 生成文件列表所需的json格式
     * @param docFileList 待处理的文件列表
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     *
     * 作者：王源
     */
    @SuppressWarnings("rawtypes")
    public static String ToFileListJson(List<?> docFileList) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"attachmentList\":");
        String json = "";
        try {
            if(docFileList==null){
                docFileList = new ArrayList();
            }
            json = JsonUtils.ObjectToJson(docFileList)
                    .replaceAll("catalogid", "catalogId")
                    .replaceAll("moduleid", "moduleId")
                    .replaceAll("filesize", "fileSize")
                    .replaceAll("targetfilename", "targetFileName")
                    .replaceAll("uploadfilename", "uploadFileName")
                    .replaceAll("uploadfiletype", "uploadFileType")
                    .replaceAll("uploadperson", "uploadPerson")
                    .replaceAll("uploadtime", "uploadTime");
        } catch (JsonGenerationException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (JsonMappingException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        } catch (IOException e) {
            throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
        }
        sb.append(json);
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * AJAX 调用简单返回
     * @return
     */
    public static String simpleAjaxJsonResponse(String resultFlag, String resultMsg){
		return "{\""+JsdpConstants.AJAX_RESULT_FLAG+"\":\""+resultFlag+"\",\""+JsdpConstants.AJAX_RESULT_MSG+"\":\""+resultMsg+"\"}";
    }
    
    public static String wrapAjaxJsonResponse(String resultFlag, String resultMsg, Object resultData){
    	Map<String,Object> retJsonMap = new HashMap<String,Object>();
		retJsonMap.put(JsdpConstants.AJAX_RESULT_FLAG, resultFlag);
		retJsonMap.put(JsdpConstants.AJAX_RESULT_MSG, resultMsg);
		retJsonMap.put(JsdpConstants.AJAX_RESULT_DATA, resultData);
		String errorMsg = "";
		try {
			return JsonUtils.ObjectToJson(retJsonMap);
		} catch (Exception e) {
			errorMsg = e.getLocalizedMessage();
		}
		return simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errorMsg);
    }
}
