/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.dyntable.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

public class Ddyntable{
	private java.lang.String id;
	private java.lang.String tableid;
	private java.lang.String tablename;
	private java.lang.String tablenamecn;
	private java.lang.String conditions;
	private java.lang.String ismodify;
	private java.lang.String remark;


	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setTableid(java.lang.String value) {
		this.tableid = value;
	}
	
	public java.lang.String getTableid() {
		return this.tableid;
	}
	public void setTablename(java.lang.String value) {
		this.tablename = value;
	}
	
	public java.lang.String getTablename() {
		return this.tablename;
	}
	public void setTablenamecn(java.lang.String value) {
		this.tablenamecn = value;
	}
	
	public java.lang.String getTablenamecn() {
		return this.tablenamecn;
	}
	public void setConditions(java.lang.String value) {
		this.conditions = value;
	}
	
	public java.lang.String getConditions() {
		return this.conditions;
	}
	public void setIsmodify(java.lang.String value) {
		this.ismodify = value;
	}
	
	public java.lang.String getIsmodify() {
		return this.ismodify;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}


	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Tableid",getTableid())
			.append("Tablename",getTablename())
			.append("Tablenamecn",getTablenamecn())
			.append("Conditions",getConditions())
			.append("Ismodify",getIsmodify())
			.append("Remark",getRemark())
			.toString();
	}
}

