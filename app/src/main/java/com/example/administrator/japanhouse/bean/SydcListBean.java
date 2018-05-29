package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/24.
 */

public class SydcListBean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","sellingPriceCn":"100","titleCn":"海外地产标题","houseType":"0","realEstateImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","titleJpn":"海外地产标题","areaJpn":"100","id":1,"specificLocationJpn":"1","sellingPriceJpn":"100"},{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","sellingPriceCn":"100","titleCn":"海外地产标题","houseType":"1","realEstateImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","titleJpn":"海外地产标题","areaJpn":"100","id":2,"specificLocationJpn":"1","sellingPriceJpn":"100"},{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","sellingPriceCn":"100","titleCn":"海外地产标题","houseType":"2","realEstateImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","titleJpn":"海外地产标题","areaJpn":"100","id":3,"specificLocationJpn":"1","sellingPriceJpn":"100"},{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","sellingPriceCn":"100","titleCn":"海外地产标题","houseType":"3","realEstateImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","titleJpn":"海外地产标题","areaJpn":"100","id":4,"specificLocationJpn":"1","sellingPriceJpn":"100"},{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","sellingPriceCn":"100","titleCn":"海外地产标题","houseType":"0","realEstateImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","titleJpn":"海外地产标题","areaJpn":"100","id":5,"specificLocationJpn":"1","sellingPriceJpn":"100"},{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","sellingPriceCn":"100","titleCn":"海外地产标题","houseType":"1","realEstateImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","titleJpn":"海外地产标题","areaJpn":"100","id":6,"specificLocationJpn":"1","sellingPriceJpn":"100"},{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","sellingPriceCn":"100","titleCn":"海外地产标题","houseType":"2","realEstateImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","titleJpn":"海外地产标题","areaJpn":"100","id":7,"specificLocationJpn":"1","sellingPriceJpn":"100"},{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","sellingPriceCn":"100","titleCn":"海外地产标题","houseType":"3","realEstateImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","titleJpn":"海外地产标题","areaJpn":"100","id":8,"specificLocationJpn":"1","sellingPriceJpn":"100"},{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","sellingPriceCn":"100","titleCn":"海外地产标题","houseType":"0","realEstateImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","titleJpn":"海外地产标题","areaJpn":"100","id":9,"specificLocationJpn":"1","sellingPriceJpn":"100"},{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","sellingPriceCn":"100","titleCn":"海外地产标题","houseType":"1","realEstateImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg","titleJpn":"海外地产标题","areaJpn":"100","id":10,"specificLocationJpn":"1","sellingPriceJpn":"100"}]
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
         * specificLocationCn : 1
         * areaCn : 100
         * videoImgs : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg
         * sellingPriceCn : 100
         * titleCn : 海外地产标题
         * houseType : 0
         * realEstateImgs : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527165849796&di=ec7155a96b50c7741aac8e50ca34cd98&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F4d086e061d950a7b9138ff1000d162d9f3d3c9d1.jpg
         * titleJpn : 海外地产标题
         * areaJpn : 100
         * id : 1
         * specificLocationJpn : 1
         * sellingPriceJpn : 100
         */
        private String specificLocationCn;
        private String areaCn;
        private String videoImgs;
        private String sellingPriceCn;
        private String titleCn;
        private String houseType;
        private String realEstateImgs;
        private String titleJpn;
        private String areaJpn;
        private int id;
        private String specificLocationJpn;
        private String sellingPriceJpn;

        public void setSpecificLocationCn(String specificLocationCn) {
            this.specificLocationCn = specificLocationCn;
        }

        public void setAreaCn(String areaCn) {
            this.areaCn = areaCn;
        }

        public void setVideoImgs(String videoImgs) {
            this.videoImgs = videoImgs;
        }

        public void setSellingPriceCn(String sellingPriceCn) {
            this.sellingPriceCn = sellingPriceCn;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public void setRealEstateImgs(String realEstateImgs) {
            this.realEstateImgs = realEstateImgs;
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

        public String getVideoImgs() {
            return videoImgs;
        }

        public String getSellingPriceCn() {
            return sellingPriceCn;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public String getHouseType() {
            return houseType;
        }

        public String getRealEstateImgs() {
            return realEstateImgs;
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
