package cn.zfcr.busi.sysmanage.visittimes.service;

import java.util.List;

import cn.zfcr.busi.sysmanage.entity.SystemVisitTimes;

public interface ISystemVisitTimesService {

	/**
	 * 动态条件查询
	 * @param systemVisitTimes
	 * @return
	 */
	List<SystemVisitTimes> find(SystemVisitTimes systemVisitTimes);

	/**
	 * 插入或更新
	 * id为空时，插入数据，否则，更新数据
	 * @param systemVisitTimes
	 */
	void saveOrUpdate(SystemVisitTimes systemVisitTimes);

	SystemVisitTimes getById(String id);

}
