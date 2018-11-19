package com.joinsoft.platform.global.entity;

public class CacheArea implements java.io.Serializable{

	private static final long serialVersionUID = 2156555599732690491L;
	
	private String sid;
	private String pid;
	private String zonecode;
	private String zonename;
	/**主键**/
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**上级主键**/
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**行政区代码**/
	public String getZonecode() {
		return zonecode;
	}
	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
	}
	/**行政区名称**/
	public String getZonename() {
		return zonename;
	}
	public void setZonename(String zonename) {
		this.zonename = zonename;
	}
	
	

}
