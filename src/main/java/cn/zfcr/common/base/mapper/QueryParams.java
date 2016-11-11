package cn.zfcr.common.base.mapper;

import java.util.Arrays;

/**
 * 查询参数，用于条件查询或分页查询
 * @author zhangfeng
 *
 */
public class QueryParams<T> {

	/** 实体类对象 */
	private T entity;
	
	/** 查询列(指定返回列) */
	private String[] columns;
	
	/** 开始行 */
	private Integer firstResult;
	
	/** 结束行 */
	private Integer maxResults;
	
	/** 排序字段 */
	private String sort;
	
	/** 升序或降序 */
	private String dir;

	public QueryParams(T entity) {
		super();
		this.entity = entity;
	}
	
	/**
	 * 分页查询
	 * @param entity 条件
	 * @param page 当前页
	 * @param rows 每页行数
	 */
	public QueryParams(T entity, int page, int rows) {
		super();
		this.entity = entity;
		if(page <= 0) page = 1; 
		this.firstResult = (page - 1) * rows;
		this.maxResults = page * rows;
	}

	public QueryParams(T entity, int page, int rows, String sort, String dir) {
		super();
		this.entity = entity;
		if(page <= 0) page = 1; 
		this.firstResult = (page - 1) * rows;
		this.maxResults = page * rows;
		this.sort = sort;
		this.dir = dir;
	}

	public QueryParams(T entity, String[] columns, int page, int rows, String sort, String dir) {
		super();
		this.entity = entity;
		this.columns = columns;
		if(page <= 0) page = 1; 
		this.firstResult = (page - 1) * rows;
		this.maxResults = page * rows;
		this.sort = sort;
		this.dir = dir;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		return "QueryParams [entity=" + entity + ", columns=" + Arrays.toString(columns) + ", firstResult="
				+ firstResult + ", maxResults=" + maxResults + ", sort=" + sort + ", dir=" + dir + "]";
	}
	
}
