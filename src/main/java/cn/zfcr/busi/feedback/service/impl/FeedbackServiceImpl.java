package cn.zfcr.busi.feedback.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zfcr.busi.entity.FeedbackInfo;
import cn.zfcr.busi.feedback.mapper.FeedbackInfoMapper;
import cn.zfcr.busi.feedback.service.FeedbackService;
import cn.zfcr.common.base.service.BaseAbstractService;
import cn.zfcr.mybatis.page.PageInfo;
import cn.zfcr.mybatis.page.PageList;
import cn.zfcr.system.constants.SystemConstants;

@Service
@Transactional
public class FeedbackServiceImpl extends BaseAbstractService implements FeedbackService{
	
	@Resource
	private FeedbackInfoMapper feedbackInfoMapper;

	@Override
	public void saved(FeedbackInfo entity) {
		// 当前留言是直接留言还是在其他留言基础上回复 upId不为空，即在该留言上回复，否则，是直接留言
		String levelId = calcLevelId(entity);
		entity.setLevelId(levelId);
		entity.setLevelNumber(levelId.split("\\.").length-1);
		entity.setCreateTime(new Date());
		entity.setPraiseTime(0);
		entity.setIcon(randomGetIconName());
		feedbackInfoMapper.insert(entity);
	}

	/**
	 * 计算LevelId
	 * @param entity
	 * @return
	 */
	private String calcLevelId(FeedbackInfo entity) {
		FeedbackInfo entityWhere = new FeedbackInfo();
		if(StringUtils.isEmpty(entity.getUpId())){
			entityWhere.setLevelId("%");
			entityWhere.setLevelNumber(0);
		}else{
			entityWhere.setLevelId(entity.getLevelId() + ".%");
			entityWhere.setLevelNumber(entity.getLevelId().split("\\.").length);
		}
		String levelId = feedbackInfoMapper.queryMaxLevelId(entityWhere);
		if(StringUtils.isEmpty(levelId)){
			if(StringUtils.isEmpty(entity.getLevelId())){
				levelId = "1";
			}else{
				levelId = entity.getLevelId() + ".1";
			}
		}else{
			if(levelId.indexOf(".") == -1){
				levelId = (Integer.parseInt(levelId) + 1) + "";
			}else{
				String lastLevelId = levelId.substring(levelId.lastIndexOf("."), levelId.length() -1);
				lastLevelId = (Integer.parseInt(lastLevelId) + 1) + "";
				levelId = levelId.substring(0, levelId.lastIndexOf(".")) + lastLevelId; 
			}
		}
		return levelId;
	}

	@Override
	public PageList<FeedbackInfo> queryPaging(FeedbackInfo entity, PageInfo pageInfo) {
		PageList<FeedbackInfo> feedbackInfos = (PageList<FeedbackInfo>) feedbackInfoMapper.selectByRowBounds(entity, pageInfo);
		int length = feedbackInfos.size();
		List<FeedbackInfo> results = new ArrayList<>();
		for (int i = 0; i < length; i++) {
			FeedbackInfo feedbackInfo = feedbackInfos.get(i);
			if(feedbackInfo.getLevelNumber() == 0){
				List<Map<String, Object>> childrens = new ArrayList<>();
				handleFeedbackSort(feedbackInfo.getId(), feedbackInfo.getUserName(), feedbackInfos, childrens);
				List<FeedbackInfo> list = new ArrayList<>();
				getSortFeedback(childrens, list);
				feedbackInfo.setChildrens(list);
				results.add(feedbackInfo);
			}
		}
		return new PageList<FeedbackInfo>(results, feedbackInfos.getPage(), feedbackInfos.getPageRows(), feedbackInfos.getTotalCount());
	}
	
	@SuppressWarnings("unchecked")
	public void getSortFeedback(List<Map<String, Object>> handleResults, List<FeedbackInfo> list){
		for (Map<String, Object> map : handleResults) {
			FeedbackInfo feedbackInfo = (FeedbackInfo) map.get("cur");
			list.add(feedbackInfo);
			
			List<Map<String, Object>> childrens = (List<Map<String, Object>>) map.get("childrens");
			if(childrens == null || childrens.size() <= 0){
				continue;
			}
			getSortFeedback(childrens, list);
		}
	}
	
	public void handleFeedbackSort(String upId,String parentUserName, PageList<FeedbackInfo> feedbackInfos, List<Map<String, Object>> childrens){
		for (FeedbackInfo feedbackInfo2 : feedbackInfos) {
			if(feedbackInfo2.getLevelNumber() == 0){
				continue;
			}
			if(upId.equals(feedbackInfo2.getUpId())){
				Map<String, Object> mapChildren = new HashMap<>();
				feedbackInfo2.setParentUserName(parentUserName);
				mapChildren.put("cur", feedbackInfo2);
				List<Map<String, Object>> childrens2 = new ArrayList<>();
				handleFeedbackSort(feedbackInfo2.getId(), feedbackInfo2.getUserName(), feedbackInfos, childrens2);
				mapChildren.put("childrens", childrens2);
				childrens.add(mapChildren);
			}
		}
	}

	@Override
	public void saveReply(FeedbackInfo entity) {
		FeedbackInfo upFeedbackInfo = feedbackInfoMapper.selectByPrimaryKey(entity.getUpId());
		FeedbackInfo where = new FeedbackInfo();
		where.setLevelId(upFeedbackInfo.getLevelId()+".%");
		where.setLevelNumber(upFeedbackInfo.getLevelNumber() + 1);
		String maxLevelId = feedbackInfoMapper.queryMaxLevelId(where);
		String lastId = "0";
		if(StringUtils.isNotEmpty(maxLevelId)){
			lastId = maxLevelId.substring(maxLevelId.lastIndexOf(".")+1, maxLevelId.length());
		}
		entity.setLevelId(upFeedbackInfo.getLevelId() + "." + (Integer.parseInt(lastId) + 1));
		entity.setLevelNumber(upFeedbackInfo.getLevelNumber() + 1);
		entity.setCreateTime(new Date());
		entity.setIcon(randomGetIconName());
		feedbackInfoMapper.insert(entity);
	}
	
	private String randomGetIconName() {
		Random random = new Random();
		int index = random.nextInt(SystemConstants.FEEDBACK_ICON_NAME.length);
		return SystemConstants.FEEDBACK_ICON_NAME[index];
	}

	@Override
    public int countFeedbacks(){
    	return feedbackInfoMapper.selectCount(new FeedbackInfo());
    }

}
