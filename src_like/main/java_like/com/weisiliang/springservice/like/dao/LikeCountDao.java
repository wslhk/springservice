package com.weisiliang.springservice.like.dao;


import com.weisiliang.springservice.like.entity.Like;
import com.weisiliang.springservice.like.entity.LikeCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LikeCountDao extends JpaRepository<LikeCount, String>, JpaSpecificationExecutor {

    @Query("select l from LikeCount as l where l.targetType=:targetType and  l.targetId=:targetId")
    LikeCount findByTargetId(@Param("targetType") Integer targetType, @Param("targetId") String targetId);

//    @Query("select l from Like as l where l.targetType=:targetType and  l.targetId=:targetId")
//    Page<Like> findAllByTargetValue(@Param("targetType")Integer targetType,@Param("targetId") String targetId, Pageable pageable);
}
