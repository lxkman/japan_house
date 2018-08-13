package com.haiwai.administrator.japanhouse.model;

import java.util.List;

/**
 * Created by   admin on 2018/5/21.
 */

public class SearchHouseBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":2,"titleCn":"房屋标题中文（新房）","titleJpn":"房屋标题日文（新房）","specificLocationCn":"1","specificLocationJpn":"1","areaJpn":"","areaCn":"","priceCn":"","priceJpn":"","videoImgs":"","roomImgs":""}]
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
         "id": 2,
         "titleCn": "房屋标题中文（新房）",  //标题
         "titleJpn": "房屋标题日文（新房）",
         "specificLocationCn": "1",  //具体位置
         "specificLocationJpn": "1",
         "areaJpn": "", //面积
         "areaCn": "",
         "priceCn": "", //价格
         "priceJpn": "",
         "videoImgs": "", //视频封面图
         "roomImgs": "" //普通图片
         */

        private int id;
        private String titleCn;
        private String titleJpn;
        private String specificLocationCn;
        private String specificLocationJpn;
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
