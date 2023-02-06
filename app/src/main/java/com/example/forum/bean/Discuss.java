package com.example.forum.bean;

/**
 * @ClassName Discuss
 * @Author name
 * @Date 2022/11/26
 * @Description
 */
public class Discuss {
    private String img;
    private String name;
    private String content;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Discuss(String img, String name, String content) {
        this.img = img;
        this.name = name;
        this.content = content;
    }
}
