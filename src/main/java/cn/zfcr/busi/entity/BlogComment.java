package cn.zfcr.busi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 博客评论表(t_blog_comment)
 * @author zhangfeng
 * @date 2017年01月10日
 * 
 */
@Table(name="t_blog_comment")
public class BlogComment {
    
    @Id
    @GeneratedValue(generator="UUID")
    private String id;

    @Column(name="nickName")
    private String nickName;

    private String comment;

    @Column(name="lastUpdateDate")
    private Date lastUpdateDate;

    @Column(name="blogId")
    private String blogId;

    @Column(name="commentId")
    private String commentId;

    @Column(name="supportTimes")
    private Integer supportTimes;

    @Column(name="opposeTimes")
    private Integer opposeTimes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Integer getSupportTimes() {
        return supportTimes;
    }

    public void setSupportTimes(Integer supportTimes) {
        this.supportTimes = supportTimes;
    }

    public Integer getOpposeTimes() {
        return opposeTimes;
    }

    public void setOpposeTimes(Integer opposeTimes) {
        this.opposeTimes = opposeTimes;
    }

    @Override
    public String toString() {
        return "BlogComment [id=" + id + ", nickName=" + nickName + ", comment=" + comment + ", lastUpdateDate="
                + lastUpdateDate + ", blogId=" + blogId + ", commentId=" + commentId + ", supportTimes=" + supportTimes
                + ", opposeTimes=" + opposeTimes + "]";
    }

}