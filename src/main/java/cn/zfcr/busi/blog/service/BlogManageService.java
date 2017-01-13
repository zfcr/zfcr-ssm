package cn.zfcr.busi.blog.service;

import cn.zfcr.busi.entity.BlogInfo;

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

}
