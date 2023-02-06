package com.example.forum.bean.NewsBean;

/**
 * @ClassName Details
 * @Author name
 * @Date 2022/11/29
 * @Description
 */
public class Details {
        private int id;
        private int journalismId;
        private int sort;
        private int type;
        private String turl;
        private String vurl;
        private String iurl;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setJournalismId(int journalismId) {
            this.journalismId = journalismId;
        }
        public int getJournalismId() {
            return journalismId;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
        public int getSort() {
            return sort;
        }

        public void setType(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }

        public void setTurl(String turl) {
            this.turl = turl;
        }
        public String getTurl() {
            return turl;
        }

        public void setVurl(String vurl) {
            this.vurl = vurl;
        }
        public String getVurl() {
            return vurl;
        }

        public void setIurl(String iurl) {
            this.iurl = iurl;
        }
        public String getIurl() {
            return iurl;
        }

}
