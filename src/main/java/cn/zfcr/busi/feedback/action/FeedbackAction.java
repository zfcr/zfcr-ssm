package cn.zfcr.busi.feedback.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import cn.zfcr.busi.blog.service.BlogManageService;
import cn.zfcr.busi.entity.BlogInfo;
import cn.zfcr.busi.entity.FeedbackInfo;
import cn.zfcr.busi.feedback.service.FeedbackService;
import cn.zfcr.common.base.action.BaseAction;
import cn.zfcr.mybatis.page.PageInfo;
import cn.zfcr.mybatis.page.PageList;

/**
 * 留言板信息管理相关action操作
 * @author zf
 * @date 2017年3月12日
 *
 */
@Controller
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class FeedbackAction extends BaseAction {

	@Resource
	private BlogManageService blogManageService;
	
	@Resource
	private FeedbackService feedbackService;
	
    private FeedbackInfo entity = new FeedbackInfo();
    
    /**
     * 跳转到留言板首页
     * @throws Exception 
     */
    public String index() throws Exception {
    	// 留言信息
    	PageList<FeedbackInfo> feedbackInfos = feedbackService.queryPaging(entity, new PageInfo(1, 20, "createTime"));
    	getRequest().setAttribute("feedbackInfos", feedbackInfos);
    	
    	// 最新文章(10条)
	    List<BlogInfo> newBlogInfos = blogManageService.queryNewTitle(new BlogInfo());
	    getRequest().setAttribute("newBlogInfos", newBlogInfos);
	    
	    List<Map<String, Object>> titleTypeNums = blogManageService.countTitleTypeNum();
		getRequest().setAttribute("titleTypeNums", titleTypeNums);
	    
	    getRequest().setAttribute("flag", getRequest().getParameter("flag"));
	    
	    getRequest().setAttribute("feedbacks", feedbackService.countFeedbacks());
	    getRequest().setAttribute("visitTimes", blogManageService.countVisitTimes());
	    return "index";
    }
    
    /**
     * 保存留言板信息
     */
    public void saved() throws Exception {
        try {
        	Assert.hasLength(entity.getUserName(),"请输入您的姓名或昵称！");
        	Assert.hasLength(entity.getMessage(),"说点什么吧！");
        	feedbackService.saved(entity);
            writeStr(true, "");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            writeStr(false, "留言保存失败！");
        }
    }
    
    /**
     * 保存回复某留言的留言信息
     */
    public void saveReply() throws Exception {
    	try {
    		Assert.hasLength(entity.getUserName(),"请求错误，请在博客留言页面进行留言！");
    		Assert.hasLength(entity.getMessage(),"请求错误，请在博客留言页面进行留言！");
    		Assert.hasLength(entity.getUpId(), "请求错误，请在博客留言页面进行留言！");
    		feedbackService.saveReply(entity);
    		writeStr(true, "");
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error("feedback/saveReply 异常，"+e.getMessage());
    		log.error(e);
    		writeStr(false, "留言保存失败！");
    	}
    }

	public FeedbackInfo getEntity() {
		return entity;
	}

	public void setEntity(FeedbackInfo entity) {
		this.entity = entity;
	}
    
}
