package com.zfcr.common;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import cn.zfcr.common.base.service.IBaseService;
import cn.zfcr.entity.City;

/**  
 * Package: com.zfcr.common  
 *  
 * File: IBaseServiceTest.java   
 *  
 * Author: zf   Email: zhangfeng2124@163.com
 * Date: 2016年11月15日 下午9:59:50 
 *  
 * Copyright @ 2016 Corpration Name  
 *   
 */
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
public class IBaseServiceTest {
	
	@Resource
	private IBaseService<City> baseService;
	
	@Test
	public void testFindAll(){
		System.out.println(baseService.findAll().size());
	}
	
	@Test
	public void testFindAllOrder(){
		System.out.println(baseService.findAll(City.class, "name").size());
	}
	
	@Test
	public void testFindByDynamic(){
		City city = new City();
		city.setId("");
		List<City> cities = baseService.findByDynamic(city);
		for (City city2 : cities) {
			System.out.println(city2.getId() + "\t" + city2.getName());
		}
	}

}
