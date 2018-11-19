/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.module.system.dictcontent.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * {功能简述}
 * 
 * @author 仝玉锐
 * @since 2015-07-20
 * @author LZX 2017-06-05 添加可序列化
 */

public class SysDictContent {
	private java.lang.String sid;
	private java.lang.String zonecode;
	private java.lang.String dictno;
	private java.lang.String dictlabel;
	private java.lang.String dictvalue;
	private java.lang.String sortno;
	private java.lang.String state;
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
	public void setZonecode(java.lang.String value) {
		this.zonecode = value;
	}
	
	public java.lang.String getZonecode() {
		return this.zonecode;
	}
	public void setDictno(java.lang.String value) {
		this.dictno = value;
	}
	
	public java.lang.String getDictno() {
		return this.dictno;
	}
	public void setDictlabel(java.lang.String value) {
		this.dictlabel = value;
	}
	
	public java.lang.String getDictlabel() {
		return this.dictlabel;
	}
	public void setDictvalue(java.lang.String value) {
		this.dictvalue = value;
	}
	
	public java.lang.String getDictvalue() {
		return this.dictvalue;
	}
	public void setSortno(java.lang.String value) {
		this.sortno = value;
	}
	
	public java.lang.String getSortno() {
		return this.sortno;
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
			.append("Zonecode",getZonecode())
			.append("Dictno",getDictno())
			.append("Dictlabel",getDictlabel())
			.append("Dictvalue",getDictvalue())
			.append("Sortno",getSortno())
			.append("State",getState())
			.append("Createby",getCreateby())
			.append("Createtime",getCreatetime())
			.append("Updateby",getUpdateby())
			.append("Updatetime",getUpdatetime())
			.append("Delflag",getDelflag())
			.append("Remark",getRemark())
			.toString();
	}
}

