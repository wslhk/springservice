package com.weisiliang.springservice.like.resp;

/**
 * Created by jia on 2016/12/23.
 */
import com.weisiliang.springservice.api.response.base.ApiPageResponse;
import com.weisiliang.springservice.api.response.base.ApiResponse;
import com.weisiliang.springservice.like.entity.Like;

import java.util.List;

public class GetLikePageResponse extends ApiPageResponse {
    List<Like> likeList;

    public List<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList;
    }
}
