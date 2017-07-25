package cn.zfcr.busi.mapper;

import java.util.List;
import java.util.Map;

import cn.zfcr.busi.entity.BlogInfo;
import cn.zfcr.common.base.mapper.BaseMapper;
import cn.zfcr.mybatis.page.PageInfo;
import cn.zfcr.mybatis.page.PageList;

public interface BlogInfoMapper extends BaseMapper<BlogInfo>{
    
    public List<BlogInfo> queryNewTitle(BlogInfo entity);
    
    public PageList<BlogInfo> queryPaging(BlogInfo entity, PageInfo pageInfo);

    /**
     * 全文搜索
     * @param name
     * @param pageInfo
     * @return
     */
	public PageList<BlogInfo> queryFullText(String name, PageInfo pageInfo);
	
	/**
	 * 统计文章一级分类的文章数量
	 * @return
	 */
	public List<Map<String, Object>> countTitleTypeNum();

	/**
     * 查询文章分类下所有文章，包括下级文章，分页查询
     * @param treeId
     * @param pageInfo
     * @return
     */
    public PageList<BlogInfo> listByBlogType(String treeId, String treeId2, PageInfo pageInfo);
    
    /**
     * 统计博客的访问次数
     * @return
     */
    public Long countVisitTimes();
   
}