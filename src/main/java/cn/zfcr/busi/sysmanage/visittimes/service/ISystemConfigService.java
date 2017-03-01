package cn.zfcr.busi.sysmanage.visittimes.service;

import cn.zfcr.busi.sysmanage.entity.SystemConfig;

public interface ISystemConfigService {

	/**
	 * 更新系统配置表
	 * id不能为空
	 * @param systemConfig
	 */
	void update(SystemConfig systemConfig);

	/**
	 * 获取系统配置信息
	 * @return
	 * @throws Exception 
	 */
	SystemConfig getSystemConfig() throws Exception;

}
