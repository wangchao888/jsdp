/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.userrole.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

public class SysUserRole{
	private java.lang.String sid;
	private java.lang.String userId;
	private java.lang.String roleId;
	private java.lang.String remark;


	public void setSid(java.lang.String value) {
		this.sid = value;
	}
	
	public java.lang.String getSid() {
		return this.sid;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	
	public java.lang.String getRoleId() {
		return this.roleId;
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
			.append("UserId",getUserId())
			.append("RoleId",getRoleId())
			.append("Remark",getRemark())
			.toString();
	}
}

