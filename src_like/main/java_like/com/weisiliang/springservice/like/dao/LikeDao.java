package com.weisiliang.springservice.like.dao;


import com.weisiliang.springservice.like.entity.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeDao extends JpaRepository<Like, String>, JpaSpecificationExecutor {

    @Query("select l from Like as l where l.targetType=:targetType and  l.targetId=:targetId")
    Page<Like> findAllByTargetValue(@Param("targetType") Integer targetType, @Param("targetId") String targetId, Pageable pageable);

    @Query("select l from Like as l where l.targetType=:targetType and  l.userId=:userId")
    Page<Like> findAllByUserId(@Param("targetType") Integer targetType, @Param("userId") String userId, Pageable pageable);

    @Query("select count(a) from Like as a where a.targetType=:targetType and a.targetId=:targetId")
    Integer countByTargetId(
            @Param("targetType") Integer targetType,
            @Param("targetId") String targetId
    );

    @Query("select count(a) from Like as a where a.targetType=:targetType and a.targetId=:targetId and a.userId=:userId")
    Integer countByUserId(
            @Param("userId") String userId,
            @Param("targetType") Integer targetType,
            @Param("targetId") String targetId
    );

    List<Like> findAllByUserId(Integer userId);

    Like findOneByTargetTypeAndTargetIdAndUserId(Integer targetType, String targetId, String userId);

}
