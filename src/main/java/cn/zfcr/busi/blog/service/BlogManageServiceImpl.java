package cn.zfcr.busi.blog.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zfcr.busi.entity.BlogInfo;
import cn.zfcr.busi.mapper.BlogInfoMapper;
import cn.zfcr.common.base.service.BaseAbstractService;

@Service
@Transactional
public class BlogManageServiceImpl extends BaseAbstractService implements BlogManageService{
	
	@Resource
	private BlogInfoMapper blogInfoMapper;

	@Override
	public void saveOrUpdate(BlogInfo entity) {
	    entity.setSummary(entity.getSummary().replaceAll("\r\n", " "));
	    if(entity.getSummary().length() > 500){
	        entity.setSummary(entity.getSummary().substring(0, 500));
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
			blogInfoMapper.insert(entity);
		}
	}

}
