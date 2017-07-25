package cn.zfcr.busi.blog.service;

import java.util.List;
import java.util.Map;

import cn.zfcr.busi.entity.BlogComment;
import cn.zfcr.busi.entity.BlogInfo;
import cn.zfcr.mybatis.page.PageInfo;
import cn.zfcr.mybatis.page.PageList;

/**
 * 博客管理Service
 * @author zhangfeng
 * @date 2017年1月10日
 */
public interface BlogManageService {

	/**
	 * 保存或更新博客信息
	 * @param entity
	 */
	void saveOrUpdate(BlogInfo entity);

	/**
	 * 分页查询博客文章
	 * @param entity
	 * @param pageInfo
	 * @return
	 */
    PageList<BlogInfo> queryPaing(BlogInfo entity, PageInfo pageInfo);

    BlogInfo findById(String id);

    /**
     * 查询最新文章处显示的文章信息
     * @param entity
     * @return
     */
    List<BlogInfo> queryNewTitle(BlogInfo entity);

    /**
     * 提交评论
     * @param blogComment
     */
    void submitComment(BlogComment blogComment);

    /**
     * 查询博客的所有评论，按评论日期倒序
     * @param blogId
     * @return
     */
    List<BlogComment> queryBlogComments(String blogId);

    /**
     * 记录文章的访问次数
     * @param blogId
     */
    void recordVisitTimes(String blogId);

    /**
     * 全文搜素
     * @param name
     * @param pageInfo
     * @return
     */
	PageList<BlogInfo> queryFullText(String name, PageInfo pageInfo);

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
    PageList<BlogInfo> listByBlogType(String treeId, PageInfo pageInfo);
    
    /**
     * 统计博客的访问次数
     * @return
     */
    Long countVisitTimes();
    
}
