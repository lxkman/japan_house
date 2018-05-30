package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/5/11.
 */

public class WenDa_Details_Pinglun_Bean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"userId":1,"askId":1,"createTime":1523951224000,"updateTime":1523951227000,"isDeleted":0,"content":null,"user":{"id":1,"phone":"13463109341","nickname":"FCL用户","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849795&di=83a34452110e5bf1f69c0881b5805cf1&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F10%2F28%2F85bOOOPIC81_1024.jpg","sex":"1","birthday":1523500486000,"status":"1","isDeleted":0,"createTime":1523951224000,"updateTime":1523951227000,"password":"1","token":"111","lastLoginTime":1525657714000},"description":null,"answerNum":0}]
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
         * userId : 1
         * askId : 1
         * createTime : 1523951224000
         * updateTime : 1523951227000
         * isDeleted : 0
         * content : null
         * user : {"id":1,"phone":"13463109341","nickname":"FCL用户","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849795&di=83a34452110e5bf1f69c0881b5805cf1&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F10%2F28%2F85bOOOPIC81_1024.jpg","sex":"1","birthday":1523500486000,"status":"1","isDeleted":0,"createTime":1523951224000,"updateTime":1523951227000,"password":"1","token":"111","lastLoginTime":1525657714000}
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
        private UserBean user;
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
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

        public static class UserBean {
            /**
             * id : 1
             * phone : 13463109341
             * nickname : FCL用户
             * pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849795&di=83a34452110e5bf1f69c0881b5805cf1&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F10%2F28%2F85bOOOPIC81_1024.jpg
             * sex : 1
             * birthday : 1523500486000
             * status : 1
             * isDeleted : 0
             * createTime : 1523951224000
             * updateTime : 1523951227000
             * password : 1
             * token : 111
             * lastLoginTime : 1525657714000
             */

            private int id;
            private String phone;
            private String nickname;
            private String pic;
            private String sex;
            private long birthday;
            private String status;
            private int isDeleted;
            private long createTime;
            private long updateTime;
            private String password;
            private String token;
            private long lastLoginTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(int isDeleted) {
                this.isDeleted = isDeleted;
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

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public long getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(long lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }
        }
    }
}
