package com.haiwai.administrator.japanhouse.bean;

/**
 * Created by Administrator on 2018/5/22.
 */

public class LoginBean {

    /**
     * msg : 登录成功
     * code : 200
     * datas : {"id":22,"phone":"88888888888","nickname":"FCL用户6048","pic":"http://www.rongcloud.cn/images/logo.png","sex":null,"birthday":null,"status":"1","isDeleted":0,"createTime":1528790248000,"updateTime":1528791609047,"password":"a12345","token":"7ba4fed163793ddcf23d591e0e95478b","lastLoginTime":1528791609047,"wxId":null,"qqId":null,"lineId":null,"wbId":null,"rongCloudToken":"Q9h8Vbw/8Zqnt998LQY1NG7tnvnoFRHtvRSk65MeRaWjhNUpICiAMcn3YV6eKD5/uLIiCll71gD5a0EWCC4laA=="}
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
         * id : 22
         * phone : 88888888888
         * nickname : FCL用户6048
         * pic : http://www.rongcloud.cn/images/logo.png
         * sex : null
         * birthday : null
         * status : 1
         * isDeleted : 0
         * createTime : 1528790248000
         * updateTime : 1528791609047
         * password : a12345
         * token : 7ba4fed163793ddcf23d591e0e95478b
         * lastLoginTime : 1528791609047
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
        private Object sex;
        private Object birthday;
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

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
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
