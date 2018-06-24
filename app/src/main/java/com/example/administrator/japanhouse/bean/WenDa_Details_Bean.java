package com.example.administrator.japanhouse.bean;

/**
 * Created by Mr赵 on 2018/5/11.
 */

public class WenDa_Details_Bean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : {"id":24,"title":"q","userId":22,"createTime":1529827882000,"updateTime":1529827882000,"isDeleted":0,"description":"qwer","descriptionType":1,"answerNum":null,"hwdcUser":{"id":24,"phone":"88888888888","nickname":"y户","pic":"http://hwdc.oss-ap-northeast-1.aliyuncs.com/15298277262511529827736195.jpg","sex":"1","birthday":1214236800000,"status":"1","isDeleted":0,"createTime":1529827882000,"updateTime":1529827882000,"password":"a12345","token":"dee710826b0430fed8fb4cbdee0b43db","lastLoginTime":1529827314000,"wxId":null,"qqId":null,"lineId":null,"wbId":null,"rongCloudToken":null}}
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
         * id : 24
         * title : q
         * userId : 22
         * createTime : 1529827882000
         * updateTime : 1529827882000
         * isDeleted : 0
         * description : qwer
         * descriptionType : 1
         * answerNum : null
         * hwdcUser : {"id":24,"phone":"88888888888","nickname":"y户","pic":"http://hwdc.oss-ap-northeast-1.aliyuncs.com/15298277262511529827736195.jpg","sex":"1","birthday":1214236800000,"status":"1","isDeleted":0,"createTime":1529827882000,"updateTime":1529827882000,"password":"a12345","token":"dee710826b0430fed8fb4cbdee0b43db","lastLoginTime":1529827314000,"wxId":null,"qqId":null,"lineId":null,"wbId":null,"rongCloudToken":null}
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
        private HwdcUserBean hwdcUser;

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

        public HwdcUserBean getHwdcUser() {
            return hwdcUser;
        }

        public void setHwdcUser(HwdcUserBean hwdcUser) {
            this.hwdcUser = hwdcUser;
        }

        public static class HwdcUserBean {
            /**
             * id : 24
             * phone : 88888888888
             * nickname : y户
             * pic : http://hwdc.oss-ap-northeast-1.aliyuncs.com/15298277262511529827736195.jpg
             * sex : 1
             * birthday : 1214236800000
             * status : 1
             * isDeleted : 0
             * createTime : 1529827882000
             * updateTime : 1529827882000
             * password : a12345
             * token : dee710826b0430fed8fb4cbdee0b43db
             * lastLoginTime : 1529827314000
             * wxId : null
             * qqId : null
             * lineId : null
             * wbId : null
             * rongCloudToken : null
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
            private Object wxId;
            private Object qqId;
            private Object lineId;
            private Object wbId;
            private Object rongCloudToken;

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

            public Object getWxId() {
                return wxId;
            }

            public void setWxId(Object wxId) {
                this.wxId = wxId;
            }

            public Object getQqId() {
                return qqId;
            }

            public void setQqId(Object qqId) {
                this.qqId = qqId;
            }

            public Object getLineId() {
                return lineId;
            }

            public void setLineId(Object lineId) {
                this.lineId = lineId;
            }

            public Object getWbId() {
                return wbId;
            }

            public void setWbId(Object wbId) {
                this.wbId = wbId;
            }

            public Object getRongCloudToken() {
                return rongCloudToken;
            }

            public void setRongCloudToken(Object rongCloudToken) {
                this.rongCloudToken = rongCloudToken;
            }
        }
    }
}
