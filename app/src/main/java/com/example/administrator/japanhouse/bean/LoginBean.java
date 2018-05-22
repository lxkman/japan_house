package com.example.administrator.japanhouse.bean;

/**
 * Created by Administrator on 2018/5/22.
 */

public class LoginBean {

    /**
     * msg : 登录成功
     * code : 200
     * datas : {"uid":3,"logintime":1526958309214,"token":"74880cc6e0b1658f5467ccc1b3b41e3a"}
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
         * uid : 3
         * logintime : 1526958309214
         * token : 74880cc6e0b1658f5467ccc1b3b41e3a
         */

        private int uid;
        private long logintime;
        private String token;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public long getLogintime() {
            return logintime;
        }

        public void setLogintime(long logintime) {
            this.logintime = logintime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
