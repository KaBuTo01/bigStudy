package com.example.forum.bean;



/**
 * @ClassName chat
 * @Author name
 * @Date 2022/11/16
 * @Description
 */
public class chat {
   private String header;
   private String title;
   private String comments;
   private String time;
   private int hint;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHint() {
        return hint;
    }

    public void setHint(int hint) {
        this.hint = hint;
    }

    public chat(String header, String title, String comments, String time, int hint) {
        this.header = header;
        this.title = title;
        this.comments = comments;
        this.time = time;
        this.hint = hint;
    }
}
