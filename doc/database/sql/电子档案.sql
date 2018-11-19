

/* Create Tables */

-- test_fsclient_filesummary
CREATE TABLE test_fsclient_filesummary
(
	-- id
	id varchar2(32) NOT NULL UNIQUE,
	-- 文件名称
	name varchar2(100),
	-- 路径
	path varchar2(100),
	-- remoteFsId
	remoteFsId varchar2(300),
	-- 后缀
	suffix varchar2(20),
	-- 上传时间
	uploadTime varchar2(20),
	-- 上传用户
	uploadUserId varchar2(100),
	PRIMARY KEY (id)
);



/* Comments */

COMMENT ON TABLE test_fsclient_filesummary IS 'test_fsclient_filesummary';
COMMENT ON COLUMN test_fsclient_filesummary.id IS 'id';
COMMENT ON COLUMN test_fsclient_filesummary.name IS '文件名称';
COMMENT ON COLUMN test_fsclient_filesummary.path IS '路径';
COMMENT ON COLUMN test_fsclient_filesummary.remoteFsId IS 'remoteFsId';
COMMENT ON COLUMN test_fsclient_filesummary.suffix IS '后缀';
COMMENT ON COLUMN test_fsclient_filesummary.uploadTime IS '上传时间';
COMMENT ON COLUMN test_fsclient_filesummary.uploadUserId IS '上传用户';



