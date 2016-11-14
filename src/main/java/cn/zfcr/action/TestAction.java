package cn.zfcr.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.zfcr.entity.Country;
import cn.zfcr.mapper.CountryMapper;

@Controller
public class TestAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private CountryMapper countryMapper;
	
	public String index(){
		System.out.println("=====");
		Country country = new Country();
		country.setCode("ABW");
		country.setName("222");
		country = countryMapper.findBy(country);
		System.out.println(country);
		return "success";
	}

	public static void main(String[] args) {
		System.out.println(Integer.toString(1));
	}
}
