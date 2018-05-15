package com.example.administrator.japanhouse.bean;

/**
 * Created by Mr赵 on 2018/5/10.
 */

public class BaiKe_Detail_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"id":12,"titleCn":"购房百科标题","titleJpn":"日文","textType":"2","isDeleted":0,"status":"0","readNum":1,"createTime":null,"contentCn":"购房百科内容","contentJpn":""}
     */

    private String msg;
    private String code;
    private DatasBean datas;

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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 12
         * titleCn : 购房百科标题
         * titleJpn : 日文
         * textType : 2
         * isDeleted : 0
         * status : 0
         * readNum : 1
         * createTime : null
         * contentCn : 购房百科内容
         * contentJpn :
         */

        private int id;
        private String titleCn;
        private String titleJpn;
        private String textType;
        private int isDeleted;
        private String status;
        private int readNum;
        private Object createTime;
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

        public String getTextType() {
            return textType;
        }

        public void setTextType(String textType) {
            this.textType = textType;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getReadNum() {
            return readNum;
        }

        public void setReadNum(int readNum) {
            this.readNum = readNum;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
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
