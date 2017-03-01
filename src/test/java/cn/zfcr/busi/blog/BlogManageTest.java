package cn.zfcr.busi.blog;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import cn.zfcr.busi.blog.service.BlogManageService;
import cn.zfcr.busi.entity.BlogInfo;
import cn.zfcr.mybatis.page.PageInfo;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class BlogManageTest {
	
	@Resource
	private BlogManageService blogManageService;

	@Test
	public void testQueryPaing(){
	    BlogInfo entity = new BlogInfo();
	    PageInfo pageInfo = new PageInfo(1, 10);
	    pageInfo.setOrderByClause("createTime desc");
	    blogManageService.queryPaing(entity, pageInfo);
	}
	
	@Test
    public void testQueryNewTitle(){
        BlogInfo entity = new BlogInfo();
        System.out.println(blogManageService.queryNewTitle(entity).get(0).getSummary());
    }
	
}
