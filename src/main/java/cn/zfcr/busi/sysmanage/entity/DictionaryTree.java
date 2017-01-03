package cn.zfcr.busi.sysmanage.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 树形字典信息表(t_system_dictionary_tree)
 * @author zhangfeng
 * @date 2016年12月26日
 * 
 */
@Table(name="t_system_dictionary_tree")
public class DictionaryTree {
	
	@Id
	@GeneratedValue(generator="UUID")
    private String id;

    /** 编号 根据类型编号自动生成 */
    private String code;

    /** 树形结构唯一id 1 1.1 1.1.1 1.2 */
    @Column(name="treeId")
    private String treeId;

    private String name;

    /** 上级id */
    private String parentId;

    /** 字典类型表编号 */
    private String typeCode;
    
    /** 指定本节点是否为叶子节点 */
    @Column(name="isLeaf")
    private String isLeaf;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId == null ? null : treeId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", treeId=").append(treeId);
        sb.append(", name=").append(name);
        sb.append(", parentId=").append(parentId);
        sb.append(", typeCode=").append(typeCode);
        sb.append(", isLeaf=").append(isLeaf);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}