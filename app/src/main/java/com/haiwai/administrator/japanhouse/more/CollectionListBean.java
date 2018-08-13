package com.haiwai.administrator.japanhouse.more;

import java.util.List;

/**
 * Created by   admin on 2018/5/31.
 */

public class CollectionListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":184,"titleCn":"","titleJpn":"","addressCn":"9","addressJpn":"9","doorModelCn":"9","doorModelJpn":"9","areaCn":"9.00平方米","areaJpn":"9.00平方メートル","priceCn":"1000.00","priceJpn":"1000.00","hType":"1","shType":"","imageUrl":"https://hwdc-huabei.oss-cn-beijing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180621161322.jpg","videoImageUrl":"https://hwdc-huabei.oss-cn-beijing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180621161322.jpg","createTime":null},{"id":1,"titleCn":"别墅标题","titleJpn":"别墅标题","addressCn":"具体位置","addressJpn":"具体位置","doorModelCn":"","doorModelJpn":"","areaCn":"","areaJpn":"","priceCn":"10.00","priceJpn":"10.00","hType":"4","shType":"","imageUrl":"https://hwdc-huabei.oss-cn-beijing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180621161322.jpg","videoImageUrl":"https://hwdc-huabei.oss-cn-beijing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180621161322.jpg","createTime":null}]
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
         * id : 184
         * titleCn :
         * titleJpn :
         * addressCn : 9
         * addressJpn : 9
         * doorModelCn : 9
         * doorModelJpn : 9
         * areaCn : 9.00平方米
         * areaJpn : 9.00平方メートル
         * priceCn : 1000.00
         * priceJpn : 1000.00
         * hType : 1
         * shType :
         * imageUrl : https://hwdc-huabei.oss-cn-beijing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180621161322.jpg
         * videoImageUrl : https://hwdc-huabei.oss-cn-beijing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20180621161322.jpg
         * createTime : null
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
        private String imageUrl;
        private String videoImageUrl;
        private Object createTime;

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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getVideoImageUrl() {
            return videoImageUrl;
        }

        public void setVideoImageUrl(String videoImageUrl) {
            this.videoImageUrl = videoImageUrl;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }
    }
}
