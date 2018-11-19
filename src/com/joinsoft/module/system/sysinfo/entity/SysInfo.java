/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2017
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.sysinfo.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * 系统信息
 * @author scy
 * @since  2018年3月27日
 */
public class SysInfo{
	private java.lang.String sid;
	private java.lang.String sysname;
	private java.lang.String sysico;
	private java.lang.String icopath;
	private java.lang.String testflag;
	private java.lang.String createby;
	private java.lang.String createtime;
	private java.lang.String updateby;
	private java.lang.String updatetime;
	private java.lang.String delflag;
	private java.lang.String remark;


	public void setSid(java.lang.String value) {
		this.sid = value;
	}
	
	public java.lang.String getSid() {
		return this.sid;
	}
	public void setSysname(java.lang.String value) {
		this.sysname = value;
	}
	
	public java.lang.String getSysname() {
		return this.sysname;
	}
	public void setSysico(java.lang.String value) {
		this.sysico = value;
	}
	
	public java.lang.String getSysico() {
		return this.sysico;
	}
	public void setIcopath(java.lang.String value) {
		this.icopath = value;
	}
	
	public java.lang.String getIcopath() {
		return this.icopath;
	}
	public void setTestflag(java.lang.String value) {
		this.testflag = value;
	}
	
	public java.lang.String getTestflag() {
		return this.testflag;
	}
	public void setCreateby(java.lang.String value) {
		this.createby = value;
	}
	
	public java.lang.String getCreateby() {
		return this.createby;
	}
	public void setCreatetime(java.lang.String value) {
		this.createtime = value;
	}
	
	public java.lang.String getCreatetime() {
		return this.createtime;
	}
	public void setUpdateby(java.lang.String value) {
		this.updateby = value;
	}
	
	public java.lang.String getUpdateby() {
		return this.updateby;
	}
	public void setUpdatetime(java.lang.String value) {
		this.updatetime = value;
	}
	
	public java.lang.String getUpdatetime() {
		return this.updatetime;
	}
	public void setDelflag(java.lang.String value) {
		this.delflag = value;
	}
	
	public java.lang.String getDelflag() {
		return this.delflag;
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
			.append("Sysname",getSysname())
			.append("Sysico",getSysico())
			.append("Icopath",getIcopath())
			.append("Testflag",getTestflag())
			.append("Createby",getCreateby())
			.append("Createtime",getCreatetime())
			.append("Updateby",getUpdateby())
			.append("Updatetime",getUpdatetime())
			.append("Delflag",getDelflag())
			.append("Remark",getRemark())
			.toString();
	}
}

