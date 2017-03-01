package cn.zfcr.busi.mapper;

import java.util.List;

import cn.zfcr.busi.entity.BlogInfo;
import cn.zfcr.common.base.mapper.BaseMapper;

public interface BlogInfoMapper extends BaseMapper<BlogInfo>{
    
    public List<BlogInfo> queryNewTitle(BlogInfo entity);
   
}