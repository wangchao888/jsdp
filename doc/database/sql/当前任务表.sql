
/* Drop Tables */

DROP TABLE wf_taskList CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE wf_taskList
(
	Id varchar2(32) NOT NULL UNIQUE,
	projectId varchar2(40),
	stepId varchar2(32),
	sortno varchar2(2),
	PRIMARY KEY (Id)
);



