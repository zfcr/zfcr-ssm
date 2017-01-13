package cn.zfcr.system.utils;

import java.util.UUID;

/**
 * 公共方法类
 * @author zhangfeng
 * @date 2017年01月12日
 * 
 */
public class CommonUtils {
    
    /**
     * 生成UUID 去掉“－”
     * @return
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
