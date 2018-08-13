package com.haiwai.administrator.japanhouse.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：  admin on 2018/6/6 09:19
 * 邮箱：github.com
 */
public class SingUpBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":2,"activityNameCn":"活动名称","activityNameJpn":"活动名称","priceCn":"价格","priceJpn":"价格","summarizeCn":"概述","summarizeJpn":"概述","peopleCount":10,"createTime":1526380661000,"updateTime":1526380661000,"isDeleted":0,"status":"","startTime":1527148353000,"endTime":1528271556000,"images":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1527823590&di=8869e90fa7328ac62c094d27b4bfbe02&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg,https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527833774869&di=554e1ab6c341d02bd30f898955c119f5&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F130107%2F1-13010F92F9.jpg","tagsCn":"标签（中文），逗号隔开","tagsJpn":"标签（中文），逗号隔开","kfPhone":"110","usingRangeCn":"海淀区","usingRangeJpn":"海淀区","houseAreaCn":"120平方米","houseAreaJpn":"120平方米","kpTime":1528529944000,"addressCn":"海淀区-昌平","addressJpn":"海淀区-昌平","bmNum":null,"isbm":1,"currentTime":1528684943182,"hwdcBroker":{"id":2,"brokerName":"姓名","phone":"1234","password":"1232132","telePhone":"123132","shop":"所属门店","turnover":100,"inYears":3,"period":1,"counts":60,"pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527155765877&di=6b937ef9850ce295871cc7e3a9ef4393&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fa08b87d6277f9e2fd7e815091530e924b999f3d6.jpg","nickname":"昵称","sex":"0","wechatId":"123456","caeateTime":1525781849000,"updateTime":1526380661000,"isDeleted":0,"status":""}}]
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

    public static class DatasBean implements Serializable {
        /**
         * id : 2
         * activityNameCn : 活动名称
         * activityNameJpn : 活动名称
         * priceCn : 价格
         * priceJpn : 价格
         * summarizeCn : 概述
         * summarizeJpn : 概述
         * peopleCount : 10
         * createTime : 1526380661000
         * updateTime : 1526380661000
         * isDeleted : 0
         * status :
         * startTime : 1527148353000
         * endTime : 1528271556000
         * images : https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1527823590&di=8869e90fa7328ac62c094d27b4bfbe02&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg,https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527833774869&di=554e1ab6c341d02bd30f898955c119f5&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F130107%2F1-13010F92F9.jpg
         * tagsCn : 标签（中文），逗号隔开
         * tagsJpn : 标签（中文），逗号隔开
         * kfPhone : 110
         * usingRangeCn : 海淀区
         * usingRangeJpn : 海淀区
         * houseAreaCn : 120平方米
         * houseAreaJpn : 120平方米
         * kpTime : 1528529944000
         * addressCn : 海淀区-昌平
         * addressJpn : 海淀区-昌平
         * bmNum : null
         * isbm : 1
         * currentTime : 1528684943182
         * hwdcBroker : {"id":2,"brokerName":"姓名","phone":"1234","password":"1232132","telePhone":"123132","shop":"所属门店","turnover":100,"inYears":3,"period":1,"counts":60,"pic":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527155765877&di=6b937ef9850ce295871cc7e3a9ef4393&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fa08b87d6277f9e2fd7e815091530e924b999f3d6.jpg","nickname":"昵称","sex":"0","wechatId":"123456","caeateTime":1525781849000,"updateTime":1526380661000,"isDeleted":0,"status":""}
         */

        private int id;
        private String activityNameCn;
        private String activityNameJpn;
        private String priceCn;
        private String priceJpn;
        private String summarizeCn;
        private String summarizeJpn;
        private int peopleCount;
        private long createTime;
        private long updateTime;
        private int isDeleted;
        private String status;
        private long startTime;
        private long endTime;
        private String images;
        private String tagsCn;
        private String tagsJpn;
        private String kfPhone;
        private String usingRangeCn;
        private String usingRangeJpn;
        private String houseAreaCn;
        private String houseAreaJpn;
        private long kpTime;
        private String addressCn;
        private String addressJpn;
        private Object bmNum;
        private int isbm;
        private long currentTime;
        private HwdcBrokerBean hwdcBroker;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getActivityNameCn() {
            return activityNameCn;
        }

        public void setActivityNameCn(String activityNameCn) {
            this.activityNameCn = activityNameCn;
        }

        public String getActivityNameJpn() {
            return activityNameJpn;
        }

        public void setActivityNameJpn(String activityNameJpn) {
            this.activityNameJpn = activityNameJpn;
        }

        public String getPriceCn() {
            return priceCn;
        }

        public void setPriceCn(String priceCn) {
            this.priceCn = priceCn;
        }

        public String getPriceJpn() {
            return priceJpn;
        }

        public void setPriceJpn(String priceJpn) {
            this.priceJpn = priceJpn;
        }

        public String getSummarizeCn() {
            return summarizeCn;
        }

        public void setSummarizeCn(String summarizeCn) {
            this.summarizeCn = summarizeCn;
        }

        public String getSummarizeJpn() {
            return summarizeJpn;
        }

        public void setSummarizeJpn(String summarizeJpn) {
            this.summarizeJpn = summarizeJpn;
        }

        public int getPeopleCount() {
            return peopleCount;
        }

        public void setPeopleCount(int peopleCount) {
            this.peopleCount = peopleCount;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getTagsCn() {
            return tagsCn;
        }

        public void setTagsCn(String tagsCn) {
            this.tagsCn = tagsCn;
        }

        public String getTagsJpn() {
            return tagsJpn;
        }

        public void setTagsJpn(String tagsJpn) {
            this.tagsJpn = tagsJpn;
        }

        public String getKfPhone() {
            return kfPhone;
        }

        public void setKfPhone(String kfPhone) {
            this.kfPhone = kfPhone;
        }

        public String getUsingRangeCn() {
            return usingRangeCn;
        }

        public void setUsingRangeCn(String usingRangeCn) {
            this.usingRangeCn = usingRangeCn;
        }

        public String getUsingRangeJpn() {
            return usingRangeJpn;
        }

        public void setUsingRangeJpn(String usingRangeJpn) {
            this.usingRangeJpn = usingRangeJpn;
        }

        public String getHouseAreaCn() {
            return houseAreaCn;
        }

        public void setHouseAreaCn(String houseAreaCn) {
            this.houseAreaCn = houseAreaCn;
        }

        public String getHouseAreaJpn() {
            return houseAreaJpn;
        }

        public void setHouseAreaJpn(String houseAreaJpn) {
            this.houseAreaJpn = houseAreaJpn;
        }

        public long getKpTime() {
            return kpTime;
        }

        public void setKpTime(long kpTime) {
            this.kpTime = kpTime;
        }

        public String getAddressCn() {
            return addressCn;
        }

        public void setAddressCn(String addressCn) {
            this.addressCn = addressCn;
        }

        public String getAddressJpn() {
            return addressJpn;
        }

        public void setAddressJpn(String addressJpn) {
            this.addressJpn = addressJpn;
        }

        public Object getBmNum() {
            return bmNum;
        }

        public void setBmNum(Object bmNum) {
            this.bmNum = bmNum;
        }

        public int getIsbm() {
            return isbm;
        }

        public void setIsbm(int isbm) {
            this.isbm = isbm;
        }

        public long getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(long currentTime) {
            this.currentTime = currentTime;
        }

        public HwdcBrokerBean getHwdcBroker() {
            return hwdcBroker;
        }

        public void setHwdcBroker(HwdcBrokerBean hwdcBroker) {
            this.hwdcBroker = hwdcBroker;
        }

        public static class HwdcBrokerBean implements Serializable {
            /**
             * id : 2
             * brokerName : 姓名
             * phone : 1234
             * password : 1232132
             * telePhone : 123132
             * shop : 所属门店
             * turnover : 100
             * inYears : 3
             * period : 1
             * counts : 60
             * pic : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527155765877&di=6b937ef9850ce295871cc7e3a9ef4393&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fa08b87d6277f9e2fd7e815091530e924b999f3d6.jpg
             * nickname : 昵称
             * sex : 0
             * wechatId : 123456
             * caeateTime : 1525781849000
             * updateTime : 1526380661000
             * isDeleted : 0
             * status :
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
        }
    }
}
