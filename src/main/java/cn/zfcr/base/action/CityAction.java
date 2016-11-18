package cn.zfcr.base.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.zfcr.base.service.ICityService;
import cn.zfcr.entity.City;
import cn.zfcr.mybatis.page.PageList;

@Controller
public class CityAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(CityAction.class);
	
	@Resource
	private ICityService cityService;
	
	public String index(){
		System.out.println(cityService.findAll());
		log.debug("log4j test ======= ");
		return "success";
	}
	
	public String indexPage(){
//		System.out.println(cityService.queryPaging(new City(), 1, 10));
		log.info("log4j test =======- ");
		return "success";
	}
	
	public String test(){
		System.out.println("=====================");
		return "success";
	}
	
	public String goadd(){
		return "success";
	}

}
