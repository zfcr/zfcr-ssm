package cn.zfcr.busi.sysmanage.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

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
    
    /** 树形结构层级 方便查询 */
    @Column(name="levelNumber")
    private Integer levelNumber;

    private String name;

    /** 上级id */
    private String parentId;

    /** 字典类型表编号 */
    private String typeCode;
    
    /** 指定本节点是否为叶子节点 */
    @Column(name="isLeaf")
    private String isLeaf;
    
    /** 排序号，指定排序的顺序 */
    @Column(name="orderNo")
    @OrderBy
    private String orderNo;

    private String remark;
    
    @Transient
    private String orderByClause;

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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public Integer getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	@Override
	public String toString() {
		return "DictionaryTree [id=" + id + ", code=" + code + ", treeId=" + treeId + ", levelNumber=" + levelNumber
				+ ", name=" + name + ", parentId=" + parentId + ", typeCode=" + typeCode + ", isLeaf=" + isLeaf
				+ ", orderNo=" + orderNo + ", remark=" + remark + ", orderByClause=" + orderByClause + "]";
	}
}