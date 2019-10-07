package com.weisiliang.springservice.like.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.weisiliang.springservice.like.config.LikeConfig;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 商城物流表
 */
@Entity
@Table(name= "#{likeConfig.tableName}")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"},ignoreUnknown = true)
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Like<U> {

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
     * 目标id
     */
    String targetId;


    /**
     * 用户id
     */
    String userId;

    @Transient
    U user;


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

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public U getUser() {
        return user;
    }

    public void setUser(U user) {
        this.user = user;
    }
}
