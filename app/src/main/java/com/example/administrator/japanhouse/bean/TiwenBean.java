package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/5/11.
 */

public class TiwenBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"title":"想买房","userId":1,"createTime":1523934288000,"updateTime":1523934291000,"isDeleted":0,"description":"想买房没钱怎么办？怎么办？怎么办？怎么办？","descriptionType":1,"answerNum":3},{"id":2,"title":"问题标题","userId":1,"createTime":1524016207000,"updateTime":1524016207000,"isDeleted":0,"description":"问题内容","descriptionType":1,"answerNum":3},{"id":3,"title":"赵伟博","userId":1,"createTime":1526008420000,"updateTime":1526008420000,"isDeleted":0,"description":"好棒！好棒！好棒","descriptionType":111,"answerNum":0},{"id":4,"title":"赵伟博","userId":1,"createTime":1526008732000,"updateTime":1526008732000,"isDeleted":0,"description":"好棒！好棒！好棒","descriptionType":111,"answerNum":0},{"id":5,"title":"赵伟博","userId":1,"createTime":1526008747000,"updateTime":1526008747000,"isDeleted":0,"description":"好棒！好棒！好棒","descriptionType":2,"answerNum":4},{"id":6,"title":"赵伟博","userId":1,"createTime":1526008789000,"updateTime":1526008789000,"isDeleted":0,"description":"好棒！好棒！好棒","descriptionType":2,"answerNum":0},{"id":7,"title":"赵伟博","userId":1,"createTime":1526008808000,"updateTime":1526008808000,"isDeleted":0,"description":"好棒！好棒！好棒","descriptionType":2,"answerNum":0},{"id":8,"title":"sdadad","userId":1,"createTime":1526009672000,"updateTime":1526009672000,"isDeleted":0,"description":"好棒！好棒！好棒","descriptionType":1,"answerNum":0},{"id":9,"title":"sdadad","userId":1,"createTime":1526019655000,"updateTime":1526019655000,"isDeleted":0,"description":"好棒！好棒！好棒","descriptionType":1,"answerNum":0},{"id":10,"title":"sdadad","userId":1,"createTime":1526019856000,"updateTime":1526019856000,"isDeleted":0,"description":"好棒！好棒！好棒","descriptionType":1,"answerNum":0}]
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
         * title : 想买房
         * userId : 1
         * createTime : 1523934288000
         * updateTime : 1523934291000
         * isDeleted : 0
         * description : 想买房没钱怎么办？怎么办？怎么办？怎么办？
         * descriptionType : 1
         * answerNum : 3
         */

        private int id;
        private String title;
        private int userId;
        private long createTime;
        private long updateTime;
        private int isDeleted;
        private String description;
        private int descriptionType;
        private int answerNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDescriptionType() {
            return descriptionType;
        }

        public void setDescriptionType(int descriptionType) {
            this.descriptionType = descriptionType;
        }

        public int getAnswerNum() {
            return answerNum;
        }

        public void setAnswerNum(int answerNum) {
            this.answerNum = answerNum;
        }
    }
}
