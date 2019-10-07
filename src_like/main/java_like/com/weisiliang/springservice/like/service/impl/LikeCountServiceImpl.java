package com.weisiliang.springservice.like.service.impl;

import com.weisiliang.springservice.like.config.LikeConfig;
import com.weisiliang.springservice.like.dao.LikeCountDao;
import com.weisiliang.springservice.like.dao.LikeDao;
import com.weisiliang.springservice.like.entity.Like;
import com.weisiliang.springservice.like.entity.LikeCount;
import com.weisiliang.springservice.like.service.LikeCountService;
import com.weisiliang.springservice.like.service.LikeService;
import com.weisiliang.springservice.like.service.LikeUserService;
import com.weisiliang.springservice.like.style.LikeParamStyle;
import com.weisiliang.springservice.like.style.LikeTargetStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;


@Component
public class LikeCountServiceImpl implements LikeCountService {

    @Autowired
    LikeCountDao likeCountDao;

    @Override
    public Map<String, Integer> findAllByTargetId(Integer targetType, Set<String> targetId) {
        return null;
    }

    @Override
    public void addCount(Integer targetType, String targetId) {
        LikeCount likeCount= likeCountDao.findByTargetId(targetType,targetId);
        if(likeCount==null){
            likeCount=new LikeCount();
            likeCount.initToSave(targetType,targetId);
        }
        likeCount.setCount(likeCount.getCount()+1);
        likeCountDao.save(likeCount);
    }

    @Override
    public void reduceCount(Integer targetType, String targetId) {
        LikeCount likeCount= likeCountDao.findByTargetId(targetType,targetId);
        if(likeCount==null){
            likeCount=new LikeCount();
            likeCount.initToSave(targetType,targetId);
        }
        likeCount.setCount(likeCount.getCount()-1);
        likeCountDao.save(likeCount);
    }

    @Override
    public LikeCount findOne(String id) {
        return likeCountDao.getOne(id);
    }

    @Override
    public void delete(String id) {
        LikeCount lk=likeCountDao.getOne(id);
        if(lk!=null){
            likeCountDao.delete(lk);
        }

    }

//    @Override
//    public void delete(LikeTargetStyle targetType, String targetId) {
//        LikeCount lk=likeCountDao.findByTargetId(targetType.getId(),targetId);
//        if(lk!=null){
//            likeCountDao.delete(lk);
//        }
//    }
}
