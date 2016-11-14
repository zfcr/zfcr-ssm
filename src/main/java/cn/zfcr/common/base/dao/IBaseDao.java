package cn.zfcr.common.base.dao;

import java.util.List;

import cn.zfcr.mybatis.page.PageInfo;
import tk.mybatis.mapper.entity.Example;

public interface IBaseDao<T> {

	/**
	 * 查询表中所有数据
	 * @return
	 */
	List<T> findAll();

	/**
	 * 根据主键值查询唯一结果
	 * @param pkValue 主键值
	 * @return
	 */
	T findByPk(String pkValue);

	/**
	 * 根据动态条件查询满足条件的结果集
	 * @param obj 条件
	 * @return
	 */
	List<T> findByDynamic(Example example);

	/**
	 * 根据动态条件查询满足条件的结果集
	 * @param obj 条件
	 * @return
	 */
	List<T> findByDynamic(T obj);

	/**
	 * 根据动态条件统计满足条件的数量
	 * @param obj
	 * @return
	 */
	long dynamicCount(T obj);

	List<T> queryPaging(T obj, PageInfo pageInfo);

}
