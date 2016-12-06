package cn.zfcr.business.city.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.zfcr.business.city.service.ICityServcice;
import cn.zfcr.entity.City;

@Controller
public class CityAction {

	@Resource
	private ICityServcice cityServcice;
	
	public String index(){
		List<City> cities = cityServcice.findAll();
		System.out.println(cities.size());
		return "success";
	}
	
	public String findById(){
		List<City> cities = cityServcice.findById("1");
		System.out.println(cities.size());
		return "success";
	}
}
