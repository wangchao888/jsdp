package com.joinsoft.platform.global.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joinsoft.CacheConstants;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.application.dao.SysRbacApplicationMapper;
import com.joinsoft.module.system.area.dao.SysAreaMapper;
import com.joinsoft.module.system.bldtype.dao.DbldtypeMapper;
import com.joinsoft.module.system.dept.dao.SysOrgAreaMapper;
import com.joinsoft.module.system.dept.dao.SysRbacOrgMapper;
import com.joinsoft.module.system.dictcontent.dao.SysDictContentMapper;
import com.joinsoft.module.system.dictype.dao.SysDicTypeMapper;
import com.joinsoft.module.system.param.dao.SysParamMapper;
import com.joinsoft.module.system.role.dao.SysRbacRoleMapper;
import com.joinsoft.module.system.user.dao.SysRbacUserMapper;
import com.joinsoft.platform.global.entity.CacheArea;
import com.joinsoft.platform.global.entity.CacheBldType;
import com.joinsoft.platform.global.entity.CacheDicType;
import com.joinsoft.platform.global.entity.CacheDictContent;
import com.joinsoft.platform.global.entity.CacheOrgArea;
import com.joinsoft.platform.global.entity.CacheParam;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalWriteService;
import com.joinsoft.platform.global.service.IXmemCacheService;

/**
 * 缓存写入接口
 * @author LZX 2017-06-07 1402
 *
 */
public class GlobalWriteService implements IGlobalWriteService {

	private static final Logger log = LoggerFactory
			.getLogger(GlobalWriteService.class);
	
	private SysRbacApplicationMapper sysRbacApplicationMapper;
	private SysRbacOrgMapper sysRbacOrgMapper;
	private SysOrgAreaMapper sysOrgAreaMapper;
	private SysRbacRoleMapper sysRbacRoleMapper;
	private SysRbacUserMapper sysRbacUserMapper;
	private SysParamMapper sysParamMapper;
	private SysDicTypeMapper sysDicTypeMapper;
	private SysDictContentMapper sysDictContentMapper;
	private SysAreaMapper sysAreaMapper;
	private DbldtypeMapper dbldtypeMapper;
	private IXmemCacheService xmemCacheService;
	
