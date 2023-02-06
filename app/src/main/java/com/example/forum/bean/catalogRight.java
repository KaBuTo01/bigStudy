package com.example.forum.bean;

/**
 * @ClassName catalogRight
 * @Author name
 * @Date 2022/11/20
 * @Description
 */
public class catalogRight {
    private String images;
    private String title;
    private String introduce;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public catalogRight(String images, String title, String introduce) {
        this.images = images;
        this.title = title;
        this.introduce = introduce;
    }
}
