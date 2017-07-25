package cn.zfcr.busi.sysmanage.treetype.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.zfcr.busi.sysmanage.entity.DictionaryTree;
import cn.zfcr.busi.sysmanage.mapper.DictionaryTreeMapper;
import cn.zfcr.common.base.service.BaseAbstractService;
import tk.mybatis.mapper.entity.Example;

@Service
public class TreeTypeManageServiceImpl extends BaseAbstractService implements ITreeTypeManageService{

	@Resource
	private DictionaryTreeMapper dictionaryTreeMapper;
	
	@Override
	public List<DictionaryTree> find(DictionaryTree dictionaryTree){
		return dictionaryTreeMapper.select(dictionaryTree);
	}
	
	@Override
	public List<DictionaryTree> find(String parentId, String typeCode){
		DictionaryTree dictionaryTree = new DictionaryTree();
		dictionaryTree.setParentId(parentId);
		dictionaryTree.setTypeCode(typeCode);
		return dictionaryTreeMapper.select(dictionaryTree);
	}
	
	@Override
	public void saveOrUpdate(DictionaryTree dictionaryTree){
	    if(dictionaryTree.getLevelNumber() == null){
	        DictionaryTree parent = dictionaryTreeMapper.selectByPrimaryKey(dictionaryTree.getParentId());
	        if(parent == null){
	            dictionaryTree.setLevelNumber(0);
	        }else{
	            dictionaryTree.setLevelNumber(parent.getLevelNumber() + 1);
	        }
	    }
		if(isEmpty(dictionaryTree.getId())){
			setTreeId(dictionaryTree);
			dictionaryTreeMapper.insert(dictionaryTree);
		}else{
			dictionaryTreeMapper.updateByPrimaryKey(dictionaryTree);
		}
	}

	/**
	 * 设置树形结构的treeId，并更新上级节点
	 * @param dictionaryTree
	 */
	private void setTreeId(DictionaryTree dictionaryTree) {
		DictionaryTree parentTree = dictionaryTreeMapper.selectByPrimaryKey(dictionaryTree.getParentId());
		dictionaryTree.setTypeCode(parentTree.getTypeCode());
		Object maxTreeId = commonQueryDao.queryFirstResult(DictionaryTreeMapper.class, "queryMaxTreeId", parentTree.getTreeId()+".%");
		if(maxTreeId == null || isEmpty(maxTreeId.toString())){
			dictionaryTree.setTreeId(parentTree.getTreeId()+".1");
		}else{
			String value = maxTreeId.toString().substring(maxTreeId.toString().lastIndexOf(".") + 1);
			dictionaryTree.setTreeId(parentTree.getTreeId() + "." + (Integer.parseInt(value) + 1));
		}
		dictionaryTree.setLevelNumber(dictionaryTree.getTreeId().split("\\.").length);
		dictionaryTree.setIsLeaf("1");
		if(!"0".equals(parentTree)){
			parentTree.setIsLeaf("0");
			dictionaryTreeMapper.updateByPrimaryKey(parentTree);
		}
	}

	@Override
	public String queryMaxCode(String typecode) {
		Object value = commonQueryDao.queryFirstResult(DictionaryTreeMapper.class, typecode+"%");
		if(value == null || isEmpty(value.toString())){
			return typecode + "01";
		}
		return calcMaxTypeCode(typecode, value);
	}

	/**
	 * 根据数据库已经存的编号，计算出最大编号
	 * @param typecode
	 * @param value
	 * @return
	 */
	private String calcMaxTypeCode(String typecode, Object value) {
		String typeCodeRight = value.toString().substring(typecode.length());
		int maxValue = Integer.parseInt(typeCodeRight);
		maxValue ++;
		typeCodeRight = "00" + maxValue;
		return typecode + typeCodeRight.substring(typeCodeRight.length() - 2, typeCodeRight.length());
	}

	@Override
	public boolean validateCode(DictionaryTree entity) {
		return dictionaryTreeMapper.selectCount(entity) > 0;
	}

	@Override
	public void deleteById(String id) {
		dictionaryTreeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public DictionaryTree getById(String id) {
		return dictionaryTreeMapper.selectByPrimaryKey(id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DictionaryTree> queryByTreeId(String treeId, String typeCode) {
		DictionaryTree dictionaryTree = new DictionaryTree();
		dictionaryTree.setTreeId(treeId+"%");
		dictionaryTree.setTypeCode(typeCode);
		List<DictionaryTree> dictionaryTrees = (List<DictionaryTree>) commonQueryDao.query(DictionaryTreeMapper.class, "queryByTreeId", dictionaryTree);
		return dictionaryTrees;
	}
	
	@Override
	public List<DictionaryTree> listByTreeId(String treeId) {
		Example example = new Example(DictionaryTree.class);
		example.createCriteria().andLike("treeId", treeId + ".%");
		return dictionaryTreeMapper.selectByExample(example);
	}

	@Override
	public DictionaryTree getByCode(String code) {
		DictionaryTree dictionaryTree = new DictionaryTree();
		dictionaryTree.setCode(code);
		List<DictionaryTree> dictionaryTrees = dictionaryTreeMapper.select(dictionaryTree);
		return CollectionUtils.isEmpty(dictionaryTrees) ? null : dictionaryTrees.get(0);
	}
}
