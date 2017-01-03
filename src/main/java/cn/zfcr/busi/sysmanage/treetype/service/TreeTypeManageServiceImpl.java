package cn.zfcr.busi.sysmanage.treetype.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zfcr.busi.sysmanage.entity.DictionaryTree;
import cn.zfcr.busi.sysmanage.mapper.DictionaryTreeMapper;
import cn.zfcr.common.base.service.BaseAbstractService;

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
		if(isEmpty(dictionaryTree.getId())){
			setTreeId(dictionaryTree);
			dictionaryTreeMapper.insert(dictionaryTree);
		}else{
			dictionaryTreeMapper.updateByPrimaryKey(dictionaryTree);
		}
	}

	/**
	 * 设置树形结构的treeId
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
}
