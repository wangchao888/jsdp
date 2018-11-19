/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.menu.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 */

public class SysRbacMenu {
	
	private java.lang.String sid;
	private java.lang.String zonecode;
	private java.lang.String appid;
	private java.lang.String menuname;
	private java.lang.String menuurl;
	private java.lang.String pid;
	private java.lang.String menuno;
	private java.lang.String menuico;
	private java.lang.String targetfrm;
	private java.lang.String permission;
	private java.lang.String state;
	private java.lang.String createby;
	private java.lang.String createtime;
	private java.lang.String updateby;
	private java.lang.String updatetime;
	private java.lang.String remark;
	private java.lang.String delflag;


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
	public void setAppid(java.lang.String value) {
		this.appid = value;
	}
	
	public java.lang.String getAppid() {
		return this.appid;
	}
	public void setMenuname(java.lang.String value) {
		this.menuname = value;
	}
	
	public java.lang.String getMenuname() {
		return this.menuname;
	}
	public void setMenuurl(java.lang.String value) {
		this.menuurl = value;
	}
	
	public java.lang.String getMenuurl() {
		return this.menuurl;
	}
	public void setPid(java.lang.String value) {
		this.pid = value;
	}
	
	public java.lang.String getPid() {
		return this.pid;
	}
	public void setMenuno(java.lang.String value) {
		this.menuno = value;
	}
	
	public java.lang.String getMenuno() {
		return this.menuno;
	}
	public void setMenuico(java.lang.String value) {
		this.menuico = value;
	}
	
	public java.lang.String getMenuico() {
		return this.menuico;
	}
	public void setTargetfrm(java.lang.String value) {
		this.targetfrm = value;
	}
	
	public java.lang.String getTargetfrm() {
		return this.targetfrm;
	}
	public void setPermission(java.lang.String value) {
		this.permission = value;
	}
	
	public java.lang.String getPermission() {
		return this.permission;
	}
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.lang.String getState() {
		return this.state;
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


	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Sid",getSid())
			.append("Zonecode",getZonecode())
			.append("Appid",getAppid())
			.append("Menuname",getMenuname())
			.append("Menuurl",getMenuurl())
			.append("Pid",getPid())
			.append("Menuno",getMenuno())
			.append("Menuico",getMenuico())
			.append("Targetfrm",getTargetfrm())
			.append("Permission",getPermission())
			.append("State",getState())
			.append("Createby",getCreateby())
			.append("Createtime",getCreatetime())
			.append("Updateby",getUpdateby())
			.append("Updatetime",getUpdatetime())
			.append("Remark",getRemark())
			.append("Delflag",getDelflag())
			.toString();
	}
}

