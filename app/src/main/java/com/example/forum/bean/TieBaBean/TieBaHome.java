package com.example.forum.bean.TieBaBean;

/**
 * @ClassName TieBaHome
 * @Author name
 * @Date 2022/12/2
 * @Description
 */
public class TieBaHome {
        private int id;
        private String image;
        private String name;
        private int concern;
        private int num;
        private int type;
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

        public void setConcern(int concern) {
            this.concern = concern;
        }
        public int getConcern() {
            return concern;
        }

        public void setNum(int num) {
            this.num = num;
        }
        public int getNum() {
            return num;
        }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
