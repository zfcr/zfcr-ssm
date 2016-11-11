package cn.zfcr.common.base.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

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
	List<T> findByExample(Example example);

	/**
	 * 根据动态条件查询满足条件的结果集
	 * @param obj 条件
	 * @return
	 */
	List<T> dynamicFind(T obj);

	/**
	 * 根据动态条件分页查询
	 * @param obj
	 * @param rowBounds
	 * @return
	 */
	List<T> findOfPage(T obj, RowBounds rowBounds);

	/**
	 * 根据动态条件统计满足条件的数量
	 * @param obj
	 * @return
	 */
	long dynamicCount(T obj);

}
