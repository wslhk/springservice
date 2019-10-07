package com.weisiliang.springservice.like.service;


import com.weisiliang.springservice.like.entity.Like;
import com.weisiliang.springservice.like.style.LikeParamStyle;
import com.weisiliang.springservice.like.style.LikeTargetStyle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface LikeService<U,T> {

    /**
     * 展示查询结果和主id
     * @param targetType
     * @param targetId

     * @return
     */
    Page<Like> findAllByTargetId(Integer targetType, String targetId, Pageable pageable, Set<LikeParamStyle> setUser);

//    Page<Like> findAllByTargetIdAndUser(LikeTargetStyle targetType, String targetId, Pageable pageable);
    /**
     * 按用户查询结果
     * @param targetType
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Like> findAllByUserId(Integer targetType, String userId, int pageNo, int pageSize);


    /**
     * 增加点赞事件,
     * @param userId
     * @param targetType
     * @param targetId
     * @return 点赞成功返回id 否则返回 ""
     */
    public String addLike(String userId, Integer targetType, String targetId);

    /**
     * 删除点赞
     * @param userId
     * @param targetType
     * @param targetId
     */
    public void deleteLike(String userId, Integer targetType, String targetId);

    /**
     * 获得点赞数
     * @param targetType
     * @param targetId
     * @return
     */
    public int findCount(Integer targetType, String targetId);

    public int findCount(String userId, Integer targetType, String targetId);

    public Like findOne(String id);

//    public Like update(Like like);

    public void delete(String id);

    public void delete(Integer targetType, String targetId, String userId);
}
