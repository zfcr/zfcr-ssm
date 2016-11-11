package cn.zfcr.system.constants;

import org.owasp.esapi.codecs.MySQLCodec;

/**  
 * Package: cn.zfcr.system.constants  
 *  
 * File: ClassConstant.java   
 *  常量类
 *  定义一些类的常量对象
 * Author: zf   Email: zhangfeng2124@163.com
 * Date: 2016年11月9日 上午11:33:43 
 *  
 * Copyright @ 2016 Corpration Name  
 *   
 */
public class ClassConstant {
	
	/**
	 * esapi中转换sql时，使用的mysql方言
	 */
	public static MySQLCodec ESAPI_CODEC_MYSQL = new MySQLCodec(MySQLCodec.Mode.STANDARD);

}
