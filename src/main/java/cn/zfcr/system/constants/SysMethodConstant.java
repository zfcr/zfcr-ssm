package cn.zfcr.system.constants;

import org.owasp.esapi.ESAPI;

/**  
 * Package: cn.zfcr.system.constants  
 *  
 * File: SysMethodConstant.java   
 *  
 *  常用方法
 *  
 * Author: zf   Email: zhangfeng2124@163.com
 * Date: 2016年11月9日 上午11:37:01 
 *  
 * Copyright @ 2016 Corpration Name  
 *   
 */
public class SysMethodConstant {

	/**
	 * sql防注入过滤
	 * @param sql
	 * @return
	 */
	public static String encodeForSQL(String sql) {
		return ESAPI.encoder().encodeForSQL(ClassConstant.ESAPI_CODEC_MYSQL, sql);
	}
}
