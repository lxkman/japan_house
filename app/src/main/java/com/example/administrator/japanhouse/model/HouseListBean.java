package com.example.administrator.japanhouse.model;

import java.util.List;

/**
 * Created by   admin on 2018/5/16.
 */

public class HouseListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":18,"plotNameCn":"1","plotNameJpn":"1","addressCn":"河北省-保定市-蠡县","addressJpn":"hebeisheng-baodingshi-lixian","areaJpn":"1.00平方メートル","areaCn":"1.00平方米","priceCn":"1.00万元","priceJpn":"1.00万元","videoImgs":"","roomImgs":""}]
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
         "id": 18,
         "plotNameCn": "1", //小区名称
         "plotNameJpn": "1",
         "addressCn": "河北省-保定市-蠡县",  //地址
         "addressJpn": "hebeisheng-baodingshi-lixian",
         "areaJpn": "1.00平方メートル",  //面积
         "areaCn": "1.00平方米",
         "priceCn": "1.00万元",   //价格
         "priceJpn": "1.00万元",
         "videoImgs": "",  //视频封面图
         "roomImgs": ""
         */

        private int id;
        private String plotNameCn;
        private String plotNameJpn;
        private String addressCn;
        private String addressJpn;
        private String areaJpn;
        private String areaCn;
        private String priceCn;
        private String priceJpn;
        private String videoImgs;
        private String roomImgs;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlotNameCn() {
            return plotNameCn;
        }

        public void setPlotNameCn(String plotNameCn) {
            this.plotNameCn = plotNameCn;
        }

        public String getPlotNameJpn() {
            return plotNameJpn;
        }

        public void setPlotNameJpn(String plotNameJpn) {
            this.plotNameJpn = plotNameJpn;
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
