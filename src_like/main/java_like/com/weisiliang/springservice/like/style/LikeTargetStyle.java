package com.weisiliang.springservice.like.style;

public enum LikeTargetStyle {
    DEFAULT(0);
    int id;
    LikeTargetStyle(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
