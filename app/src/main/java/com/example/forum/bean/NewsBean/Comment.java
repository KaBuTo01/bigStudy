package com.example.forum.bean.NewsBean;

/**
 * @ClassName Comment
 * @Author name
 * @Date 2022/12/1
 * @Description
 */
public class Comment {
        private int id;
        private String content;
        private String data;
        private String uhead;
        private String uname;
        private int uid;
        private int jid;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setData(String data) {
            this.data = data;
        }
        public String getData() {
            return data;
        }

        public void setUhead(String uhead) {
            this.uhead = uhead;
        }
        public String getUhead() {
            return uhead;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }
        public String getUname() {
            return uname;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
        public int getUid() {
            return uid;
        }

        public void setJid(int jid) {
            this.jid = jid;
        }
        public int getJid() {
            return jid;
        }


}
