package com.example.forum.bean.User;

/**
 * @ClassName User
 * @Author name
 * @Date 2022/12/4
 * @Description
 */
public class User {
        private int id;
        private String name;
        private String password;
        private String headimg;

    public User(int id, String name, String password, String headimg) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.headimg = headimg;
    }

    public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setPassword(String password) {
            this.password = password;
        }
        public String getPassword() {
            return password;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }
        public String getHeadimg() {
            return headimg;
        }

}