	@Override
	public List<CacheRbacApplication> writeGlobalApplication() {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("delFlag", JsdpConstants.HFMP_Delflag_N);
			List<CacheRbacApplication> list = sysRbacApplicationMapper.queryListByCache(params);
			if(list != null && list.size() > 0){
				List<String> ids = new ArrayList<String>();
				for (CacheRbacApplication entity : list) {
					ids.add(entity.getSid());
					xmemCacheService.set(String.format(CacheConstants.GLOBAL_SYS_RBAC_APPLICATION_KEY, new Object[] { entity.getSid() }), entity);
					if (JsdpConstants.RULE_ONE.equals(entity.getAppflag())) {
						xmemCacheService.set(CacheConstants.GLOBAL_CORE_APPLICATION_KEY, entity);
					}
				}
				xmemCacheService.set(CacheConstants.GLOBAL_SYS_RBAC_APPLICATION_LIST_IDS_KEY, ids);
				log.info("缓存写入应用数据条数：" + list.size());
				return list;
			}
		} catch (Exception e) {
			log.info("缓存写入应用异常：" + e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public List<CacheRbacOrg> writeGlobalOrg() {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			List<CacheRbacOrg> list = sysRbacOrgMapper.queryListByCache(params);
			if(list != null && list.size() > 0){
				params.clear();
				List<String> ids = new ArrayList<String>();
				for (CacheRbacOrg cacheRbacOrg : list) {
					ids.add(cacheRbacOrg.getSid());
					params.put("orgid", cacheRbacOrg.getSid());
					List<CacheOrgArea> orgAreaList = sysOrgAreaMapper.queryListByCache(params);
					if(orgAreaList != null && orgAreaList.size() > 0){
						cacheRbacOrg.setAreaList(orgAreaList);
					}
					xmemCacheService.set(String.format(CacheConstants.GLOBAL_SYS_RBAC_ORG_KEY, new Object[] { cacheRbacOrg.getSid() }), cacheRbacOrg);
				}
				xmemCacheService.set(CacheConstants.GLOBAL_SYS_RBAC_ORG_LIST_IDS_KEY, ids);
				log.info("缓存写入机构数据条数：" + list.size());
				return list;
			}
		} catch (Exception e) {
			log.info("缓存写入机构异常：" + e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public List<CacheRbacUser> writeGlobalUser() {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			List<CacheRbacUser> list = sysRbacUserMapper.queryListByCache(params);
			if(list != null && list.size() > 0){
				List<String> ids = new ArrayList<String>();
				for (CacheRbacUser entity : list) {
					ids.add(entity.getSid());
					xmemCacheService.set(String.format(CacheConstants.GLOBAL_SYS_RBAC_USER_KEY, new Object[] { entity.getSid() }), entity);
				}
				xmemCacheService.set(CacheConstants.GLOBAL_SYS_RBAC_USER_LIST_IDS_KEY, ids);
				log.info("缓存写入用户数据条数 " + list.size());
				return list;
			}
		} catch (Exception e) {
			log.info("缓存写入用户异常：" + e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public List<CacheRbacRole> writeGlobalRole() {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			List<CacheRbacRole> list = sysRbacRoleMapper.queryListByCache(params);
			if(list != null && list.size() > 0){
				List<String> ids = new ArrayList<String>();
				for (CacheRbacRole entity : list) {
					ids.add(entity.getSid());
					xmemCacheService.set(String.format(CacheConstants.GLOBAL_SYS_RBAC_ROLE_KEY, new Object[] { entity.getSid() }), entity);
				}
				xmemCacheService.set(CacheConstants.GLOBAL_SYS_RBAC_ROLE_LIST_IDS_KEY, ids);
				log.info("缓存写入角色数据条数：" + list.size());
				return list;
			}
		} catch (Exception e) {
			log.info("缓存写入角色异常：" + e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public List<CacheDicType> writeGlobalDicType() {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			List<CacheDicType> list = sysDicTypeMapper.queryListByCache(params);
			if(list != null && list.size() > 0){
				xmemCacheService.set(CacheConstants.GLOBAL_SYS_DIC_TYPE_LIST_KEY, list);
				log.info("缓存写入字典种类数据条数：" + list.size());
				return list;
			}
		} catch (Exception e) {
			log.info("缓存写入字典种类信息异常：" + e.getLocalizedMessage());
		}
		return null;
	}
	public void writeGlobalDictContent(List<CacheDicType> list){
		try {
			if(list != null && list.size() > 0){
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("delflag", JsdpConstants.HFMP_Delflag_N);
				for(int t=0;t<list.size();t++){
					CacheDicType sysDicType = list.get(t);
					params.put("dictno", sysDicType.getDictno());
					params.put("order", "sortno");
					List<CacheDictContent> dictList = sysDictContentMapper.queryListByCache(params);
					if(dictList != null && dictList.size() > 0){
						xmemCacheService.set(String.format(CacheConstants.GLOBAL_SYS_DICT_CONTENT_DICTNO, new Object[] { sysDicType.getDictno() }), dictList);
						log.info("缓存写入字典种类["+sysDicType.getDictno()+"]数据条数：" + dictList.size());
					}
				}
			}
		} catch (Exception e) {
			log.info("缓存写入字典种类明细信息异常：" + e.getLocalizedMessage());
		}
	}
	public List<CacheDictContent> writeGlobalDictContentByDictno(String dictno){
		try {
			if(dictno != null && !"".equals(dictno)){
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("delflag", JsdpConstants.HFMP_Delflag_N);
				params.put("dictno", dictno);
				List<CacheDictContent> list = sysDictContentMapper.queryListByCache(params);
				if(list != null && list.size() > 0){
					xmemCacheService.set(String.format(CacheConstants.GLOBAL_SYS_DICT_CONTENT_DICTNO, new Object[] { dictno }), list);
					log.info("缓存写入字典种类明细["+dictno+"]数据条数：" + list.size());
					return list;
				}
			}
		} catch (Exception e) {
			log.info("缓存写入字典种类明细信息异常：" + e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public List<CacheParam> writeGlobalParam() {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			List<CacheParam> list = sysParamMapper.queryListByCache(params);
			if(list != null && list.size() >0){
				xmemCacheService.set(CacheConstants.GLOBAL_SYS_PARAM_LIST_KEY, list);
				log.info("缓存写入参数数据条数： " + list.size());
				return list;
			}
		} catch (Exception e) {
			log.info("缓存写入参数异常： " + e.getLocalizedMessage());
		}
		return null;
	}
	
	public void writeGlobalUserMap(HashMap<String, String> userMap){
		if(userMap != null){
			xmemCacheService.set(CacheConstants.GLOBAL_USER_MAP_KEY, userMap);
		}
	}
	
	@Override
	public List<CacheArea> writeGlobalArea() {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			List<CacheArea> list = sysAreaMapper.queryListByCache(params);
			if(list != null && list.size() >0){
				xmemCacheService.set(CacheConstants.GLOBAL_SYS_AREA_LIST_KEY, list);
				log.info("缓存写入行政区代码数据条数： " + list.size());
				return list;
			}
		} catch (Exception e) {
			log.info("缓存写入行政区代码异常： " + e.getLocalizedMessage());
		}
		return null;
	}
	
	@Override
	public List<CacheBldType> writeGlobalBldType() {
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("delflag", JsdpConstants.HFMP_Delflag_N);
			List<CacheBldType> list = dbldtypeMapper.queryListByBldtype(params);
			if(list != null && list.size() >0){
				xmemCacheService.set(CacheConstants.GLOBAL_D_BLDTYPE_LIST_KEY, list);
				log.info("缓存写入自然幢类型数据条数： " + list.size());
				return list;
			}
		} catch (Exception e) {
			log.info("缓存写入自然幢类型异常： " + e.getLocalizedMessage());
		}
		return null;
	}
	
	public SysRbacApplicationMapper getSysRbacApplicationMapper() {
		return sysRbacApplicationMapper;
	}

	public void setSysRbacApplicationMapper(SysRbacApplicationMapper sysRbacApplicationMapper) {
		this.sysRbacApplicationMapper = sysRbacApplicationMapper;
	}

	public SysRbacOrgMapper getSysRbacOrgMapper() {
		return sysRbacOrgMapper;
	}

	public void setSysRbacOrgMapper(SysRbacOrgMapper sysRbacOrgMapper) {
		this.sysRbacOrgMapper = sysRbacOrgMapper;
	}

	public SysRbacRoleMapper getSysRbacRoleMapper() {
		return sysRbacRoleMapper;
	}

	public void setSysRbacRoleMapper(SysRbacRoleMapper sysRbacRoleMapper) {
		this.sysRbacRoleMapper = sysRbacRoleMapper;
	}

	public SysRbacUserMapper getSysRbacUserMapper() {
		return sysRbacUserMapper;
	}

	public void setSysRbacUserMapper(SysRbacUserMapper sysRbacUserMapper) {
		this.sysRbacUserMapper = sysRbacUserMapper;
	}

	public SysParamMapper getSysParamMapper() {
		return sysParamMapper;
	}

	public void setSysParamMapper(SysParamMapper sysParamMapper) {
		this.sysParamMapper = sysParamMapper;
	}

	public SysDicTypeMapper getSysDicTypeMapper() {
		return sysDicTypeMapper;
	}

	public void setSysDicTypeMapper(SysDicTypeMapper sysDicTypeMapper) {
		this.sysDicTypeMapper = sysDicTypeMapper;
	}

	public SysDictContentMapper getSysDictContentMapper() {
		return sysDictContentMapper;
	}

	public void setSysDictContentMapper(SysDictContentMapper sysDictContentMapper) {
		this.sysDictContentMapper = sysDictContentMapper;
	}

	public IXmemCacheService getXmemCacheService() {
		return xmemCacheService;
	}

	public void setXmemCacheService(IXmemCacheService xmemCacheService) {
		this.xmemCacheService = xmemCacheService;
	}

	public SysOrgAreaMapper getSysOrgAreaMapper() {
		return sysOrgAreaMapper;
	}

	public void setSysOrgAreaMapper(SysOrgAreaMapper sysOrgAreaMapper) {
		this.sysOrgAreaMapper = sysOrgAreaMapper;
	}

	public SysAreaMapper getSysAreaMapper() {
		return sysAreaMapper;
	}

	public void setSysAreaMapper(SysAreaMapper sysAreaMapper) {
		this.sysAreaMapper = sysAreaMapper;
	}

	public DbldtypeMapper getDbldtypeMapper() {
		return dbldtypeMapper;
	}

	public void setDbldtypeMapper(DbldtypeMapper dbldtypeMapper) {
		this.dbldtypeMapper = dbldtypeMapper;
	}

	
}
