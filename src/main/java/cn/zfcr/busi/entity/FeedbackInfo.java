package cn.zfcr.busi.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 留言板信息表(t_feedback_info)
 * @author zhangfeng
 * @date 2017年03月12日
 * 
 */
@Table(name="t_feedback_info")
public class FeedbackInfo {
    
	@Id
    @GeneratedValue(generator="UUID")
    private String id;

    @Column(name="levelId")
    private String levelId;

    @Column(name="levelNumber")
    private Integer levelNumber;

    @Column(name="upId")
    private String upId;

    private String message;

    private String icon;

    @Column(name="praiseTime")
    private Integer praiseTime;

    @Column(name="userName")
    private String userName;

    private String website;

    private String email;
    
    @Column(name="createTime")
    private Date createTime;

    private String remark;
    
    /**
     * 当前节点的子节点，即当前留言的回复留言
     */
    @Transient
    private List<FeedbackInfo> childrens = new ArrayList<>();
    
    /** 留言上级节点的用户 */
    @Transient
    private String parentUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPraiseTime() {
        return praiseTime;
    }

    public void setPraiseTime(Integer praiseTime) {
        this.praiseTime = praiseTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<FeedbackInfo> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<FeedbackInfo> childrens) {
		this.childrens = childrens;
	}

	public String getParentUserName() {
		return parentUserName;
	}

	public void setParentUserName(String parentUserName) {
		this.parentUserName = parentUserName;
	}

	@Override
	public String toString() {
		return "FeedbackInfo [id=" + id + ", levelId=" + levelId + ", levelNumber=" + levelNumber + ", upId=" + upId
				+ ", message=" + message + ", icon=" + icon + ", praiseTime=" + praiseTime + ", userName=" + userName
				+ ", website=" + website + ", email=" + email + ", createTime=" + createTime + ", remark=" + remark
				+ ", parentUserName=" + parentUserName + "]";
	}

}