package com.example.administrator.japanhouse.bean;

/**
 * Created by Mr赵 on 2018/5/11.
 */

public class WenDa_Details_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"id":1,"title":"想买房","userId":1,"createTime":1523934288000,"updateTime":1523934291000,"isDeleted":0,"description":"想买房没钱怎么办？怎么办？怎么办？怎么办？","descriptionType":1,"answerNum":null}
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
         * id : 1
         * title : 想买房
         * userId : 1
         * createTime : 1523934288000
         * updateTime : 1523934291000
         * isDeleted : 0
         * description : 想买房没钱怎么办？怎么办？怎么办？怎么办？
         * descriptionType : 1
         * answerNum : null
         */

        private int id;
        private String title;
        private int userId;
        private long createTime;
        private long updateTime;
        private int isDeleted;
        private String description;
        private int descriptionType;
        private Object answerNum;

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

        public Object getAnswerNum() {
            return answerNum;
        }

        public void setAnswerNum(Object answerNum) {
            this.answerNum = answerNum;
        }
    }
}
