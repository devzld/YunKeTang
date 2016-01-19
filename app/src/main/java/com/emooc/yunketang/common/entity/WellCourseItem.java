package com.emooc.yunketang.common.entity;

import java.io.Serializable;

/**
 * Created by BZT on 2016/1/14.
 */
public class WellCourseItem implements Serializable {
    private int type;
    private String title;
    private String imageUrl;
    private String url;
    private String price;

    public WellCourseItem(String title, String imageUrl, String url, String price) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.url = url;
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
