package com.weisiliang.springservice.like.config;

import com.weisiliang.springservice.like.style.LikeTargetStyle;
import lombok.Data;
import org.springframework.boot.autoconfigure.elasticsearch.jest.JestProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(
        prefix = "com.weisiliang.springservice.like.config"
)
//@PropertySource("classpath:application.properties")
public class LikeConfig {
    //com.weisiliang.springservice.like.table_pre

//    public static final String aa=tablepre;
     public String tableName;

     public Integer[] targetType;

    public LikeTargetStyle[] targetStyles;

    public Integer[] getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer[] targetType) {
        this.targetType = targetType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LikeTargetStyle[] getTargetStyles() {
        return targetStyles;
    }

    public void setTargetStyles(LikeTargetStyle[] targetStyles) {
        this.targetStyles = targetStyles;
    }

    public static class LikeTargetStyleObj{
        int id;
        String name;

    }
}
