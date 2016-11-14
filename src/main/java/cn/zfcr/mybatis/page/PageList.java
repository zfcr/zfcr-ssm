package cn.zfcr.mybatis.page;

import java.util.ArrayList;
import java.util.Collection;

/**  
 * Package: cn.zfcr.mybatis.page  
 *  
 * File: PageList.java   
 * 
 * 分页查询返回的结果集
 *  包括分页的相关信息及数据结果
 * Author: zf   Email: zhangfeng2124@163.com
 * Date: 2016年11月9日 上午10:58:06 
 *  
 * Copyright @ 2016 Corpration Name  
 *   
 */
public class PageList<E> extends ArrayList<E> {

	private static final long serialVersionUID = -2117942698541417865L;

	/** 当前页 */
	private int page;
	
	/** 每页的行数 */
	private int pageRows;
	
	/** 总行数 */
	private int totalCount;
	
	public PageList(Collection<? extends E> c) {
		super(c);
	}
	
	public PageList(Collection<? extends E> c, int page, int pageRows, int totalCount) {
		super(c);
		this.page = page;
		this.pageRows = pageRows;
		this.totalCount = totalCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PageList(int page, int pageRows, int totalCount) {
		super();
		this.page = page;
		this.pageRows = pageRows;
		this.totalCount = totalCount;
	}
	
}
