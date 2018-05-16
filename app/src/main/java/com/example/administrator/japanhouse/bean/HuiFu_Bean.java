package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/5/11.
 */

public class HuiFu_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":4,"userId":1,"askId":1,"createTime":1524541894000,"updateTime":1524541894000,"isDeleted":0,"content":"内容","user":null,"description":"想买房没钱怎么办？怎么办？怎么办？怎么办？","answerNum":3},{"id":6,"userId":1,"askId":5,"createTime":1526027737000,"updateTime":1526027737000,"isDeleted":0,"content":"","user":null,"description":"好棒！好棒！好棒","answerNum":4},{"id":7,"userId":1,"askId":5,"createTime":1526027774000,"updateTime":1526027774000,"isDeleted":0,"content":"2222","user":null,"description":"好棒！好棒！好棒","answerNum":4},{"id":8,"userId":1,"askId":2,"createTime":1526028203000,"updateTime":1526028203000,"isDeleted":0,"content":"","user":null,"description":"问题内容","answerNum":3},{"id":9,"userId":1,"askId":2,"createTime":1526028514000,"updateTime":1526028514000,"isDeleted":0,"content":"","user":null,"description":"问题内容","answerNum":3},{"id":10,"userId":1,"askId":5,"createTime":1526028543000,"updateTime":1526028543000,"isDeleted":0,"content":"","user":null,"description":"好棒！好棒！好棒","answerNum":4},{"id":11,"userId":1,"askId":5,"createTime":1526028824000,"updateTime":1526028824000,"isDeleted":0,"content":"，，，，，","user":null,"description":"好棒！好棒！好棒","answerNum":4}]
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
         * id : 4
         * userId : 1
         * askId : 1
         * createTime : 1524541894000
         * updateTime : 1524541894000
         * isDeleted : 0
         * content : 内容
         * user : null
         * description : 想买房没钱怎么办？怎么办？怎么办？怎么办？
         * answerNum : 3
         */

        private int id;
        private int userId;
        private int askId;
        private long createTime;
        private long updateTime;
        private int isDeleted;
        private String content;
        private Object user;
        private String description;
        private int answerNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getAskId() {
            return askId;
        }

        public void setAskId(int askId) {
            this.askId = askId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
            this.user = user;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getAnswerNum() {
            return answerNum;
        }

        public void setAnswerNum(int answerNum) {
            this.answerNum = answerNum;
        }
    }
}
