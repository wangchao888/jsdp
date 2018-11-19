package com.joinsoft.module.system.dyntable.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joinsoft.core.collection.CollectionUtils;
import com.joinsoft.core.persistence.Page;
import com.joinsoft.core.utils.StringUtils;

import com.joinsoft.module.system.dynfield.dao.DdynfieldMapper;
import com.joinsoft.module.system.dynfield.entity.Ddynfield;
import com.joinsoft.module.system.dyntable.dao.CommonMapper;
import com.joinsoft.module.system.dyntable.dao.DdyntableMapper;
import com.joinsoft.module.system.dyntable.entity.Ddyntable;


@Service
public class DdyndataService {

	@Autowired
    private DdyntableMapper ddynTableMapper;
	
	@Autowired
	private DdynfieldMapper ddynFieldMapper;
	
	@Autowired
	private CommonMapper commonMapper;
	
	
	/**
     * 功能: 动态表数据维护，获取动态表列标题
     *       首先获取动态表信息；
     *       第二获取动态表字段信息(过滤ISDISP = '1')；
     *       第三封装JSON数据
     * @param String id
     * @return
     * @author 孙晨阳   2017-06-16
     * 版本：1.0
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map listColumns(String id){
		/*定义返回Map*/
		Map retMap = new HashMap();
		List ddynFieldTempList = new ArrayList<HashMap>();
		
		
		/*获取动态表信息*/
		Ddyntable ddynTable = ddynTableMapper.queryById(id);
		retMap.put("ddynTable", ddynTable);
		
		/*获取动态表字段信息*/
		Map<String, Object> conditions = new HashMap<String, Object>();
        StringBuilder param = new StringBuilder();
        if (!StringUtils.isEmpty(id)) {
            param.append("and EXTID = '").append(id).append("'");
        }
        conditions.put("where", param.toString());
        conditions.put("order", "orderno");
		ddynFieldTempList = ddynFieldMapper.getDdynfield(conditions);
		retMap.put("ddynFieldTempList", ddynFieldTempList);
		
