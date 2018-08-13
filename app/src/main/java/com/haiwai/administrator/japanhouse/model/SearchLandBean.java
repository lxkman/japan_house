package com.haiwai.administrator.japanhouse.model;

import java.util.List;

/**
 * Created by   admin on 2018/5/21.
 */

public class SearchLandBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"titleCn":"","titleJpn":"","sellingPriceCn":"","sellingPriceJpn":"","areaJpn":"","areaCn":"","specificLocationCn":"1","specificLocationJpn":"1","videoImgs":"","landImages":""}]
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
         "titleCn": "", //标题
         "titleJpn": "",
         "sellingPriceCn": "", //价格
         "sellingPriceJpn": "",
         "areaJpn": "", //面积
         "areaCn": "",
         "specificLocationCn": "1", //具体位置
         "specificLocationJpn": "1",
         "videoImgs": "", //视频封面图
         "landImages": "" //普通图片
         */

        private int id;
        private String titleCn;
        private String titleJpn;
        private String sellingPriceCn;
        private String sellingPriceJpn;
        private String areaJpn;
        private String areaCn;
        private String specificLocationCn;
        private String specificLocationJpn;
        private String videoImgs;
        private String landImages;

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

        public String getAreaJpn() {
            return areaJpn;
        }

        public void setAreaJpn(String areaJpn) {
            this.areaJpn = areaJpn;
        }

        public String getAreaCn() {
            return areaCn;
        }

        public void setAreaCn(String areaCn) {
            this.areaCn = areaCn;
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

        public String getVideoImgs() {
            return videoImgs;
        }

        public void setVideoImgs(String videoImgs) {
            this.videoImgs = videoImgs;
        }

        public String getLandImages() {
            return landImages;
        }

        public void setLandImages(String landImages) {
            this.landImages = landImages;
        }
    }
}
