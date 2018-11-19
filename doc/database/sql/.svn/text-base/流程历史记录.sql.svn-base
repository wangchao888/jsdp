
/* Drop Tables */

DROP TABLE wf_taskhistory CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE wf_taskhistory
(
	Id varchar2(32) NOT NULL UNIQUE,
	projectId varchar2(32),
	stepId varchar2(32),
	sortNo varchar2(2),
	orderNo number(6),
	-- 01_同意
	-- 02_不同意
	-- 03_打回
	State varchar2(2),
	operator varchar2(150),
	opinion varchar2(300),
	opinionTime varchar2(10),
	createTime varchar2(19),
	remark varchar2(300),
	PRIMARY KEY (Id)
);



/* Comments */

COMMENT ON COLUMN wf_taskhistory.State IS '01_同意
02_不同意
03_打回';



