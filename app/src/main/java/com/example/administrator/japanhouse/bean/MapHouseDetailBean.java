package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class MapHouseDetailBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"communityNameJpn":"新悦家园-日文","isDeleted":"0","createTime":1526026000762,"latiude":40.2,"communityNameCn":"新悦家园","updateTime":1526026000762,"id":1,"parentAreaId":0,"houseNum":1,"longitude":116.1}]
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
         * communityNameJpn : 新悦家园-日文
         * isDeleted : 0
         * createTime : 1526026000762
         * latiude : 40.2
         * communityNameCn : 新悦家园
         * updateTime : 1526026000762
         * id : 1
         * parentAreaId : 0
         * houseNum : 1
         * longitude : 116.1
         */
        private String communityNameJpn;
        private String isDeleted;
        private long createTime;
        private double latiude;
        private String communityNameCn;
        private long updateTime;
        private int id;
        private int parentAreaId;
        private int houseNum;
        private double longitude;

        public void setCommunityNameJpn(String communityNameJpn) {
            this.communityNameJpn = communityNameJpn;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setLatiude(double latiude) {
            this.latiude = latiude;
        }

        public void setCommunityNameCn(String communityNameCn) {
            this.communityNameCn = communityNameCn;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setParentAreaId(int parentAreaId) {
            this.parentAreaId = parentAreaId;
        }

        public void setHouseNum(int houseNum) {
            this.houseNum = houseNum;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getCommunityNameJpn() {
            return communityNameJpn;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public long getCreateTime() {
            return createTime;
        }

        public double getLatiude() {
            return latiude;
        }

        public String getCommunityNameCn() {
            return communityNameCn;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public int getId() {
            return id;
        }

        public int getParentAreaId() {
            return parentAreaId;
        }

        public int getHouseNum() {
            return houseNum;
        }

        public double getLongitude() {
            return longitude;
        }
    }
}
