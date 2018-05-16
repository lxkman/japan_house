package com.example.administrator.japanhouse.model;

import java.util.List;

/**
 * Created by   admin on 2018/5/16.
 */

public class LandBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"titleCn":"房屋标题中文","titleJpn":"房屋标题日文","priceCn":"100","priceJpn":"100","areaJpn":"500平米","areaCn":"500平米","addressCn":"","addressJpn":"","videoImgs":"","landImages":""}]
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
         * id : 1
         * titleCn : 房屋标题中文
         * titleJpn : 房屋标题日文
         * priceCn : 100
         * priceJpn : 100
         * areaJpn : 500平米
         * areaCn : 500平米
         * addressCn :
         * addressJpn :
         * videoImgs :
         * landImages :
         */

        private int id;
        private String titleCn;
        private String titleJpn;
        private String priceCn;
        private String priceJpn;
        private String areaJpn;
        private String areaCn;
        private String addressCn;
        private String addressJpn;
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

        public String getPriceCn() {
            return priceCn;
        }

        public void setPriceCn(String priceCn) {
            this.priceCn = priceCn;
        }

        public String getPriceJpn() {
            return priceJpn;
        }

        public void setPriceJpn(String priceJpn) {
            this.priceJpn = priceJpn;
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

        public String getAddressCn() {
            return addressCn;
        }

        public void setAddressCn(String addressCn) {
            this.addressCn = addressCn;
        }

        public String getAddressJpn() {
            return addressJpn;
        }

        public void setAddressJpn(String addressJpn) {
            this.addressJpn = addressJpn;
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
