package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */

public class OldHouseListbean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"areaCn":"1.00平方米","plotNameJpn":"1","videoImgs":"","priceJpn":"1.00万元","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","id":18,"priceCn":"1.00万元","roomImgs":"","plotNameCn":"1","addressCn":"河北省-保定市-蠡县"},{"areaCn":"1.00平方米","plotNameJpn":"1","videoImgs":"","priceJpn":"1.00万元","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","id":21,"priceCn":"1.00万元","roomImgs":"","plotNameCn":"1","addressCn":"河北省-保定市-蠡县"},{"areaCn":"1.00平方米","plotNameJpn":"1","videoImgs":"","priceJpn":"1.00万元","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","id":22,"priceCn":"1.00万元","roomImgs":"","plotNameCn":"1","addressCn":"河北省-保定市-蠡县"},{"areaCn":"1.00平方米","plotNameJpn":"1","videoImgs":"","priceJpn":"1.00万元","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","id":23,"priceCn":"1.00万元","roomImgs":"","plotNameCn":"1","addressCn":"河北省-保定市-蠡县"},{"areaCn":"1.00平方米","plotNameJpn":"1","videoImgs":"","priceJpn":"1.00万元","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","id":24,"priceCn":"1.00万元","roomImgs":"","plotNameCn":"1","addressCn":"河北省-保定市-蠡县"},{"areaCn":"1.00平方米","plotNameJpn":"1","videoImgs":"","priceJpn":"1.00万元","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","id":25,"priceCn":"1.00万元","roomImgs":"","plotNameCn":"1","addressCn":"河北省-保定市-蠡县"},{"areaCn":"1.00平方米","plotNameJpn":"1","videoImgs":"","priceJpn":"1.00万元","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","id":26,"priceCn":"1.00万元","roomImgs":"","plotNameCn":"1","addressCn":"河北省-保定市-蠡县"},{"areaCn":"1.00平方米","plotNameJpn":"1","videoImgs":"","priceJpn":"1.00万元","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","id":27,"priceCn":"1.00万元","roomImgs":"","plotNameCn":"1","addressCn":"河北省-保定市-蠡县"},{"areaCn":"1.00平方米","plotNameJpn":"1","videoImgs":"","priceJpn":"1.00万元","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","id":28,"priceCn":"1.00万元","roomImgs":"","plotNameCn":"1","addressCn":"河北省-保定市-蠡县"},{"areaCn":"1.00平方米","plotNameJpn":"1","videoImgs":"","priceJpn":"1.00万元","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","id":29,"priceCn":"1.00万元","roomImgs":"","plotNameCn":"1","addressCn":"河北省-保定市-蠡县"}]
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
         * areaCn : 1.00平方米
         * plotNameJpn : 1
         * videoImgs :
         * priceJpn : 1.00万元
         * addressJpn : hebeisheng-baodingshi-lixian
         * areaJpn : 1.00平方メートル
         * id : 18
         * priceCn : 1.00万元
         * roomImgs :
         * plotNameCn : 1
         * addressCn : 河北省-保定市-蠡县
         */
        private String areaCn;
        private String plotNameJpn;
        private String videoImgs;
        private String priceJpn;
        private String addressJpn;
        private String areaJpn;
        private int id;
        private String priceCn;
        private String roomImgs;
        private String plotNameCn;
        private String addressCn;

        public void setAreaCn(String areaCn) {
            this.areaCn = areaCn;
        }

        public void setPlotNameJpn(String plotNameJpn) {
            this.plotNameJpn = plotNameJpn;
        }

        public void setVideoImgs(String videoImgs) {
            this.videoImgs = videoImgs;
        }

        public void setPriceJpn(String priceJpn) {
            this.priceJpn = priceJpn;
        }

        public void setAddressJpn(String addressJpn) {
            this.addressJpn = addressJpn;
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

        public void setRoomImgs(String roomImgs) {
            this.roomImgs = roomImgs;
        }

        public void setPlotNameCn(String plotNameCn) {
            this.plotNameCn = plotNameCn;
        }

        public void setAddressCn(String addressCn) {
            this.addressCn = addressCn;
        }

        public String getAreaCn() {
            return areaCn;
        }

        public String getPlotNameJpn() {
            return plotNameJpn;
        }

        public String getVideoImgs() {
            return videoImgs;
        }

        public String getPriceJpn() {
            return priceJpn;
        }

        public String getAddressJpn() {
            return addressJpn;
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

        public String getRoomImgs() {
            return roomImgs;
        }

        public String getPlotNameCn() {
            return plotNameCn;
        }

        public String getAddressCn() {
            return addressCn;
        }
    }
}
