package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/25.
 */
public class TudiListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"specificLocationCn":"具体位置","areaCn":"面积","sellingPriceCn":"售价","videoImgs":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1526960221&di=d5a70cd88be67479c20836cbdb04c46d&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg","titleCn":"标题","titleJpn":"标题","areaJpn":"面积","landImages":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1526960221&di=d5a70cd88be67479c20836cbdb04c46d&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg","id":1,"specificLocationJpn":"具体位置","sellingPriceJpn":"售价"}]
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
         * specificLocationCn : 具体位置
         * areaCn : 面积
         * sellingPriceCn : 售价
         * videoImgs : https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1526960221&di=d5a70cd88be67479c20836cbdb04c46d&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg
         * titleCn : 标题
         * titleJpn : 标题
         * areaJpn : 面积
         * landImages : https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1526960221&di=d5a70cd88be67479c20836cbdb04c46d&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg
         * id : 1
         * specificLocationJpn : 具体位置
         * sellingPriceJpn : 售价
         */
        private String specificLocationCn;
        private String areaCn;
        private String sellingPriceCn;
        private String videoImgs;
        private String titleCn;
        private String titleJpn;
        private String areaJpn;
        private String landImages;
        private int id;
        private String specificLocationJpn;
        private String sellingPriceJpn;

        public void setSpecificLocationCn(String specificLocationCn) {
            this.specificLocationCn = specificLocationCn;
        }

        public void setAreaCn(String areaCn) {
            this.areaCn = areaCn;
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

        public void setTitleJpn(String titleJpn) {
            this.titleJpn = titleJpn;
        }

        public void setAreaJpn(String areaJpn) {
            this.areaJpn = areaJpn;
        }

        public void setLandImages(String landImages) {
            this.landImages = landImages;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setSpecificLocationJpn(String specificLocationJpn) {
            this.specificLocationJpn = specificLocationJpn;
        }

        public void setSellingPriceJpn(String sellingPriceJpn) {
            this.sellingPriceJpn = sellingPriceJpn;
        }

        public String getSpecificLocationCn() {
            return specificLocationCn;
        }

        public String getAreaCn() {
            return areaCn;
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

        public String getTitleJpn() {
            return titleJpn;
        }

        public String getAreaJpn() {
            return areaJpn;
        }

        public String getLandImages() {
            return landImages;
        }

        public int getId() {
            return id;
        }

        public String getSpecificLocationJpn() {
            return specificLocationJpn;
        }

        public String getSellingPriceJpn() {
            return sellingPriceJpn;
        }
    }
}
