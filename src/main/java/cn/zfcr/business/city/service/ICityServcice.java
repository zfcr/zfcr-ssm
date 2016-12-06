package cn.zfcr.business.city.service;

import java.util.List;

import cn.zfcr.entity.City;

public interface ICityServcice {

	List<City> findAll();

	List<City> findById(String id);

}
