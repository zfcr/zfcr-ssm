package cn.zfcr.mybatis.page;

import org.apache.ibatis.session.RowBounds;

import cn.zfcr.system.constants.SysMethodConstant;

/**  
 * Package: cn.zfcr.mybatis.page  
 *  
 * File: PageInfo.java   
 * 
 * 分页信息，定义分页的相关参数
 *  
 * Author: zf   Email: zhangfeng2124@163.com
 * Date: 2016年11月9日 上午10:57:47 
 *  
 * Copyright @ 2016 Corpration Name  
 *   
 */
public class PageInfo extends RowBounds{

	/** 当前页 */
	private int page;
	
	/** 每页的行数 */
	private int pageRows;
	
	/** 是否分页 false：不行页，true：分页 ，不启用*/
	private boolean whetherPage;
	
	/** 排序子句 */
	private String orderByClause;
	
	public PageInfo(RowBounds rowBounds){
		if(rowBounds instanceof PageInfo){
			PageInfo pageInfo = (PageInfo) rowBounds;
			this.page = pageInfo.getPage();
			this.pageRows = pageInfo.getPageRows();
			this.whetherPage = pageInfo.getWhetherPage();
			this.orderByClause = pageInfo.getOrderByClause();
			return;
		}
		this.page = 1;
		this.pageRows = 10;
		this.whetherPage = true;
	}

	public PageInfo(int page, int pageRows) {
		super((page - 1) * pageRows,page * pageRows);
		this.page = page;
		this.pageRows = pageRows;
		this.whetherPage = true;
	}

	public PageInfo(int page, int pageRows, String orderByClause) {
		super((page - 1) * pageRows,page * pageRows);
		this.page = page;
		this.pageRows = pageRows;
		this.whetherPage = true;
		this.orderByClause = orderByClause;
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

	public boolean getWhetherPage() {
		return whetherPage;
	}

	public void setWhetherPage(boolean whetherPage) {
		this.whetherPage = whetherPage;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = SysMethodConstant.encodeForSQL(orderByClause);
	}
	
}
