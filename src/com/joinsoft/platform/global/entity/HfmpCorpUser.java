package com.joinsoft.platform.global.entity;

public class HfmpCorpUser extends CacheRbacUser {

	private static final long serialVersionUID = -8759430536013888536L;
	
	private String isadmin;         /*是否管理员，0-否，1-是*/
	private String corpNo;          /*企业编号*/
	private String corpType;        /*企业类型，01-开发，02-物业，03-施工，04-业委会 */
	private String corpName;        /*企业名称*/
	
	private String userid;          /*操作用户*/
	private String zonecode; 		/*行政代码*/
	private String state;			/*状态*/
	private String delflag;			/*删除标志*/
	private String updateby;		/*修改者 */
	private String updatetime;	   /*修改时间 */
	private String compno;          /*主管单位编号 */
	
	public String getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
	public String getCorpNo() {
		return corpNo;
	}
	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
	}
	public String getCorpType() {
		return corpType;
	}
	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getZonecode() {
		return zonecode;
	}
	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getCompno() {
		return compno;
	}
	public void setCompno(String compno) {
		this.compno = compno;
	}
	
	
}
