package com.example.administrator.japanhouse.model;

/**
 * admin  2018/6/6
 */
public class UserInfo {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"scnum":2,"dynum":0,"lxrnum":3,"lsjlnum":6,"user":{"id":22,"phone":"88888888888","nickname":"FCL用户","pic":"http://hwdc.oss-ap-northeast-1.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180621115832.jpg","sex":"1","birthday":185126400000,"status":"1","isDeleted":0,"createTime":1528790248000,"updateTime":1529647792000,"password":"a12345","token":"2c44a8504bef4b1800f126f5279c5789","lastLoginTime":1529647792000,"wxId":null,"qqId":null,"lineId":null,"wbId":null,"rongCloudToken":"Q9h8Vbw/8Zqnt998LQY1NG7tnvnoFRHtvRSk65MeRaWjhNUpICiAMcn3YV6eKD5/uLIiCll71gD5a0EWCC4laA=="},"daynum":9}
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
         * scnum : 2
         * dynum : 0
         * lxrnum : 3
         * lsjlnum : 6
         * user : {"id":22,"phone":"88888888888","nickname":"FCL用户","pic":"http://hwdc.oss-ap-northeast-1.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180621115832.jpg","sex":"1","birthday":185126400000,"status":"1","isDeleted":0,"createTime":1528790248000,"updateTime":1529647792000,"password":"a12345","token":"2c44a8504bef4b1800f126f5279c5789","lastLoginTime":1529647792000,"wxId":null,"qqId":null,"lineId":null,"wbId":null,"rongCloudToken":"Q9h8Vbw/8Zqnt998LQY1NG7tnvnoFRHtvRSk65MeRaWjhNUpICiAMcn3YV6eKD5/uLIiCll71gD5a0EWCC4laA=="}
         * daynum : 9
         */

        private int scnum;
        private int dynum;
        private int lxrnum;
        private int lsjlnum;
        private UserBean user;
        private int daynum;

        public int getScnum() {
            return scnum;
        }

        public void setScnum(int scnum) {
            this.scnum = scnum;
        }

        public int getDynum() {
            return dynum;
        }

        public void setDynum(int dynum) {
            this.dynum = dynum;
        }

        public int getLxrnum() {
            return lxrnum;
        }

        public void setLxrnum(int lxrnum) {
            this.lxrnum = lxrnum;
        }

        public int getLsjlnum() {
            return lsjlnum;
        }

        public void setLsjlnum(int lsjlnum) {
            this.lsjlnum = lsjlnum;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public int getDaynum() {
            return daynum;
        }

        public void setDaynum(int daynum) {
            this.daynum = daynum;
        }

        public static class UserBean {
            /**
             * id : 22
             * phone : 88888888888
             * nickname : FCL用户
             * pic : http://hwdc.oss-ap-northeast-1.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180621115832.jpg
             * sex : 1
             * birthday : 185126400000
             * status : 1
             * isDeleted : 0
             * createTime : 1528790248000
             * updateTime : 1529647792000
             * password : a12345
             * token : 2c44a8504bef4b1800f126f5279c5789
             * lastLoginTime : 1529647792000
             * wxId : null
             * qqId : null
             * lineId : null
             * wbId : null
             * rongCloudToken : Q9h8Vbw/8Zqnt998LQY1NG7tnvnoFRHtvRSk65MeRaWjhNUpICiAMcn3YV6eKD5/uLIiCll71gD5a0EWCC4laA==
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
            private String rongCloudToken;

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

            public String getRongCloudToken() {
                return rongCloudToken;
            }

            public void setRongCloudToken(String rongCloudToken) {
                this.rongCloudToken = rongCloudToken;
            }
        }
    }
}
