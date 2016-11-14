package cn.zfcr.base.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.zfcr.base.service.ICityService;
import cn.zfcr.entity.City;
import cn.zfcr.mybatis.page.PageList;

@Controller
public class CityAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ICityService cityService;
	
	public String index(){
//		City city = new City();
////		city.setId("1");
//		PageList<City> cities = cityService.queryPaging(city, 1, 10);
//		System.out.println(cities);
		
		System.out.println(cityService.findAll());
		return "success";
	}
	
	public String goadd(){
		return "success";
	}

}