		/*封装数据*/
		if(CollectionUtils.isNotEmpty(ddynFieldTempList)){//判断字段表信息是否为空
			String retFieldName = "";//字段名备用
			retMap.put("COLUMNLEN", ddynFieldTempList.size());//添加字段的数量
			List<Map<String, Object>> colList = new ArrayList<Map<String, Object>>();//循环拼接的字段内容存放在这里
			for(int t=0; t<ddynFieldTempList.size(); t++){//根据字段的数量循环
				Map<String, Object> tmap = (HashMap)ddynFieldTempList.get(t);//获取字段内容
				Map<String, Object> jmap = new HashMap<String, Object>();
				String iskey = "0";   //是否主键，默认否
				if(tmap.get("FIELDNAME") != null){ //如果字段名存在
					String fieldname = (String)tmap.get("FIELDNAME");//获取字段名
					jmap.put("name", fieldname.toUpperCase());//将字段名放入jmap中，并转换成大写
					if(!StringUtils.isBlank(retFieldName)){//字段名赋值给retFieldName +~
						retFieldName = retFieldName + "~" + fieldname;
					}else{
						retFieldName = fieldname;
					}
					if(tmap.get("ISKEY") != null){ //是否主键不等于空的话
						iskey = (String)tmap.get("ISKEY");//是否主键值赋给 iskey
						if("1".equals(iskey)){//如果是主键 添加keyname /大写
							retMap.put("KEYNAME", fieldname.toUpperCase());
						}
					}
				}
				if(tmap.get("FIELDNAMECN") != null){//拼接字段中文名
					String fieldnamecn = (String)tmap.get("FIELDNAMECN");
					jmap.put("display", fieldnamecn);
				}
				if(tmap.get("FIELDTYPE") != null){//根据字段类型  决定字段像哪边对齐，暂时用不到
					String fieldtype = (String)tmap.get("FIELDTYPE");
					if("S".equals(fieldtype)){
						jmap.put("align", "left");
					}else if("D".equals(fieldtype)){
						jmap.put("align", "center");
					}else if("N".equals(fieldtype)){
						jmap.put("align", "right");
					}else{
						jmap.put("align", "left");
					}
				}
				if(tmap.get("FIELDWIDTH") != null){//拼接宽度
					String fieldwidth = (String)tmap.get("FIELDWIDTH");
					jmap.put("width", fieldwidth);
				}
				if(tmap.get("ISDISP") != null){//是否显示
					String fieldwidth = (String)tmap.get("ISDISP");
					jmap.put("isdisp", fieldwidth);
				}
				/*判断是否固定值，固定值不能修改；非固定值判断是否字典*/
				if(tmap.get("FIXVALUE") == null){
					if(jmap.get("DICTABLENAME") == null || jmap.get("DICKEY") == null || jmap.get("DICVALUE") == null){
						jmap.put("editor", "{type:'text'}");
        			}else{
        				jmap.put("editor", "{type:'select'}");
        			}
				}
				colList.add(jmap);
			}			
			retMap.put("JSONDATA", colList);			
			retMap.put("retFieldName", retFieldName);
		}
		return retMap;
	}
			
	/**
     * 功能: 获取动态表相应的数据
     *       首先获取动态表信息；
     *       第二获取动态表字段信息(过滤ISDISP = '1')；
     *       第三拼接查询SQL，执行SQL，查询数据
     *       type 判断是增加调用还是修改调用 keyid 修改所用
     * @param String id
     * @return
     * @author 孙晨阳   2017-06-16
     * 版本：1.0    
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map listData(String id,String type,String keyid, Page page){
		/*动态表对应的字段信息列表*/
		List ddynFieldTempList= new ArrayList<Map>();
		
		/*动态表信息*/
		Ddyntable ddynTable = ddynTableMapper.queryById(id);
		
		/*查询动态表对应的字段列表*/
		Map<String, Object> conditions = new HashMap<String, Object>();
        StringBuilder param = new StringBuilder();
        if (!StringUtils.isEmpty(id)) {
        	param.append(" and EXTID = '").append(id).append("'");
        }
        conditions.put("where", param.toString());
        conditions.put("order", "orderno");
		ddynFieldTempList = ddynFieldMapper.getDdynfield(conditions);//字段信息 
		
		/*拼接查询用的字段，EXP: field1,field2,field3 */
		String fieldname = linkStr(ddynFieldTempList, "FIELDNAME", "1", "SELECT");
		String keyname = getkeyid(ddynFieldTempList,"FIELDNAME");//取出主键的字段定义
		/*拼接查询用的SQL，EXP: select field1,field2,field3 from TABLENAME */
		String sql = "";
		//判断是列表显示还是修改界面//如果是修改界面值查询一条数据，即需要修改的数据
		if(type.equals("1")){
			String where = "where delflag = '0' and " + keyname +"="+"'"+keyid+"'";
			sql = linkSql(ddynTable.getTablename(), fieldname, null, "select", where, null, 0, 0);
		}else{
			sql = linkSql(ddynTable.getTablename(), fieldname, null, "page", "where delflag = '0'", null, page.getCurrentPage(), page.getShowCount());
		}
		//动态表的条件
		if(!StringUtils.isBlank(ddynTable.getConditions())){
			if(ddynTable.getConditions().indexOf("where") != -1){
				sql = sql + ddynTable.getConditions();
			}else{
				sql = sql + " where " + ddynTable.getConditions();
			}
		}
		
		/*定义返回MAP*/
		Map retMap=new HashMap();
		retMap.put("ddynTable", ddynTable);
		
		/*查询动态表对应的数据列表*/
		Map map = new HashMap();
		map.put("sql", sql);
		
		List dataList = commonMapper.queryListBySql(map);//查询数据
		if(CollectionUtils.isNotEmpty(dataList)){
			retMap.put("dataList", dataList);			
			List ddynFieldList = new ArrayList<Map>();
			if (CollectionUtils.isNotEmpty(ddynFieldTempList)) {//字段内容不为空的话
				for(int t=0;t<ddynFieldTempList.size();t++){//根据字段数量循环
					Map dataMap = (Map)ddynFieldTempList.get(t);//dataMap获取字段值
					if(dataMap != null){
						dataMap.put("ISDIC", "0");//标记字典
						String dictableName = (String)dataMap.get("DICTABLENAME");//字典名称
						String dickey = (String)dataMap.get("DICKEY");//字典key
						String dicvalue = (String)dataMap.get("DICVALUE");//字典value
						String condition = (String)dataMap.get("CONDITIONS");//条件
						//查询字典里的值：
						if(dictableName != null && !"".equals(dictableName) && condition!=null && !"".equals(condition)){
							dataMap.put("ISDIC", "1");
							String dicsql = "select * from " + dictableName + " " + condition + " order by " + dickey;
							Map tempMap = new HashMap();
							tempMap.put("sql", dicsql);
							List dicList=commonMapper.queryListBySql(tempMap);
							if(CollectionUtils.isNotEmpty(dicList)){
								Map dicMap = new HashMap();
								for(int i=0;i<dicList.size();i++){
									Map dicTempMap = (Map)dicList.get(i);
									if(dicTempMap != null){
										dicMap.put(dicTempMap.get(dickey.toUpperCase()), dicTempMap.get(dicvalue.toUpperCase()));
									}
								}
								dataMap.put("DICMAP", dicMap);
							}
						}
					}
					ddynFieldList.add(dataMap);
				}
			}
			retMap.put("ddynFieldList", ddynFieldList);
		}		
		return retMap;
	}
	
	
	/**
     * 功能: 获取分页所用的count
     * @param String id
     * @return
     * @author 孙晨阳   2017-06-16
     * 版本：1.0
     * type 判断是增加调用还是修改调用 keyid 修改所用
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getcount(String id){
		/*动态表对应的字段信息列表*/
		List ddynFieldTempList= new ArrayList<Map>();		
		/*动态表信息*/
		Ddyntable ddynTable = ddynTableMapper.queryById(id);		
		/*查询动态表对应的字段列表*/
		Map<String, Object> conditions = new HashMap<String, Object>();
        StringBuilder param = new StringBuilder();
        if (!StringUtils.isEmpty(id)) {
        	param.append(" and EXTID = '").append(id).append("'");
        }
        conditions.put("where", param.toString());
        conditions.put("order", "orderno");
		ddynFieldTempList = ddynFieldMapper.getDdynfield(conditions);//字段信息 		
		/*拼接查询用的字段，EXP: field1,field2,field3 */
		String fieldname = linkStr(ddynFieldTempList, "FIELDNAME", "1", "SELECT");
		/*拼接查询用的SQL，EXP: select field1,field2,field3 from TABLENAME */
		String sql = linkSql(ddynTable.getTablename(), fieldname, null, "count", "where delflag = '0'", null, 0, 0);		
		/*查询动态表对应的数据列表*/
		Map map = new HashMap();
		map.put("sql", sql);		
		List dataList = commonMapper.queryListBySql(map);//查询数据
		Map countMap = new HashMap();
		countMap = (Map) dataList.get(0);		
		int count = Integer.parseInt(countMap.get("COUNT(*)").toString());
		return count;	
	}
	
	
	/**
     * 功能: 获取动态表相应的字段，用于动态内容的增加操作
     *       首先获取动态表信息；
     *       第二获取动态表字段的全部信息,区分主键，字段个数；
     *       第三查询相应的字典信息数据
     * @param String id
     * @return
     * @author 孙晨阳   2017-06-16
     * 版本：1.0
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getOptField(String id){
		/*定义返回Map*/
		Map retMap = new HashMap();
		List tempddynFieldList = new ArrayList<HashMap>();
		List ddynFieldList = new ArrayList<HashMap>();
		
		/*获取动态表信息*/
		Ddyntable ddynTable = ddynTableMapper.queryById(id);
		retMap.put("ddynTable", ddynTable);
		
		/*获取动态表字段信息*/
		Map<String, Object> conditions = new HashMap<String, Object>();
        StringBuilder param = new StringBuilder();
        if (!StringUtils.isEmpty(id)) {
            param.append("and isDisp = '1'and EXTID = '").append(id).append("'");
        }
        conditions.put("where", param.toString());
        conditions.put("order", "orderno");
        tempddynFieldList = ddynFieldMapper.getDdynfield(conditions);
        
        /*获取动态表字段对应的字典信息*/
        for(int t=0; t<tempddynFieldList.size(); t++){
        	HashMap ddynmap = (HashMap)tempddynFieldList.get(t);
        	if(CollectionUtils.isNotEmpty(ddynmap)){
        		/*固定值不为空获取固定值*/
        		if(ddynmap.get("FIXVALUE") != null){
        			ddynmap.put("TYPEVALUE", "FIXVALUE");//固定值
        		}else{
        			if(ddynmap.get("DICTABLENAME") == null || ddynmap.get("DICKEY") == null || ddynmap.get("DICVALUE") == null){
        				ddynmap.put("TYPEVALUE", "NORMALVALUE");//正常存储
        			}else{
        				ddynmap.put("TYPEVALUE", "DICVALUE");//字典值
        				String dictablename = (String)ddynmap.get("DICTABLENAME");
        				String dickey = (String)ddynmap.get("DICKEY");
        				String dicvalue = (String)ddynmap.get("DICVALUE");
        				String condition = (String)ddynmap.get("CONDITIONS");
        				if(!StringUtils.isBlank(condition)){
        					StringBuilder dicsql = new StringBuilder("select ")
        					.append(dickey)
        					.append(",")
        					.append(dicvalue)
        					.append(" from ")
        					.append(dictablename)
        					.append(" ")
        					.append(condition);
        					Map conMap = new HashMap();
        					conMap.put("sql", dicsql.toString());
        					List dicList = commonMapper.queryListBySql(conMap);
        					//封装为字典值的下拉列表：
        					if(CollectionUtils.isNotEmpty(dicList)){
        						List<Map<String, Object>> dicDataList = new ArrayList<Map<String, Object>>();//储存字典值
        						for(int i=0;i<dicList.size();i++){
        							Map dicTempMap = (Map)dicList.get(i);
        							if(dicTempMap != null){
        						        Map<String, Object> select = new HashMap<String, Object>();
        						        select.put("KEY", dicTempMap.get(dicvalue.toUpperCase()));
        						        select.put("VALUE", dicTempMap.get(dickey.toUpperCase()));
        						        dicDataList.add(select);
        							}
        						}
        						ddynmap.put("DICMAP", dicDataList);
        					}
        				}
        			}
        		}
        	}
        	ddynFieldList.add(ddynmap);
        }        
		retMap.put("ddynFieldList", ddynFieldList);
		
		return retMap;
	}
	
	/**动态信息维护添加新数据
	 * @param Ddyntable
	 * @param List
	 * @param map
	 * @return
     * @author 孙晨阳   2017-06-16
	 * 版本：1.0
	 */
	@SuppressWarnings("rawtypes")
	public void addData(Ddyntable obj, List fieldList, Map map){
		/*获取动态表信息*/
		String fieldnames =linkStr(fieldList, "FIELDNAME", "1", "INSERT");//组合拼FIELDNAME
		String fieldtypes =linkStr(fieldList, "FIELDTYPE", "1", null);//组合拼FIELDNAME
		String fieldvalues = getFieldValue(fieldnames, fieldtypes, map);
		String sql=linkSql(obj.getTablename(), fieldnames, fieldvalues, "insert", null, null, 0, 0);
		commonMapper.insertData(sql);
	}
	/**
	 * 功能: 获取动态表数据的实体对象，用于动态内容的编辑操作
	 *       首先获取动态表对应的主键，根据主键查询实体；
	 * @param String keyid 动态表实体主键
	 * @param String extid 动态表主键
	 * @return
     * @author 孙晨阳   2017-06-16
	 * 版本：1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getEntityByKeyId(String keyid, String extid){
		Map dataMap = new HashMap();
		/*获取该动态表的主键字段名*/
		List<Ddynfield> fieldList = ddynFieldMapper.getEntityKeyId(extid);
		if(CollectionUtils.isNotEmpty(fieldList)){
			Ddynfield fieldEntity = fieldList.get(0);//将主键字段传入实体
			if(fieldEntity != null){
				String keyname = fieldEntity.getFieldname();//获取主键字段名
				/*查询动态表实体以及动态表对应的字段列表*/
				List ddynFieldTempList= new ArrayList<Map>();
				Map<String, Object> conditions = new HashMap<String, Object>();
		        StringBuilder param = new StringBuilder();
		        if (!StringUtils.isEmpty(extid)) {
		        	param.append("and EXTID = '").append(extid).append("'");
		        }
		        conditions.put("where", param.toString());
		        conditions.put("order", "orderno");
				ddynFieldTempList = ddynFieldMapper.getDdynfield(conditions);//所有的字段
				/*动态表信息*/
				Ddyntable ddynTable = ddynTableMapper.queryById(extid);//表信息
				/*拼接查询用的字段，EXP: field1,field2,field3 */
				String fieldname = linkStr(ddynFieldTempList, "FIELDNAME", "1", "SELECT");
				/*拼接查询用的SQL，EXP: select field1,field2,field3 from TABLENAME */
				String sql = linkSql(ddynTable.getTablename(), fieldname, null, "select", null, null, 0, 0);
				if(!StringUtils.isBlank(sql)){
					sql = sql + " where " + keyname + " = '" + keyid+"'";
					/*查询动态表对应的数据列表*/
					Map map = new HashMap();
					map.put("sql", sql);
					List dataList = commonMapper.queryListBySql(map);
					if(CollectionUtils.isNotEmpty(dataList)){
						dataMap = (HashMap)dataList.get(0);
					}
				}
				dataMap.put("KEYNAME", keyname);
				dataMap.put("KEYID", keyid);
			}
		}
		return dataMap;
	}	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getEntityByKeyId(String keyid, String extid, Ddyntable ddynTable, List ddynFieldTempList){
		Map dataMap = new HashMap();
		/*获取该动态表的主键字段名*/
		List<Ddynfield> fieldList = ddynFieldMapper.getEntityKeyId(extid);
		if(CollectionUtils.isNotEmpty(fieldList)){
			Ddynfield fieldEntity = fieldList.get(0);
			if(fieldEntity != null){
				String keyname = fieldEntity.getFieldname();
				dataMap.put("KEYNAME", keyname);
				dataMap.put("KEYID", keyid);
				/*拼接查询用的字段，EXP: field1,field2,field3 */
				String fieldname = linkStr(ddynFieldTempList, "FIELDNAME", "1", "SELECT");
				/*拼接查询用的SQL，EXP: select field1,field2,field3 from TABLENAME */
				String sql = linkSql(ddynTable.getTablename(), fieldname, null, "select", null, null, 0, 0);
				if(!StringUtils.isBlank(sql)){
					sql = sql + " where " + keyname + " = " + keyid;
					/*查询动态表对应的数据列表*/
					Map map = new HashMap();
					map.put("sql", sql);
					List dataList = commonMapper.queryListBySql(map);
					if(CollectionUtils.isNotEmpty(dataList)){
						dataMap = (HashMap)dataList.get(0);
					}
				}
			}
		}
		return dataMap;
	}
	
	/**动态信息维护更新数据
	 * @param tableid
	 * @param map
	 * @return
     * @author 孙晨阳   2017-06-16
	 * 版本：1.0
	 */
	@SuppressWarnings("rawtypes")
	public void updateData(Ddyntable obj, List fieldList, Map map, String keyid, String keyname){
		/*先删除原表数据*/
		if(!StringUtils.isBlank(keyid) && !StringUtils.isBlank(keyname)){
			StringBuilder sb = new StringBuilder("delete from ");
			sb.append(obj.getTablename())
			  .append(" where ")
			  .append(keyname)
			  .append(" = '")
			  .append(keyid)
			  .append("'");
			commonMapper.deleteData(sb.toString());
			/*添加编辑后的数据*/
			String fieldnames =linkStr(fieldList, "FIELDNAME", "1", "INSERT");//组合拼FIELDNAME
			String fieldtypes =linkStr(fieldList, "FIELDTYPE", "1", null);//组合拼FIELDTYPE
			String fieldvalues = getFieldValue(fieldnames, fieldtypes, map);
			String sql=linkSql(obj.getTablename(), fieldnames, fieldvalues, "insert", null, null, 0, 0);
			commonMapper.insertData(sql);
		}
	}
	
	/**动态信息维护删除数据
	 * @param Ddyntable
	 * @param map
	 * @return
     * @author 孙晨阳   2017-06-16
	 * 版本：1.0
	 */
	public void delData(List<String> ids, String keyname, String extid){
		/*获取动态表及其字段信息*/
    	Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("id", extid);
        Ddyntable tableObj = ddynTableMapper.queryById(extid);
		/*更新delflag字段*/
		if(!StringUtils.isBlank(keyname) && CollectionUtils.isNotEmpty(ids)){
			for(int t=0; t<ids.size(); t++){
				String id = ids.get(t);
				if(!StringUtils.isBlank(id)){
					StringBuilder sb = new StringBuilder("update ");
					sb.append(tableObj.getTablename())
					.append(" set delflag = '1'")
					.append(" where ")
					.append(keyname)
					.append(" = '")
					.append(id)
					.append("'");					
					commonMapper.deleteData(sb.toString());
				}
			}
		}
	}
	/**
	 * 字段的是否重复属性的值查询
	 * @param List dataList
	 * @param String key
     * @author 孙晨阳   2017-06-16
	 * 
	 */
	public List getIsRept(Map map){
		List dataList = commonMapper.queryListBySql(map);//查询数据
		return dataList;
	}
	
	/**
	 * 取出主键的字段值
	 * @param List dataList
	 * @param String key
     * @author 孙晨阳   2017-06-16
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public String getkeyid(List dataList,String key){
		String keyid="";
		if(dataList != null && dataList.size()>0){
			for(int i=0;i<dataList.size();i++){
				Map map = (Map)dataList.get(i);
				if(map != null){
					if(map.get("ISKEY") != null){
						String iskey = (String)map.get("ISKEY");
						if("1".equals(iskey)){							
							keyid = map.get(key).toString();
						}
					}				
				}
			}			
		}
		return keyid;
	}	

	/**
	 * 组合字符串
	 * @param List dataList
	 * @param String key
	 * @param String datatype 0-字符('TYPE')，1-非字符(TYPE)
	 * @param String opttype SELECT，INSERT，DELETE
	 * @author 李中新 2013-02-22
	 * 版本：1.0
	 * **/
	@SuppressWarnings("rawtypes")
	public String linkStr(List dataList, String key, String datatype, String opttype){
		StringBuilder builder = new StringBuilder();
		if(dataList != null && dataList.size()>0){
			for(int i=0;i<dataList.size();i++){
				Map map = (Map)dataList.get(i);
				if(map != null){
					if(map.get(key)!=null){
						if("0".endsWith(datatype)){
							builder.append("'").append(map.get(key).toString()).append("',");
						}else if("1".endsWith(datatype)){
							if(!StringUtils.isBlank(opttype)){
								if("SELECT".equals(opttype)){
									if(map.get("ISKEY") != null){
										String iskey = (String)map.get("ISKEY");
										if("1".equals(iskey)){
											builder.append(map.get(key).toString()).append(" AS KEYID,");
											builder.append(map.get(key).toString()).append(",");
										}else{
											builder.append(map.get(key).toString()).append(",");
										}
									}
								}else if("INSERT".equals(opttype) || "DELETE".equals(opttype)){
									builder.append(map.get(key).toString()).append(",");
								}else{
									builder.append(map.get(key).toString()).append(",");
								}
							}else{
								builder.append(map.get(key).toString()).append(",");
							}
						}
					}
				}
			}
			if(builder.length()>0){
				builder.deleteCharAt(builder.length()-1);
			}
		}
		return builder.toString();
	}
		
	
	
	/**
	 * 组合sql , , , 
	 * @param String tableName  表名
	 * @param String fieldName  字段名
	 * @param String sqltype    sql类别
	 * 		  insert update delete select count page
	 * @param String fieldValue 字段值
	 * @param String condition  条件
	 * @param String orderBy    排序
	 * @param int pageNo        当前页
	 * @param int pageSize      页数大小
	 * **/
	public String linkSql(String tableName, String fieldName, String fieldValue, String sqltype, String condition, String orderBy, int pageNo, int pageSize){
		StringBuilder builder = new StringBuilder();
		if(sqltype != null && !"".equals(sqltype) && !"null".equals(sqltype)){
			//插入sql
			if("insert".equals(sqltype.toLowerCase())){
				builder.append("insert into ");
				if(tableName != null && !"".equals(tableName) && !"null".equals(tableName)){
					builder.append(tableName);
					if(fieldName != null && !"".equals(fieldName) && !"null".equals(fieldName)){
						builder.append(" (").append(fieldName).append(") ");
						if(fieldValue != null && !"".equals(fieldValue) && !"null".equals(fieldValue)){
							builder.append(" values (").append(fieldValue).append(") ");
						}else{
							return null;
						}
					}else{
						return null;
					}
				}else{
					return null;
				}
			}
			//更新sql
			else if("update".equals(sqltype.toLowerCase())){
				
			}
			//删除sql
			else if("delete".equals(sqltype.toLowerCase())){
				builder.append("delete from ");
				if(tableName != null && !"".equals(tableName) && !"null".equals(tableName)){
					builder.append(tableName);
					if(condition != null && !"".equals(condition) && !"null".equals(condition)){
						builder.append(" ").append(condition);
					}
				}else{
					return null;
				}
			}
			//查询sql
			else if("select".equals(sqltype.toLowerCase())){
				builder.append("select ");
				if(tableName != null && !"".equals(tableName) && !"null".equals(tableName)){
					if(fieldName != null && !"".equals(fieldName) && !"null".equals(fieldName)){
						builder.append(fieldName).append(" from ").append(tableName);
						if(condition != null && !"".equals(condition) && !"null".equals(condition)){
							builder.append(" ").append(condition);
						}
					}else{
						return null;
					}
				}else{
					return null;
				}
			}
			//查询 count sql
			else if("count".equals(sqltype.toLowerCase())){
				builder.append("select count(*) from ");
				if(tableName != null && !"".equals(tableName) && !"null".equals(tableName)){
					builder.append(tableName);
					if(condition != null && !"".equals(condition) && !"null".equals(condition)){
						builder.append(" ").append(condition);
					}
				}else{
					return null;
				}
			}
			//分页查询sql
			else if("page".equals(sqltype.toLowerCase())){
				int startNum = (pageNo - 1) * pageSize + 1;
				int endNum = startNum + pageSize;
				builder.append("select * from (select my_table.*, rownum as my_rownum from (");//分页头部
				
				builder.append("select ");
				if(tableName != null && !"".equals(tableName) && !"null".equals(tableName)){
					if(fieldName != null && !"".equals(fieldName) && !"null".equals(fieldName)){
						builder.append(fieldName).append(" from ").append(tableName);
						if(condition != null && !"".equals(condition) && !"null".equals(condition)){
							builder.append(" ").append(condition);
						}
					}else{
						return null;
					}
				}else{
					return null;
				}
				//分页尾部
				builder.append(" ) my_table) where my_rownum <" + endNum + " and my_rownum>=").append(startNum);
			}
		}
		return builder.toString();
	}
	
	/**
	 * 组合字符串, 动态表数据单条维护时使用
	 * @param String fieldName '1','2','3'……
	 * @param String fieldType '1','2','4','1'……
	 * @param Map dataMap 1-字符，2-数值，4-ID
	 * @param int num 索引号
	 * **/
	@SuppressWarnings("rawtypes")
	public String getFieldValue(String fieldName, String fieldType, Map dataMap){
		StringBuilder builder = new StringBuilder();
		if(fieldName != null && !"".equals(fieldName) && !"null".equals(fieldName)){
			String[] fieldArr = fieldName.split(",");
			String[] typeArr = fieldType.split(",");
			if(fieldArr != null && fieldArr.length > 0){
				for(int t=0;t<fieldArr.length;t++){
					if(!StringUtils.isBlank(typeArr[t])){
						String dataTemp = (String)dataMap.get(fieldArr[t].toUpperCase());
						//判断数据类型，拼接存入
						if("S".equals(typeArr[t])){
							builder.append("'").append(dataTemp).append("',");
						}else if("N".equals(typeArr[t])){
							builder.append(dataTemp).append(",");
						}else if("D".equals(typeArr[t])){
							builder.append("to_date('").append(dataTemp).append("','yyyy-mm-dd'),");
						}
					}else {
						builder.append("' ',");
					}
				}
				if(builder.length()>1){
					builder.deleteCharAt(builder.length()-1);
				}
			}
			return builder.toString();
		}else{
			return null;
		}
	}
	
}
