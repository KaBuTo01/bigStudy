package com.example.forum.bean;

/**
 * @ClassName HomeBean
 * @Author name
 * @Date 2022/11/9
 * @Description
 */
public class HomeBean {
    private String image;
    private String title;
    public HomeBean() {
    }

    public HomeBean(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
