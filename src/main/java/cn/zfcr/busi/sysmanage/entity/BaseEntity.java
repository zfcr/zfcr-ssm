package cn.zfcr.busi.sysmanage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** 
文件名：BaseEntity.java
类说明：  所有实体类的父类，定义实体类的一些公用属性/字段
作者：章锋
邮箱: zhangfeng2124@163.com
日期：2017年6月2日 上午11:33:17  
描述：章锋-基础模块
版本：1.0
*/
public class BaseEntity {

    /** 主键  */
    @Id
    @GeneratedValue(generator="UUID")
    protected String id;
    
    /** 创建用户 */
    @Column(name="createUser")
    protected String createUser;

    /** 创建时间 */
    @Column(name="createTime")
    protected Date createTime;

    /** 更新用户 */
    @Column(name="lastUpdateUser")
    protected String lastUpdateUser;

    /** 更新时间 */
    @Column(name="lastUpdateTime")
    protected Date lastUpdateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
    
}
