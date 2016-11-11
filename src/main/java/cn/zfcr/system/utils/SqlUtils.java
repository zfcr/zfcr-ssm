package cn.zfcr.system.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**  
 * Package: cn.zfcr.system.constants.utils  
 *  
 * File: SqlUtils.java   
 * 
 * 与sql相关的常用类
 *  
 * Author: zf   Email: zhangfeng2124@163.com
 * Date: 2016年11月9日 上午11:58:28 
 *  
 * Copyright @ 2016 Corpration Name  
 *   
 */
public class SqlUtils {
	
	/** 匹配sql中的order by子句 */
	public final static String PATTERN_ORDERBY = "order\\s*by[\\w|\\W|\\s|\\S]*";
	
	/** 拼装统计sql的模板 */
	public final static String SQL_COUNT_TEMPALTE = "select count(1) as cn from (%s) as temp";
	
	/** 
     * 去除hql的orderBy子句。 
     * @param hql 
     * @return 
     */  
	public static String removeOrders(String qlString) {  
        Pattern p = Pattern.compile(PATTERN_ORDERBY, Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(qlString);  
        StringBuffer sb = new StringBuffer();  
        while (m.find()) {  
            m.appendReplacement(sb, "");  
        }
        m.appendTail(sb);
        return sb.toString();  
    }
	
	/**
	 * 查询sql转换成统计sql
	 * @param sql
	 * @return
	 */
	public static String convertCountSql(String sql) {
		return String.format(SQL_COUNT_TEMPALTE, removeOrders(sql));
	}

}
