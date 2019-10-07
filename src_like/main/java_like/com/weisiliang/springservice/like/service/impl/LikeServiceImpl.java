package com.weisiliang.springservice.like.service.impl;

import com.weisiliang.springservice.like.dao.LikeDao;
import com.weisiliang.springservice.like.entity.Like;
import com.weisiliang.springservice.like.service.LikeCountService;
import com.weisiliang.springservice.like.service.LikeService;
import com.weisiliang.springservice.like.service.LikeUserService;
import com.weisiliang.springservice.like.style.LikeParamStyle;
import com.weisiliang.springservice.like.style.LikeTargetStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;


@Component
public class LikeServiceImpl<U extends Serializable,T> implements LikeService {

    @Autowired
    LikeDao likeDao;

    @Autowired
    LikeUserService<U> likeUserService;
@Autowired
    LikeCountService likeCountService;

//    @Override
//    public Page<Like> findAllByTargetId(LikeTargetStyle targetType, String targetId, Pageable pageable, Set setUser) {
//        return null;
//    }

    @Override
    public Page<Like> findAllByTargetId(Integer targetType, String targetId, Pageable pageable, Set setUser){
        Page<Like> likePage= likeDao.findAllByTargetValue(targetType,targetId,pageable);

        if(setUser.contains(LikeParamStyle.USER)){
            for(Like like:likePage.getContent()){
                U u=likeUserService.findUserById(like.getUserId());
                if(u==null){
                    continue;
                }
                like.setUser(u);
            }
        }

        return likePage;
    }

    @Override
    public Page<Like> findAllByUserId(Integer targetType, String userId, int pageNo, int pageSize) {
        return likeDao.findAllByUserId(targetType,userId,PageRequest.of(pageNo,pageSize,new Sort(Sort.Direction.DESC,"createTime")));
    }

    @Override
    public String addLike(String userId, Integer targetType, String targetId) {

//        likeUserService.findUserById();

        Like like=new Like();

        like.setTargetType(targetType);
        like.setTargetId(targetId);
        like.setUserId(userId);

        Integer count= likeDao.countByUserId(userId,targetType,targetId);
        if(count==0){
            like.setCreateTime(System.currentTimeMillis());
            String id= likeDao.save(like).getId();

            //增加计数
            likeCountService.addCount(targetType,targetId);

            return id;
        }

        return "";
    }

    @Override
    public void deleteLike(String userId, Integer targetType, String targetId) {
        Like like=likeDao.findOneByTargetTypeAndTargetIdAndUserId(targetType,targetId,userId);
        if(like!=null){
            likeDao.delete(like);
            //减少计数
            likeCountService.reduceCount(targetType,targetId);

        }

    }

    @Override
    public int findCount(Integer targetType, String targetId) {
        return likeDao.countByTargetId(targetType,targetId);
    }

    @Override
    public int findCount(String userId, Integer targetType, String targetId) {
        return likeDao.countByUserId(userId,targetType,targetId);
    }

    @Override
    public Like findOne(String id) {

        return likeDao.getOne(id);
    }

    @Override
    public void delete(String id) {
        Like like=likeDao.getOne(id);
        likeDao.delete(like);
    }

    @Override
    public void delete(Integer targetType, String targetId, String userId) {
        Like like=likeDao.findOneByTargetTypeAndTargetIdAndUserId(targetType,targetId,userId);
        if(like==null){
            return;
        }
        likeDao.delete(like);
    }
}
