package com.example.administrator.japanhouse.more;

import java.util.List;

/**
 * Created by   admin on 2018/5/31.
 */

public class CollectionListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":3,"titleCn":"房屋标题中文（租房）","titleJpn":"房屋标题日文（租房）","addressCn":"1","addressJpn":"1","doorModelCn":"户型","doorModelJpn":"户型","areaCn":"100","areaJpn":"100","priceCn":"10100","priceJpn":"10000","hType":"2","shType":"0"}]
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
         * id : 3
         * titleCn : 房屋标题中文（租房）
         * titleJpn : 房屋标题日文（租房）
         * addressCn : 1
         * addressJpn : 1
         * doorModelCn : 户型
         * doorModelJpn : 户型
         * areaCn : 100
         * areaJpn : 100
         * priceCn : 10100
         * priceJpn : 10000
         * hType : 2
         * shType : 0
         */

        private int id;
        private String titleCn;
        private String titleJpn;
        private String addressCn;
        private String addressJpn;
        private String doorModelCn;
        private String doorModelJpn;
        private String areaCn;
        private String areaJpn;
        private String priceCn;
        private String priceJpn;
        private String hType;
        private String shType;

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

        public String getDoorModelCn() {
            return doorModelCn;
        }

        public void setDoorModelCn(String doorModelCn) {
            this.doorModelCn = doorModelCn;
        }

        public String getDoorModelJpn() {
            return doorModelJpn;
        }

        public void setDoorModelJpn(String doorModelJpn) {
            this.doorModelJpn = doorModelJpn;
        }

        public String getAreaCn() {
            return areaCn;
        }

        public void setAreaCn(String areaCn) {
            this.areaCn = areaCn;
        }

        public String getAreaJpn() {
            return areaJpn;
        }

        public void setAreaJpn(String areaJpn) {
            this.areaJpn = areaJpn;
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

        public String getHType() {
            return hType;
        }

        public void setHType(String hType) {
            this.hType = hType;
        }

        public String getShType() {
            return shType;
        }

        public void setShType(String shType) {
            this.shType = shType;
        }
    }
}
