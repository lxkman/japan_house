package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */

public class ChinaCityItemBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"endVal":2147483647,"screeType":"21","starVal":0,"createTime":1527059634000,"countryOrcityId":"","houseType":"4","screeValCn":"北京","updateTime":1527059635000,"id":68,"screeValJpn":"北京","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"screeType":"21","starVal":0,"createTime":1527059652000,"countryOrcityId":"","houseType":"4","screeValCn":"上海","updateTime":1527059654000,"id":69,"screeValJpn":"上海","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"screeType":"21","starVal":0,"createTime":1527059670000,"countryOrcityId":"","houseType":"4","screeValCn":"广州","updateTime":1527059671000,"id":70,"screeValJpn":"广州","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"screeType":"21","starVal":0,"createTime":1527059685000,"countryOrcityId":"","houseType":"4","screeValCn":"深圳","updateTime":1527059687000,"id":71,"screeValJpn":"深圳","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"screeType":"21","starVal":0,"createTime":1527059704000,"countryOrcityId":"","houseType":"4","screeValCn":"杭州","updateTime":1527059705000,"id":72,"screeValJpn":"杭州","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"screeType":"21","starVal":0,"createTime":1527059730000,"countryOrcityId":"","houseType":"4","screeValCn":"重庆","updateTime":1527059732000,"id":73,"screeValJpn":"重庆","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"screeType":"21","starVal":0,"createTime":1527059786000,"countryOrcityId":"-1","houseType":"4","screeValCn":"其他","updateTime":1527059788000,"id":74,"screeValJpn":"其他","isdeleted":"0","logoUrl":""}]
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
         * endVal : 2147483647
         * screeType : 21
         * starVal : 0
         * createTime : 1527059634000
         * countryOrcityId :
         * houseType : 4
         * screeValCn : 北京
         * updateTime : 1527059635000
         * id : 68
         * screeValJpn : 北京
         * isdeleted : 0
         * logoUrl :
         */
        private int endVal;
        private String screeType;
        private int starVal;
        private long createTime;
        private String countryOrcityId;
        private String houseType;
        private String screeValCn;
        private long updateTime;
        private int id;
        private String screeValJpn;
        private String isdeleted;
        private String logoUrl;

        public void setEndVal(int endVal) {
            this.endVal = endVal;
        }

        public void setScreeType(String screeType) {
            this.screeType = screeType;
        }

        public void setStarVal(int starVal) {
            this.starVal = starVal;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setCountryOrcityId(String countryOrcityId) {
            this.countryOrcityId = countryOrcityId;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public void setScreeValCn(String screeValCn) {
            this.screeValCn = screeValCn;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setScreeValJpn(String screeValJpn) {
            this.screeValJpn = screeValJpn;
        }

        public void setIsdeleted(String isdeleted) {
            this.isdeleted = isdeleted;
        }

        public void setLogoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
        }

        public int getEndVal() {
            return endVal;
        }

        public String getScreeType() {
            return screeType;
        }

        public int getStarVal() {
            return starVal;
        }

        public long getCreateTime() {
            return createTime;
        }

        public String getCountryOrcityId() {
            return countryOrcityId;
        }

        public String getHouseType() {
            return houseType;
        }

        public String getScreeValCn() {
            return screeValCn;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public int getId() {
            return id;
        }

        public String getScreeValJpn() {
            return screeValJpn;
        }

        public String getIsdeleted() {
            return isdeleted;
        }

        public String getLogoUrl() {
            return logoUrl;
        }
    }
}
