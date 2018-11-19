/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.application.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

public class SysRbacApplication {
	
	
	private java.lang.String sid;
	private java.lang.String appname;
	private java.lang.String appdesc;
	private java.lang.String appip;
	private java.lang.String appport;
	private java.lang.String domain;
	private java.lang.String webview;
	private java.lang.String createBy;
	private java.lang.String createTime;
	private java.lang.String updateBy;
	private java.lang.String updateTime;
	private java.lang.String remark;
	private java.lang.String delFlag;
	private String appflag; //0-否 1-是


	public void setSid(java.lang.String value) {
		this.sid = value;
	}
	
	public java.lang.String getSid() {
		return this.sid;
	}
	public void setAppname(java.lang.String value) {
		this.appname = value;
	}
	
	public java.lang.String getAppname() {
		return this.appname;
	}
	public void setAppdesc(java.lang.String value) {
		this.appdesc = value;
	}
	
	public java.lang.String getAppdesc() {
		return this.appdesc;
	}
	public void setAppip(java.lang.String value) {
		this.appip = value;
	}
	
	public java.lang.String getAppip() {
		return this.appip;
	}
	public void setAppport(java.lang.String value) {
		this.appport = value;
	}
	
	public java.lang.String getAppport() {
		return this.appport;
	}
	public void setDomain(java.lang.String value) {
		this.domain = value;
	}
	
	public java.lang.String getDomain() {
		return this.domain;
	}
	public void setWebview(java.lang.String value) {
		this.webview = value;
	}
	
	public java.lang.String getWebview() {
		return this.webview;
	}
	public void setCreateBy(java.lang.String value) {
		this.createBy = value;
	}
	
	public java.lang.String getCreateBy() {
		return this.createBy;
	}
	public void setCreateTime(java.lang.String value) {
		this.createTime = value;
	}
	
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	public void setUpdateBy(java.lang.String value) {
		this.updateBy = value;
	}
	
	public java.lang.String getUpdateBy() {
		return this.updateBy;
	}
	public void setUpdateTime(java.lang.String value) {
		this.updateTime = value;
	}
	
	public java.lang.String getUpdateTime() {
		return this.updateTime;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setDelFlag(java.lang.String value) {
		this.delFlag = value;
	}
	
	public java.lang.String getDelFlag() {
		return this.delFlag;
	}


	public String getAppflag() {
		return appflag;
	}

	public void setAppflag(String appflag) {
		this.appflag = appflag;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Sid",getSid())
			.append("Appname",getAppname())
			.append("Appdesc",getAppdesc())
			.append("Appip",getAppip())
			.append("Appport",getAppport())
			.append("Domain",getDomain())
			.append("Webview",getWebview())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Remark",getRemark())
			.append("DelFlag",getDelFlag())
			.toString();
	}
}

