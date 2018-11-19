/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.role.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 * @author LZX 2017-06-05 添加可序列化
 */

public class SysRbacRole implements java.io.Serializable{
	
	private static final long serialVersionUID = 5365195922872847412L;
	
	private java.lang.String sid;
	private java.lang.String rolename;
	private java.lang.String zonecode;
	private java.lang.String roledesc;
	private java.lang.String createBy;
	private java.lang.String createTime;
	private java.lang.String updateBy;
	private java.lang.String updateTime;
	private java.lang.String remark;
	private java.lang.String delFlag;
	private java.lang.String orgno;


	public void setSid(java.lang.String value) {
		this.sid = value;
	}
	
	public java.lang.String getSid() {
		return this.sid;
	}
	public void setRolename(java.lang.String value) {
		this.rolename = value;
	}
	
	public java.lang.String getRolename() {
		return this.rolename;
	}
	public void setZonecode(java.lang.String value) {
		this.zonecode = value;
	}
	
	public java.lang.String getZonecode() {
		return this.zonecode;
	}
	public void setRoledesc(java.lang.String value) {
		this.roledesc = value;
	}
	
	public java.lang.String getRoledesc() {
		return this.roledesc;
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
	public void setOrgno(java.lang.String value) {
		this.orgno = value;
	}
	
	public java.lang.String getOrgno() {
		return this.orgno;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Sid",getSid())
			.append("Rolename",getRolename())
			.append("Zonecode",getZonecode())
			.append("Roledesc",getRoledesc())
			.append("CreateBy",getCreateBy())
			.append("CreateTime",getCreateTime())
			.append("UpdateBy",getUpdateBy())
			.append("UpdateTime",getUpdateTime())
			.append("Remark",getRemark())
			.append("DelFlag",getDelFlag())
			.append("Orgno",getOrgno())
			.toString();
	}
}

