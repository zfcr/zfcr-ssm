package cn.zfcr.busi.feedback.service;

import cn.zfcr.busi.entity.FeedbackInfo;
import cn.zfcr.mybatis.page.PageInfo;
import cn.zfcr.mybatis.page.PageList;

/**
 * 留言板信息管理Service
 * @author zhangfeng
 * @date 2017年3月12日
 */
public interface FeedbackService {

	/**
	 * 新增留言信息
	 * @param entity
	 */
	void saved(FeedbackInfo entity);

	/**
	 * 分页查询留言信息
	 * @param entity
	 * @param pageInfo
	 * @return
	 */
	PageList<FeedbackInfo> queryPaging(FeedbackInfo entity, PageInfo pageInfo);

	/**
	 * 保存回复某留言的留言信息
	 * @param entity
	 */
	void saveReply(FeedbackInfo entity);

	/**
     * 统计博客的留言次数
     * @return
     */
	int countFeedbacks();

}
