package com.joinsoft;

public class CacheConstants {

	/*缓存KEY[全局类]*/
	public static final String GLOBAL_SYS_RBAC_APPLICATION_LIST_IDS_KEY = "GLOBAL_SYS_RBAC_APPLICATION_LIST_IDS_KEY"; // 应用主键列表
	public static final String GLOBAL_SYS_RBAC_ORG_LIST_IDS_KEY = "GLOBAL_SYS_RBAC_ORG_LIST_IDS_KEY";   // 机构主键列表
	public static final String GLOBAL_SYS_RBAC_USER_LIST_IDS_KEY = "GLOBAL_SYS_RBAC_USER_LIST_IDS_KEY"; // 用户主键列表
	public static final String GLOBAL_SYS_RBAC_ROLE_LIST_IDS_KEY = "GLOBAL_SYS_RBAC_ROLE_LIST_IDS_KEY"; // 角色主键列表
	
	public static final String GLOBAL_SYS_RBAC_APPLICATION_KEY = "GLOBAL_SYS_RBAC_APPLICATION_KEY_%s";// 应用对象
	public static final String GLOBAL_CORE_APPLICATION_KEY = "GLOBAL_CORE_APPLICATION_KEY"; // 核心应用对象
	public static final String GLOBAL_SYS_RBAC_ORG_KEY = "GLOBAL_SYS_RBAC_ORG_KEY_%s";   // 机构对象
	public static final String GLOBAL_SYS_RBAC_USER_KEY = "GLOBAL_SYS_RBAC_USER_KEY_%s"; // 用户对象
	public static final String GLOBAL_SYS_RBAC_ROLE_KEY = "GLOBAL_SYS_RBAC_ROLE_KEY_%s"; // 角色对象
	public static final String GLOBAL_SYS_RBAC_USER_SESSION_KEY = "GLOBAL_SYS_RBAC_USER_SESSION_KEY_%s"; // 用户ID和sessionId的对应（%s对应userId）
	
    public static final String GLOBAL_SYS_RBAC_APPLICATION_LIST_KEY = "GLOBAL_SYS_RBAC_APPLICATION_LIST_KEY";
    public static final String GLOBAL_SYS_RBAC_ORG_LIST_KEY = "GLOBAL_SYS_RBAC_ORG_LIST_KEY";
    public static final String GLOBAL_SYS_RBAC_USER_LIST_KEY = "GLOBAL_SYS_RBAC_USER_LIST_KEY";
    public static final String GLOBAL_SYS_RBAC_ROLE_LIST_KEY = "GLOBAL_SYS_RBAC_ROLE_LIST_KEY";
    public static final String GLOBAL_SYS_PARAM_LIST_KEY = "GLOBAL_SYS_PARAM_LIST_KEY";
    public static final String GLOBAL_SYS_DIC_TYPE_LIST_KEY = "GLOBAL_SYS_DIC_TYPE_LIST_KEY";
    public static final String GLOBAL_SYS_DICT_CONTENT_DICTNO = "GLOBAL_SYS_DICT_CONTENT_DICTNO_%s";
    public static final String GLOBAL_USER_MAP_KEY = "GLOBAL_USER_MAP_KEY";
    public static final String GLOBAL_SYS_AREA_LIST_KEY = "GLOBAL_SYS_AREA_LIST_KEY";    //行政区代码
    public static final String GLOBAL_D_BLDTYPE_LIST_KEY = "GLOBAL_D_BLDTYPE_LIST_KEY";  //自然幢类型
    
    /*缓存KEY[Session类]*/
    public static final String SESSION_SYS_RBAC_USER_KEY = "SESSION_SYS_RBAC_USER_KEY_%s";
    public static final String SESSION_SYS_RBAC_ORG_KEY = "SESSION_SYS_RBAC_ORG_KEY_%s";
    public static final String SESSION_SYS_RBAC_ROLE_KEY = "SESSION_SYS_RBAC_ROLE_KEY_%s";
    public static final String SESSION_SYS_RBAC_MENU_TOP_KEY = "SESSION_SYS_RBAC_MENU_TOP_KEY_%s";
    public static final String SESSION_SYS_RBAC_MENU_LEFT_KEY = "SESSION_SYS_RBAC_MENU_LEFT_KEY_%s";
    
    public static final int SESSION_TIMEOUT = 1200; //120秒=2分钟
    
}
