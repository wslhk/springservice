package com.weisiliang.springservice.like.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 商城物流表
 */
@Entity
@Table(name="like_user_count")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class LikeUserCount {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    String id;

    /**
     * 创建时间
     */
    Long createTime;

    /**
     * 目标类型
     */
    Integer targetType;

    /**
     * userid
     */
    Integer userId;


    Integer count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
