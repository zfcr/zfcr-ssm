package cn.zfcr.system.utils;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

/**  
 * Package: cn.zfcr.utils  
 *  
 * File: ReflectionUtils.java   
 *  
 *  反射常用方法
 *  
 * Author: zf   Email: zhangfeng2124@163.com
 * Date: 2016年11月14日 下午5:13:51 
 *  
 * Copyright @ 2016 Corpration Name  
 *   
 */
public class ReflectionUtils extends org.springframework.util.ReflectionUtils {

	/**
	 * 调用属性的get方法，返回属性的值
	 * @param obj
	 * @param propName
	 * @return
	 */
	public static Object invokeGetMethodByProp(Object obj, String propName){
		Method method = findMethod(obj.getClass(), "get"+StringUtils.capitalize(propName));
		return invokeMethod(method, obj);
	}
	
}
