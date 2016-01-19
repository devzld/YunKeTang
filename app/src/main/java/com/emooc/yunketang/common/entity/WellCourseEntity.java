package com.emooc.yunketang.common.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BZT on 2016/1/14.
 */
public class WellCourseEntity implements Serializable {
    private int layoutType;
    private String headName;
    private String headDes;
    private String bottomDes;

    public int getHashValue() {
        return hashValue;
    }

    public void setHashValue(int hashValue) {
        this.hashValue = hashValue;
    }

    private int hashValue;
    public List<WellCourseItem> list = new ArrayList<>();

    public WellCourseEntity(int layoutType, String headName, String headDes, String bottomDes, List<WellCourseItem> list) {
        this.layoutType = layoutType;
        this.headName = headName;
        this.headDes = headDes;
        this.bottomDes = bottomDes;
        this.list = list;
        this.hashValue = this.hashCode();
    }

    @Override
    public String toString() {
        return "WellCourseEntity{" +
                "layoutType=" + layoutType +
                ", headName='" + headName + '\'' +
                ", headDes='" + headDes + '\'' +
                ", bottomDes='" + bottomDes + '\'' +
                ", hashValue=" + hashValue +
                '}';
    }

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getHeadDes() {
        return headDes;
    }

    public void setHeadDes(String headDes) {
        this.headDes = headDes;
    }

    public String getBottomDes() {
        return bottomDes;
    }

    public void setBottomDes(String bottomDes) {
        this.bottomDes = bottomDes;
    }
}
