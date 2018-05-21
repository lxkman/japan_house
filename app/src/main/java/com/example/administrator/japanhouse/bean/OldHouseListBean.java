package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */

public class OldHouseListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"specificLocationCn":"1","areaCn":"","videoImgs":"","priceJpn":"","titleCn":"房屋标题中文（二手房）","titleJpn":"房屋标题日文（二手房）","areaJpn":"","id":1,"priceCn":"","specificLocationJpn":"1","roomImgs":""}]
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
         * specificLocationCn : 1
         * areaCn :
         * videoImgs :
         * priceJpn :
         * titleCn : 房屋标题中文（二手房）
         * titleJpn : 房屋标题日文（二手房）
         * areaJpn :
         * id : 1
         * priceCn :
         * specificLocationJpn : 1
         * roomImgs :
         */
        private String specificLocationCn;
        private String areaCn;
        private String videoImgs;
        private String priceJpn;
        private String titleCn;
        private String titleJpn;
        private String areaJpn;
        private int id;
        private String priceCn;
        private String specificLocationJpn;
        private String roomImgs;

        public void setSpecificLocationCn(String specificLocationCn) {
            this.specificLocationCn = specificLocationCn;
        }

        public void setAreaCn(String areaCn) {
            this.areaCn = areaCn;
        }

        public void setVideoImgs(String videoImgs) {
            this.videoImgs = videoImgs;
        }

        public void setPriceJpn(String priceJpn) {
            this.priceJpn = priceJpn;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public void setTitleJpn(String titleJpn) {
            this.titleJpn = titleJpn;
        }

        public void setAreaJpn(String areaJpn) {
            this.areaJpn = areaJpn;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPriceCn(String priceCn) {
            this.priceCn = priceCn;
        }

        public void setSpecificLocationJpn(String specificLocationJpn) {
            this.specificLocationJpn = specificLocationJpn;
        }

        public void setRoomImgs(String roomImgs) {
            this.roomImgs = roomImgs;
        }

        public String getSpecificLocationCn() {
            return specificLocationCn;
        }

        public String getAreaCn() {
            return areaCn;
        }

        public String getVideoImgs() {
            return videoImgs;
        }

        public String getPriceJpn() {
            return priceJpn;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public String getTitleJpn() {
            return titleJpn;
        }

        public String getAreaJpn() {
            return areaJpn;
        }

        public int getId() {
            return id;
        }

        public String getPriceCn() {
            return priceCn;
        }

        public String getSpecificLocationJpn() {
            return specificLocationJpn;
        }

        public String getRoomImgs() {
            return roomImgs;
        }
    }
}
