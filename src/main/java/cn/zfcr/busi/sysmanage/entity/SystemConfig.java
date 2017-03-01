package cn.zfcr.busi.sysmanage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统配置表(t_system_config)
 * @author zhangfeng
 * @date 2017年02月15日
 * 
 */
@Table(name="t_system_config")
public class SystemConfig {
	
    @Id
    @GeneratedValue(generator="UUID")
    private String id;

    private String name;
    
    @Column(name="visitTimes")
    private Integer visitTimes;

    @Column(name="lastUpdateDate")
    private Date lastUpdateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(Integer visitTimes) {
        this.visitTimes = visitTimes;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "SystemConfig [id=" + id + ", name=" + name + ", visitTimes=" + visitTimes + ", lastUpdateDate="
                + lastUpdateDate + "]";
    }

}