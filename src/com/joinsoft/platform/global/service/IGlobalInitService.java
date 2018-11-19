package com.joinsoft.platform.global.service;

public interface IGlobalInitService {

	/**
	 * 初始化应用列表
	 * Key: GLOBAL_SYS_RBAC_APPLICATION_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-06-05 0903
	 */
	public void initApplication();
	
	/**
	 * 初始化机构列表
	 * Key: GLOBAL_SYS_RBAC_ORG_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-06-05 0903
	 */
	public void initOrg();
	
	/**
	 * 初始化用户列表
	 * Key: GLOBAL_SYS_RBAC_USER_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-06-05 0903
	 */
	public void initUser();
	
	/**
	 * 初始化角色列表
	 * Key: GLOBAL_SYS_RBAC_ROLE_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-06-05 0903
	 */
	public void initRole();
	
	/**
	 * 初始化字典信息
	 * Key: GLOBAL_SYS_DICT_CONTENT_DICTNO_s%  s%是字典类别的占位符
	 * Value: 字典类别对应的字典明细List集合
	 * 
	 * @author LZX 2017-06-05 1339
	 */
	public void initDic();
	
	/**
	 * 初始化参数列表
	 * Key: GLOBAL_SYS_PARAM_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-06-05 0903
	 */
	public void initParam();
	
	/**
	 * 初始化行政区代码列表
	 * Key: GLOBAL_SYS_AREA_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-07-20 0940
	 */
	public void initArea();
	
	/**
	 * 初始化自然幢类型列表
	 * Key: GLOBAL_D_BLDTYPE_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-07-20 1422
	 */
	public void initBldType();
}
