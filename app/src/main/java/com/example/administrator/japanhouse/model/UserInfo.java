package com.example.administrator.japanhouse.model;

/**
 * admin  2018/6/6
 */
public class UserInfo {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"id":3,"phone":"13463109341","nickname":"FCL用户","pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849795&di=83a34452110e5bf1f69c0881b5805cf1&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F10%2F28%2F85bOOOPIC81_1024.jpg","sex":"1","birthday":1527156729000,"status":"1","isDeleted":0,"createTime":1527156773000,"updateTime":1526958309000,"password":"123","token":"74880cc6e0b1658f5467ccc1b3b41e3a","lastLoginTime":1526958309000,"wxId":null,"qqId":null,"lineId":null,"wbId":null}
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
         * id : 3
         * phone : 13463109341
         * nickname : FCL用户
         * pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849795&di=83a34452110e5bf1f69c0881b5805cf1&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F10%2F28%2F85bOOOPIC81_1024.jpg
         * sex : 1
         * birthday : 1527156729000
         * status : 1
         * isDeleted : 0
         * createTime : 1527156773000
         * updateTime : 1526958309000
         * password : 123
         * token : 74880cc6e0b1658f5467ccc1b3b41e3a
         * lastLoginTime : 1526958309000
         * wxId : null
         * qqId : null
         * lineId : null
         * wbId : null
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
        private String wxId;
        private String qqId;
        private String lineId;
        private String wbId;

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

        public String getWxId() {
            return wxId;
        }

        public void setWxId(String wxId) {
            this.wxId = wxId;
        }

        public String getQqId() {
            return qqId;
        }

        public void setQqId(String qqId) {
            this.qqId = qqId;
        }

        public String getLineId() {
            return lineId;
        }

        public void setLineId(String lineId) {
            this.lineId = lineId;
        }

        public String getWbId() {
            return wbId;
        }

        public void setWbId(String wbId) {
            this.wbId = wbId;
        }
    }
}
