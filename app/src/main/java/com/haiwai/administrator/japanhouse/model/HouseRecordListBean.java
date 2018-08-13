package com.haiwai.administrator.japanhouse.model;

import java.util.List;

/**
 * 作者：  admin on 2018/6/1 11:52
 * <p>
 * 邮箱：github.com
 */
public class HouseRecordListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":4,"titleCn":"房屋标题中文（租房）","titleJpn":"房屋标题日文（租房）","addressCn":"1","addressJpn":"1","doorModelCn":"户型","doorModelJpn":"户型","areaCn":"100","areaJpn":"100","priceCn":"3000元/月","priceJpn":"10000","hType":"2","shType":"1","imageUrl":"","videoImageUrl":"","createTime":1528699738000},{"id":1,"titleCn":"别墅标题","titleJpn":"别墅标题","addressCn":"具体位置","addressJpn":"具体位置","doorModelCn":"","doorModelJpn":"","areaCn":"","areaJpn":"","priceCn":"售价","priceJpn":"售价","hType":"4","shType":"","imageUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849795&di=83a34452110e5bf1f69c0881b5805cf1&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F10%2F28%2F85bOOOPIC81_1024.jpg","videoImageUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849795&di=83a34452110e5bf1f69c0881b5805cf1&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F12%2F10%2F28%2F85bOOOPIC81_1024.jpg","createTime":1528699664000},{"id":2,"titleCn":"房屋标题中文（租房）","titleJpn":"房屋标题日文（租房）","addressCn":"1","addressJpn":"1","doorModelCn":"户型","doorModelJpn":"户型","areaCn":"100","areaJpn":"100","priceCn":"3000万","priceJpn":"3000万","hType":"1","shType":"","imageUrl":"","videoImageUrl":"","createTime":1528699366000}]
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
         * id : 4
         * titleCn : 房屋标题中文（租房）
         * titleJpn : 房屋标题日文（租房）
         * addressCn : 1
         * addressJpn : 1
         * doorModelCn : 户型
         * doorModelJpn : 户型
         * areaCn : 100
         * areaJpn : 100
         * priceCn : 3000元/月
         * priceJpn : 10000
         * hType : 2
         * shType : 1
         * imageUrl :
         * videoImageUrl :
         * createTime : 1528699738000
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
        private long createTime;

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

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
