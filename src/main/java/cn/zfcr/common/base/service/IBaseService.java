package cn.zfcr.common.base.service;

import java.util.List;
import java.util.Map;

public interface IBaseService<T> {

	List<T> findAll();

	/**
	 * 动态查询
	 * @param obj
	 * @return
	 */
	List<T> dynamicFind(T obj);

	/**
	 * 分页查询
	 * @param obj
	 * @param page 页数
	 * @param rows 每页行数
	 * @return
	 */
	Map<String, Object> findOfPage(T obj, int page, int rows);

}
