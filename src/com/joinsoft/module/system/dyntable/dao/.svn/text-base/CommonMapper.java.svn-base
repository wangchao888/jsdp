package com.joinsoft.module.system.dyntable.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.joinsoft.core.bean.SelectBean;
import com.joinsoft.core.persistence.annotation.MyBatisDao;

/**
 * {功能简述}
 * 实现实体查询(statement)、删除和修改
 * @author scy
 * @since 2017-06-02
 */
@MyBatisDao
public interface CommonMapper {

	@SuppressWarnings("rawtypes")
	List<Map> queryListBySql(Map map);
	
	void deleteData(@Param(value="sql")String sql);
	
	void insertData(@Param(value="sql")String sql);
	
	/*根据城市代码查询主管部门信息*/
	@SuppressWarnings("rawtypes")
	HashMap getCompeinfoByCitycode(Map map);
	
	/*查询字典信息，条件: citycode、dictypeid、dickey 李中新*/
	@SuppressWarnings("rawtypes")
	List<Map> getDicinfo(Map map);
	
	/*查询财务管理员信息，条件: citycode 李中新*/
	List<SelectBean> getUserinfo(String citycode);
	
	
	/*查询单表不带条件的下拉框信息  李中新*/
	@SuppressWarnings("rawtypes")
	List<SelectBean> getSelectData(Map map);
	/*根据sql获得 int类型数据 安顺*/
	Integer getIntBySql(@Param(value="sql")String sql);
	
	/*根据sql获得 Double类型数据 安顺*/
	Double getDoubleBySql(@Param(value="sql")String sql);
	
	/*根据缴存模式取模式基准数据  李中新*/
	@SuppressWarnings("rawtypes")
	List<Map> queryDepmodeByType(Map map);
	
	/*查询业主信息表中的基础字段  李中新*/
	@SuppressWarnings("rawtypes")
	List<Map> queryRpsnaccinfo(Map map);
	
	
	/*查询交存模式条件表，查找deptype模式下的交存条件  李中新*/
	@SuppressWarnings("rawtypes")
	List<Map> queryDmononditionByType(Map map);
	
	
	/*动态查询交存数据  李中新*/
	@SuppressWarnings("rawtypes")
	List<Map> queryDynamic(Map map);
	
	
	/*查询交存标准表  李中新*/
	@SuppressWarnings("rawtypes")
	List<Map> queryDdepstandardByType(Map map);
	
	String getStr(@Param(value="sql")String sql);
	/*动态sql查询select组件数据*/
	List<SelectBean> queryForSelect(@Param(value="sql")String sql);
	
	void updateBySql(@Param(value="sql")String sql);
	
	/*资金缴存统计--缴存窗口工作量查询--业主缴存统计*/
	List<Map<String, Object>> pageOperFindMore(Map<String, Object> map);
	/*资金缴存统计-缴存窗口工作量查询-业务员资金收缴统计*/
	List<Map<String, Object>> pageOperFind(Map<String, Object> map);
	/*业主缴存凭证补打信息明细*/
	List<Map<String, Object>> pagePsnPrintList(Map<String, Object> map);
	/*业主缴存凭证打印统计*/
	List<Map<String, Object>> pagevouStatistic(Map<String, Object> map);
	/*日业务量统计查询*/
	List<Map<String, Object>> pagebusiList(Map<String, Object> map);
	/*缴存业务工作量统计显示*/
	List<Map<String, Object>> pageworkStatisticShow(Map<String, Object> map);
	
	List<Map<String, Object>> pageworkStatisticDetail(Map<String, Object> map);
	/*区县缴存量统计显示*/
	List<Map<String, Object>> DepositStatisticShowByCity(Map<String, Object> map);
	/*小区缴存量统计显示*/
	List<Map<String, Object>> pageDepositStatisticShowByRegion(Map<String, Object> map);
	
	/*获取资产类科目*/
	List<SelectBean> getAssetsItem();
	/*获取资产类科目*/
	List<SelectBean> getBankItem();
	
	List<SelectBean> getItemONETHREEETWO();
	List<SelectBean> getItemFOURZEROONE();
	List<SelectBean> getItemAccr();
}
