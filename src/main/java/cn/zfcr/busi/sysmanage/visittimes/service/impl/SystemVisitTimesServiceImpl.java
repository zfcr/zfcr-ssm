package cn.zfcr.busi.sysmanage.visittimes.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zfcr.busi.sysmanage.entity.SystemVisitTimes;
import cn.zfcr.busi.sysmanage.mapper.SystemVisitTimesMapper;
import cn.zfcr.busi.sysmanage.visittimes.service.ISystemVisitTimesService;
import cn.zfcr.common.base.service.BaseAbstractService;

@Service
public class SystemVisitTimesServiceImpl extends BaseAbstractService implements ISystemVisitTimesService{

	@Resource
	private SystemVisitTimesMapper systemVisitTimesMapper;
	
	@Override
	public List<SystemVisitTimes> find(SystemVisitTimes systemVisitTimes){
		return systemVisitTimesMapper.select(systemVisitTimes);
	}
	
	@Override
	public void saveOrUpdate(SystemVisitTimes systemVisitTimes){
		if(isEmpty(systemVisitTimes.getId())){
			systemVisitTimesMapper.insert(systemVisitTimes);
		}else{
			systemVisitTimesMapper.updateByPrimaryKey(systemVisitTimes);
		}
	}

    @Override
    public SystemVisitTimes getById(String id) {
        return systemVisitTimesMapper.selectByPrimaryKey(id);
    }

}
