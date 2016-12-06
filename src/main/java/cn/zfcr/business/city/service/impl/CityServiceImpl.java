package cn.zfcr.business.city.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.zfcr.business.city.service.ICityServcice;
import cn.zfcr.business.mapper.CityMapper;
import cn.zfcr.common.base.service.BaseAbstractService;
import cn.zfcr.entity.City;

@Service
public class CityServiceImpl extends BaseAbstractService implements ICityServcice{

	@Resource
	private CityMapper cityMapper;
	
	@Override
	public List<City> findAll() {
		return cityMapper.selectAll();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<City> findById(String id) {
		return (List<City>) commonQueryDao.findByStatementId(City.class, "findById", id);
	}
	
}
