package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class BieShuListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"specificLocationCn":"1","coveredAreaJpn":"","sellingPriceCn":"","videoImgs":"","titleCn":"别墅标题","coveredAreaCn":"","titleJpn":"别墅标题","id":1,"specificLocationJpn":"1","roomImgs":"","sellingPriceJpn":""}]
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
         * coveredAreaJpn :
         * sellingPriceCn :
         * videoImgs :
         * titleCn : 别墅标题
         * coveredAreaCn :
         * titleJpn : 别墅标题
         * id : 1
         * specificLocationJpn : 1
         * roomImgs :
         * sellingPriceJpn :
         */
        private String specificLocationCn;
        private String coveredAreaJpn;
        private String sellingPriceCn;
        private String videoImgs;
        private String titleCn;
        private String coveredAreaCn;
        private String titleJpn;
        private int id;
        private String specificLocationJpn;
        private String roomImgs;
        private String sellingPriceJpn;

        public void setSpecificLocationCn(String specificLocationCn) {
            this.specificLocationCn = specificLocationCn;
        }

        public void setCoveredAreaJpn(String coveredAreaJpn) {
            this.coveredAreaJpn = coveredAreaJpn;
        }

        public void setSellingPriceCn(String sellingPriceCn) {
            this.sellingPriceCn = sellingPriceCn;
        }

        public void setVideoImgs(String videoImgs) {
            this.videoImgs = videoImgs;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public void setCoveredAreaCn(String coveredAreaCn) {
            this.coveredAreaCn = coveredAreaCn;
        }

        public void setTitleJpn(String titleJpn) {
            this.titleJpn = titleJpn;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setSpecificLocationJpn(String specificLocationJpn) {
            this.specificLocationJpn = specificLocationJpn;
        }

        public void setRoomImgs(String roomImgs) {
            this.roomImgs = roomImgs;
        }

        public void setSellingPriceJpn(String sellingPriceJpn) {
            this.sellingPriceJpn = sellingPriceJpn;
        }

        public String getSpecificLocationCn() {
            return specificLocationCn;
        }

        public String getCoveredAreaJpn() {
            return coveredAreaJpn;
        }

        public String getSellingPriceCn() {
            return sellingPriceCn;
        }

        public String getVideoImgs() {
            return videoImgs;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public String getCoveredAreaCn() {
            return coveredAreaCn;
        }

        public String getTitleJpn() {
            return titleJpn;
        }

        public int getId() {
            return id;
        }

        public String getSpecificLocationJpn() {
            return specificLocationJpn;
        }

        public String getRoomImgs() {
            return roomImgs;
        }

        public String getSellingPriceJpn() {
            return sellingPriceJpn;
        }
    }
}
