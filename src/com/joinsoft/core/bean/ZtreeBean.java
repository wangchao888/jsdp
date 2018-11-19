/**
 * 文   件  名   : ZtreeBean.java
 * 版          本   : 
 * 创建日期   : 2013-1-10
 *
 * Copyright © 1995-2013 JoinSoft Co.Ltd. All right reserved. 
 */

package com.joinsoft.core.bean;

public class ZtreeBean {
    private String id;                  /* 节点编码 */
    private String parentId;            /* 上级节点编码 */
    private String name;                /* 节点名称 */
    private String isParent = "false";  /* 是否为父节点 ,默认为false  */
    private String title;               /* 提示文字 */
    private String icon;                /*节点图标*/
    private String checked;             /*设置复选框是否可选 0：可选1：不可选*/
    private String treeLevel;           /* 判断当前节点为几级节点 */
    private String clickExpand;         /*设置当前节点是否可选 true：可选 false：不可选*/
    private String bldno;               /*自然幢编号*/
    private String catalog;             /*目录归类,可作为扩展属性*/
    
	public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBldno() {
		return bldno;
	}
	public void setBldno(String bldno) {
		this.bldno = bldno;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getClickExpand() {
        return clickExpand;
    }
    public void setClickExpand(String clickExpand) {
        this.clickExpand = clickExpand;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIsParent() {
        return isParent;
    }
    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }
    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTreeLevel() {
        return treeLevel;
    }
    public void setTreeLevel(String treeLevel) {
        this.treeLevel = treeLevel;
    }
    public String getCatalog() {
        return catalog;
    }
    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }
    
}
