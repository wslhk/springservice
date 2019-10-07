package com.weisiliang.springservice.like.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.weisiliang.springservice.like.style.LikeTargetStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 商城物流表
 */
@Entity
@Table(name="#{likeConfig.tableName}_count")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class LikeCount {

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


    Integer count;

    public void initToSave(Integer targetType,String targetId){
        this.createTime=System.currentTimeMillis();
        this.count=0;
        this.targetType=targetType;
        this.targetId=targetId;
    }

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
