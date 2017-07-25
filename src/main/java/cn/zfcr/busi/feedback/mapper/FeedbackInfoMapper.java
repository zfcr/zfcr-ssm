package cn.zfcr.busi.feedback.mapper;

import cn.zfcr.busi.entity.FeedbackInfo;
import cn.zfcr.common.base.mapper.BaseMapper;

/**
 * 留言板管理
 * @author zhangfeng
 * @date 2017年03月03日 14:25:55
 *
 */
public interface FeedbackInfoMapper  extends BaseMapper<FeedbackInfo>{
    
	public String queryMaxLevelId(FeedbackInfo entity);
}