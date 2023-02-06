package com.example.forum.bean.Translate;

import java.util.List;

/**
 * @ClassName Translate
 * @Author name
 * @Date 2022/12/2
 * @Description
 */
public class Translate {
        private int code;
        private String msg;
        private List<Result> data;
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

        public void setData(List<Result> data) {
            this.data = data;
        }
        public List<Result> getData() {
            return data;
        }

}
