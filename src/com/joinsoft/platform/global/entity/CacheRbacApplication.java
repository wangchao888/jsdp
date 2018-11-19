package com.joinsoft.platform.global.entity;

/**
 * 缓存专用实体
 *   注意修改本实体类一定要修改 @see com.joinsoft.module.system.application.dao.SysRbacApplicationMapper#queryListByCache(java.util.Map)
 * @author LZX 20170608 1356
 * 
 */
public class CacheRbacApplication implements java.io.Serializable{

	private static final long serialVersionUID = -8174606519397497334L;
	private java.lang.String sid;
	private java.lang.String appname;
	private java.lang.String appdesc;
	private java.lang.String appip;
	private java.lang.String appport;
	private java.lang.String domain;
	private java.lang.String webview;
	private String appflag;           /*是否核心应用标记*/
	
	public java.lang.String getSid() {
		return sid;
	}
	public void setSid(java.lang.String sid) {
		this.sid = sid;
	}
	public java.lang.String getAppname() {
		return appname;
	}
	public void setAppname(java.lang.String appname) {
		this.appname = appname;
	}
	public java.lang.String getAppdesc() {
		return appdesc;
	}
	public void setAppdesc(java.lang.String appdesc) {
		this.appdesc = appdesc;
	}
	public java.lang.String getAppip() {
		return appip;
	}
	public void setAppip(java.lang.String appip) {
		this.appip = appip;
	}
	public java.lang.String getAppport() {
		return appport;
	}
	public void setAppport(java.lang.String appport) {
		this.appport = appport;
	}
	public java.lang.String getDomain() {
		return domain;
	}
	public void setDomain(java.lang.String domain) {
		this.domain = domain;
	}
	public java.lang.String getWebview() {
		return webview;
	}
	public void setWebview(java.lang.String webview) {
		this.webview = webview;
	}
	public String getAppflag() {
		return appflag;
	}
	public void setAppflag(String appflag) {
		this.appflag = appflag;
	}
	
}
