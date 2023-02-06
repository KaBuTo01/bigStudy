package com.example.forum.bean.NewsBean;

/**
 * @ClassName journalism
 * @Author name
 * @Date 2022/11/28
 * @Description
 */
public class Journalism {
    private int id;
    private String title;
    private String name;
    private String date;
    private int type;
    private String cover;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getCover() {
        return cover;
    }
}
