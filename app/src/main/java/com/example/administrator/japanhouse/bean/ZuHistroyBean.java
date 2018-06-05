package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/5.
 */

public class ZuHistroyBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"areaCn":"100","hType":"2","priceJpn":"10000","priceCn":"10100","shType":"1","videoImageUrl":"","titleCn":"房屋标题中文（租房）","imageUrl":"","titleJpn":"房屋标题日文（租房）","addressJpn":"1","areaJpn":"100","id":4,"doorModelCn":"户型","doorModelJpn":"户型","addressCn":"1"}]
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
         * areaCn : 100
         * hType : 2
         * priceJpn : 10000
         * priceCn : 10100
         * shType : 1
         * videoImageUrl :
         * titleCn : 房屋标题中文（租房）
         * imageUrl :
         * titleJpn : 房屋标题日文（租房）
         * addressJpn : 1
         * areaJpn : 100
         * id : 4
         * doorModelCn : 户型
         * doorModelJpn : 户型
         * addressCn : 1
         */
        private String areaCn;
        private String hType;
        private String priceJpn;
        private String priceCn;
        private String shType;
        private String videoImageUrl;
        private String titleCn;
        private String imageUrl;
        private String titleJpn;
        private String addressJpn;
        private String areaJpn;
        private int id;
        private String doorModelCn;
        private String doorModelJpn;
        private String addressCn;

        public void setAreaCn(String areaCn) {
            this.areaCn = areaCn;
        }

        public void setHType(String hType) {
            this.hType = hType;
        }

        public void setPriceJpn(String priceJpn) {
            this.priceJpn = priceJpn;
        }

        public void setPriceCn(String priceCn) {
            this.priceCn = priceCn;
        }

        public void setShType(String shType) {
            this.shType = shType;
        }

        public void setVideoImageUrl(String videoImageUrl) {
            this.videoImageUrl = videoImageUrl;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setTitleJpn(String titleJpn) {
            this.titleJpn = titleJpn;
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

        public void setDoorModelCn(String doorModelCn) {
            this.doorModelCn = doorModelCn;
        }

        public void setDoorModelJpn(String doorModelJpn) {
            this.doorModelJpn = doorModelJpn;
        }

        public void setAddressCn(String addressCn) {
            this.addressCn = addressCn;
        }

        public String getAreaCn() {
            return areaCn;
        }

        public String getHType() {
            return hType;
        }

        public String getPriceJpn() {
            return priceJpn;
        }

        public String getPriceCn() {
            return priceCn;
        }

        public String getShType() {
            return shType;
        }

        public String getVideoImageUrl() {
            return videoImageUrl;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getTitleJpn() {
            return titleJpn;
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

        public String getDoorModelCn() {
            return doorModelCn;
        }

        public String getDoorModelJpn() {
            return doorModelJpn;
        }

        public String getAddressCn() {
            return addressCn;
        }
    }
}
