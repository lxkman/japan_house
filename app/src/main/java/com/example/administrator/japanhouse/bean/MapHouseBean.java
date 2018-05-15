package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class MapHouseBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"administrationNameCn":"昌平区","isDeleted":0,"createTime":1526023796471,"latitude":40.2,"citylevel":"","id":15,"administrationNameJpn":"","parentId":0,"houseNum":1,"status":"","longitude":116.2}]
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
         * administrationNameCn : 昌平区
         * isDeleted : 0
         * createTime : 1526023796471
         * latitude : 40.2
         * citylevel :
         * id : 15
         * administrationNameJpn :
         * parentId : 0
         * houseNum : 1
         * status :
         * longitude : 116.2
         */
        private String administrationNameCn;
        private int isDeleted;
        private long createTime;
        private double latitude;
        private String citylevel;
        private int id;
        private String administrationNameJpn;
        private int parentId;
        private int houseNum;
        private String status;
        private double longitude;

        public void setAdministrationNameCn(String administrationNameCn) {
            this.administrationNameCn = administrationNameCn;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
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

        public void setParentId(int parentId) {
            this.parentId = parentId;
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

        public String getAdministrationNameCn() {
            return administrationNameCn;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public long getCreateTime() {
            return createTime;
        }

        public double getLatitude() {
            return latitude;
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

        public int getParentId() {
            return parentId;
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
}
