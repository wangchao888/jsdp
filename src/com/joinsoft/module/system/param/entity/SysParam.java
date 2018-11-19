/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.param.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 * @author LZX 2017-06-05 添加可序列化
 */

public class SysParam {
	private java.lang.String sid;
	private java.lang.String zonecode;
	private java.lang.String paramno;
	private java.lang.String paramdesc;
	private java.lang.String paramvalue;
	private java.lang.String ismodify;
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
	public void setParamno(java.lang.String value) {
		this.paramno = value;
	}
	
	public java.lang.String getParamno() {
		return this.paramno;
	}
	public void setParamdesc(java.lang.String value) {
		this.paramdesc = value;
	}
	
	public java.lang.String getParamdesc() {
		return this.paramdesc;
	}
	public void setParamvalue(java.lang.String value) {
		this.paramvalue = value;
	}
	
	public java.lang.String getParamvalue() {
		return this.paramvalue;
	}
	public void setIsmodify(java.lang.String value) {
		this.ismodify = value;
	}
	
	public java.lang.String getIsmodify() {
		return this.ismodify;
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
			.append("Paramno",getParamno())
			.append("Paramdesc",getParamdesc())
			.append("Paramvalue",getParamvalue())
			.append("Ismodify",getIsmodify())
			.append("Createby",getCreateby())
			.append("Createtime",getCreatetime())
			.append("Updateby",getUpdateby())
			.append("Updatetime",getUpdatetime())
			.append("Remark",getRemark())
			.append("Delflag",getDelflag())
			.toString();
	}
}

