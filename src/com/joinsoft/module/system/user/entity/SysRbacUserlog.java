/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.user.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * {功能简述}
 * 
 * @author 李中新
 * @since 2018-05-02
 */

public class SysRbacUserlog{
	private java.lang.String sid;
	private java.lang.String userid;
	private java.lang.String logtype;
	private java.lang.String opertime;
	private java.lang.String operip;
	private java.lang.String opermac;
	private java.lang.String passwd;
	private java.lang.String result;
	private java.lang.String remark;


	public void setSid(java.lang.String value) {
		this.sid = value;
	}
	
	public java.lang.String getSid() {
		return this.sid;
	}
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}
	public void setLogtype(java.lang.String value) {
		this.logtype = value;
	}
	
	public java.lang.String getLogtype() {
		return this.logtype;
	}
	public void setOpertime(java.lang.String value) {
		this.opertime = value;
	}
	
	public java.lang.String getOpertime() {
		return this.opertime;
	}
	public void setOperip(java.lang.String value) {
		this.operip = value;
	}
	
	public java.lang.String getOperip() {
		return this.operip;
	}
	public void setOpermac(java.lang.String value) {
		this.opermac = value;
	}
	
	public java.lang.String getOpermac() {
		return this.opermac;
	}
	public void setPasswd(java.lang.String value) {
		this.passwd = value;
	}
	
	public java.lang.String getPasswd() {
		return this.passwd;
	}
	public void setResult(java.lang.String value) {
		this.result = value;
	}
	
	public java.lang.String getResult() {
		return this.result;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}


	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Sid",getSid())
			.append("Userid",getUserid())
			.append("Logtype",getLogtype())
			.append("Opertime",getOpertime())
			.append("Operip",getOperip())
			.append("Opermac",getOpermac())
			.append("Passwd",getPasswd())
			.append("Result",getResult())
			.append("Remark",getRemark())
			.toString();
	}
}

