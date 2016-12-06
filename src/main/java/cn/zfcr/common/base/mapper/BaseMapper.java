package cn.zfcr.common.base.mapper;

import tk.mybatis.mapper.common.Mapper;

/**  
 * Package: cn.zfcr.common.base.mapper  
 *  
 * File: BaseMapper.java   
 * 
 * 基础Mapper，项目中所有Mappper都继承该类，方便扩展
 *  
 * Author: zf   Email: zhangfeng2124@163.com
 * Date: 2016年11月16日 上午11:07:40 
 *  
 * Copyright @ 2016 Corpration Name  
 *   
 */
public interface BaseMapper<T> extends Mapper<T> {

}
