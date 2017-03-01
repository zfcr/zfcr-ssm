package cn.zfcr.busi.sysmanage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统访问次数详细记录表(t_system_visit_times)
 * @author zhangfeng
 * @date 2017年02月15日
 * 
 */
@Table(name="t_system_visit_times")
public class SystemVisitTimes {
	
    @Id
    @GeneratedValue(generator="UUID")
    private String id;

    @Column(name="visitIp")
    private String visitIp;
    
    @Column(name="visitTime")
    private Date visitTime;

    @Column(name="visitUrl")
    private String visitUrl;
    
    @Column(name="visitTitleId")
    private String visitTitleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisitIp() {
        return visitIp;
    }

    public void setVisitIp(String visitIp) {
        this.visitIp = visitIp;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }

    public String getVisitTitleId() {
        return visitTitleId;
    }

    public void setVisitTitleId(String visitTitleId) {
        this.visitTitleId = visitTitleId;
    }

    @Override
    public String toString() {
        return "SystemVisitTimes [id=" + id + ", visitIp=" + visitIp + ", visitTime=" + visitTime + ", visitUrl="
                + visitUrl + ", visitTitleId=" + visitTitleId + "]";
    }
    
}