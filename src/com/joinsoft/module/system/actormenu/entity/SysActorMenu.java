/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.actormenu.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

public class SysActorMenu{
	private java.lang.String sid;
	private java.lang.String actortype;
	private java.lang.String actorid;
	private java.lang.String menuid;
	private java.lang.String remark;


	public void setSid(java.lang.String value) {
		this.sid = value;
	}
	
	public java.lang.String getSid() {
		return this.sid;
	}
	public void setActortype(java.lang.String value) {
		this.actortype = value;
	}
	
	public java.lang.String getActortype() {
		return this.actortype;
	}
	public void setActorid(java.lang.String value) {
		this.actorid = value;
	}
	
	public java.lang.String getActorid() {
		return this.actorid;
	}
	public void setMenuid(java.lang.String value) {
		this.menuid = value;
	}
	
	public java.lang.String getMenuid() {
		return this.menuid;
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
			.append("Actortype",getActortype())
			.append("Actorid",getActorid())
			.append("Menuid",getMenuid())
			.append("Remark",getRemark())
			.toString();
	}
}

