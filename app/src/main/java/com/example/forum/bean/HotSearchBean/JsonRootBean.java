package com.example.forum.bean.HotSearchBean;

/**
 * @ClassName JsonRootBean
 * @Author name
 * @Date 2022/11/26
 * @Description
 */
/**
 * Copyright 2022 json.cn
 */

import java.util.List;

/**
 * Auto-generated: 2022-11-26 21:21:9
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class JsonRootBean {

    private int code;
    private String msg;
    private List<Data> data;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }

}
