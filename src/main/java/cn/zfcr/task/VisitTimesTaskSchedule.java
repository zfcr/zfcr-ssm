package cn.zfcr.task;

import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import cn.zfcr.busi.sysmanage.entity.SystemConfig;
import cn.zfcr.busi.sysmanage.entity.SystemVisitTimes;
import cn.zfcr.busi.sysmanage.units.SysManageConstants;
import cn.zfcr.busi.sysmanage.visittimes.service.ISystemConfigService;
import cn.zfcr.busi.sysmanage.visittimes.service.ISystemVisitTimesService;

/**
 * 访问次数记录的定时任务调度
 * @author zhangfeng
 * @date 2017年02月15日
 * 
 */
@Component
public class VisitTimesTaskSchedule {
    
    private Logger log = Logger.getLogger(VisitTimesTaskSchedule.class);

    /**
     * 每10分钟调用一次
     */
    @Scheduled(cron="0 0/10 * * * ?")
    public void taskJob(){
        log.info("启动访问次数定时存储任务.");
        ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
        if (ac == null) {
            log.info("访问次数定时存储任务-获取Spring上下文为null.");
            return;
        }
        log.info("访问次数定时存储任务－开始，处理数量："+SysManageConstants.getSystemConfigMap().size());
        ISystemVisitTimesService systemVisitTimesService = (ISystemVisitTimesService) ac.getBean("systemVisitTimesServiceImpl");
        ISystemConfigService systemConfigService = (ISystemConfigService) ac.getBean("systemConfigServiceImpl");
        int visitTimes = 0;
        if (SysManageConstants.getSystemConfigMap().size() > 0) {
            for (Iterator<Date> ite = SysManageConstants.getSystemConfigMap().keySet().iterator(); ite.hasNext();) {
                Date date = ite.next();
                SystemVisitTimes systemVisitTimes = SysManageConstants.getSystemConfigMap().get(date);
                visitTimes ++;
                systemVisitTimesService.saveOrUpdate(systemVisitTimes);
                SysManageConstants.putOrRemoveSystemConfigMap(systemVisitTimes, 2);
            }
        }
        try {
            if(visitTimes > 0){
                SystemConfig systemConfig = systemConfigService.getSystemConfig();
                systemConfig.setVisitTimes(systemConfig.getVisitTimes() + visitTimes);
                systemConfig.setLastUpdateDate(new Date());
                systemConfigService.update(systemConfig);
            }
            log.info("访问次数定时存储任务－结束.");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("访问次数定时存储任务处理异常.",e);
        }
        
    }
}
