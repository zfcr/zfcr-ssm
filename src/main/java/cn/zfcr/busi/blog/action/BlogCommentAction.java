package cn.zfcr.busi.blog.action;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import cn.zfcr.busi.blog.service.BlogManageService;
import cn.zfcr.busi.entity.BlogComment;
import cn.zfcr.common.base.action.BaseAction;

/**
 * 博客评论管理相关action操作
 * @author zf
 * @date 2017年2月7日
 *
 */
@Controller
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class BlogCommentAction extends BaseAction {

    @Resource
    private BlogManageService blogManageService;
    
    private BlogComment blogComment = new BlogComment();
    
    /**
     * 提交评论
     * @throws Exception 
     */
    public void submitComment() throws Exception {
        try {
            Assert.hasLength(blogComment.getBlogId(), "关联的博客ID不能为空！");
            blogManageService.submitComment(blogComment);
            writeStr(true, "");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            writeStr(false, "评论提交失败！");
        }
    }
    
    /**
     * 记录文章访问次数
     */
    public void recordVisitTimes() throws Exception {
        String blogId = getRequest().getParameter("blogId");
        blogManageService.recordVisitTimes(blogId);
    }

    public BlogComment getBlogComment() {
        return blogComment;
    }

    public void setBlogComment(BlogComment blogComment) {
        this.blogComment = blogComment;
    }

}
