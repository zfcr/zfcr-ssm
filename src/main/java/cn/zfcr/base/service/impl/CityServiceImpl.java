package cn.zfcr.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zfcr.base.service.ICityService;
import cn.zfcr.common.base.dao.IBaseDao;
import cn.zfcr.common.base.dao.impl.CommonDaoImpl;
import cn.zfcr.common.constants.MyBatisStatementConstans;
import cn.zfcr.entity.City;

@Service
public class CityServiceImpl implements ICityService{
	
	@Resource
	protected IBaseDao<City> commonDao=new CommonDaoImpl<City>(City.class);

	@Override
	public List<City> findAll(){
		List<City> list = commonDao.findAll();
		return list;
	}
}
