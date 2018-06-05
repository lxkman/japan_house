package com.example.administrator.japanhouse.model;

import java.util.List;

/**
 * 作者：  admin on 2018/6/5 17:31
 * 邮箱：github.com
 */
public class OrderBean {
    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":8,"houseId":1,"userId":17,"transactionPriceCn":"成交价格","transactionPriceJpn":"成交价格","titleCn":"标题","titleJpn":"标题","priceCn":"价格","priceJpn":"价格","areaCn":"面积","areaJpn":"面积","yearBuiltCn":"建筑年份","yearBuiltJpn":"建筑年份","imgUrl":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1526960221&di=d5a70cd88be67479c20836cbdb04c46d&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg,https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1526960221&di=d5a70cd88be67479c20836cbdb04c46d&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg","addressCn":"地址","addressJpn":"","houseType":"7","createTime":1527147823000,"updateTime":1527147827000,"isDeleted":0,"doorTypeCn":"户型","doorTypeJpn":"户型"}]
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
         * id : 8
         * houseId : 1
         * userId : 17
         * transactionPriceCn : 成交价格
         * transactionPriceJpn : 成交价格
         * titleCn : 标题
         * titleJpn : 标题
         * priceCn : 价格
         * priceJpn : 价格
         * areaCn : 面积
         * areaJpn : 面积
         * yearBuiltCn : 建筑年份
         * yearBuiltJpn : 建筑年份
         * imgUrl : https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1526960221&di=d5a70cd88be67479c20836cbdb04c46d&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg,https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1526960221&di=d5a70cd88be67479c20836cbdb04c46d&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg
         * addressCn : 地址
         * addressJpn :
         * houseType : 7
         * createTime : 1527147823000
         * updateTime : 1527147827000
         * isDeleted : 0
         * doorTypeCn : 户型
         * doorTypeJpn : 户型
         */

        private int id;
        private int houseId;
        private int userId;
        private String transactionPriceCn;
        private String transactionPriceJpn;
        private String titleCn;
        private String titleJpn;
        private String priceCn;
        private String priceJpn;
        private String areaCn;
        private String areaJpn;
        private String yearBuiltCn;
        private String yearBuiltJpn;
        private String imgUrl;
        private String addressCn;
        private String addressJpn;
        private String houseType;
        private long createTime;
        private long updateTime;
        private int isDeleted;
        private String doorTypeCn;
        private String doorTypeJpn;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHouseId() {
            return houseId;
        }

        public void setHouseId(int houseId) {
            this.houseId = houseId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getTransactionPriceCn() {
            return transactionPriceCn;
        }

        public void setTransactionPriceCn(String transactionPriceCn) {
            this.transactionPriceCn = transactionPriceCn;
        }

        public String getTransactionPriceJpn() {
            return transactionPriceJpn;
        }

        public void setTransactionPriceJpn(String transactionPriceJpn) {
            this.transactionPriceJpn = transactionPriceJpn;
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

        public String getYearBuiltCn() {
            return yearBuiltCn;
        }

        public void setYearBuiltCn(String yearBuiltCn) {
            this.yearBuiltCn = yearBuiltCn;
        }

        public String getYearBuiltJpn() {
            return yearBuiltJpn;
        }

        public void setYearBuiltJpn(String yearBuiltJpn) {
            this.yearBuiltJpn = yearBuiltJpn;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
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

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getDoorTypeCn() {
            return doorTypeCn;
        }

        public void setDoorTypeCn(String doorTypeCn) {
            this.doorTypeCn = doorTypeCn;
        }

        public String getDoorTypeJpn() {
            return doorTypeJpn;
        }

        public void setDoorTypeJpn(String doorTypeJpn) {
            this.doorTypeJpn = doorTypeJpn;
        }
    }
}
