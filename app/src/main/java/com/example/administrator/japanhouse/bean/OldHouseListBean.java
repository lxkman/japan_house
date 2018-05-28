package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */

public class OldHouseListBean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"titleCn":"房屋标题中文（租房）","titleJpn":"房屋标题日文（租房）","specificLocationCn":"1","specificLocationJpn":"1","areaJpn":"100","areaCn":"100","priceCn":"","priceJpn":"","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg","roomImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg","doorModelCn":"","doorModelJpn":""}]
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
         * titleCn : 房屋标题中文（租房）
         * titleJpn : 房屋标题日文（租房）
         * specificLocationCn : 1
         * specificLocationJpn : 1
         * areaJpn : 100
         * areaCn : 100
         * priceCn :
         * priceJpn :
         * videoImgs : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg
         * roomImgs : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg
         * doorModelCn :
         * doorModelJpn :
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
        private String doorModelCn;
        private String doorModelJpn;

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
    }
}
