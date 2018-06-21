package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */

public class FangjiaMapBean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : {"yeaybfb":"217.6471%","monthbfb":"75.0%","city":{"latitude":0,"avgMoney":2,"priceStete":1,"parentId":0,"administrationNameCn":"北京市","isDeleted":0,"createTime":1528709039046,"citylevel":"","id":0,"administrationNameJpn":"北京市","houseNum":0,"status":"","longitude":0},"qys":[{"latitude":39.53,"avgMoney":5.698113,"priceStete":2,"parentId":0,"administrationNameCn":"海淀区","isDeleted":0,"createTime":1528709038968,"citylevel":"3","id":3,"administrationNameJpn":"海淀区","houseNum":0,"status":"0","longitude":116.03},{"latitude":39.83,"avgMoney":3,"priceStete":2,"parentId":0,"administrationNameCn":"朝阳区","isDeleted":0,"createTime":1528709038969,"citylevel":"3","id":27,"administrationNameJpn":"朝阳区","houseNum":0,"status":"0","longitude":116.46},{"latitude":39.53,"avgMoney":0,"priceStete":2,"parentId":0,"administrationNameCn":"大兴区","isDeleted":0,"createTime":1528709038969,"citylevel":"3","id":32,"administrationNameJpn":"大兴区","houseNum":0,"status":"0","longitude":116.03}],"cityzxt":[{"avgPrice":2,"days":1526572800000},{"avgPrice":5,"days":1528300800000},{"avgPrice":2,"days":1528646400000}]}
     */
    private String msg;
    private String code;
    private DatasEntity datas;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * yeaybfb : 217.6471%
         * monthbfb : 75.0%
         * city : {"latitude":0,"avgMoney":2,"priceStete":1,"parentId":0,"administrationNameCn":"北京市","isDeleted":0,"createTime":1528709039046,"citylevel":"","id":0,"administrationNameJpn":"北京市","houseNum":0,"status":"","longitude":0}
         * qys : [{"latitude":39.53,"avgMoney":5.698113,"priceStete":2,"parentId":0,"administrationNameCn":"海淀区","isDeleted":0,"createTime":1528709038968,"citylevel":"3","id":3,"administrationNameJpn":"海淀区","houseNum":0,"status":"0","longitude":116.03},{"latitude":39.83,"avgMoney":3,"priceStete":2,"parentId":0,"administrationNameCn":"朝阳区","isDeleted":0,"createTime":1528709038969,"citylevel":"3","id":27,"administrationNameJpn":"朝阳区","houseNum":0,"status":"0","longitude":116.46},{"latitude":39.53,"avgMoney":0,"priceStete":2,"parentId":0,"administrationNameCn":"大兴区","isDeleted":0,"createTime":1528709038969,"citylevel":"3","id":32,"administrationNameJpn":"大兴区","houseNum":0,"status":"0","longitude":116.03}]
         * cityzxt : [{"avgPrice":2,"days":1526572800000},{"avgPrice":5,"days":1528300800000},{"avgPrice":2,"days":1528646400000}]
         */
        private String yeaybfb;
        private String monthbfb;
        private CityEntity city;
        private List<QysEntity> qys;
        private List<CityzxtEntity> cityzxt;

        public void setYeaybfb(String yeaybfb) {
            this.yeaybfb = yeaybfb;
        }

        public void setMonthbfb(String monthbfb) {
            this.monthbfb = monthbfb;
        }

        public void setCity(CityEntity city) {
            this.city = city;
        }

        public void setQys(List<QysEntity> qys) {
            this.qys = qys;
        }

        public void setCityzxt(List<CityzxtEntity> cityzxt) {
            this.cityzxt = cityzxt;
        }

        public String getYeaybfb() {
            return yeaybfb;
        }

        public String getMonthbfb() {
            return monthbfb;
        }

        public CityEntity getCity() {
            return city;
        }

        public List<QysEntity> getQys() {
            return qys;
        }

        public List<CityzxtEntity> getCityzxt() {
            return cityzxt;
        }

        public static class CityEntity {
            /**
             * latitude : 0
             * avgMoney : 2
             * priceStete : 1
             * parentId : 0
             * administrationNameCn : 北京市
             * isDeleted : 0
             * createTime : 1528709039046
             * citylevel :
             * id : 0
             * administrationNameJpn : 北京市
             * houseNum : 0
             * status :
             * longitude : 0
             */
            private int latitude;
            private int avgMoney;
            private int priceStete;
            private int parentId;
            private String administrationNameCn;
            private int isDeleted;
            private long createTime;
            private String citylevel;
            private int id;
            private String administrationNameJpn;
            private int houseNum;
            private String status;
            private int longitude;

            public void setLatitude(int latitude) {
                this.latitude = latitude;
            }

            public void setAvgMoney(int avgMoney) {
                this.avgMoney = avgMoney;
            }

            public void setPriceStete(int priceStete) {
                this.priceStete = priceStete;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public void setAdministrationNameCn(String administrationNameCn) {
                this.administrationNameCn = administrationNameCn;
            }

            public void setIsDeleted(int isDeleted) {
                this.isDeleted = isDeleted;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setCitylevel(String citylevel) {
                this.citylevel = citylevel;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setAdministrationNameJpn(String administrationNameJpn) {
                this.administrationNameJpn = administrationNameJpn;
            }

            public void setHouseNum(int houseNum) {
                this.houseNum = houseNum;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setLongitude(int longitude) {
                this.longitude = longitude;
            }

            public int getLatitude() {
                return latitude;
            }

            public int getAvgMoney() {
                return avgMoney;
            }

            public int getPriceStete() {
                return priceStete;
            }

            public int getParentId() {
                return parentId;
            }

            public String getAdministrationNameCn() {
                return administrationNameCn;
            }

            public int getIsDeleted() {
                return isDeleted;
            }

            public long getCreateTime() {
                return createTime;
            }

            public String getCitylevel() {
                return citylevel;
            }

            public int getId() {
                return id;
            }

            public String getAdministrationNameJpn() {
                return administrationNameJpn;
            }

            public int getHouseNum() {
                return houseNum;
            }

            public String getStatus() {
                return status;
            }

            public int getLongitude() {
                return longitude;
            }
        }

        public static class QysEntity {
            /**
             * latitude : 39.53
             * avgMoney : 5.698113
             * priceStete : 2
             * parentId : 0
             * administrationNameCn : 海淀区
             * isDeleted : 0
             * createTime : 1528709038968
             * citylevel : 3
             * id : 3
             * administrationNameJpn : 海淀区
             * houseNum : 0
             * status : 0
             * longitude : 116.03
             */
            private double latitude;
            private double avgMoney;
            private int priceStete;
            private int parentId;
            private String administrationNameCn;
            private int isDeleted;
            private long createTime;
            private String citylevel;
            private int id;
            private String administrationNameJpn;
            private int houseNum;
            private String status;
            private double longitude;

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public void setAvgMoney(double avgMoney) {
                this.avgMoney = avgMoney;
            }

            public void setPriceStete(int priceStete) {
                this.priceStete = priceStete;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public void setAdministrationNameCn(String administrationNameCn) {
                this.administrationNameCn = administrationNameCn;
            }

            public void setIsDeleted(int isDeleted) {
                this.isDeleted = isDeleted;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setCitylevel(String citylevel) {
                this.citylevel = citylevel;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setAdministrationNameJpn(String administrationNameJpn) {
                this.administrationNameJpn = administrationNameJpn;
            }

            public void setHouseNum(int houseNum) {
                this.houseNum = houseNum;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public double getLatitude() {
                return latitude;
            }

            public double getAvgMoney() {
                return avgMoney;
            }

            public int getPriceStete() {
                return priceStete;
            }

            public int getParentId() {
                return parentId;
            }

            public String getAdministrationNameCn() {
                return administrationNameCn;
            }

            public int getIsDeleted() {
                return isDeleted;
            }

            public long getCreateTime() {
                return createTime;
            }

            public String getCitylevel() {
                return citylevel;
            }

            public int getId() {
                return id;
            }

            public String getAdministrationNameJpn() {
                return administrationNameJpn;
            }

            public int getHouseNum() {
                return houseNum;
            }

            public String getStatus() {
                return status;
            }

            public double getLongitude() {
                return longitude;
            }
        }

        public static class CityzxtEntity {
            /**
             * avgPrice : 2
             * days : 1526572800000
             */
            private double avgPrice;
            private long days;

            public void setAvgPrice(double avgPrice) {
                this.avgPrice = avgPrice;
            }

            public void setDays(long days) {
                this.days = days;
            }

            public double getAvgPrice() {
                return avgPrice;
            }

            public long getDays() {
                return days;
            }
        }
    }
}
