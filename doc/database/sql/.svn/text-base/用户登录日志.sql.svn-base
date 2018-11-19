

/* Create Tables */

-- 用户登录日志表
CREATE TABLE sys_rbac_userLog
(
	-- 主键
	sid varchar2(32) NOT NULL UNIQUE,
	-- 用户主键
	userId varchar2(32),
	-- 日志类别
	logType varchar2(1) NOT NULL,
	-- 操作时间
	operTime varchar2(19) NOT NULL,
	-- 登录IP
	operIp varchar2(30),
	-- 登录Mac地址
	operMac varchar2(30),
	-- 输入密码
	passwd varchar2(40),
	-- 操作结果
	result varchar2(1),
	-- 备注
	remark varchar2(300),
	PRIMARY KEY (sid)
);



/* Comments */

COMMENT ON TABLE sys_rbac_userLog IS '用户登录日志表';
COMMENT ON COLUMN sys_rbac_userLog.sid IS '主键';
COMMENT ON COLUMN sys_rbac_userLog.userId IS '用户主键';
COMMENT ON COLUMN sys_rbac_userLog.logType IS '日志类别';
COMMENT ON COLUMN sys_rbac_userLog.operTime IS '操作时间';
COMMENT ON COLUMN sys_rbac_userLog.operIp IS '登录IP';
COMMENT ON COLUMN sys_rbac_userLog.operMac IS '登录Mac地址';
COMMENT ON COLUMN sys_rbac_userLog.passwd IS '输入密码';
COMMENT ON COLUMN sys_rbac_userLog.result IS '操作结果';
COMMENT ON COLUMN sys_rbac_userLog.remark IS '备注';



