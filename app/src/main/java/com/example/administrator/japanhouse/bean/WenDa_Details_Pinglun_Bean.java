package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/5/11.
 */

public class WenDa_Details_Pinglun_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"userId":2,"askId":1,"createTime":1523951224000,"updateTime":1523951227000,"isDeleted":0,"content":"抢","user":null,"description":null,"answerNum":0},{"id":2,"userId":2,"askId":1,"createTime":1523953417000,"updateTime":1523953419000,"isDeleted":0,"content":"~~~~","user":null,"description":null,"answerNum":0},{"id":4,"userId":1,"askId":1,"createTime":1524541894000,"updateTime":1524541894000,"isDeleted":0,"content":"内容","user":null,"description":null,"answerNum":0}]
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
         * userId : 2
         * askId : 1
         * createTime : 1523951224000
         * updateTime : 1523951227000
         * isDeleted : 0
         * content : 抢
         * user : null
         * description : null
         * answerNum : 0
         */

        private int id;
        private int userId;
        private int askId;
        private long createTime;
        private long updateTime;
        private int isDeleted;
        private String content;
        private Object user;
        private Object description;
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

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
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
