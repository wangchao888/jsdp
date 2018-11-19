/*******************************************************************************
 *    系统名称   ： 物业维修资金管理系统
 *    开发部门   ： 房产事业部
 *    文件名     ： 
 *              (C) Copyright shandong joinsoft Corporation 2014
 *               All Rights Reserved.
 * *****************************************************************************
 *    注意： 本内容仅限于山东嘉友软件有限公司内部使用，禁止转发
 ******************************************************************************/


package com.joinsoft.core.bean;
/**
 * 
 * %session类%。
 * @author 
 * @since  2014-9-5
 */
public class UserBean {
	private String userid;                /*用户编号*/
	private String username;              /*个人用户登录名*/
	private String loginname;             /*工号*/
	private String orgid;                 /*所属部门id*/
	private String roleid;
	private String rolename;
	private String corpno;
	private String corptype;
	private String sessionId;
	// 2018-03-22 添加
	private String orgname;    /*机构名称*/
	private String usertype;   /*用户类型*/
	
	
    public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getCorptype() {
        return corptype;
    }
    public void setCorptype(String corptype) {
        this.corptype = corptype;
    }
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getCorpno() {
		return corpno;
	}
	public void setCorpno(String corpno) {
		this.corpno = corpno;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
}
