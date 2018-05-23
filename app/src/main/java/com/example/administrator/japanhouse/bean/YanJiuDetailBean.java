package com.example.administrator.japanhouse.bean;

/**
 * Created by Administrator on 2018/5/23.
 */

public class YanJiuDetailBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"yjTitle":"一居大阳台","yjType":1,"isDeleted":"0","createTime":1527064650000,"isTop":0,"updateTime":1527064653000,"id":2,"yjContent":"一居大阳台、一居大阳台"}
     */
    private String msg;
    private String code;
    private DatasEntity datas;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * yjTitle : 一居大阳台
         * yjType : 1
         * isDeleted : 0
         * createTime : 1527064650000
         * isTop : 0
         * updateTime : 1527064653000
         * id : 2
         * yjContent : 一居大阳台、一居大阳台
         */
        private String yjTitle;
        private int yjType;
        private String isDeleted;
        private long createTime;
        private int isTop;
        private long updateTime;
        private int id;
        private String yjContent;

        public void setYjTitle(String yjTitle) {
            this.yjTitle = yjTitle;
        }

        public void setYjType(int yjType) {
            this.yjType = yjType;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setIsTop(int isTop) {
            this.isTop = isTop;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setYjContent(String yjContent) {
            this.yjContent = yjContent;
        }

        public String getYjTitle() {
            return yjTitle;
        }

        public int getYjType() {
            return yjType;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public long getCreateTime() {
            return createTime;
        }

        public int getIsTop() {
            return isTop;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public int getId() {
            return id;
        }

        public String getYjContent() {
            return yjContent;
        }
    }
}
