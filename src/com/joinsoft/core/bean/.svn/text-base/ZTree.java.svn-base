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
 * Ztree 的实际转换类
 * %类功能的概括%。
 * @author MALIANG
 * @since  2014年8月19日
 */

public class ZTree {
	private String id;
	private String pid;
	private String name;
	private String other;
	private String remark;
	private boolean open=false;
	private boolean checked = false;
	@SuppressWarnings("unused")
	private String permissStr="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}	
	public boolean getOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Override
	public String toString() {
		return " {id:"  +"\""+ id +"\""+ ", pid:"  +"\""+ pid  +"\""+ ", name:" +"\""+ name+"\""
				+ ", other:"  +"\""+ other  +"\""+ ", remark:"  +"\""+ remark  +"\""+ ",open:"+open+",checked:"+checked+"}";
	}
	
	public String getPermissStr() {
		if("0".equals(pid)){
			return "  {id:'"+id+"',dataObject:{menuname:'"+name+"',userObject:{isGroup:true}},order:"+other+",isOpen:true}, ";
		}else{
			return "  {id:'"+id+"',pid:'"+pid+"',dataObject:{menuname:'"+name+"',userObject:{isGroup:true}},order:"+other+",isOpen:true}, ";
		}
	}
	
	public void setPermissStr(String permissStr) {
		this.permissStr = "  {id:'"+id+"',pid:'"+pid+"',dataObject:{menuname:'"+name+"',userObject:{isGroup:true}}}, ";
	}
	
	
	
	
	
	
}
