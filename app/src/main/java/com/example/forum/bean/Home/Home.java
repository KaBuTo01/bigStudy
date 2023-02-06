package com.example.forum.bean.Home;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * @ClassName Home
 * @Author name
 * @Date 2022/11/30
 * @Description
 */
public class Home {
        private int id;
        private int userid;
        private String head;
        private String username;
        private String title;
        private String url;
        private String content;
        private int tags;
        private int nt;
        private int collect;
        private String cover;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }
        public int getUserid() {
            return userid;
        }

        public void setHead(String head) {
            this.head = head;
        }
        public String getHead() {
            return head;
        }

        public void setUsername(String username) {
            this.username = username;
        }
        public String getUsername() {
            return username;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }

        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setTags(int tags) {
            this.tags = tags;
        }
        public int getTags() {
            return tags;
        }

        public void setNt(int nt) {
            this.nt = nt;
        }
        public int getNt() {
            return nt;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }
        public int getCollect() {
            return collect;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
        public String getCover() {
            return cover;
        }


}
