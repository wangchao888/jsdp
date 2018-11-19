

/* Create Tables */

-- 住宅建筑安装成本档案表
CREATE TABLE d_buildCost
(
	-- 主键
	id varchar2(32) NOT NULL UNIQUE,
	-- 行政代码
	zoneCode varchar2(6),
	-- 年度
	buildYear varchar2(4),
	-- 自然幢类型
	bldType varchar2(2),
	-- 交存条件串
	conLists varchar2(300),
	-- 建筑安装成本
	buildCost number(20,2),
	-- 建安成本对应标准
	buildStandard number(20,4),
	-- 备注
	remark varchar2(300),
	-- 删除标识
	delflag varchar2(1),
	PRIMARY KEY (id)
);


-- 资金交存条件
CREATE TABLE d_depCondition
(
	-- 主键
	id varchar2(32) NOT NULL UNIQUE,
	-- 行政代码
	zoneCode varchar2(6),
	-- 管理单位
	compNo varchar2(12),
	-- 条件项明细
	conListInfo varchar2(30),
	-- 条件描述
	desCription varchar2(30),
	-- 表名
	tableName varchar2(30),
	-- 表别名
	aliasName varchar2(30),
	-- 备注
	remark varchar2(300),
	-- 删除标识
	Delflag varchar2(1),
	PRIMARY KEY (id)
);


-- 交存楼层分配表
CREATE TABLE d_depfloorType
(
	-- id
	id varchar2(32) NOT NULL UNIQUE,
	-- 行政代码
	zoneCode varchar2(6),
	-- 管理单位
	compNo varchar2(12),
	-- 交存条件Id
	ExtId varchar2(32),
	-- 楼层条件串标示
	floorTypeNo varchar2(2),
	-- 描述信息
	floorDescribe varchar2(100),
	-- 起始层数
	startFloor number(3),
	-- 结束层数
	endFloor number(3),
	-- 备注
	remark varchar2(300),
	-- 删除标识
	delflag varchar2(1),
	PRIMARY KEY (id)
);


-- 交存模式表
CREATE TABLE d_depMode
(
	-- 主键
	id varchar2(32) NOT NULL UNIQUE,
	-- 行政代码
	zoneCode varchar2(6),
	-- 管理单位
	compNo varchar2(14),
	-- 交存模式
	depType varchar2(2),
	-- 交存模式描述
	depName varchar2(60),
	-- 计算依据描述
	description varchar2(60),
	-- 计算基础字段
	depField varchar2(30),
	-- 是否年度交存
	yearFlag varchar2(1),
	-- 条件项
	conList varchar2(300),
	-- 资金性质
	accAttr varchar2(2),
	-- 备注
	remark varchar2(300),
	-- 删除标识
	delflag varchar2(1),
	PRIMARY KEY (id)
);


-- 交存标准表
CREATE TABLE d_depStandard
(
	-- 编号
	id varchar2(32) NOT NULL UNIQUE,
	-- 行政代码
	zoneCode varchar2(6),
	-- 管理单位
	compNo varchar2(12),
	-- 交存模式
	depType varchar2(2),
	-- 条件串
	conLists varchar2(300),
	-- 交存标准
	depStandard number(20,4),
	-- 描述
	DESCRIPTION varchar2(300),
	-- 备注
	remark varchar2(300),
	-- 删除标识
	delflag varchar2(1),
	PRIMARY KEY (id)
);


-- 打印样式表
CREATE TABLE d_printStyle
(
	-- 主键
	id varchar2(32) NOT NULL UNIQUE,
	-- 行政代码
	zoneCode varchar2(6),
	-- 管理单位
	compNo varchar2(12),
	-- 交存类型
	depType varchar2(4),
	-- 通知样式
	desCribe clob,
	-- 备注
	remark varchar2(300),
	-- 删除标识
	delflag varchar2(1),
	PRIMARY KEY (id)
);


