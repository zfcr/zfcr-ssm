package cn.zfcr.common.base.dao;

import java.util.List;

import cn.zfcr.mybatis.page.PageInfo;
import tk.mybatis.mapper.entity.Example;

public interface IBaseDao<T extends Object> {

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

	/**
	 * 分页查询
	 * @param obj
	 * @param pageInfo
	 * @return
	 */
	List<T> queryPaging(T obj, PageInfo pageInfo);

	/**
	 * 插入实体类中所有数据
	 * @param obj
	 * @return
	 */
	int insert(T obj);

	/**
	 * 动态插入，只插入不为null的
	 * @param obj
	 * @return
	 */
	int insertSelective(T obj);

	/**
	 * 根据主键更新表中所有数据
	 * @param obj
	 * @return
	 */
	int updateByPk(T obj);

	/**
	 * 根据example中指定的条件，更新表中所有数据
	 * @param obj
	 * @param example
	 * @return
	 */
	int updateByExample(T obj, Example example);

	/**
	 * 动态更新，根据example中指定的条件，更新表中不为null的数据
	 * @param obj
	 * @param example
	 * @return
	 */
	int updateByExampleSelective(T obj, Example example);

	/**
	 * 动态更新，根据主键，更新表中不为null的数据
	 * @param obj
	 * @param example
	 * @return
	 */
	int updateByPkSelective(T obj);

	/**
	 * 根据主键删除记录
	 * @param key
	 * @return
	 */
	int deleteByPk(Object key);

	/**
	 * 动态删除记录
	 * @param obj
	 * @return
	 */
	int delete(T obj);

}
