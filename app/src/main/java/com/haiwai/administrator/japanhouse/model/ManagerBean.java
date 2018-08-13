package com.haiwai.administrator.japanhouse.model;

/**
 * admin  2018/6/22
 */
public class ManagerBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"brokerinfo":{"id":1,"brokerName":"姓名","phone":"1234","password":"1232132","telePhone":"123132","shop":"所属门店","turnover":100,"inYears":3,"period":1,"counts":60,"pic":"http://hwdc.oss-ap-northeast-1.aliyuncs.com/abc.jpg","nickname":"昵称","sex":"0","wechatId":"123456","caeateTime":1525781849000,"updateTime":1525781851000,"isDeleted":0,"status":"","avgStar":0,"areaNameCn":"","areaNameJpn":""},"fwrs":3}
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
         * brokerinfo : {"id":1,"brokerName":"姓名","phone":"1234","password":"1232132","telePhone":"123132","shop":"所属门店","turnover":100,"inYears":3,"period":1,"counts":60,"pic":"http://hwdc.oss-ap-northeast-1.aliyuncs.com/abc.jpg","nickname":"昵称","sex":"0","wechatId":"123456","caeateTime":1525781849000,"updateTime":1525781851000,"isDeleted":0,"status":"","avgStar":0,"areaNameCn":"","areaNameJpn":""}
         * fwrs : 3
         */

        private BrokerinfoBean brokerinfo;
        private int fwrs;

        public BrokerinfoBean getBrokerinfo() {
            return brokerinfo;
        }

        public void setBrokerinfo(BrokerinfoBean brokerinfo) {
            this.brokerinfo = brokerinfo;
        }

        public int getFwrs() {
            return fwrs;
        }

        public void setFwrs(int fwrs) {
            this.fwrs = fwrs;
        }

        public static class BrokerinfoBean {
            /**
             * id : 1
             * brokerName : 姓名
             * phone : 1234
             * password : 1232132
             * telePhone : 123132
             * shop : 所属门店
             * turnover : 100
             * inYears : 3
             * period : 1
             * counts : 60
             * pic : http://hwdc.oss-ap-northeast-1.aliyuncs.com/abc.jpg
             * nickname : 昵称
             * sex : 0
             * wechatId : 123456
             * caeateTime : 1525781849000
             * updateTime : 1525781851000
             * isDeleted : 0
             * status :
             * avgStar : 0
             * areaNameCn :
             * areaNameJpn :
             */

            private int id;
            private String brokerName;
            private String phone;
            private String password;
            private String telePhone;
            private String shop;
            private int turnover;
            private int inYears;
            private int period;
            private int counts;
            private String pic;
            private String nickname;
            private String sex;
            private String wechatId;
            private long caeateTime;
            private long updateTime;
            private int isDeleted;
            private String status;
            private int avgStar;
            private String areaNameCn;
            private String areaNameJpn;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBrokerName() {
                return brokerName;
            }

            public void setBrokerName(String brokerName) {
                this.brokerName = brokerName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getTelePhone() {
                return telePhone;
            }

            public void setTelePhone(String telePhone) {
                this.telePhone = telePhone;
            }

            public String getShop() {
                return shop;
            }

            public void setShop(String shop) {
                this.shop = shop;
            }

            public int getTurnover() {
                return turnover;
            }

            public void setTurnover(int turnover) {
                this.turnover = turnover;
            }

            public int getInYears() {
                return inYears;
            }

            public void setInYears(int inYears) {
                this.inYears = inYears;
            }

            public int getPeriod() {
                return period;
            }

            public void setPeriod(int period) {
                this.period = period;
            }

            public int getCounts() {
                return counts;
            }

            public void setCounts(int counts) {
                this.counts = counts;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getWechatId() {
                return wechatId;
            }

            public void setWechatId(String wechatId) {
                this.wechatId = wechatId;
            }

            public long getCaeateTime() {
                return caeateTime;
            }

            public void setCaeateTime(long caeateTime) {
                this.caeateTime = caeateTime;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getAvgStar() {
                return avgStar;
            }

            public void setAvgStar(int avgStar) {
                this.avgStar = avgStar;
            }

            public String getAreaNameCn() {
                return areaNameCn;
            }

            public void setAreaNameCn(String areaNameCn) {
                this.areaNameCn = areaNameCn;
            }

            public String getAreaNameJpn() {
                return areaNameJpn;
            }

            public void setAreaNameJpn(String areaNameJpn) {
                this.areaNameJpn = areaNameJpn;
            }
        }
    }
}