-- 利率配置表
CREATE TABLE d_rateConf
(
	-- 主键
	id varchar2(32) NOT NULL UNIQUE,
	-- 行政代码
	zoneCode varchar2(6),
	-- 管理单位
	compNo varchar2(12),
	-- 利息修改日期
	modifyRateDate varchar2(10),
	-- 活期利率
	rate number(6,4),
	-- 定期利率
	regRate number(6,4),
	-- 备注
	remark varchar2(300),
	-- 删除标识
	delflag varchar2(1),
	PRIMARY KEY (id)
);


-- 结息类别表
CREATE TABLE d_settleCode
(
	-- 主键
	id varchar2(32) NOT NULL UNIQUE,
	-- 行政代码
	zoneCode varchar2(6),
	-- 管理单位
	compNo varchar2(12),
	-- 结息类型
	settleCode varchar2(2),
	-- 类型含义
	settleName varchar2(60),
	-- 计息方法
	settleType varchar2(2),
	-- 是否分段计息
	subsectFlag varchar2(1),
	-- 利率来源
	rateSource varchar2(1),
	-- 支用支取后计息方法
	useSettle varchar2(1),
	-- 备注
	remark varchar2(300),
	-- 删除标识
	delflag varchar2(1),
	PRIMARY KEY (id)
);

-- 菜单权限表
CREATE TABLE SYS_ACTOR_MENU
(
	-- sId
	SID VARCHAR2(32) NOT NULL,
	-- 参与者类型
	ACTORTYPE VARCHAR2(15),
	-- 角色/用户编号
	ACTORID VARCHAR2(32),
	-- 菜单编号
	MENUID VARCHAR2(32),
	-- 备注
	REMARK VARCHAR2(300),
	PRIMARY KEY (SID)
);


-- 行政区划表
CREATE TABLE SYS_AREA
(
	-- 编码
	SID VARCHAR2(32) NOT NULL,
	-- 父级编号
	PID VARCHAR2(32),
	-- 行政代码
	ZONECODE VARCHAR2(6) NOT NULL CONSTRAINT zoneCode_UK UNIQUE zoneCode,
	-- 区域名称
	ZONENAME VARCHAR2(300) NOT NULL,
	-- 区域类型
	ZONETYPE VARCHAR2(4) NOT NULL,
	-- 创建者
	CREATE_BY VARCHAR2(150),
	-- 创建时间
	CREATE_TIME VARCHAR2(19),
	-- 修改者
	UPDATE_BY VARCHAR2(150),
	-- 修改时间
	UPDATE_TIME VARCHAR2(19),
	-- 备注
	REMARK VARCHAR2(300),
	-- 删除标志
	DEL_FLAG VARCHAR2(1) DEFAULT '0',
	PRIMARY KEY (SID)
);


-- 机构行政区数据权限关系表
CREATE TABLE SYS_ORG_AREA
(
	-- 编号
	SID VARCHAR2(32) NOT NULL,
	-- 组织机构编号
	ORGID VARCHAR2(32) NOT NULL,
	-- 行政区编号
	ZONECODE VARCHAR2(6) NOT NULL,
	-- 备注
	REMARK VARCHAR2(300),
	PRIMARY KEY (SID)
);


-- 应用表
CREATE TABLE SYS_RBAC_APPLICATION
(
	-- 编号
	SID VARCHAR2(32) NOT NULL,
	-- 应用名称
	APPNAME VARCHAR2(150),
	-- 应用说明
	APPDESC VARCHAR2(300),
	-- 应用IP
	APPIP VARCHAR2(150),
	-- 应用端口
	APPPORT VARCHAR2(10),
	-- 域名
	DOMAIN VARCHAR2(150),
	-- 访问视图
	WEBVIEW VARCHAR2(30),
	-- 创建者
	CREATEBY VARCHAR2(150),
	-- 创建时间
	CREATETIME VARCHAR2(19),
	-- 修改者
	UPDATEBY VARCHAR2(150),
	-- 修改时间
	UPDATETIME VARCHAR2(19),
	-- 备注
	REMARK VARCHAR2(300),
	-- 删除标志
	DELFLAG VARCHAR2(1) DEFAULT '0',
	PRIMARY KEY (SID)
);


