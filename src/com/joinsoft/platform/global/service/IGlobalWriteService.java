package com.joinsoft.platform.global.service;

import java.util.HashMap;
import java.util.List;

import com.joinsoft.platform.global.entity.CacheArea;
import com.joinsoft.platform.global.entity.CacheBldType;
import com.joinsoft.platform.global.entity.CacheDicType;
import com.joinsoft.platform.global.entity.CacheDictContent;
import com.joinsoft.platform.global.entity.CacheParam;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;

/**
 * 缓存写入接口
 * @author LZX 2017-06-07 1402
 *
 */
public interface IGlobalWriteService {

	/**
	 * 缓存写入应用列表
	 * Key: GLOBAL_SYS_RBAC_APPLICATION_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-06-07 1402
	 */
	public List<CacheRbacApplication> writeGlobalApplication();
	
	/**
	 * 缓存写入机构列表
	 * Key: GLOBAL_SYS_RBAC_ORG_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-06-07 1402
	 */
	public List<CacheRbacOrg> writeGlobalOrg();
	
	/**
	 * 缓存写入用户列表
	 * Key: GLOBAL_SYS_RBAC_USER_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-06-07 1402
	 */
	public List<CacheRbacUser> writeGlobalUser();
	
	/**
	 * 缓存写入角色列表
	 * Key: GLOBAL_SYS_RBAC_ROLE_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-06-07 1402
	 */
	public List<CacheRbacRole> writeGlobalRole();
	
	/**
	 * 缓存写入字典信息
	 * Key: GLOBAL_SYS_DICT_CONTENT_DICTNO_s%  s%是字典类别的占位符
	 * Value: 字典类别对应的字典明细List集合
	 * 
	 * @author LZX 2017-06-07 1402
	 */
	public List<CacheDicType> writeGlobalDicType();
	public void writeGlobalDictContent(List<CacheDicType> list);
	/**
	 * 字典种类明细更新写入
	 * @param dictno
	 * @param list
	 * @author LZX 2017-06-07 1402
	 */
	public List<CacheDictContent> writeGlobalDictContentByDictno(String dictno);
	
	/**
	 * 缓存写入参数列表
	 * Key: GLOBAL_SYS_PARAM_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-06-07 1402
	 */
	public List<CacheParam> writeGlobalParam();
	
	/**
	 * 写入全局的用户Map信息
	 * @return
	 * 
	 * @author LZX 2017-06-12 0926
	 */
	public void writeGlobalUserMap(HashMap<String, String> userMap);
	
	/**
	 * 缓存写入行政区列表
	 * Key: GLOBAL_SYS_AREA_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-07-20 0946
	 */
	public List<CacheArea> writeGlobalArea();
	
	/**
	 * 缓存写入自然幢类型列表
	 * Key: GLOBAL_D_BLDTYPE_LIST_KEY
	 * Value: 应用列表List集合
	 * 
	 * @author LZX 2017-07-20 1416
	 */
	public List<CacheBldType> writeGlobalBldType();
	
}
