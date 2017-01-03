package cn.zfcr.common.dao;

import java.util.List;

/**
 * 提供调用MyBatis映射文件中的statement的公共方法
 * 调用该类中的方法，必须有MyBatis映射文件
 * @author zf
 * @date 2016年11月22日
 *
 */
public interface ICommonQueryDao {

	/**
	 * 调用MyBatis文件中的指定sql查询语句
	 * @param cla 指定实体类，用于获取映射文件的命名空间，不能为空
	 * @param statementId 指定查询的statementId，用于调用映射文件中的sql语句，不能为空
	 * @param parameters 查询条件
	 * @return
	 */
	List<?> query(Class<?> cla, String statementId, Object parameters);

	/**
	 * 查询，返回一个对象（一行数据或第一行的第一列值），调用statementid为"queryFirstResult"的查询
	 * @param cla
	 * @param parameters
	 * @return
	 */
	Object queryFirstResult(Class<?> cla, Object parameters);

	/**
	 * 查询，返回一个对象（一行数据或第一行的第一列值）
	 * @param cla
	 * @param parameters
	 * @return
	 */
	Object queryFirstResult(Class<?> cla, String string, Object parameters);

	
}