-- 系统菜单表
CREATE TABLE SYS_RBAC_MENU
(
	-- sid
	SID VARCHAR2(32) NOT NULL,
	-- 行政代码
	ZONECODE VARCHAR2(6),
	-- 应用编号
	APPID VARCHAR2(32),
	-- 菜单名称
	MENUNAME VARCHAR2(150),
	-- 菜单链接
	MENUURL VARCHAR2(150),
	-- 上级菜单编号
	PID VARCHAR2(32),
	-- 菜单序号
	MENUNO VARCHAR2(2),
	-- 菜单图标
	MENUICO VARCHAR2(150),
	-- 目前框架
	TARGETFRM VARCHAR2(30),
	-- 权限标示
	PERMISSION VARCHAR2(180),
	-- 状态
	STATE VARCHAR2(1),
	-- 创建者
	CREATEBY VARCHAR2(150),
	-- 创建时间
	CREATETIME VARCHAR2(19),
	-- 修改者
	UPDATEBY VARCHAR2(150),
	-- 修改时间
	UPDATETIME VARCHAR2(19),
	-- 备注
	REMARK VARCHAR2(300),
	-- 删除标志
	DELFLAG VARCHAR2(1) DEFAULT '0',
	PRIMARY KEY (SID)
);


-- 组织机构表
CREATE TABLE SYS_RBAC_ORG
(
	-- 编号
	SID VARCHAR2(32) NOT NULL,
	-- 上级组织编号
	PID VARCHAR2(32),
	-- 组织名称
	ORGNAME VARCHAR2(300) NOT NULL,
	-- 机构标识
	ORGFLAG VARCHAR2(1) NOT NULL,
	-- 行政级别
	ORGLEVL VARCHAR2(4) NOT NULL,
	-- 行政代码
	ZONECODE VARCHAR2(6),
	-- 组织类型
	ORGTYPE VARCHAR2(4),
	-- 创建者
	CREATEBY VARCHAR2(150),
	-- 创建时间
	CREATETIME VARCHAR2(19),
	-- 修改者
	UPDATEBY VARCHAR2(150),
	-- 修改时间
	UPDATETIME VARCHAR2(19),
	-- 备注
	REMARK VARCHAR2(300),
	-- 删除标志
	DELFLAG VARCHAR2(1) DEFAULT '0',
	PRIMARY KEY (SID)
);


-- 角色表
CREATE TABLE SYS_RBAC_ROLE
(
	-- 编号
	SID VARCHAR2(32) NOT NULL,
	-- 行政代码
	ZONECODE VARCHAR2(6),
	-- 角色名称
	ROLENAME VARCHAR2(150),
	-- 角色说明
	ROLEDESC VARCHAR2(300),
	-- 创建者
	CREATEBY VARCHAR2(150),
	-- 创建时间
	CREATETIME VARCHAR2(19),
	-- 修改者
	UPDATEBY VARCHAR2(150),
	-- 修改时间
	UPDATETIME VARCHAR2(19),
	-- remark
	REMARK VARCHAR2(300),
	-- 删除标志
	DELFLAG VARCHAR2(1) DEFAULT '0',
	PRIMARY KEY (SID)
);


-- 系统用户表
CREATE TABLE SYS_RBAC_USER
(
	-- sId
	SID VARCHAR2(32) NOT NULL,
	-- 行政代码
	ZONECODE VARCHAR2(6) NOT NULL CONSTRAINT zoneCode_UK UNIQUE zoneCode,
	-- 用户类型
	USERTYPE VARCHAR2(2),
	-- 部门编号
	ORGID VARCHAR2(32),
	-- 工号
	LOGINNAME VARCHAR2(8),
	-- 姓名
	USERNAME VARCHAR2(150),
	-- 密码
	PASSWD VARCHAR2(40),
	-- 密码时限
	PASSWORDTIMELIMIT NUMBER(3,0),
	-- 密码最后修改时间
	PASSWORDMODIFYTIME VARCHAR2(19),
	-- 状态
	STATE VARCHAR2(1) DEFAULT '1',
	-- 创建者
	CREATEBY VARCHAR2(150),
	-- 创建时间
	CREATETIME VARCHAR2(19),
	-- 修改者
	UPDATEBY VARCHAR2(150),
	-- 修改时间
	UPDATETIME VARCHAR2(19),
	PRIMARY KEY (SID)
);


