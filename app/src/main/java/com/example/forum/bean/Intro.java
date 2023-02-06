package com.example.forum.bean;

import android.widget.ImageView;

/**
 * @ClassName Intro
 * @Author name
 * @Date 2022/11/26
 * @Description
 */
public class Intro {
    private String img;
    private String title;
    private String name;


    public Intro(String img, String title, String name) {
        this.img = img;
        this.title = title;
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
