package com.joinsoft.platform.global.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joinsoft.platform.global.entity.CacheArea;
import com.joinsoft.platform.global.entity.CacheBldType;
import com.joinsoft.platform.global.entity.CacheDicType;
import com.joinsoft.platform.global.entity.CacheParam;
import com.joinsoft.platform.global.entity.CacheRbacApplication;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.entity.CacheRbacRole;
import com.joinsoft.platform.global.entity.CacheRbacUser;
import com.joinsoft.platform.global.service.IGlobalInitService;
import com.joinsoft.platform.global.service.IGlobalWriteService;

public class GlobalInitService implements IGlobalInitService {

	private static final Logger log = LoggerFactory
			.getLogger(GlobalInitService.class);
	
	private IGlobalWriteService globalWriteService;
					
	@Override
	public void initApplication() {
		try {
			List<CacheRbacApplication> list = globalWriteService.writeGlobalApplication();
			if(list != null && list.size() > 0){
				log.info("缓存初始化应用数据条数：" + list.size());
			}
		} catch (Exception e) {
			log.info("缓存初始化应用异常：" + e.getLocalizedMessage());
		}
	}

	@Override
	public void initOrg() {
		try {
			List<CacheRbacOrg> list = globalWriteService.writeGlobalOrg();
			if(list != null && list.size() > 0){
				log.info("缓存初始化机构数据条数：" + list.size());
			}
		} catch (Exception e) {
			log.info("缓存初始化机构异常：" + e.getLocalizedMessage());
		}
	}

	@Override
	public void initUser() {
		try {
			List<CacheRbacUser> list = globalWriteService.writeGlobalUser();
			if(list != null && list.size() > 0){
				log.info("缓存初始化用户数据条数 " + list.size());
			}
		} catch (Exception e) {
			log.info("缓存初始化用户异常：" + e.getLocalizedMessage());
		}
	}

	@Override
	public void initRole() {
		try {
			List<CacheRbacRole> list = globalWriteService.writeGlobalRole();
			if(list != null && list.size() > 0){
				log.info("缓存初始化角色数据条数：" + list.size());
			}
		} catch (Exception e) {
			log.info("缓存初始化角色异常：" + e.getLocalizedMessage());
		}
	}

	@Override
	public void initDic() {
		try {
			List<CacheDicType> list = globalWriteService.writeGlobalDicType();
			if(list != null && list.size() > 0){
				log.info("缓存初始化字典种类数据条数：" + list.size());
				globalWriteService.writeGlobalDictContent(list);
			}
		} catch (Exception e) {
			log.info("缓存初始化字典异常：" + e.getLocalizedMessage());
		}
	}

	@Override
	public void initParam() {
		try {
			List<CacheParam> list = globalWriteService.writeGlobalParam();
			if(list != null && list.size() >0){
				log.info("缓存初始化参数数据条数： " + list.size());
			}
		} catch (Exception e) {
			log.info("缓存初始化参数异常： " + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void initArea() {
		try {
			List<CacheArea> list = globalWriteService.writeGlobalArea();
			if(list != null && list.size() >0){
				log.info("缓存初始化行政区代码数据条数： " + list.size());
			}
		} catch (Exception e) {
			log.info("缓存初始化行政区代码异常： " + e.getLocalizedMessage());
		}
	}
	
	@Override
	public void initBldType() {
		try {
			List<CacheBldType> list = globalWriteService.writeGlobalBldType();
			if(list != null && list.size() >0){
				log.info("缓存初始化自然幢类型数据条数： " + list.size());
			}
		} catch (Exception e) {
			log.info("缓存初始化自然幢类型异常： " + e.getLocalizedMessage());
		}
	}



	public IGlobalWriteService getGlobalWriteService() {
		return globalWriteService;
	}

	public void setGlobalWriteService(IGlobalWriteService globalWriteService) {
		this.globalWriteService = globalWriteService;
	}

	
}
