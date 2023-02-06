package com.example.forum.bean;

/**
 * @ClassName News
 * @Author name
 * @Date 2022/11/22
 * @Description
 */
public class News {
    private String text;
    private String image;
    private String name;
    private String date;

    public News(String text, String image, String name, String date, int type) {
        this.text = text;
        this.image = image;
        this.name = name;
        this.date = date;
        Type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private  int Type;

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

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }


}
