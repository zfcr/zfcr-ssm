package cn.zfcr.busi.sysmanage.visittimes.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zfcr.busi.sysmanage.entity.SystemConfig;
import cn.zfcr.busi.sysmanage.mapper.SystemConfigMapper;
import cn.zfcr.busi.sysmanage.visittimes.service.ISystemConfigService;
import cn.zfcr.common.base.service.BaseAbstractService;

@Service
public class SystemConfigServiceImpl extends BaseAbstractService implements ISystemConfigService{

	@Resource
	private SystemConfigMapper systemConfigMapper;

    @Override
    public void update(SystemConfig systemConfig) {
        systemConfigMapper.updateByPrimaryKey(systemConfig);
    }

    @Override
    public SystemConfig getSystemConfig() throws Exception{
        List<SystemConfig> systemConfigs = systemConfigMapper.selectAll();
        if(systemConfigs == null || systemConfigs.size() <= 0){
            throw new NullPointerException("未初始化系统配置信息！");
        }
        return systemConfigs.get(0);
    }
	
	
}
