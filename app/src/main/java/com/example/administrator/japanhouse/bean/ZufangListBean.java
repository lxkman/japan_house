package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/5.
 */

public class ZufangListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"specificLocationCn":"1","areaCn":"100","videoImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg","priceJpn":"","houseType":"5","priceCn":"","rentJpn":"10000","specificLocationJpn":"1","roomImgs":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg","rentCn":"10100","titleCn":"房屋标题中文（租房）","titleJpn":"房屋标题日文（租房）","areaJpn":"100","id":10,"doorModelCn":"户型","imageList":[{"val":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg"}],"doorModelJpn":"户型"}]
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
         * videoImgs : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg
         * priceJpn :
         * houseType : 5
         * priceCn :
         * rentJpn : 10000
         * specificLocationJpn : 1
         * roomImgs : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg
         * rentCn : 10100
         * titleCn : 房屋标题中文（租房）
         * titleJpn : 房屋标题日文（租房）
         * areaJpn : 100
         * id : 10
         * doorModelCn : 户型
         * imageList : [{"val":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg"}]
         * doorModelJpn : 户型
         */
        private String specificLocationCn;
        private String areaCn;
        private String videoImgs;
        private String priceJpn;
        private String houseType;
        private String priceCn;
        private String rentJpn;
        private String specificLocationJpn;
        private String roomImgs;
        private String rentCn;
        private String titleCn;
        private String titleJpn;
        private String areaJpn;
        private int id;
        private int zfType;
        private String doorModelCn;
        private List<ImageListEntity> imageList;
        private String doorModelJpn;

        public int getZfType() {
            return zfType;
        }

        public void setZfType(int zfType) {
            this.zfType = zfType;
        }

        public void setSpecificLocationCn(String specificLocationCn) {
            this.specificLocationCn = specificLocationCn;
        }

        public void setAreaCn(String areaCn) {
            this.areaCn = areaCn;
        }

        public void setVideoImgs(String videoImgs) {
            this.videoImgs = videoImgs;
        }

        public void setPriceJpn(String priceJpn) {
            this.priceJpn = priceJpn;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public void setPriceCn(String priceCn) {
            this.priceCn = priceCn;
        }

        public void setRentJpn(String rentJpn) {
            this.rentJpn = rentJpn;
        }

        public void setSpecificLocationJpn(String specificLocationJpn) {
            this.specificLocationJpn = specificLocationJpn;
        }

        public void setRoomImgs(String roomImgs) {
            this.roomImgs = roomImgs;
        }

        public void setRentCn(String rentCn) {
            this.rentCn = rentCn;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
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

        public void setDoorModelCn(String doorModelCn) {
            this.doorModelCn = doorModelCn;
        }

        public void setImageList(List<ImageListEntity> imageList) {
            this.imageList = imageList;
        }

        public void setDoorModelJpn(String doorModelJpn) {
            this.doorModelJpn = doorModelJpn;
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

        public String getPriceJpn() {
            return priceJpn;
        }

        public String getHouseType() {
            return houseType;
        }

        public String getPriceCn() {
            return priceCn;
        }

        public String getRentJpn() {
            return rentJpn;
        }

        public String getSpecificLocationJpn() {
            return specificLocationJpn;
        }

        public String getRoomImgs() {
            return roomImgs;
        }

        public String getRentCn() {
            return rentCn;
        }

        public String getTitleCn() {
            return titleCn;
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

        public String getDoorModelCn() {
            return doorModelCn;
        }

        public List<ImageListEntity> getImageList() {
            return imageList;
        }

        public String getDoorModelJpn() {
            return doorModelJpn;
        }

        public static class ImageListEntity {
            /**
             * val : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527055539469&di=2767442bde9416f0f4110731a704eae5&imgtype=0&src=http%3A%2F%2Fg.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F03087bf40ad162d97cc5dcbe1ddfa9ec8a13cd6d.jpg
             */
            private String val;

            public void setVal(String val) {
                this.val = val;
            }

            public String getVal() {
                return val;
            }
        }
    }
}
