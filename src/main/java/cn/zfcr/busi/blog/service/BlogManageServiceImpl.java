package cn.zfcr.busi.blog.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.zfcr.busi.entity.BlogComment;
import cn.zfcr.busi.entity.BlogInfo;
import cn.zfcr.busi.mapper.BlogCommentMapper;
import cn.zfcr.busi.mapper.BlogInfoMapper;
import cn.zfcr.common.base.service.BaseAbstractService;
import cn.zfcr.mybatis.page.PageInfo;
import cn.zfcr.mybatis.page.PageList;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional
public class BlogManageServiceImpl extends BaseAbstractService implements BlogManageService{
	
	@Resource
	private BlogInfoMapper blogInfoMapper;
	
	@Resource
    private BlogCommentMapper blogCommentMapper;

	@Override
	public void saveOrUpdate(BlogInfo entity) {
	    entity.setSummary(entity.getSummary().replaceAll("\r\n", " "));
	    if(entity.getSummary().length() > 400){
	        entity.setSummary(entity.getSummary().substring(0, 400) + "...");
	    }
		if(StringUtils.isNotEmpty(entity.getId())){
			BlogInfo blogInfo = blogInfoMapper.selectByPrimaryKey(entity.getId());
			entity.setBlogStatus(blogInfo.getBlogStatus());
			if(StringUtils.isEmpty(entity.getCreateUser())){
			    entity.setLastUpdateUser("章锋");
			}else{
			    entity.setLastUpdateUser(entity.getCreateUser());
			}
			entity.setCreateUser(blogInfo.getCreateUser());
			entity.setCreateTime(blogInfo.getCreateTime());
			entity.setCreateAddr(blogInfo.getCreateAddr());
			entity.setLastUpdateTime(new Date());
			blogInfoMapper.updateByPrimaryKey(entity);
		}else{
			entity.setCreateAddr(ServletActionContext.getRequest().getRemoteAddr());
			if(StringUtils.isEmpty(entity.getCreateUser())){
			    entity.setCreateUser("章锋");
			}
			entity.setCreateTime(new Date());
			entity.setId(null);
			blogInfoMapper.insert(entity);
		}
	}

	@Override
	public PageList<BlogInfo> queryPaing(BlogInfo entity, PageInfo pageInfo){
	    PageList<BlogInfo> blogInfos = (PageList<BlogInfo>) blogInfoMapper.queryPaging(entity, pageInfo);
	    return blogInfos;
	}
	
	@Override
	public BlogInfo findById(String id){
	   return blogInfoMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<BlogInfo> queryNewTitle(BlogInfo entity){
	    return blogInfoMapper.queryNewTitle(entity);
	}
	
	@Override
    public void submitComment(BlogComment blogComment) {
	    BlogInfo blogInfo = blogInfoMapper.selectByPrimaryKey(blogComment.getBlogId());
	    Assert.isTrue(blogInfo != null, "博客文章不存在，请检查！");
	    
	    blogComment.setLastUpdateDate(new Date());
	    if(StringUtils.isEmpty(blogComment.getNickName())){
	        blogComment.setNickName(generateNickName());
	    }
	    blogComment.setId(null);
	    blogCommentMapper.insert(blogComment);
	    blogInfo.setContent(null);
	    blogInfo.setComments(blogInfo.getComments()+1);
	    blogInfoMapper.updateByPrimaryKeySelective(blogInfo);
	}
	
	private String generateNickName(){
	    String str = "abcdefghijklmnopqrstuvwxyz0123456789";  
	    Random random = new Random();  
	    StringBuilder sb = new StringBuilder();  
	    for (int i = 0; i < 6; i++) {  
	        int num = random.nextInt(36);  
	        sb.append(str.charAt(num));  
	    }  
	    return sb.toString();  
	}

    @Override
    public List<BlogComment> queryBlogComments(String blogId) {
        Example example = new Example(BlogComment.class);
        example.createCriteria().andEqualTo("blogId", blogId);
        example.setOrderByClause("lastUpdateDate desc");
        return blogCommentMapper.selectByExample(example);
    }

    @Override
    public void recordVisitTimes(String blogId) {
        BlogInfo blogInfo = findById(blogId);
        Assert.isTrue(blogInfo != null, "没有找到对应的博客文章，blogId:"+blogId);
        BlogInfo entity = new BlogInfo();
        entity.setId(blogInfo.getId());
        entity.setBlogStatus(blogInfo.getBlogStatus());
        entity.setVisitTimes(blogInfo.getVisitTimes() == null ? 1 : blogInfo.getVisitTimes() + 1);
        blogInfoMapper.updateByPrimaryKeySelective(entity);
    }

	@Override
	public PageList<BlogInfo> queryFullText(String name, PageInfo pageInfo) {
		PageList<BlogInfo> blogInfos = blogInfoMapper.queryFullText(name, pageInfo);
		return blogInfos;
	}

	@Override
	public List<Map<String, Object>> countTitleTypeNum() {
		return blogInfoMapper.countTitleTypeNum();
	}

    @Override
    public PageList<BlogInfo> listByBlogType(String treeId, PageInfo pageInfo) {
        PageList<BlogInfo> blogInfos = (PageList<BlogInfo>) blogInfoMapper.listByBlogType(treeId, treeId + ".%", pageInfo);
        return blogInfos;
    }

	@Override
	public Long countVisitTimes() {
		return blogInfoMapper.countVisitTimes();
	}
}
