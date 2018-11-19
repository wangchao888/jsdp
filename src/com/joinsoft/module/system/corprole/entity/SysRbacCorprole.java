/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.corprole.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

public class SysRbacCorprole{
	private java.lang.String sid;
	private java.lang.String zonecode;
	private java.lang.String roleid;
	private java.lang.String corptype;
	private java.lang.String remark;
	private java.lang.String delflag;
	private java.lang.String orgno;


	public void setSid(java.lang.String value) {
		this.sid = value;
	}
	
	public java.lang.String getSid() {
		return this.sid;
	}
	public void setZonecode(java.lang.String value) {
		this.zonecode = value;
	}
	
	public java.lang.String getZonecode() {
		return this.zonecode;
	}
	public void setRoleid(java.lang.String value) {
		this.roleid = value;
	}
	
	public java.lang.String getRoleid() {
		return this.roleid;
	}
	public void setCorptype(java.lang.String value) {
		this.corptype = value;
	}
	
	public java.lang.String getCorptype() {
		return this.corptype;
	}
	public void setRemark(java.lang.String value) {
		this.remark = value;
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setDelflag(java.lang.String value) {
		this.delflag = value;
	}
	
	public java.lang.String getDelflag() {
		return this.delflag;
	}
	public java.lang.String getOrgno() {
		return orgno;
	}

	public void setOrgno(java.lang.String orgno) {
		this.orgno = orgno;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Sid",getSid())
			.append("Zonecode",getZonecode())
			.append("Roleid",getRoleid())
			.append("Corptype",getCorptype())
			.append("Remark",getRemark())
			.append("Delflag",getDelflag())
			.append("Orgno",getOrgno())
			.toString();
	}
}

