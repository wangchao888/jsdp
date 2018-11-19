package com.joinsoft.dubbo.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.joinsoft.core.utils.StringUtils;
import com.joinsoft.core.utils.UUIDUtils;
import com.joinsoft.core.utils.date.DateUtils;
import com.joinsoft.dubbo.service.IOrgService;
import com.joinsoft.module.DicConstants;
import com.joinsoft.module.JsdpConstants;
import com.joinsoft.module.system.dept.dao.SysOrgAreaMapper;
import com.joinsoft.module.system.dept.dao.SysRbacOrgMapper;
import com.joinsoft.module.system.dept.entity.SysOrgArea;
import com.joinsoft.module.system.dept.entity.SysRbacOrg;
import com.joinsoft.platform.global.entity.CacheOrgArea;
import com.joinsoft.platform.global.entity.CacheRbacOrg;
import com.joinsoft.platform.global.service.IGlobalWriteService;

public class OrgService implements IOrgService {
	
	private SysRbacOrgMapper sysRbacOrgMapper; 
	private SysOrgAreaMapper sysOrgAreaMapper; 
	private IGlobalWriteService globalWriteService;

	@Override
	public String insertOrg(CacheRbacOrg cacheRbacOrg) {
		try {
			if (cacheRbacOrg.getSid() != null) {
				// 添加机构表信息
				SysRbacOrg entity = new SysRbacOrg();
				entity.setSid(cacheRbacOrg.getSid());
				if (cacheRbacOrg.getOrgname() != null) {
					entity.setOrgname(cacheRbacOrg.getOrgname());
				}
				if (cacheRbacOrg.getOrgNo() != null) {
					entity.setOrgNo(cacheRbacOrg.getOrgNo());
				}
				entity.setDelFlag(JsdpConstants.HFMP_Delflag_N);//默认正常
				entity.setCreateTime(DateUtils.getTimeNow());//创建时间
				if (cacheRbacOrg.getOrgtype() != null) {
					entity.setOrgtype(cacheRbacOrg.getOrgtype());
				}
				if (cacheRbacOrg.getZonecode() != null) {
					entity.setZonecode(cacheRbacOrg.getZonecode());
				}
				if (cacheRbacOrg.getOrgflag() != null) {
					entity.setOrgflag(cacheRbacOrg.getOrgflag()); //1 内部机构 2 外部机构
				}
				if (cacheRbacOrg.getCorpType() != null) {
					entity.setCorpType(cacheRbacOrg.getCorpType()); // 企业类型，01 开发，02 物业，03 施工，04 业主委员会
				}
				if (cacheRbacOrg.getCorpno() != null) {
					entity.setCorpno(cacheRbacOrg.getCorpno()); // 企业编号或承办银行编号
				}
				if (cacheRbacOrg.getOrglevl() != null) {
					entity.setOrglevl(cacheRbacOrg.getOrglevl());
				}
				sysRbacOrgMapper.insertSysRbacOrg(entity);
				// 将数据范围添加到sys_org_area表中
				if (cacheRbacOrg.getAreaList() != null && cacheRbacOrg.getAreaList().size() > 0) {
					for (CacheOrgArea cacheOrgArea : cacheRbacOrg.getAreaList()) {
		    			SysOrgArea orgAreaEntity = new SysOrgArea();
		    			orgAreaEntity.setSid(UUIDUtils.get32UUID());
		    			orgAreaEntity.setZonecode(cacheOrgArea.getZonecode());
		    			orgAreaEntity.setOrgid(cacheRbacOrg.getSid());
		    			sysOrgAreaMapper.insertSysOrgArea(orgAreaEntity);
		    		}
				}
				// 更新缓存信息
				globalWriteService.writeGlobalOrg();
				
				return JsdpConstants.HFMP_RESULT_SUCCESS; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsdpConstants.HFMP_RESULT_FAIL; 
	}

	@Override
	public String updateOrg(CacheRbacOrg cacheRbacOrg) {
		if (cacheRbacOrg.getSid() != null) {
			// 添加机构表信息
			SysRbacOrg entity = new SysRbacOrg();
			entity.setSid(cacheRbacOrg.getSid());
			if (cacheRbacOrg.getOrgname() != null) {
				entity.setOrgname(cacheRbacOrg.getOrgname());
			}
			if (cacheRbacOrg.getOrgNo() != null) {
				entity.setOrgNo(cacheRbacOrg.getOrgNo());
			}
			if (cacheRbacOrg.getOrgtype() != null) {
				entity.setOrgtype(cacheRbacOrg.getOrgtype());
			}
			if (cacheRbacOrg.getZonecode() != null) {
				entity.setZonecode(cacheRbacOrg.getZonecode());
			}
			if (cacheRbacOrg.getOrgflag() != null) {
				entity.setOrgflag(cacheRbacOrg.getOrgflag()); //1 内部机构 2 外部机构
			}
			if (cacheRbacOrg.getCorpType() != null) {
				entity.setCorpType(cacheRbacOrg.getCorpType()); // 企业类型，01 开发，02 物业，03 施工，04 业主委员会
			}
			if (cacheRbacOrg.getCorpno() != null) {
				entity.setCorpno(cacheRbacOrg.getCorpno()); // 企业编号或承办银行编号
			}
			if (cacheRbacOrg.getOrglevl() != null) {
				entity.setOrglevl(cacheRbacOrg.getOrglevl());
			}
			sysRbacOrgMapper.updateSysRbacOrg(entity);
			
			//更新sys_org_area时先删除表中原有数据
	    	Map<String,Object> params = new HashMap<String,Object>();
			params.put("orgid", entity.getSid());
			sysOrgAreaMapper.deleteSysOrgArea(params);
			// 将数据范围添加到sys_org_area表中
			if (cacheRbacOrg.getAreaList() != null && cacheRbacOrg.getAreaList().size() > 0) {
				for (CacheOrgArea cacheOrgArea : cacheRbacOrg.getAreaList()) {
	    			SysOrgArea orgAreaEntity = new SysOrgArea();
	    			orgAreaEntity.setSid(UUIDUtils.get32UUID());
	    			orgAreaEntity.setZonecode(cacheOrgArea.getZonecode());
	    			orgAreaEntity.setOrgid(cacheRbacOrg.getSid());
	    			sysOrgAreaMapper.insertSysOrgArea(orgAreaEntity);
	    		}
			}
			// 更新缓存信息
			globalWriteService.writeGlobalOrg();
			
			return JsdpConstants.HFMP_RESULT_SUCCESS; 
		}
		return JsdpConstants.HFMP_RESULT_FAIL; 
	}

	@Override
	public String deleteOrg(String sid) {
		if(!StringUtils.isEmpty(sid)){
			// 更新机构删除标记
			SysRbacOrg entity = new SysRbacOrg();
			entity.setSid(sid);
			entity.setDelFlag(JsdpConstants.HFMP_Delflag_Y);
			sysRbacOrgMapper.updateSysRbacOrg(entity);
			
			// 删除机构对应的区县权限
			Map<String,Object> params = new HashMap<String,Object>();
    		params.put("orgid", sid);
    		sysOrgAreaMapper.deleteSysOrgArea(params);
    	}
		return JsdpConstants.HFMP_RESULT_SUCCESS; 
	}

	public SysRbacOrgMapper getSysRbacOrgMapper() {
		return sysRbacOrgMapper;
	}

	public void setSysRbacOrgMapper(SysRbacOrgMapper sysRbacOrgMapper) {
		this.sysRbacOrgMapper = sysRbacOrgMapper;
	}

	public SysOrgAreaMapper getSysOrgAreaMapper() {
		return sysOrgAreaMapper;
	}

	public void setSysOrgAreaMapper(SysOrgAreaMapper sysOrgAreaMapper) {
		this.sysOrgAreaMapper = sysOrgAreaMapper;
	}

	public IGlobalWriteService getGlobalWriteService() {
		return globalWriteService;
	}

	public void setGlobalWriteService(IGlobalWriteService globalWriteService) {
		this.globalWriteService = globalWriteService;
	}
	
}
