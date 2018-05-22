package com.example.administrator.japanhouse.model;

import java.util.List;

/**
 * Created by   admin on 2018/5/21.
 */

public class SearchVillaBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"titleCn":"别墅标题","titleJpn":"别墅标题","specificLocationCn":"1","specificLocationJpn":"1","coveredAreaCn":"","coveredAreaJpn":"","sellingPriceCn":"","sellingPriceJpn":"","videoImgs":"","roomImgs":""}]
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

    public static class DatasBean {
        /**
         "id": 1,
         "titleCn": "别墅标题", //标题
         "titleJpn": "别墅标题",
         "specificLocationCn": "1", //具体位置
         "specificLocationJpn": "1",
         "coveredAreaCn": "", //面积
         "coveredAreaJpn": "",
         "sellingPriceCn": "", //价格
         "sellingPriceJpn": "",
         "videoImgs": "", //视频封面图
         "roomImgs": "" //普通图片
         */

        private int id;
        private String titleCn;
        private String titleJpn;
        private String specificLocationCn;
        private String specificLocationJpn;
        private String coveredAreaCn;
        private String coveredAreaJpn;
        private String sellingPriceCn;
        private String sellingPriceJpn;
        private String videoImgs;
        private String roomImgs;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public String getTitleJpn() {
            return titleJpn;
        }

        public void setTitleJpn(String titleJpn) {
            this.titleJpn = titleJpn;
        }

        public String getSpecificLocationCn() {
            return specificLocationCn;
        }

        public void setSpecificLocationCn(String specificLocationCn) {
            this.specificLocationCn = specificLocationCn;
        }

        public String getSpecificLocationJpn() {
            return specificLocationJpn;
        }

        public void setSpecificLocationJpn(String specificLocationJpn) {
            this.specificLocationJpn = specificLocationJpn;
        }

        public String getCoveredAreaCn() {
            return coveredAreaCn;
        }

        public void setCoveredAreaCn(String coveredAreaCn) {
            this.coveredAreaCn = coveredAreaCn;
        }

        public String getCoveredAreaJpn() {
            return coveredAreaJpn;
        }

        public void setCoveredAreaJpn(String coveredAreaJpn) {
            this.coveredAreaJpn = coveredAreaJpn;
        }

        public String getSellingPriceCn() {
            return sellingPriceCn;
        }

        public void setSellingPriceCn(String sellingPriceCn) {
            this.sellingPriceCn = sellingPriceCn;
        }

        public String getSellingPriceJpn() {
            return sellingPriceJpn;
        }

        public void setSellingPriceJpn(String sellingPriceJpn) {
            this.sellingPriceJpn = sellingPriceJpn;
        }

        public String getVideoImgs() {
            return videoImgs;
        }

        public void setVideoImgs(String videoImgs) {
            this.videoImgs = videoImgs;
        }

        public String getRoomImgs() {
            return roomImgs;
        }

        public void setRoomImgs(String roomImgs) {
            this.roomImgs = roomImgs;
        }
    }
}
