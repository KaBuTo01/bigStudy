package com.example.forum.bean;

/**
 * @ClassName catalog
 * @Author name
 * @Date 2022/11/20
 * @Description
 */
public class catalog {
    private Integer index;
    private String name;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public catalog(Integer index, String name) {
        this.index = index;
        this.name = name;
    }
}
