package com.example.forum.bean.HotSearchBean;

import java.util.List;


public class Data {

    private List<Top> top;
    private List<Hot> hot;
    public void setTop(List<Top> top) {
        this.top = top;
    }
    public List<Top> getTop() {
        return top;
    }

    public void setHot(List<Hot> hot) {
        this.hot = hot;
    }
    public List<Hot> getHot() {
        return hot;
    }

}