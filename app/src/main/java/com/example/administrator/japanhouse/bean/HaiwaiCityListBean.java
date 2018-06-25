package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/25.
 */

public class HaiwaiCityListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"latitude":1,"avgMoney":0,"priceStete":0,"parentId":0,"administrationNameCn":"悉尼","isDeleted":0,"createTime":1527146646000,"citylevel":"1","id":12,"administrationNameJpn":"悉尼","houseNum":0,"longitude":1,"status":"2"},{"latitude":1,"avgMoney":0,"priceStete":0,"parentId":0,"administrationNameCn":"英国","isDeleted":0,"createTime":1527501705000,"citylevel":"1","id":14,"administrationNameJpn":"英国","houseNum":0,"longitude":1,"status":"2"}]
     */
    private String msg;
    private String code;
    private List<DatasEntity> datas;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDatas(List<DatasEntity> datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public List<DatasEntity> getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * latitude : 1
         * avgMoney : 0
         * priceStete : 0
         * parentId : 0
         * administrationNameCn : 悉尼
         * isDeleted : 0
         * createTime : 1527146646000
         * citylevel : 1
         * id : 12
         * administrationNameJpn : 悉尼
         * houseNum : 0
         * longitude : 1
         * status : 2
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
        private int longitude;
        private String status;

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

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public int getLongitude() {
            return longitude;
        }

        public String getStatus() {
            return status;
        }
    }
}
