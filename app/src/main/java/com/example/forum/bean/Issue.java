package com.example.forum.bean;

/**
 * @ClassName Issue
 * @Author name
 * @Date 2022/11/24
 * @Description
 */
public class Issue {
    private int type;

    public Issue(int type, String text, String image, String voide) {
        this.type = type;
        this.text = text;
        this.image = image;
        this.voide = voide;
    }

    private String text;
    private String image;
    private String voide;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVoide() {
        return voide;
    }

    public void setVoide(String voide) {
        this.voide = voide;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
