package com.joinsoft.module.system.dyntable.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joinsoft.SessionConstants;
import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.core.utils.json.JsonTools;
import com.joinsoft.dubbo.service.IUserService;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dynfield.service.DdynfieldService;
import com.joinsoft.module.system.dyntable.entity.Ddyntable;
import com.joinsoft.module.system.dyntable.service.DdyndataService;
import com.joinsoft.module.system.dyntable.service.DdyntableService;
import com.joinsoft.platform.global.entity.CacheRbacUser;


@Controller
@RequestMapping(value="/system/dyndata/")
public class DdyndataController {
	@Autowired
	private DdyntableService ddyntableService;
	@Autowired
	private IUserService userService;
	@Autowired
	private DdynfieldService ddynfieldService;
	@Autowired
	private DdyndataService ddyndataService;
	private String url ="system/dyndata/";
	private String 			menuid;
	
	
	/**
     * 功能: 进入动态表数据维护列表页面
     * 
     * @param model
     * @param request
     * @author 孙晨阳   2017-06-16
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "/list")
    public String list(String extid, String menuid, Model model, Page page) {
		extid = StringUtils.clean(extid);
        menuid = StringUtils.clean(menuid);
        this.menuid = menuid;
        Map map = ddyndataService.listColumns(extid);//拼接好的表头数据 即字段信息  
        Ddyntable ddynTable = (Ddyntable) map.get("ddynTable");//表实体获取表信息数据
        model.addAttribute("ddynTable", ddynTable);//将表实体放入前台界面
        if (map.get("KEYNAME") != null) {//将主键字段放入前台界面
            model.addAttribute("keyname", (String) map.get("KEYNAME"));
        }
        List<Map<String, Object>> headerList = (List<Map<String, Object>>) map.get("JSONDATA");//表头
        model.addAttribute("headerList", headerList);
        model.addAttribute("extid", extid);
        model.addAttribute("menuid", menuid);        
        //分页信息
        int count = ddyndataService.getcount(extid);
        page.setTotalResult(count);       
        page.setTotalPage(page.getTotalResult()/page.getShowCount());
        model.addAttribute("pagehtml", page.getPageStr());
        //列表数据部分     
        Map dataMap = ddyndataService.listData(extid,"0",null,page);//0表示是列表部分
        Ddyntable ddynTabledata = (Ddyntable) dataMap.get("ddynTable");
        model.addAttribute("ddynTable", ddynTabledata);//表实体
        List<Map<String, Object>> dataList = (List) dataMap.get("dataList");
        model.addAttribute("dataList", dataList);//表数据   
        return url+"list";
    }
	
	
	 /**
     * 功能: 初始化动态表字段信息
     * 
     * @param model
     * @author 孙晨阳   2017-06-16
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/initAdd")
    public String initAdd(Model model, HttpServletRequest request) {
        String extid = StringUtils.clean(request.getParameter("extid"));
        Map map = ddyndataService.getOptField(extid);//表实体，和字段list
        Ddyntable ddynTable = (Ddyntable) map.get("ddynTable");//获取表实体
        List ddynFieldList = (List) map.get("ddynFieldList");//获取字段list
        
        model.addAttribute("tableName", ddynTable.getTablename());
        model.addAttribute("ddynFieldList", ddynFieldList);
        model.addAttribute("ddynTable", ddynTable);
        model.addAttribute("extid", extid);
        model.addAttribute("add", "add");
        model.addAttribute("menuid", StringUtils.clean(menuid));
        return url+"edit";
    }
	
    /**
     * 功能: 增加动态表内容信息
     * 
     * @param String
     * @author 孙晨阳   2017-06-16
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(String extid, HttpServletRequest request) {
    	CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	String errMsg = "";
        extid = StringUtils.clean(extid);
        String tableName = StringUtils.clean(request.getParameter("tablename"));
        try {
        /* 获取动态表及其字段信息 */
        Map<String, Object> conditions = new HashMap<String, Object>();
        Ddyntable tableObj = ddyntableService.queryEntity(extid);//表信息 /实体
        /* 获取动态表字段信息 */
        StringBuilder param = new StringBuilder();
        param.append("and EXTID = '" + extid + "'");
        conditions.put("where", param.toString());
        conditions.put("order", "orderno");
        List fieldList = ddynfieldService.getMapByList(conditions);//所有的字段信息        
        /* 循环获取页面元素的值 */
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < fieldList.size(); i++) {
            Map dmap = (Map) fieldList.get(i);
            if (dmap != null) {
            	if(dmap.get("ISKEY").equals("1")){//如果是主键
            		String fieldname = (String) dmap.get("FIELDNAME");//获取字段定义值
            	    map.put(fieldname.toUpperCase(),UUIDUtils.get32UUID());
            	}else{         	
            	if(dmap.get("ISREPT").equals("1")){//若可以重复
            	if(dmap.get("ISZERO").equals("0")){//若不需要补零
                String fieldname = (String) dmap.get("FIELDNAME");//获取字段定义值
                if(fieldname.equals("CREATEBY")){//创建者                   	
                	//获取当前登陆用户
            		map.put(fieldname.toUpperCase(), rbacUser.getUsername());
                }else{
                	if(fieldname.equals("CREATETIME")){//创建时间
                		map.put(fieldname.toUpperCase(),DateUtils.getTimeNow());
                	}else{
                		if(fieldname.equals("DELFLAG")){//删除标识
            				map.put(fieldname.toUpperCase(),JsdpConstants.HFMP_Delflag_N);
            			}else{
            				map.put(fieldname.toUpperCase(), request.getParameter(fieldname));//在添加界面上获取对应的值
            			}
                	}              	
                } 
              }else{
            	  //若需要补零            	  
            	  String fieldname = (String) dmap.get("FIELDNAME");//获取字段定义值
                  if(fieldname.equals("CREATEBY")){//创建者                   	
                  	//获取当前登陆用户
              		int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
              		String current = rbacUser.getUsername();
          			if(fieldlen != current.length()){
          				int length = fieldlen - current.length();
          					for (int j = 0; j < length; j++) {
          						current = current+"0";
							}
          			}
              		map.put(fieldname.toUpperCase(),current);	
                  }else{
                  	if(fieldname.equals("CREATETIME")){//创建时间
                  		int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                  		String current = DateUtils.getTimeNow();
                  		if(fieldlen != current.length()){
              				int length = fieldlen - current.length();
              					for (int j = 0; j < length; j++) {
              						current = current+"0";
								}
              			}
                  		map.put(fieldname.toUpperCase(),current);
                  	}else{
                  		if(fieldname.equals("DELFLAG")){//删除标识                  				
              				map.put(fieldname.toUpperCase(),JsdpConstants.HFMP_Delflag_N);
              			}else{
              				int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                      		String current = request.getParameter(fieldname);
                      		if(fieldlen != current.length()){
                  				int length = fieldlen - current.length();
                  					for (int j = 0; j < length; j++) {
                  						current = current+"0";
    								}
                  			}
              				map.put(fieldname.toUpperCase(), current);//在添加界面上获取对应的值
              			}
                  	}              	
                  }            	  
              }//是否补零完毕
              }	else{//若不能重复，开始
            	  if(dmap.get("ISZERO").equals("0")){//若不需要补零           		  
                      String fieldname = (String) dmap.get("FIELDNAME");//获取字段定义值
                      if(fieldname.equals("CREATEBY")){//创建者                   	
                      	//获取当前登陆用户
                  		//是否重复判断
                  		Map isRept = new HashMap();
                  		String where = "select "+fieldname+" from "+tableName+" where "+fieldname+" = "+"'"+rbacUser.getUsername()+"'";
                  		isRept.put("sql", where);
                  		List dataList = ddyndataService.getIsRept(isRept);
                  		if(dataList!=null && !dataList.isEmpty()){
                  			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, dmap.get("FIELDNAMECN")+"不允许重复");
                  		}
                  		map.put(fieldname.toUpperCase(), rbacUser.getUsername());
                      }else{
                      	if(fieldname.equals("CREATETIME")){//创建时间
                      		map.put(fieldname.toUpperCase(),DateUtils.getTimeNow());
                      	}else{
                      		if(fieldname.equals("DELFLAG")){//删除标识
                  				map.put(fieldname.toUpperCase(),JsdpConstants.HFMP_Delflag_N);
                  			}else{  
                  				//是否重复判断
                  				Map isRept = new HashMap();
                          		String where = "select "+fieldname+" from "+tableName+" where "+fieldname+" = "+"'"+request.getParameter(fieldname)+"'";
                          		isRept.put("sql", where);
                          		List dataList = ddyndataService.getIsRept(isRept);
                          		if(dataList!=null && !dataList.isEmpty()){
                          			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, dmap.get("FIELDNAMECN")+"不允许重复");
                          		}
                          		map.put(fieldname.toUpperCase(), request.getParameter(fieldname));//在添加界面上获取对应的值
                  			}
                      	}              	
                      } 
                    }else{
                  	  //若需要补零            	  
                  	  String fieldname = (String) dmap.get("FIELDNAME");//获取字段定义值
                        if(fieldname.equals("CREATEBY")){//创建者                   	
                        	//获取当前登陆用户
                    		int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                    		String current = rbacUser.getUsername();
                			if(fieldlen != current.length()){
                				int length = fieldlen - current.length();
                					for (int j = 0; j < length; j++) {
                						current = current+"0";
      							}
                			}
                			//是否重复判断
                			Map isRept = new HashMap();
                      		String where = "select "+fieldname+" from "+tableName+" where "+fieldname+" = "+"'"+rbacUser.getUsername()+"'";
                      		isRept.put("sql", where);
                      		List dataList = ddyndataService.getIsRept(isRept);
                      		if(dataList!=null && !dataList.isEmpty()){
                      			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, dmap.get("FIELDNAMECN")+"不允许重复");
                      		}
                    		map.put(fieldname.toUpperCase(),current);	
                        }else{
                        	if(fieldname.equals("CREATETIME")){//创建时间
                        		int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                        		String current = DateUtils.getTimeNow();
                        		if(fieldlen != current.length()){
                    				int length = fieldlen - current.length();
                    					for (int j = 0; j < length; j++) {
                    						current = current+"0";
      								}
                    			}
                        		map.put(fieldname.toUpperCase(),current);
                        	}else{
                        		if(fieldname.equals("DELFLAG")){//删除标识                  				
                    				map.put(fieldname.toUpperCase(),JsdpConstants.HFMP_Delflag_N);
                    			}else{
                    				int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                            		String current = request.getParameter(fieldname);
                            		if(fieldlen != current.length()){
                        				int length = fieldlen - current.length();
                        					for (int j = 0; j < length; j++) {
                        						current = current+"0";
          								}
                        			}
                            		Map isRept = new HashMap();
                              		String where = "select "+fieldname+" from "+tableName+" where "+fieldname+" = "+"'"+current+"'";
                              		isRept.put("sql", where);
                              		List dataList = ddyndataService.getIsRept(isRept);
                              		if(dataList!=null && !dataList.isEmpty()){
                              			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, dmap.get("FIELDNAMECN")+"不允许重复");
                              		}
                    				map.put(fieldname.toUpperCase(), current);//在添加界面上获取对应的值
                    			}
                        	}              	
                        }            	  
                    }	  
              }//判断是否重复结束
              }//主键结束
            }
        } 
        	ddyndataService.addData(tableObj, fieldList, map);      
        	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
		} catch (Exception e) {
			errMsg = e.getMessage();
		}       
        return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);   
    }
    
    /**
     * 功能: 初始化修改动态表字段
     * 
     * @param model
     * @param id            
     * @author 孙晨阳   2017-06-16
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/initUpdate")
    public String initUpdate(Model model, HttpServletRequest request) {
        String extid = StringUtils.clean(request.getParameter("extid"));
        String keyid = StringUtils.clean(request.getParameter("keyid"));
        Map map = ddyndataService.getOptField(extid);
        Ddyntable ddynTable = (Ddyntable) map.get("ddynTable");//表实体
        List ddynFieldList = (List) map.get("ddynFieldList");//字段
        /* 获取待编辑实体信息 */
        Map dataObj = ddyndataService.getEntityByKeyId(keyid, extid);
        
        model.addAttribute("tableName", ddynTable.getTablename());
        model.addAttribute("dataObj", dataObj);
        model.addAttribute("ddynFieldList", ddynFieldList);
        model.addAttribute("ddynTable", ddynTable);
        model.addAttribute("extid", extid);
        model.addAttribute("add", "update");
        model.addAttribute("menuid", StringUtils.clean(menuid));
        return url+"edit";
    }
   
    /**
     * 功能: 修改动态表字段信息
     * 
     * @param Ddyntable
     * @author 孙晨阳   2017-06-16
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Model model, HttpServletRequest request) {
    	CacheRbacUser rbacUser = userService.getRbacUserBySessionid((String)request.getSession().getAttribute(SessionConstants.JSDP_USER_SESSIONID));
    	String errMsg = "";
        String extid = StringUtils.clean(request.getParameter("extid"));
        String keyid = StringUtils.clean(request.getParameter("keyid"));
        String keyname = StringUtils.clean(request.getParameter("keyname"));
        String tableName = StringUtils.clean(request.getParameter("tablename"));
        Map dataMap = ddyndataService.listData(extid,"1",keyid,null);//获取编辑数据
        List<Map<String, Object>> dataList = (List) dataMap.get("dataList");//待编辑的数据
        try {
            /* 获取动态表及其字段信息 */
            Map<String, Object> conditions = new HashMap<String, Object>();
            //conditions.put("id", extid);
            Ddyntable tableObj = ddyntableService.queryEntity(extid);//表信息 /实体
            /* 获取动态表字段信息 */
           // conditions.clear();
            StringBuilder param = new StringBuilder();
            param.append("and EXTID = '" + extid + "'");
            conditions.put("where", param.toString());
            conditions.put("order", "orderno");
            List fieldList = ddynfieldService.getMapByList(conditions);
            
          //拼接待编辑的数据：
            Map<String, Object> map = new HashMap<String, Object>();
            for (int j = 0; j < fieldList.size(); j++) {
            	 Map dmap = (Map) fieldList.get(j);
            	 if (dmap != null) {
            		 String fieldname = (String) dmap.get("FIELDNAME");//获取字段定义值
            		 for (int i = 0; i < dataList.size(); i++) {
                     	String value = dataList.get(i).get(fieldname).toString();
                     	if(!StringUtils.isBlank(value)){
                     		map.put(fieldname.toUpperCase(), value);
                     	}
         			}
            	 }
            }
            for (int i = 0; i < fieldList.size(); i++) {
                Map dmap = (Map) fieldList.get(i);
                if (dmap != null) {
                	if(dmap.get("ISREPT").equals("1")){//若可以重复
                	if(dmap.get("ISZERO").equals("0")){//若不需要补零
                    String fieldname = (String) dmap.get("FIELDNAME");//获取字段定义值
                    if(fieldname.equals("UPDATEBY")){//修改者                   	
                    	//获取当前登陆用户
                		map.put(fieldname.toUpperCase(), rbacUser.getUsername());
                    }else{
                    	if(fieldname.equals("UPDATETIME")){//修改时间
                    		map.put(fieldname.toUpperCase(),DateUtils.getTimeNow());
                    	}else{
                    		if(!StringUtils.isEmpty(request.getParameter(fieldname))){//为保留已存在的值
                    		map.put(fieldname.toUpperCase(), request.getParameter(fieldname));//在添加界面上获取对应的值
                    		}
                    	}              	
                    } 
                  }else{
                	  //若需要补零            	  
                	  String fieldname = (String) dmap.get("FIELDNAME");//获取字段定义值
                      if(fieldname.equals("UPDATEBY")){//修改者                   	
                      	//获取当前登陆用户
                  		int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                  		String current = rbacUser.getUsername();
              			if(fieldlen != current.length()){
              				int length = fieldlen - current.length();
              					for (int j = 0; j < length; j++) {
              						current = current+"0";
    							}
              			}
                  		map.put(fieldname.toUpperCase(),current);	
                      }else{
                      	if(fieldname.equals("UPDATETIME")){//修改时间
                      		int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                      		String current = DateUtils.getTimeNow();
                      		if(fieldlen != current.length()){
                  				int length = fieldlen - current.length();
                  					for (int j = 0; j < length; j++) {
                  						current = current+"0";
    								}
                  			}
                      		map.put(fieldname.toUpperCase(),current);
                      	}else{
                      		int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                      		String current = request.getParameter(fieldname);
                      		if(fieldlen != current.length()){
                  				int length = fieldlen - current.length();
                  					for (int j = 0; j < length; j++) {
                  						current = current+"0";
    								}
                  			}
                      		if(!StringUtils.isEmpty(request.getParameter(fieldname))){//为保留已存在的值
                      			map.put(fieldname.toUpperCase(), current);//在添加界面上获取对应的值
                        	}
              				
                      	}              	
                      }            	  
                  }//是否补零完毕
                  }	else{//若不能重复，开始
                	  if(dmap.get("ISZERO").equals("0")){//若不需要补零           		  
                          String fieldname = (String) dmap.get("FIELDNAME");//获取字段定义值
                          if(fieldname.equals("UPDATEBY")){//修改者                   	
                          	//获取当前登陆用户
                      		//是否重复判断
                      		Map isRept = new HashMap();
                      		String where = "select "+fieldname+" from "+tableName+" where "+fieldname+" = "+"'"+rbacUser.getUsername()+"'";
                      		isRept.put("sql", where);
                      		List dataisRept = ddyndataService.getIsRept(isRept);
                      		if(dataisRept!=null && !dataisRept.isEmpty()){
                      			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, dmap.get("FIELDNAMECN")+"不允许重复");
                      		}
                      		map.put(fieldname.toUpperCase(), rbacUser.getUsername());
                          }else{
                          	if(fieldname.equals("UPDATETIME")){//修改时间
                          		map.put(fieldname.toUpperCase(),DateUtils.getTimeNow());
                          	}else{
                          	//是否重复判断
                  				Map isRept = new HashMap();
                          		String where = "select "+fieldname+" from "+tableName+" where "+fieldname+" = "+"'"+request.getParameter(fieldname)+"'";
                          		isRept.put("sql", where);
                          		List dataisRept = ddyndataService.getIsRept(isRept);
                          		if(dataisRept!=null && !dataisRept.isEmpty()){
                          			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, dmap.get("FIELDNAMECN")+"不允许重复");
                          		}
                          		if(!StringUtils.isEmpty(request.getParameter(fieldname))){//为保留已存在的值
                          			map.put(fieldname.toUpperCase(), request.getParameter(fieldname));//在添加界面上获取对应的值
                            	}                         		
                          	}              	
                          } 
                        }else{
                      	  //若需要补零            	  
                      	  String fieldname = (String) dmap.get("FIELDNAME");//获取字段定义值
                            if(fieldname.equals("UPDATEBY")){//修改者                   	
                            	//获取当前登陆用户
                        		int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                        		String current = rbacUser.getUsername();
                    			if(fieldlen != current.length()){
                    				int length = fieldlen - current.length();
                    					for (int j = 0; j < length; j++) {
                    						current = current+"0";
          							}
                    			}
                    			//是否重复判断
                    			Map isRept = new HashMap();
                          		String where = "select "+fieldname+" from "+tableName+" where "+fieldname+" = "+"'"+rbacUser.getUsername()+"'";
                          		isRept.put("sql", where);
                          		List dataisRept = ddyndataService.getIsRept(isRept);
                          		if(dataisRept!=null && !dataisRept.isEmpty()){
                          			return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, dmap.get("FIELDNAMECN")+"不允许重复");
                          		}
                        		map.put(fieldname.toUpperCase(),current);	
                            }else{
                            	if(fieldname.equals("UPDATETIME")){//修改时间
                            		int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                            		String current = DateUtils.getTimeNow();
                            		if(fieldlen != current.length()){
                        				int length = fieldlen - current.length();
                        					for (int j = 0; j < length; j++) {
                        						current = current+"0";
          								}
                        			}
                            		map.put(fieldname.toUpperCase(),current);
                            	}else{
                            		int fieldlen =   Integer.parseInt((String) dmap.get("FIELDLEN"));//获取字段长度
                            		String current = request.getParameter(fieldname);
                            		if(fieldlen != current.length()){
                        				int length = fieldlen - current.length();
                        					for (int j = 0; j < length; j++) {
                        						current = current+"0";
          								}
                        			}
                            		Map isRept = new HashMap();
                              		String where = "select "+fieldname+" from "+tableName+" where "+fieldname+" = "+"'"+current+"'";
                              		isRept.put("sql", where);
                              		List dataisRept = ddyndataService.getIsRept(isRept);
                              		if(dataisRept!=null && !dataisRept.isEmpty()){
                              			if(!map.get(fieldname).toString().equals(current)){
                              				return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, dmap.get("FIELDNAMECN")+"不允许重复");
                              			}
                              			
                              		}
                              		if(!StringUtils.isEmpty(request.getParameter(fieldname))){//为保留已存在的值
                              			map.put(fieldname.toUpperCase(), current);//在添加界面上获取对应的值
                                	} 
                    				
                            	}              	
                            }            	  
                        }	  
                  }//判断是否重复结束
                	
                }
            }
            ddyndataService.updateData(tableObj, fieldList, map, keyid, keyname);
            return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
        } catch (Exception e) {
        	errMsg = e.getMessage();
        }
        return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg); 
    }

    /**
     * 功能: 删除动态表信息
     * 
     * @param ids           
     * @author 孙晨阳   2017-06-16
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(String id, String keyname, String extid, HttpServletRequest request) {
    	String errMsg = "";
        id = StringUtils.clean(id);
        keyname = StringUtils.clean(keyname);
        extid = StringUtils.clean(extid);
        List<String> idList = Arrays.asList(id.split("_"));//备用于以后实现批量删除，现在暂时可不用
        try {
            ddyndataService.delData(idList, keyname, extid);
        	return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_SUCCESS, JsdpConstants.AJAX_RESULT_MSG_T);
        } catch (Exception e) {
        	errMsg = e.getMessage();        	 
        }
        return JsonTools.simpleAjaxJsonResponse(JsdpConstants.HFMP_RESULT_FAIL, errMsg);
    }  
}
