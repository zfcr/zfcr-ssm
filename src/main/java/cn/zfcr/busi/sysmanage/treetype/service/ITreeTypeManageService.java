package cn.zfcr.busi.sysmanage.treetype.service;

import java.util.List;

import cn.zfcr.busi.sysmanage.entity.DictionaryTree;

public interface ITreeTypeManageService {

	/**
	 * 查询树形字典信息
	 * @param dictionaryTree
	 * @return
	 */
	List<DictionaryTree> find(DictionaryTree dictionaryTree);

	/**
	 * 根据字典类型和父节点查询节点下的所有子节点
	 * @param parentId
	 * @param typeCode
	 * @return
	 */
	List<DictionaryTree> find(String parentId, String typeCode);

	/**
	 * 插入或更新树形字典表
	 * id为空时，插入数据，否则，更新数据
	 * @param dictionaryTree
	 */
	void saveOrUpdate(DictionaryTree dictionaryTree);

	/**
	 * 查询某一个分类下面的最大编号，用于自动生成唯一编号
	 * @param typecode
	 */
	String queryMaxCode(String typecode);

	/**
	 * 验证编号是否存在
	 * @param entity
	 * @return true:存在,false:不存在
	 */
	boolean validateCode(DictionaryTree entity);

	/**
	 * 删除
	 * @param id
	 */
	void deleteById(String id);

	DictionaryTree getById(String id);

	/**
	 * 查询所有下级字典信息
	 * @param string
	 * @param treetypeTypecode
	 * @return
	 */
	List<DictionaryTree> queryByTreeId(String treeId, String typeCode);

	/**
	 * 查询所有下级字典
	 * @param treeId
	 * @return
	 */
	List<DictionaryTree> listByTreeId(String treeId);

	DictionaryTree getByCode(String code);
}
