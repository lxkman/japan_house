package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */

public class FangjiaMapBean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : {"yeaybfb":1149155.5555999998,"monthbfb":848.6371403689386,"city":{"id":0,"administrationNameCn":"北京市","administrationNameJpn":"北京市","parentId":0,"createTime":1529824100718,"isDeleted":0,"citylevel":"","status":"","longitude":0,"latitude":0,"houseNum":0,"avgMoney":0,"priceStete":2,"hwdcAreaManages":[]},"bigandsmallval":{"endVal":"0.10","bigVal":"11.11"},"qys":[{"id":3,"administrationNameCn":"2222","administrationNameJpn":"2222","parentId":0,"createTime":1529824100662,"isDeleted":0,"citylevel":"3","status":"0","longitude":116.03,"latitude":39.53,"houseNum":0,"avgMoney":18998.448052,"priceStete":2,"hwdcAreaManages":[]},{"id":27,"administrationNameCn":"朝阳区","administrationNameJpn":"朝阳区","parentId":0,"createTime":1529824100663,"isDeleted":0,"citylevel":"3","status":"0","longitude":116.46,"latitude":39.83,"houseNum":0,"avgMoney":12171.5,"priceStete":2,"hwdcAreaManages":[]},{"id":32,"administrationNameCn":"大兴区","administrationNameJpn":"大兴区","parentId":0,"createTime":1529824100663,"isDeleted":0,"citylevel":"3","status":"0","longitude":116.03,"latitude":39.53,"houseNum":0,"avgMoney":0,"priceStete":2,"hwdcAreaManages":[]}],"cityzxt":[{"days":1526572800000,"avgPrice":0.47596000000000005},{"days":1528300800000,"avgPrice":2.3343},{"days":1528646400000,"avgPrice":11.1111},{"days":1528992000000,"avgPrice":0.1}]}
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
         * yeaybfb : 1149155.5555999998
         * monthbfb : 848.6371403689386
         * city : {"id":0,"administrationNameCn":"北京市","administrationNameJpn":"北京市","parentId":0,"createTime":1529824100718,"isDeleted":0,"citylevel":"","status":"","longitude":0,"latitude":0,"houseNum":0,"avgMoney":0,"priceStete":2,"hwdcAreaManages":[]}
         * bigandsmallval : {"endVal":"0.10","bigVal":"11.11"}
         * qys : [{"id":3,"administrationNameCn":"2222","administrationNameJpn":"2222","parentId":0,"createTime":1529824100662,"isDeleted":0,"citylevel":"3","status":"0","longitude":116.03,"latitude":39.53,"houseNum":0,"avgMoney":18998.448052,"priceStete":2,"hwdcAreaManages":[]},{"id":27,"administrationNameCn":"朝阳区","administrationNameJpn":"朝阳区","parentId":0,"createTime":1529824100663,"isDeleted":0,"citylevel":"3","status":"0","longitude":116.46,"latitude":39.83,"houseNum":0,"avgMoney":12171.5,"priceStete":2,"hwdcAreaManages":[]},{"id":32,"administrationNameCn":"大兴区","administrationNameJpn":"大兴区","parentId":0,"createTime":1529824100663,"isDeleted":0,"citylevel":"3","status":"0","longitude":116.03,"latitude":39.53,"houseNum":0,"avgMoney":0,"priceStete":2,"hwdcAreaManages":[]}]
         * cityzxt : [{"days":1526572800000,"avgPrice":0.47596000000000005},{"days":1528300800000,"avgPrice":2.3343},{"days":1528646400000,"avgPrice":11.1111},{"days":1528992000000,"avgPrice":0.1}]
         */

        private float yeaybfb;
        private float monthbfb;
        private CityBean city;
        private BigandsmallvalBean bigandsmallval;
        private List<QysBean> qys;
        private List<CityzxtBean> cityzxt;

        public float getYeaybfb() {
            return yeaybfb;
        }

        public void setYeaybfb(float yeaybfb) {
            this.yeaybfb = yeaybfb;
        }

        public float getMonthbfb() {
            return monthbfb;
        }

        public void setMonthbfb(float monthbfb) {
            this.monthbfb = monthbfb;
        }

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public BigandsmallvalBean getBigandsmallval() {
            return bigandsmallval;
        }

        public void setBigandsmallval(BigandsmallvalBean bigandsmallval) {
            this.bigandsmallval = bigandsmallval;
        }

        public List<QysBean> getQys() {
            return qys;
        }

        public void setQys(List<QysBean> qys) {
            this.qys = qys;
        }

        public List<CityzxtBean> getCityzxt() {
            return cityzxt;
        }

        public void setCityzxt(List<CityzxtBean> cityzxt) {
            this.cityzxt = cityzxt;
        }

        public static class CityBean {
            /**
             * id : 0
             * administrationNameCn : 北京市
             * administrationNameJpn : 北京市
             * parentId : 0
             * createTime : 1529824100718
             * isDeleted : 0
             * citylevel :
             * status :
             * longitude : 0
             * latitude : 0
             * houseNum : 0
             * avgMoney : 0
             * priceStete : 2
             * hwdcAreaManages : []
             */

            private int id;
            private String administrationNameCn;
            private String administrationNameJpn;
            private int parentId;
            private long createTime;
            private int isDeleted;
            private String citylevel;
            private String status;
            private int longitude;
            private int latitude;
            private int houseNum;
            private int avgMoney;
            private int priceStete;
            private List<?> hwdcAreaManages;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAdministrationNameCn() {
                return administrationNameCn;
            }

            public void setAdministrationNameCn(String administrationNameCn) {
                this.administrationNameCn = administrationNameCn;
            }

            public String getAdministrationNameJpn() {
                return administrationNameJpn;
            }

            public void setAdministrationNameJpn(String administrationNameJpn) {
                this.administrationNameJpn = administrationNameJpn;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(int isDeleted) {
                this.isDeleted = isDeleted;
            }

            public String getCitylevel() {
                return citylevel;
            }

            public void setCitylevel(String citylevel) {
                this.citylevel = citylevel;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getLongitude() {
                return longitude;
            }

            public void setLongitude(int longitude) {
                this.longitude = longitude;
            }

            public int getLatitude() {
                return latitude;
            }

            public void setLatitude(int latitude) {
                this.latitude = latitude;
            }

            public int getHouseNum() {
                return houseNum;
            }

            public void setHouseNum(int houseNum) {
                this.houseNum = houseNum;
            }

            public int getAvgMoney() {
                return avgMoney;
            }

            public void setAvgMoney(int avgMoney) {
                this.avgMoney = avgMoney;
            }

            public int getPriceStete() {
                return priceStete;
            }

            public void setPriceStete(int priceStete) {
                this.priceStete = priceStete;
            }

            public List<?> getHwdcAreaManages() {
                return hwdcAreaManages;
            }

            public void setHwdcAreaManages(List<?> hwdcAreaManages) {
                this.hwdcAreaManages = hwdcAreaManages;
            }
        }

        public static class BigandsmallvalBean {
            /**
             * endVal : 0.10
             * bigVal : 11.11
             */

            private double endVal;
            private double bigVal;

            public double getEndVal() {
                return endVal;
            }

            public void setEndVal(double endVal) {
                this.endVal = endVal;
            }

            public double getBigVal() {
                return bigVal;
            }

            public void setBigVal(double bigVal) {
                this.bigVal = bigVal;
            }
        }

        public static class QysBean {
            /**
             * id : 3
             * administrationNameCn : 2222
             * administrationNameJpn : 2222
             * parentId : 0
             * createTime : 1529824100662
             * isDeleted : 0
             * citylevel : 3
             * status : 0
             * longitude : 116.03
             * latitude : 39.53
             * houseNum : 0
             * avgMoney : 18998.448052
             * priceStete : 2
             * hwdcAreaManages : []
             */

            private int id;
            private String administrationNameCn;
            private String administrationNameJpn;
            private int parentId;
            private long createTime;
            private int isDeleted;
            private String citylevel;
            private String status;
            private double longitude;
            private double latitude;
            private int houseNum;
            private double avgMoney;
            private int priceStete;
            private List<?> hwdcAreaManages;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAdministrationNameCn() {
                return administrationNameCn;
            }

            public void setAdministrationNameCn(String administrationNameCn) {
                this.administrationNameCn = administrationNameCn;
            }

            public String getAdministrationNameJpn() {
                return administrationNameJpn;
            }

            public void setAdministrationNameJpn(String administrationNameJpn) {
                this.administrationNameJpn = administrationNameJpn;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(int isDeleted) {
                this.isDeleted = isDeleted;
            }

            public String getCitylevel() {
                return citylevel;
            }

            public void setCitylevel(String citylevel) {
                this.citylevel = citylevel;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public int getHouseNum() {
                return houseNum;
            }

            public void setHouseNum(int houseNum) {
                this.houseNum = houseNum;
            }

            public double getAvgMoney() {
                return avgMoney;
            }

            public void setAvgMoney(double avgMoney) {
                this.avgMoney = avgMoney;
            }

            public int getPriceStete() {
                return priceStete;
            }

            public void setPriceStete(int priceStete) {
                this.priceStete = priceStete;
            }

            public List<?> getHwdcAreaManages() {
                return hwdcAreaManages;
            }

            public void setHwdcAreaManages(List<?> hwdcAreaManages) {
                this.hwdcAreaManages = hwdcAreaManages;
            }
        }

        public static class CityzxtBean {
            /**
             * days : 1526572800000
             * avgPrice : 0.47596000000000005
             */

            private long days;
            private double avgPrice;

            public long getDays() {
                return days;
            }

            public void setDays(long days) {
                this.days = days;
            }

            public double getAvgPrice() {
                return avgPrice;
            }

            public void setAvgPrice(double avgPrice) {
                this.avgPrice = avgPrice;
            }
        }
    }
}
