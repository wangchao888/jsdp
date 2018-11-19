package com.joinsoft.platform.global.entity;

import java.util.List;

public class CacheRbacOrg implements java.io.Serializable{
	private static final long serialVersionUID = 8017941450989290672L;
	private java.lang.String sid;
	private java.lang.String pid;
	private java.lang.String orgname;
	private java.lang.String zonecode;
	private java.lang.String orglevl;   /*行政级别*/
	private java.lang.String orgtype;   /*组织类型*/
	private java.lang.String orgflag;
	private List<CacheOrgArea> areaList;
	
	// 20180316 add
	private java.lang.String orgNo;    /*主管单位编号、企业编号*/ 
	private java.lang.String corpType; /*外部机构的企业类型*/ 
	private java.lang.String rootOrgname; /*主管单位名称*/
	private java.lang.String rootOrgid; /*主管单位主键*/
	
	// 20180330 add 银行用户
	private String corpno; //企业编号
	
	public java.lang.String getRootOrgid() {
		return rootOrgid;
	}
	public void setRootOrgid(java.lang.String rootOrgid) {
		this.rootOrgid = rootOrgid;
	}
	public java.lang.String getSid() {
		return sid;
	}
	public void setSid(java.lang.String sid) {
		this.sid = sid;
	}
	public java.lang.String getPid() {
		return pid;
	}
	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}
	public java.lang.String getOrgname() {
		return orgname;
	}
	public void setOrgname(java.lang.String orgname) {
		this.orgname = orgname;
	}
	public java.lang.String getZonecode() {
		return zonecode;
	}
	public void setZonecode(java.lang.String zonecode) {
		this.zonecode = zonecode;
	}
	public java.lang.String getOrglevl() {
		return orglevl;
	}
	public void setOrglevl(java.lang.String orglevl) {
		this.orglevl = orglevl;
	}
	public java.lang.String getOrgtype() {
		return orgtype;
	}
	public void setOrgtype(java.lang.String orgtype) {
		this.orgtype = orgtype;
	}
	public java.lang.String getOrgflag() {
		return orgflag;
	}
	public void setOrgflag(java.lang.String orgflag) {
		this.orgflag = orgflag;
	}
	public List<CacheOrgArea> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<CacheOrgArea> areaList) {
		this.areaList = areaList;
	}
	public java.lang.String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(java.lang.String orgNo) {
		this.orgNo = orgNo;
	}
	public java.lang.String getCorpType() {
		return corpType;
	}
	public void setCorpType(java.lang.String corpType) {
		this.corpType = corpType;
	}
	public java.lang.String getRootOrgname() {
		return rootOrgname;
	}
	public void setRootOrgname(java.lang.String rootOrgname) {
		this.rootOrgname = rootOrgname;
	}
	public String getCorpno() {
		return corpno;
	}
	public void setCorpno(String corpno) {
		this.corpno = corpno;
	}
	
}
