package com.weisiliang.springservice.like.service;


import com.weisiliang.springservice.like.entity.Like;
import com.weisiliang.springservice.like.style.LikeTargetStyle;
import org.springframework.data.domain.Page;

public interface LikeUserService<U> {

    U findUserById(String uid);

    /**
     * 通过上下文获取用户id
     * @return
     */
    String getCurrentUserId();
}
