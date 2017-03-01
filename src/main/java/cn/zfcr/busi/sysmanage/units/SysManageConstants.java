package cn.zfcr.busi.sysmanage.units;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import cn.zfcr.busi.sysmanage.entity.SystemVisitTimes;

/**
 * 系统管理模块中所需常量
 * @author zhangfeng
 * @date 2017年02月15日
 * 
 */
public class SysManageConstants {

    /**
     * 用于记录网站的访问次数，每访问一次，增加一个对象
     * 会有一个定时任务，定时将里面的数据保存到数据库中。
     */
    private static final ConcurrentHashMap<Date, SystemVisitTimes> systemConfigMap= new ConcurrentHashMap<Date, SystemVisitTimes>();
    
    public static ConcurrentHashMap<Date, SystemVisitTimes> getSystemConfigMap(){
        return systemConfigMap;
    }
    
    /**
     * 添加或删除Map中对象
     * 1:添加,2:删除
     * @param systemVisitTimes
     */
    public synchronized static void putOrRemoveSystemConfigMap(SystemVisitTimes systemVisitTimes, int flag){
        if(flag == 1){
            systemConfigMap.put(systemVisitTimes.getVisitTime(), systemVisitTimes);
        }else{
            systemConfigMap.remove(systemVisitTimes.getVisitTime());
        }
    }
}
