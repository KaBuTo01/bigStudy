package com.example.forum.bean.Circle;

/**
 * @ClassName CircleDetail
 * @Author name
 * @Date 2022/12/3
 * @Description
 */
public class CircleDetail {
        private int id;
        private int tieBaId;
        private int userId;
        private String userName;
        private String userHead;
        private String title;
        private String content;
        private String image1;
        private String image2;
        private String image3;
        private int type;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setTieBaId(int tieBaId) {
            this.tieBaId = tieBaId;
        }
        public int getTieBaId() {
            return tieBaId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
        public int getUserId() {
            return userId;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
        public String getUserName() {
            return userName;
        }

        public void setUserHead(String userHead) {
            this.userHead = userHead;
        }
        public String getUserHead() {
            return userHead;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }
        public String getImage1() {
            return image1;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }
        public String getImage2() {
            return image2;
        }

        public void setImage3(String image3) {
            this.image3 = image3;
        }
        public String getImage3() {
            return image3;
        }

        public void setType(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }


}
