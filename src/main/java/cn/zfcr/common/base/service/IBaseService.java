package cn.zfcr.common.base.service;

import java.util.List;
import cn.zfcr.mybatis.page.PageList;

public interface IBaseService<T> {

	/**
	 * 查询表中所有数据
	 * @return
	 */
	List<T> findAll();

	/**
	 * 查询表中的数据，动态条件，即数据不为空的字段做为条件查询
	 * @param obj
	 * @return
	 */
	List<T> findByDynamic(T obj);

	/**
	 * 分页查询
	 * @param obj
	 * @param page
	 * @param rows
	 * @return
	 */
	PageList<T> queryPaging(T obj, int page, int rows);

	/**
	 * 查询指定表中所有数据，可排序
	 * @param cla
	 * @param orderBy
	 * @return
	 */
	List<T> findAll(Class<T> cla, String orderBy);

	/**
	 * 查询表中的数据，动态条件，即数据不为空的字段做为条件查询，可排序
	 * @param obj
	 * @param orderBy
	 * @return
	 */
	List<T> findByDynamic(T obj, String orderBy);

}
