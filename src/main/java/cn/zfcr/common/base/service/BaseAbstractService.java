package cn.zfcr.common.base.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zfcr.common.dao.ICommonQueryDao;

/**
 * 基础的服务类
 * 项目中所有的服务类都继承该类
 * @author zf
 * @date 2016年11月23日
 *
 */
@Service
public class BaseAbstractService {
	
	@Resource
	protected ICommonQueryDao commonQueryDao;

}
