package com.example.administrator.japanhouse.model;

import java.util.List;

/**
 * Created by   admin on 2018/5/16.
 */

public class ZNHouseBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"theKeyword":"关键字","answer":"回复","createTime":1526366884000,"updateTime":1526366887000,"isDeleted":"0"}]
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
         * theKeyword : 关键字
         * answer : 回复
         * createTime : 1526366884000
         * updateTime : 1526366887000
         * isDeleted : 0
         */

        private int id;
        private String theKeyword;
        private String answer;
        private long createTime;
        private long updateTime;
        private String isDeleted;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTheKeyword() {
            return theKeyword;
        }

        public void setTheKeyword(String theKeyword) {
            this.theKeyword = theKeyword;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
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

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }
    }
}
