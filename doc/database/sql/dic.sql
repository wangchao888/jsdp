

/* Create Tables */

-- 字典内容
CREATE TABLE sys_dict_content
(
	-- sid
	sid varchar2(32) NOT NULL UNIQUE,
	-- zoneCode
	zoneCode varchar2(6),
	-- dictNo
	dictNo varchar2(2),
	-- dictLabel
	dictLabel varchar2(4),
	-- dictValue
	dictValue varchar2(90),
	-- sortNo
	sortNo varchar2(2),
	-- state
	state varchar2(1),
	-- createBy
	createBy varchar2(150),
	-- createTime
	createTime varchar2(19),
	-- updateBy
	updateBy varchar2(150),
	-- updateTime
	updateTime varchar2(19),
	-- delFlag
	delFlag varchar2(1),
	-- remark
	remark varchar2(300),
	PRIMARY KEY (sid)
);


-- 字典类别
CREATE TABLE sys_dic_type
(
	-- sid
	sid varchar2(32) NOT NULL UNIQUE,
	-- dictNo
	dictNo varchar2(2),
	-- dictName
	dictName varchar2(90),
	-- dictDesc
	dictDesc varchar2(300),
	-- createBy
	createBy varchar2(150),
	-- createTime
	createTime varchar2(19),
	-- updateBy
	updateBy varchar2(150),
	-- updateTime
	updateTime varchar2(19),
	-- remark
	remark varchar2(300),
	-- delFlag
	delFlag varchar2(1),
	PRIMARY KEY (sid)
);



/* Comments */

COMMENT ON TABLE new_table IS '字典内容';
COMMENT ON COLUMN new_table.sid IS 'sid';
COMMENT ON COLUMN new_table.zoneCode IS 'zoneCode';
COMMENT ON COLUMN new_table.dictNo IS 'dictNo';
COMMENT ON COLUMN new_table.dictLabel IS 'dictLabel';
COMMENT ON COLUMN new_table.dictValue IS 'dictValue';
COMMENT ON COLUMN new_table.sortNo IS 'sortNo';
COMMENT ON COLUMN new_table.state IS 'state';
COMMENT ON COLUMN new_table.createBy IS 'createBy';
COMMENT ON COLUMN new_table.createTime IS 'createTime';
COMMENT ON COLUMN new_table.updateBy IS 'updateBy';
COMMENT ON COLUMN new_table.updateTime IS 'updateTime';
COMMENT ON COLUMN new_table.delFlag IS 'delFlag';
COMMENT ON COLUMN new_table.remark IS 'remark';
COMMENT ON TABLE sys_dic_type IS '字典类别';
COMMENT ON COLUMN sys_dic_type.sid IS 'sid';
COMMENT ON COLUMN sys_dic_type.dictNo IS 'dictNo';
COMMENT ON COLUMN sys_dic_type.dictName IS 'dictName';
COMMENT ON COLUMN sys_dic_type.dictDesc IS 'dictDesc';
COMMENT ON COLUMN sys_dic_type.createBy IS 'createBy';
COMMENT ON COLUMN sys_dic_type.createTime IS 'createTime';
COMMENT ON COLUMN sys_dic_type.updateBy IS 'updateBy';
COMMENT ON COLUMN sys_dic_type.updateTime IS 'updateTime';
COMMENT ON COLUMN sys_dic_type.remark IS 'remark';
COMMENT ON COLUMN sys_dic_type.delFlag IS 'delFlag';



