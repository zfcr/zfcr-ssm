package cn.zfcr.base.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.zfcr.base.service.ICityService;
import cn.zfcr.entity.City;

@Controller
public class CityAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private ICityService cityService;
	
	public String index(){
		City city = new City();
//		city.setId("1");
		Map<String, Object> cities = cityService.findOfPage(city, 1, 10);
		System.out.println(cities);
		return "success";
	}
	
	public String goadd(){
		return "success";
	}

}
