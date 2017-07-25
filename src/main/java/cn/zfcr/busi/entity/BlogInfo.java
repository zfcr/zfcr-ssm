package cn.zfcr.busi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 博客信息表(t_blog_info)
 * @author zhangfeng
 * @date 2017年01月10日
 * 
 */
@Table(name="t_blog_info")
public class BlogInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    private String title;
    
    private String content;

    @Column(name="blogType")
    private String blogType;
    
    /** 自定义分类 即文章分类的下级分类  */
    @Column(name="customType")
    private String customType;

    private String summary;

    @Column(name="blogTag")
    private String blogTag;

    /** 博客状态 0:草稿,1:正常,2:推荐 */
    @Column(name="blogStatus")
    private String blogStatus="0";

    @Column(name="createUser")
    private String createUser;

    @Column(name="createTime")
    private Date createTime;

    @Column(name="createAddr")
    private String createAddr;

    @Column(name="visitTimes")
    private Integer visitTimes;
    
    /** 评论次数 */
    @Column(name="comments")
    private Integer comments;
    
    @Column(name="lastUpdateUser")
    private String lastUpdateUser;
    
    @Column(name="lastUpdateTime")
    private Date lastUpdateTime;
    
    @Column(name="imagePath")
    private String imagePath;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

	public String getBlogType() {
		return blogType;
	}

	public void setBlogType(String blogType) {
		this.blogType = blogType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getBlogTag() {
		return blogTag;
	}

	public void setBlogTag(String blogTag) {
		this.blogTag = blogTag;
	}

	public String getBlogStatus() {
		return blogStatus;
	}

	public void setBlogStatus(String blogStatus) {
		this.blogStatus = blogStatus;
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

	public String getCreateAddr() {
		return createAddr;
	}

	public void setCreateAddr(String createAddr) {
		this.createAddr = createAddr;
	}

	public Integer getVisitTimes() {
		return visitTimes;
	}

	public void setVisitTimes(Integer visitTimes) {
		this.visitTimes = visitTimes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

	public String getCustomType() {
		return customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
	}

	@Override
	public String toString() {
		return "BlogInfo [id=" + id + ", title=" + title + ", content=" + content + ", blogType=" + blogType
				+ ", customType=" + customType + ", summary=" + summary + ", blogTag=" + blogTag + ", blogStatus="
				+ blogStatus + ", createUser=" + createUser + ", createTime=" + createTime + ", createAddr="
				+ createAddr + ", visitTimes=" + visitTimes + ", comments=" + comments + ", lastUpdateUser="
				+ lastUpdateUser + ", lastUpdateTime=" + lastUpdateTime + ", imagePath=" + imagePath + "]";
	}

}