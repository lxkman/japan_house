package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class TouTiaoListBean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"titleCn":"1","titleJpn":"1","status":"1","createTime":1524552167000,"isDeleted":0,"readNum":0,"contentCn":"","contentJpn":""}]
     */

    private String msg;
    private String code;
    private List<DatasBean> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 1
         * titleCn : 1
         * titleJpn : 1
         * status : 1
         * createTime : 1524552167000
         * isDeleted : 0
         * readNum : 0
         * contentCn :
         * contentJpn :
         */

        private int id;
        private String titleCn;
        private String titleJpn;
        private String status;
        private long createTime;
        private int isDeleted;
        private int readNum;
        private String contentCn;
        private String contentJpn;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public String getTitleJpn() {
            return titleJpn;
        }

        public void setTitleJpn(String titleJpn) {
            this.titleJpn = titleJpn;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public int getReadNum() {
            return readNum;
        }

        public void setReadNum(int readNum) {
            this.readNum = readNum;
        }

        public String getContentCn() {
            return contentCn;
        }

        public void setContentCn(String contentCn) {
            this.contentCn = contentCn;
        }

        public String getContentJpn() {
            return contentJpn;
        }

        public void setContentJpn(String contentJpn) {
            this.contentJpn = contentJpn;
        }
    }
}
