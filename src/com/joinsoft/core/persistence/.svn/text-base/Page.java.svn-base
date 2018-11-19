/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名        ： Page.java
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/
package com.joinsoft.core.persistence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 分页类。
 * 
 * @author wkd
 * @since 2014年8月14日
 */

public class Page {
	private int showCount = 10; // 每页显示记录数
	private int totalPage; // 总页数
	private int totalResult; // 总记录数
	private int currentPage; // 当前页
	private int currentResult; // 当前记录起始索引
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	private String where;
	private String orderBy;
	private Map paramMap = new HashMap();
	
	//datatable分页 使用参数
//	private int start;  //第一条数据的起始位置，比如0代表第一条数据
//	private int length; //服务器每页显示的条数，-1代表需要返回全部数据
	private int draw;
	
	public Map getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map paramMap) {
		this.paramMap = paramMap;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getTotalPage() {
		if (totalResult % showCount == 0)
			totalPage = totalResult / showCount;
		else
			totalPage = totalResult / showCount + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if (currentPage <= 0)
			currentPage = 1;
		if (currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {	
			sb.append("<div class='row'>");
			//第一块
			sb.append("<div class='col-sm-3'>");
			sb.append("<span style='padding-left: 0px; float: left'>数据共<iclass='blue'>&nbsp;"+totalResult+"&nbsp;</i>条，&nbsp;");
			sb.append("&nbsp;当前第<i class='blue'>&nbsp;"+this.getCurrentPage()+"&nbsp;</i>页</span>");
			sb.append("</div>");
			//第二块
			sb.append("<div class='col-sm-3'>");
			sb.append("每页<select onchange=\"Change()\" class=\"form-control m-b\" id=\"showCount\"name=\"showCount\" style=\"width:70px; display:inline-block\">");			
			//每页显示条数
			if(showCount == 10){
				sb.append("<option selected = \"selected\" value=\"10\">10</option>");
			}else{
				sb.append("<option value=\"10\">10</option>");
			}
			if(showCount == 25){
				sb.append("<option selected = \"selected\" value=\"25\">25</option>");
			}else{
				sb.append("<option value=\"25\">25</option>");
			}
			if(showCount == 50){
				sb.append("<option selected = \"selected\" value=\"50\">50</option>");
			}else{
				sb.append("<option value=\"50\">50</option>");
			}
			if(showCount == 100){
				sb.append("<option selected = \"selected\" value=\"100\">100</option>");
			}else{
				sb.append("<option value=\"100\">100</option>");
			}
			sb.append("</select>条");
			sb.append("</div>");
			//第三块
			sb.append("<div class='col-sm-6'>");
			sb.append("<div class='dataTables_filter fr'><div class='text-center'><div class='btn-group'>");
			if(this.getCurrentPage() == 1){					
				sb.append("<button onclick='nextPage("+(this.getCurrentPage()-1)+");' class='btn btn-white' disabled=\"false\">上一页</button>");
			}else{
				sb.append("<button onclick='nextPage("+(this.getCurrentPage()-1)+");' class='btn btn-white'>上一页</button>");
			}				
			for (int i = 1; i < totalPage+1; i++) {
				sb.append("<button onclick='nextPage("+i+");'class='btn btn-white'>"+i+"</button>");
			}
			if(this.getCurrentPage() == totalPage){
				sb.append("<button onclick='nextPage("+(this.getCurrentPage()+1)+");' class='btn btn-white' disabled=\"false\">下一页</button>");
			}else{
				sb.append("<button onclick='nextPage("+(this.getCurrentPage()+1)+");' class='btn btn-white'>下一页</button>");
			}
			sb.append("</div></div></div>\n");
			sb.append("</div>");
			sb.append("</div>");
			
			
			
			sb.append("<script type='text/javascript'>\n");	
			sb.append("function Change(){nextPage();}");
			sb.append("function nextPage(page){\n");
			
			sb.append("     if(page){\n");
			sb.append("     if(isNaN(page)){\n");
			sb.append("     page = "+currentPage+";\n");
			sb.append("     }else{\n");
			sb.append("     page = parseInt(page)\n");
			sb.append("     }\n");
			sb.append("     }else{\n");
			sb.append("      page = "+currentPage+";\n");
			sb.append("      }\n");
			sb.append("		var url = document.location+'';\n");
	        sb.append("		url = url.replace(\"#\",\"\");\n");
	        sb.append("		url = url.replace(\"@\",\"\");\n");
	        sb.append("		if(url.indexOf('?')>-1){\n");
	        sb.append("		url = url.substring(0,url.indexOf('?'));\n");
	        sb.append("		}\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('currentPage')>-1){\n");
			sb.append("				var reg = /currentPage=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'currentPage=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&" + (entityOrField ? "currentPage" : "currentPage") + "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?" + (entityOrField ? "currentPage" : "currentPage") + "=\";}\n");
	        sb.append("		var $form_att = $(\":input\").not(\"input[type=checkbox]\").not(\"input[type=radio]\").not(\".signOwner\")\n");
	        sb.append("		var val=\"\";\n");
	      //判断每页显示条数
	        sb.append("		var shownumpage = $.trim(document.getElementById('showCount').value);" );	        
	        sb.append("		$form_att.each(function(index,name){\n");
	        sb.append("		var nameval = name.name;\n");
	        sb.append("		var valval  = name.value;\n");
	      //判断每页显示条数
	        sb.append("		if(nameval=='showCount'){\n");
	        sb.append("		if(isNaN(shownumpage)||shownumpage == ''){\n");
	        sb.append("		valval = "+showCount+";}}\n");
	        //去掉为空的input
			sb.append("if(valval != \"\"){\n");
			sb.append("val +=\"&\"+nameval+\"=\"+valval;}});\n");
			sb.append(" window.location = url + page + encodeURI(val);\n");
			sb.append(" }\n");
			sb.append("</script>\n");
		
			
			
			
			
			/*之前的分页代码	*/			
//			sb.append("<div class='message'><span style='padding-left:0px;float:left'>数据共");
//			sb.append("<i class='blue'>&nbsp;"+totalResult+"&nbsp;</i>条，&nbsp;当前第<i class='blue'>&nbsp;"+currentPage+"&nbsp;</i>页</span>\n</div>\n<div style='float:right'>");
//			sb.append("<ul class='paginList'>\n");
//			sb.append("<li class='paginItem'>\n");
//			if (currentPage == 1) {
//				sb.append("	<a style='text-valign:center;width:40px'>首页</a>\n");
//				sb.append("	<a style='width:40px'>上页</a>\n");
//				sb.append("</li>");
//			} else {
//				sb.append("	<a  style='width:40px' href=\"javascript:nextPage(1)\">首页</a>\n");
//				sb.append("	<a  style='width:40px' href=\"javascript:nextPage(" + (currentPage - 1) + ")\">上页</a>\n");
//				sb.append("</li>");
//			}
//			//显示中间几页信息
//			int showTag = 5; // 分页标签显示数量
//			int startTag = 1;
//			if (currentPage > showTag) {
//				startTag = currentPage - 1;
//			}
//			int endTag = startTag + showTag - 1;
//
//			sb.append("<li class='paginItem'>");
//			
//			
//			if (currentPage == totalPage) {
//				sb.append("	<a  style='width:40px'>下页</a>\n");
//				sb.append("	<a  style='width:40px'>尾页</a>\n");
//			} else {
//				sb.append("	<a style='width:40px' href=\"javascript:nextPage(" + (currentPage + 1) + ")\">下页</a>\n");
//				sb.append("	<a style='width:40px'  href=\"javascript:nextPage(" + totalPage + ")\">尾页</a>\n");
//			}
//			
//			sb.append(" <input type='hidden' name='thisPage' value='"+currentPage+"'/>");
//			sb.append(" <input type='hidden' name='totalPage' value='"+totalPage+"'/>");
//			//add by zhangyq
//			sb.append(" <input type='hidden' name='totalResult' value='"+totalResult+"'/>");
//			
//			sb.append("</li>");
//			sb.append("<li class='paginItem'>");
//			sb.append("每页&nbsp;<input id='showCount' name='showCount' onblur=\"nextPage($(1).val())\" type=\"text\" value="+showCount+"  style=\"border:1px solid #dddddd; width:30px;height:25px;line-height:25px;text-align:center\" />");
//			sb.append("	条&nbsp;共"+totalPage+"页&nbsp;转到\n");
//			sb.append("<input id='pagenum' name='pagenum'type=\"text\" style=\"width:30px;height:25px;line-height:25px;text-align:center;border:1px solid #dddddd; \" />");
//			sb.append("&nbsp;&nbsp;<input class=\"btn_fy\"  onclick=\"nextPage($('#pagenum').val())\" type=\"button\" name=\"button\" id=\"button\" value=\"GO\"  style='color:#3399d5;background:#FFF;width:30px;height:25px;border:1px solid #dddddd; text-align:center;line-height:25px;' />");
//			sb.append(" </li>\n");
//			sb.append(" </ul>\n");
//			sb.append("</div>\n");
//			
//			
//			sb.append("<script type=\"text/javascript\">\n");
//			sb.append("function nextPage(page){");
//			
//			sb.append("     if(page){");
//			sb.append("     if(isNaN(page)){");
//			sb.append("     page = "+currentPage+";");
//			sb.append("     }else{");
//			sb.append("     page = parseInt(page)");
//			sb.append("     }");
//			sb.append("     }else{");
//			sb.append("      page = "+currentPage+";");
//			sb.append("      }");
////			
//			
//			sb.append("		var url = document.location+'';\n");
//	        sb.append("		url = url.replace(\"#\",\"\");\n");
//	        sb.append("		url = url.replace(\"@\",\"\");\n");
//	        
//	        
//	        sb.append("		if(url.indexOf('?')>-1){\n");
//	        sb.append("		url = url.substring(0,url.indexOf('?'));\n");
//	        sb.append("		}\n");
//			sb.append("		if(url.indexOf('?')>-1){\n");
//			sb.append("			if(url.indexOf('currentPage')>-1){\n");
//			sb.append("				var reg = /currentPage=\\d*/g;\n");
//			sb.append("				url = url.replace(reg,'currentPage=');\n");
//			sb.append("			}else{\n");
//			sb.append("				url += \"&" + (entityOrField ? "currentPage" : "currentPage") + "=\";\n");
//			sb.append("			}\n");
//			sb.append("		}else{url += \"?" + (entityOrField ? "currentPage" : "currentPage") + "=\";}\n");
//	        sb.append("		var $form_att = $(\":input\").not(\"input[type=checkbox]\").not(\"input[type=radio]\").not(\".signOwner\")\n");
//	        sb.append("		var val=\"\";\n");
//	        		
//			//判断每页显示条数
//	        sb.append("		var shownumpage = $.trim(document.getElementById('showCount').value);" );
//	        
//	        sb.append("		$form_att.each(function(index,name){\n");
//	        sb.append("		var nameval = name.name;\n");
//	        sb.append("		var valval  = name.value;\n");
//	      //判断每页显示条数
//	        sb.append("		if(nameval=='showCount'){");
//	        sb.append("		if(isNaN(shownumpage)||shownumpage == ''){");
//	        sb.append("		valval = "+showCount+";}}");
//	        
//			
//			
//			sb.append("     val +=\"&\"+nameval+\"=\"+valval;\n");
//			sb.append("     })\n ");
//			sb.append("     document.location = url + page + encodeURI(val);\n");
//			
//			sb.append(" }\n");
//			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}
	
}