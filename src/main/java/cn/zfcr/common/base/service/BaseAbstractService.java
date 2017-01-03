package cn.zfcr.common.base.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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

	
	protected boolean isEmpty(String value) {
		return StringUtils.isEmpty(value);
	}
	
	protected boolean isNotEmpty(String value) {
		return StringUtils.isNotEmpty(value);
	}
}
