package com.weisiliang.springservice.like.service;


import com.weisiliang.springservice.like.entity.Like;
import com.weisiliang.springservice.like.entity.LikeCount;
import com.weisiliang.springservice.like.style.LikeParamStyle;
import com.weisiliang.springservice.like.style.LikeTargetStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;

public interface LikeCountService {

    /**
     * 查询所有数字
     * @param targetType
     * @param targetId

     * @return
     */
    Map<String,Integer> findAllByTargetId(Integer targetType, Set<String> targetId);


    void addCount(Integer targetType, String targetId);

    void reduceCount(Integer targetType, String targetId);

    public LikeCount findOne(String id);


    public void delete(String id);

//    public void delete(LikeTargetStyle targetType, String targetId);
}
