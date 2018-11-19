
/* Drop Tables */

DROP TABLE wf_flowStep CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE wf_flowStep
(
	Id varchar2(32) NOT NULL UNIQUE,
	sortNo varchar2(2),
	stepDesc varchar2(150),
	flowId varchar2(32),
	-- 0-否
	-- 1-是
	IsLimFlag varchar2(1),
	-- 当前流程节点需要进行判断的多少个工作日期限
	limDateNum varchar2(3),
	-- 角色sId
	-- Sys_role.sId
	roleNo varchar2(32),
	-- 01_抢占
	-- 02_会签
	execMode varchar2(2),
	-- Sys_user. loginName
	-- 多人同时参与的话，
	-- 中间以“~”线分割。
	exexutor varchar2(300),
	Remark varchar2(300),
	PRIMARY KEY (Id)
);



/* Comments */

COMMENT ON COLUMN wf_flowStep.IsLimFlag IS '0-否
1-是';
COMMENT ON COLUMN wf_flowStep.limDateNum IS '当前流程节点需要进行判断的多少个工作日期限';
COMMENT ON COLUMN wf_flowStep.roleNo IS '角色sId
Sys_role.sId';
COMMENT ON COLUMN wf_flowStep.execMode IS '01_抢占
02_会签';
COMMENT ON COLUMN wf_flowStep.exexutor IS 'Sys_user. loginName
多人同时参与的话，
中间以“~”线分割。';



