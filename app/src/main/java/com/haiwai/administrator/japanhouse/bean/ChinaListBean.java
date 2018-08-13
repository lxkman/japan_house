package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/28.
 */

public class ChinaListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"specificLocationCn":"具体位置","areaCn":"面积","sellingPriceCn":"售价","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527162033136&di=c0908596c05ce9499323f5887b1a549d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fd50735fae6cd7b896fede657052442a7d8330e65.jpg","titleCn":"标题","houseImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527162033136&di=c0908596c05ce9499323f5887b1a549d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fd50735fae6cd7b896fede657052442a7d8330e65.jpg","titleJpn":"标题","areaJpn":"面积","id":2,"specificLocationJpn":"具体位置","sellingPriceJpn":"售价"}]
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
         * specificLocationCn : 具体位置
         * areaCn : 面积
         * sellingPriceCn : 售价
         * videoImgs : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527162033136&di=c0908596c05ce9499323f5887b1a549d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fd50735fae6cd7b896fede657052442a7d8330e65.jpg
         * titleCn : 标题
         * houseImgs : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527162033136&di=c0908596c05ce9499323f5887b1a549d&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fd50735fae6cd7b896fede657052442a7d8330e65.jpg
         * titleJpn : 标题
         * areaJpn : 面积
         * id : 2
         * specificLocationJpn : 具体位置
         * sellingPriceJpn : 售价
         */
        private String specificLocationCn;
        private String areaCn;
        private String sellingPriceCn;
        private String videoImgs;
        private String titleCn;
        private String houseImgs;
        private String titleJpn;
        private String areaJpn;
        private int id;
        private String specificLocationJpn;
        private String sellingPriceJpn;
        private String houseTypeCn;
        private String houseTypeJpn;

        public String getHouseTypeJpn() {
            return houseTypeJpn;
        }

        public void setHouseTypeJpn(String houseTypeJpn) {
            this.houseTypeJpn = houseTypeJpn;
        }

        public String getHouseTypeCn() {
            return houseTypeCn;
        }

        public void setHouseTypeCn(String houseTypeCn) {
            this.houseTypeCn = houseTypeCn;
        }

        public void setSpecificLocationCn(String specificLocationCn) {
            this.specificLocationCn = specificLocationCn;
        }

        public void setAreaCn(String areaCn) {
            this.areaCn = areaCn;
        }

        public void setSellingPriceCn(String sellingPriceCn) {
            this.sellingPriceCn = sellingPriceCn;
        }

        public void setVideoImgs(String videoImgs) {
            this.videoImgs = videoImgs;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public void setHouseImgs(String houseImgs) {
            this.houseImgs = houseImgs;
        }

        public void setTitleJpn(String titleJpn) {
            this.titleJpn = titleJpn;
        }

        public void setAreaJpn(String areaJpn) {
            this.areaJpn = areaJpn;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setSpecificLocationJpn(String specificLocationJpn) {
            this.specificLocationJpn = specificLocationJpn;
        }

        public void setSellingPriceJpn(String sellingPriceJpn) {
            this.sellingPriceJpn = sellingPriceJpn;
        }

        public String getSpecificLocationCn() {
            return specificLocationCn;
        }

        public String getAreaCn() {
            return areaCn;
        }

        public String getSellingPriceCn() {
            return sellingPriceCn;
        }

        public String getVideoImgs() {
            return videoImgs;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public String getHouseImgs() {
            return houseImgs;
        }

        public String getTitleJpn() {
            return titleJpn;
        }

        public String getAreaJpn() {
            return areaJpn;
        }

        public int getId() {
            return id;
        }

        public String getSpecificLocationJpn() {
            return specificLocationJpn;
        }

        public String getSellingPriceJpn() {
            return sellingPriceJpn;
        }
    }
}
