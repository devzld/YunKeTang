package com.emooc.yunketang.common.entity;

import java.io.Serializable;

/**
 * Created by BZT on 2016/1/20.
 */
public class CateEntity implements Serializable {
    private String text;
    private String imageUrl;

    public CateEntity(String text, String imageUrl) {
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