-- 用户角色表
CREATE TABLE SYS_USER_ROLE
(
	-- 编号
	SID VARCHAR2(32) NOT NULL,
	-- 用户编号
	USERID VARCHAR2(32),
	-- 角色编号
	ROLEID VARCHAR2(32),
	-- remark
	REMARK VARCHAR2(300),
	PRIMARY KEY (SID)
);



/* Comments */

COMMENT ON TABLE d_buildCost IS '住宅建筑安装成本档案表';
COMMENT ON COLUMN d_buildCost.id IS '主键';
COMMENT ON COLUMN d_buildCost.zoneCode IS '行政代码';
COMMENT ON COLUMN d_buildCost.buildYear IS '年度';
COMMENT ON COLUMN d_buildCost.bldType IS '自然幢类型';
COMMENT ON COLUMN d_buildCost.conLists IS '交存条件串';
COMMENT ON COLUMN d_buildCost.buildCost IS '建筑安装成本';
COMMENT ON COLUMN d_buildCost.buildStandard IS '建安成本对应标准';
COMMENT ON COLUMN d_buildCost.remark IS '备注';
COMMENT ON COLUMN d_buildCost.delflag IS '删除标识';
COMMENT ON TABLE d_depCondition IS '资金交存条件';
COMMENT ON COLUMN d_depCondition.id IS '主键';
COMMENT ON COLUMN d_depCondition.zoneCode IS '行政代码';
COMMENT ON COLUMN d_depCondition.compNo IS '管理单位';
COMMENT ON COLUMN d_depCondition.conListInfo IS '条件项明细';
COMMENT ON COLUMN d_depCondition.desCription IS '条件描述';
COMMENT ON COLUMN d_depCondition.tableName IS '表名';
COMMENT ON COLUMN d_depCondition.aliasName IS '表别名';
COMMENT ON COLUMN d_depCondition.remark IS '备注';
COMMENT ON COLUMN d_depCondition.Delflag IS '删除标识';
COMMENT ON TABLE d_depfloorType IS '交存楼层分配表';
COMMENT ON COLUMN d_depfloorType.id IS 'id';
COMMENT ON COLUMN d_depfloorType.zoneCode IS '行政代码';
COMMENT ON COLUMN d_depfloorType.compNo IS '管理单位';
COMMENT ON COLUMN d_depfloorType.ExtId IS '交存条件Id';
COMMENT ON COLUMN d_depfloorType.floorTypeNo IS '楼层条件串标示';
COMMENT ON COLUMN d_depfloorType.floorDescribe IS '描述信息';
COMMENT ON COLUMN d_depfloorType.startFloor IS '起始层数';
COMMENT ON COLUMN d_depfloorType.endFloor IS '结束层数';
COMMENT ON COLUMN d_depfloorType.remark IS '备注';
COMMENT ON COLUMN d_depfloorType.delflag IS '删除标识';
COMMENT ON TABLE d_depMode IS '交存模式表';
COMMENT ON COLUMN d_depMode.id IS '主键';
COMMENT ON COLUMN d_depMode.zoneCode IS '行政代码';
COMMENT ON COLUMN d_depMode.compNo IS '管理单位';
COMMENT ON COLUMN d_depMode.depType IS '交存模式';
COMMENT ON COLUMN d_depMode.depName IS '交存模式描述';
COMMENT ON COLUMN d_depMode.description IS '计算依据描述';
COMMENT ON COLUMN d_depMode.depField IS '计算基础字段';
COMMENT ON COLUMN d_depMode.yearFlag IS '是否年度交存';
COMMENT ON COLUMN d_depMode.conList IS '条件项';
COMMENT ON COLUMN d_depMode.accAttr IS '资金性质';
COMMENT ON COLUMN d_depMode.remark IS '备注';
COMMENT ON COLUMN d_depMode.delflag IS '删除标识';
COMMENT ON TABLE d_depStandard IS '交存标准表';
COMMENT ON COLUMN d_depStandard.id IS '编号';
COMMENT ON COLUMN d_depStandard.zoneCode IS '行政代码';
COMMENT ON COLUMN d_depStandard.compNo IS '管理单位';
COMMENT ON COLUMN d_depStandard.depType IS '交存模式';
COMMENT ON COLUMN d_depStandard.conLists IS '条件串';
COMMENT ON COLUMN d_depStandard.depStandard IS '交存标准';
COMMENT ON COLUMN d_depStandard.DESCRIPTION IS '描述';
COMMENT ON COLUMN d_depStandard.remark IS '备注';
COMMENT ON COLUMN d_depStandard.delflag IS '删除标识';
COMMENT ON TABLE d_printStyle IS '打印样式表';
COMMENT ON COLUMN d_printStyle.id IS '主键';
COMMENT ON COLUMN d_printStyle.zoneCode IS '行政代码';
COMMENT ON COLUMN d_printStyle.compNo IS '管理单位';
COMMENT ON COLUMN d_printStyle.depType IS '交存类型';
COMMENT ON COLUMN d_printStyle.desCribe IS '通知样式';
COMMENT ON COLUMN d_printStyle.remark IS '备注';
COMMENT ON COLUMN d_printStyle.delflag IS '删除标识';
COMMENT ON TABLE d_rateConf IS '利率配置表';
COMMENT ON COLUMN d_rateConf.id IS '主键';
COMMENT ON COLUMN d_rateConf.zoneCode IS '行政代码';
COMMENT ON COLUMN d_rateConf.compNo IS '管理单位';
COMMENT ON COLUMN d_rateConf.modifyRateDate IS '利息修改日期';
COMMENT ON COLUMN d_rateConf.rate IS '活期利率';
COMMENT ON COLUMN d_rateConf.regRate IS '定期利率';
COMMENT ON COLUMN d_rateConf.remark IS '备注';
COMMENT ON COLUMN d_rateConf.delflag IS '删除标识';
COMMENT ON TABLE d_settleCode IS '结息类别表';
COMMENT ON COLUMN d_settleCode.id IS '主键';
COMMENT ON COLUMN d_settleCode.zoneCode IS '行政代码';
COMMENT ON COLUMN d_settleCode.compNo IS '管理单位';
COMMENT ON COLUMN d_settleCode.settleCode IS '结息类型';
COMMENT ON COLUMN d_settleCode.settleName IS '类型含义';
COMMENT ON COLUMN d_settleCode.settleType IS '计息方法';
COMMENT ON COLUMN d_settleCode.subsectFlag IS '是否分段计息';
COMMENT ON COLUMN d_settleCode.rateSource IS '利率来源';
COMMENT ON COLUMN d_settleCode.useSettle IS '支用支取后计息方法';
COMMENT ON COLUMN d_settleCode.remark IS '备注';
COMMENT ON COLUMN d_settleCode.delflag IS '删除标识';
COMMENT ON TABLE SYS_ACTOR_MENU IS '菜单权限表';
COMMENT ON COLUMN SYS_ACTOR_MENU.SID IS 'sId';
COMMENT ON COLUMN SYS_ACTOR_MENU.ACTORTYPE IS '参与者类型';
COMMENT ON COLUMN SYS_ACTOR_MENU.ACTORID IS '角色/用户编号';
COMMENT ON COLUMN SYS_ACTOR_MENU.MENUID IS '菜单编号';
COMMENT ON COLUMN SYS_ACTOR_MENU.REMARK IS '备注';
COMMENT ON TABLE SYS_AREA IS '行政区划表';
COMMENT ON COLUMN SYS_AREA.SID IS '编码';
COMMENT ON COLUMN SYS_AREA.PID IS '父级编号';
COMMENT ON COLUMN SYS_AREA.ZONECODE IS '行政代码';
COMMENT ON COLUMN SYS_AREA.ZONENAME IS '区域名称';
COMMENT ON COLUMN SYS_AREA.ZONETYPE IS '区域类型';
COMMENT ON COLUMN SYS_AREA.CREATE_BY IS '创建者';
COMMENT ON COLUMN SYS_AREA.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN SYS_AREA.UPDATE_BY IS '修改者';
COMMENT ON COLUMN SYS_AREA.UPDATE_TIME IS '修改时间';
COMMENT ON COLUMN SYS_AREA.REMARK IS '备注';
COMMENT ON COLUMN SYS_AREA.DEL_FLAG IS '删除标志';
COMMENT ON TABLE SYS_ORG_AREA IS '机构行政区数据权限关系表';
COMMENT ON COLUMN SYS_ORG_AREA.SID IS '编号';
COMMENT ON COLUMN SYS_ORG_AREA.ORGID IS '组织机构编号';
COMMENT ON COLUMN SYS_ORG_AREA.AREAID IS '行政区编号';
COMMENT ON COLUMN SYS_ORG_AREA.REMARK IS '备注';
COMMENT ON TABLE SYS_RBAC_APPLICATION IS '应用表';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.SID IS '编号';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.APPNAME IS '应用名称';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.APPDESC IS '应用说明';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.APPIP IS '应用IP';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.APPPORT IS '应用端口';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.DOMAIN IS '域名';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.WEBVIEW IS '访问视图';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.CREATEBY IS '创建者';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.CREATETIME IS '创建时间';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.UPDATEBY IS '修改者';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.UPDATETIME IS '修改时间';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.REMARK IS '备注';
COMMENT ON COLUMN SYS_RBAC_APPLICATION.DELFLAG IS '删除标志';
COMMENT ON TABLE SYS_RBAC_MENU IS '系统菜单表';
COMMENT ON COLUMN SYS_RBAC_MENU.SID IS 'sid';
COMMENT ON COLUMN SYS_RBAC_MENU.ZONECODE IS '行政代码';
COMMENT ON COLUMN SYS_RBAC_MENU.APPID IS '应用编号';
COMMENT ON COLUMN SYS_RBAC_MENU.MENUNAME IS '菜单名称';
COMMENT ON COLUMN SYS_RBAC_MENU.MENUURL IS '菜单链接';
COMMENT ON COLUMN SYS_RBAC_MENU.PID IS '上级菜单编号';
COMMENT ON COLUMN SYS_RBAC_MENU.MENUNO IS '菜单序号';
COMMENT ON COLUMN SYS_RBAC_MENU.MENUICO IS '菜单图标';
COMMENT ON COLUMN SYS_RBAC_MENU.TARGETFRM IS '目前框架';
COMMENT ON COLUMN SYS_RBAC_MENU.PERMISSION IS '权限标示';
COMMENT ON COLUMN SYS_RBAC_MENU.STATE IS '状态';
COMMENT ON COLUMN SYS_RBAC_MENU.CREATEBY IS '创建者';
COMMENT ON COLUMN SYS_RBAC_MENU.CREATETIME IS '创建时间';
COMMENT ON COLUMN SYS_RBAC_MENU.UPDATEBY IS '修改者';
COMMENT ON COLUMN SYS_RBAC_MENU.UPDATETIME IS '修改时间';
COMMENT ON COLUMN SYS_RBAC_MENU.REMARK IS '备注';
COMMENT ON COLUMN SYS_RBAC_MENU.DELFLAG IS '删除标志';
COMMENT ON TABLE SYS_RBAC_ORG IS '组织机构表';
COMMENT ON COLUMN SYS_RBAC_ORG.SID IS '编号';
COMMENT ON COLUMN SYS_RBAC_ORG.PID IS '上级组织编号';
COMMENT ON COLUMN SYS_RBAC_ORG.ORGNAME IS '组织名称';
COMMENT ON COLUMN SYS_RBAC_ORG.ORGFLAG IS '机构标识';
COMMENT ON COLUMN SYS_RBAC_ORG.ORGLEVL IS '行政级别';
COMMENT ON COLUMN SYS_RBAC_ORG.ZONECODE IS '行政代码';
COMMENT ON COLUMN SYS_RBAC_ORG.ORGTYPE IS '组织类型';
COMMENT ON COLUMN SYS_RBAC_ORG.CREATEBY IS '创建者';
COMMENT ON COLUMN SYS_RBAC_ORG.CREATETIME IS '创建时间';
COMMENT ON COLUMN SYS_RBAC_ORG.UPDATEBY IS '修改者';
COMMENT ON COLUMN SYS_RBAC_ORG.UPDATETIME IS '修改时间';
COMMENT ON COLUMN SYS_RBAC_ORG.REMARK IS '备注';
COMMENT ON COLUMN SYS_RBAC_ORG.DELFLAG IS '删除标志';
COMMENT ON TABLE SYS_RBAC_ROLE IS '角色表';
COMMENT ON COLUMN SYS_RBAC_ROLE.SID IS '编号';
COMMENT ON COLUMN SYS_RBAC_ROLE.ZONECODE IS '行政代码';
COMMENT ON COLUMN SYS_RBAC_ROLE.ROLENAME IS '角色名称';
COMMENT ON COLUMN SYS_RBAC_ROLE.ROLEDESC IS '角色说明';
COMMENT ON COLUMN SYS_RBAC_ROLE.CREATEBY IS '创建者';
COMMENT ON COLUMN SYS_RBAC_ROLE.CREATETIME IS '创建时间';
COMMENT ON COLUMN SYS_RBAC_ROLE.UPDATEBY IS '修改者';
COMMENT ON COLUMN SYS_RBAC_ROLE.UPDATETIME IS '修改时间';
COMMENT ON COLUMN SYS_RBAC_ROLE.REMARK IS 'remark';
COMMENT ON COLUMN SYS_RBAC_ROLE.DELFLAG IS '删除标志';
COMMENT ON TABLE SYS_RBAC_USER IS '系统用户表';
COMMENT ON COLUMN SYS_RBAC_USER.SID IS 'sId';
COMMENT ON COLUMN SYS_RBAC_USER.ZONECODE IS '行政代码';
COMMENT ON COLUMN SYS_RBAC_USER.USERTYPE IS '用户类型';
COMMENT ON COLUMN SYS_RBAC_USER.ORGID IS '部门编号';
COMMENT ON COLUMN SYS_RBAC_USER.LOGINNAME IS '工号';
COMMENT ON COLUMN SYS_RBAC_USER.USERNAME IS '姓名';
COMMENT ON COLUMN SYS_RBAC_USER.PASSWD IS '密码';
COMMENT ON COLUMN SYS_RBAC_USER.PASSWORDTIMELIMIT IS '密码时限';
COMMENT ON COLUMN SYS_RBAC_USER.PASSWORDMODIFYTIME IS '密码最后修改时间';
COMMENT ON COLUMN SYS_RBAC_USER.STATE IS '状态';
COMMENT ON COLUMN SYS_RBAC_USER.CREATEBY IS '创建者';
COMMENT ON COLUMN SYS_RBAC_USER.CREATETIME IS '创建时间';
COMMENT ON COLUMN SYS_RBAC_USER.UPDATEBY IS '修改者';
COMMENT ON COLUMN SYS_RBAC_USER.UPDATETIME IS '修改时间';
COMMENT ON TABLE SYS_USER_ROLE IS '用户角色表';
COMMENT ON COLUMN SYS_USER_ROLE.SID IS '编号';
COMMENT ON COLUMN SYS_USER_ROLE.USERID IS '用户编号';
COMMENT ON COLUMN SYS_USER_ROLE.ROLEID IS '角色编号';
COMMENT ON COLUMN SYS_USER_ROLE.REMARK IS 'remark';



