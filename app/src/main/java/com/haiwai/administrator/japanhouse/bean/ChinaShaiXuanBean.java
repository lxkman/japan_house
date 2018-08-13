package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/29.
 */
public class ChinaShaiXuanBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"shoujia":[{"endVal":30000,"countryOrcityId":"","createTime":1527490849000,"screeType":"2","starVal":0,"houseType":"4","screeValCn":"3万以下","updateTime":1527490851000,"id":93,"screeValJpn":"3万以上","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"countryOrcityId":"","createTime":1527490878000,"screeType":"2","starVal":30000,"houseType":"4","screeValCn":"3万以上","updateTime":1527490879000,"id":94,"screeValJpn":"3万以上","isdeleted":"0","logoUrl":""}],"more":[{"data":[{"endVal":80,"countryOrcityId":"","createTime":1527491018000,"screeType":"1","starVal":0,"houseType":"4","screeValCn":"80以下","updateTime":1527491020000,"id":96,"screeValJpn":"80以下","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"countryOrcityId":"","createTime":1527491048000,"screeType":"1","starVal":80,"houseType":"4","screeValCn":"80以上","updateTime":1527491050000,"id":97,"screeValJpn":"80以上","isdeleted":"0","logoUrl":""}],"nameCn":"面积","nameJpn":"面積"},{"data":[{"endVal":2147483647,"countryOrcityId":"","createTime":1527491073000,"screeType":"6","starVal":0,"houseType":"4","screeValCn":"商业街","updateTime":1527491075000,"id":98,"screeValJpn":"商业街","isdeleted":"0","logoUrl":""}],"nameCn":"地段","nameJpn":"地帯"},{"data":[{"endVal":2147483647,"countryOrcityId":"","createTime":1527491101000,"screeType":"3","starVal":0,"houseType":"4","screeValCn":"1层","updateTime":1527491102000,"id":99,"screeValJpn":"1层","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"countryOrcityId":"","createTime":1527491115000,"screeType":"3","starVal":0,"houseType":"4","screeValCn":"2层","updateTime":1527491117000,"id":100,"screeValJpn":"2层","isdeleted":"0","logoUrl":""}],"nameCn":"楼层","nameJpn":"階建て"},{"data":[{"endVal":2147483647,"countryOrcityId":"","createTime":1527491146000,"screeType":"7","starVal":0,"houseType":"4","screeValCn":"南向","updateTime":1527491148000,"id":101,"screeValJpn":"南向","isdeleted":"0","logoUrl":""}],"nameCn":"朝向","nameJpn":"向き"}],"huxing":[{"endVal":2147483647,"countryOrcityId":"","createTime":1527490968000,"screeType":"0","starVal":0,"houseType":"4","screeValCn":"1R","updateTime":1527490970000,"id":95,"screeValJpn":"1R","isdeleted":"0","logoUrl":""}]}
     */
    private String msg;
    private String code;
    private DatasEntity datas;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDatas(DatasEntity datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public DatasEntity getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * shoujia : [{"endVal":30000,"countryOrcityId":"","createTime":1527490849000,"screeType":"2","starVal":0,"houseType":"4","screeValCn":"3万以下","updateTime":1527490851000,"id":93,"screeValJpn":"3万以上","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"countryOrcityId":"","createTime":1527490878000,"screeType":"2","starVal":30000,"houseType":"4","screeValCn":"3万以上","updateTime":1527490879000,"id":94,"screeValJpn":"3万以上","isdeleted":"0","logoUrl":""}]
         * more : [{"data":[{"endVal":80,"countryOrcityId":"","createTime":1527491018000,"screeType":"1","starVal":0,"houseType":"4","screeValCn":"80以下","updateTime":1527491020000,"id":96,"screeValJpn":"80以下","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"countryOrcityId":"","createTime":1527491048000,"screeType":"1","starVal":80,"houseType":"4","screeValCn":"80以上","updateTime":1527491050000,"id":97,"screeValJpn":"80以上","isdeleted":"0","logoUrl":""}],"nameCn":"面积","nameJpn":"面積"},{"data":[{"endVal":2147483647,"countryOrcityId":"","createTime":1527491073000,"screeType":"6","starVal":0,"houseType":"4","screeValCn":"商业街","updateTime":1527491075000,"id":98,"screeValJpn":"商业街","isdeleted":"0","logoUrl":""}],"nameCn":"地段","nameJpn":"地帯"},{"data":[{"endVal":2147483647,"countryOrcityId":"","createTime":1527491101000,"screeType":"3","starVal":0,"houseType":"4","screeValCn":"1层","updateTime":1527491102000,"id":99,"screeValJpn":"1层","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"countryOrcityId":"","createTime":1527491115000,"screeType":"3","starVal":0,"houseType":"4","screeValCn":"2层","updateTime":1527491117000,"id":100,"screeValJpn":"2层","isdeleted":"0","logoUrl":""}],"nameCn":"楼层","nameJpn":"階建て"},{"data":[{"endVal":2147483647,"countryOrcityId":"","createTime":1527491146000,"screeType":"7","starVal":0,"houseType":"4","screeValCn":"南向","updateTime":1527491148000,"id":101,"screeValJpn":"南向","isdeleted":"0","logoUrl":""}],"nameCn":"朝向","nameJpn":"向き"}]
         * huxing : [{"endVal":2147483647,"countryOrcityId":"","createTime":1527490968000,"screeType":"0","starVal":0,"houseType":"4","screeValCn":"1R","updateTime":1527490970000,"id":95,"screeValJpn":"1R","isdeleted":"0","logoUrl":""}]
         */
        private List<ShoujiaEntity> shoujia;
        private List<MoreEntity> more;
        private List<HuxingEntity> huxing;

        public void setShoujia(List<ShoujiaEntity> shoujia) {
            this.shoujia = shoujia;
        }

        public void setMore(List<MoreEntity> more) {
            this.more = more;
        }

        public void setHuxing(List<HuxingEntity> huxing) {
            this.huxing = huxing;
        }

        public List<ShoujiaEntity> getShoujia() {
            return shoujia;
        }

        public List<MoreEntity> getMore() {
            return more;
        }

        public List<HuxingEntity> getHuxing() {
            return huxing;
        }

        public static class ShoujiaEntity {
            /**
             * endVal : 30000
             * countryOrcityId :
             * createTime : 1527490849000
             * screeType : 2
             * starVal : 0
             * houseType : 4
             * screeValCn : 3万以下
             * updateTime : 1527490851000
             * id : 93
             * screeValJpn : 3万以上
             * isdeleted : 0
             * logoUrl :
             */
            private int endVal;
            private String countryOrcityId;
            private long createTime;
            private String screeType;
            private int starVal;
            private String houseType;
            private String screeValCn;
            private long updateTime;
            private int id;
            private String screeValJpn;
            private String isdeleted;
            private String logoUrl;

            public void setEndVal(int endVal) {
                this.endVal = endVal;
            }

            public void setCountryOrcityId(String countryOrcityId) {
                this.countryOrcityId = countryOrcityId;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setScreeType(String screeType) {
                this.screeType = screeType;
            }

            public void setStarVal(int starVal) {
                this.starVal = starVal;
            }

            public void setHouseType(String houseType) {
                this.houseType = houseType;
            }

            public void setScreeValCn(String screeValCn) {
                this.screeValCn = screeValCn;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setScreeValJpn(String screeValJpn) {
                this.screeValJpn = screeValJpn;
            }

            public void setIsdeleted(String isdeleted) {
                this.isdeleted = isdeleted;
            }

            public void setLogoUrl(String logoUrl) {
                this.logoUrl = logoUrl;
            }

            public int getEndVal() {
                return endVal;
            }

            public String getCountryOrcityId() {
                return countryOrcityId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public String getScreeType() {
                return screeType;
            }

            public int getStarVal() {
                return starVal;
            }

            public String getHouseType() {
                return houseType;
            }

            public String getScreeValCn() {
                return screeValCn;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public int getId() {
                return id;
            }

            public String getScreeValJpn() {
                return screeValJpn;
            }

            public String getIsdeleted() {
                return isdeleted;
            }

            public String getLogoUrl() {
                return logoUrl;
            }
        }

        public static class MoreEntity {
            /**
             * data : [{"endVal":80,"countryOrcityId":"","createTime":1527491018000,"screeType":"1","starVal":0,"houseType":"4","screeValCn":"80以下","updateTime":1527491020000,"id":96,"screeValJpn":"80以下","isdeleted":"0","logoUrl":""},{"endVal":2147483647,"countryOrcityId":"","createTime":1527491048000,"screeType":"1","starVal":80,"houseType":"4","screeValCn":"80以上","updateTime":1527491050000,"id":97,"screeValJpn":"80以上","isdeleted":"0","logoUrl":""}]
             * nameCn : 面积
             * nameJpn : 面積
             */
            private List<DataEntity> data;
            private String nameCn;
            private String nameJpn;

            public void setData(List<DataEntity> data) {
                this.data = data;
            }

            public void setNameCn(String nameCn) {
                this.nameCn = nameCn;
            }

            public void setNameJpn(String nameJpn) {
                this.nameJpn = nameJpn;
            }

            public List<DataEntity> getData() {
                return data;
            }

            public String getNameCn() {
                return nameCn;
            }

            public String getNameJpn() {
                return nameJpn;
            }

            public static class DataEntity {
                /**
                 * endVal : 80
                 * countryOrcityId :
                 * createTime : 1527491018000
                 * screeType : 1
                 * starVal : 0
                 * houseType : 4
                 * screeValCn : 80以下
                 * updateTime : 1527491020000
                 * id : 96
                 * screeValJpn : 80以下
                 * isdeleted : 0
                 * logoUrl :
                 */
                private int endVal;
                private String countryOrcityId;
                private long createTime;
                private String screeType;
                private int starVal;
                private String houseType;
                private String screeValCn;
                private long updateTime;
                private int id;
                private String screeValJpn;
                private String isdeleted;
                private String logoUrl;

                public void setEndVal(int endVal) {
                    this.endVal = endVal;
                }

                public void setCountryOrcityId(String countryOrcityId) {
                    this.countryOrcityId = countryOrcityId;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public void setScreeType(String screeType) {
                    this.screeType = screeType;
                }

                public void setStarVal(int starVal) {
                    this.starVal = starVal;
                }

                public void setHouseType(String houseType) {
                    this.houseType = houseType;
                }

                public void setScreeValCn(String screeValCn) {
                    this.screeValCn = screeValCn;
                }

                public void setUpdateTime(long updateTime) {
                    this.updateTime = updateTime;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setScreeValJpn(String screeValJpn) {
                    this.screeValJpn = screeValJpn;
                }

                public void setIsdeleted(String isdeleted) {
                    this.isdeleted = isdeleted;
                }

                public void setLogoUrl(String logoUrl) {
                    this.logoUrl = logoUrl;
                }

                public int getEndVal() {
                    return endVal;
                }

                public String getCountryOrcityId() {
                    return countryOrcityId;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public String getScreeType() {
                    return screeType;
                }

                public int getStarVal() {
                    return starVal;
                }

                public String getHouseType() {
                    return houseType;
                }

                public String getScreeValCn() {
                    return screeValCn;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public int getId() {
                    return id;
                }

                public String getScreeValJpn() {
                    return screeValJpn;
                }

                public String getIsdeleted() {
                    return isdeleted;
                }

                public String getLogoUrl() {
                    return logoUrl;
                }
            }
        }

        public static class HuxingEntity {
            /**
             * endVal : 2147483647
             * countryOrcityId :
             * createTime : 1527490968000
             * screeType : 0
             * starVal : 0
             * houseType : 4
             * screeValCn : 1R
             * updateTime : 1527490970000
             * id : 95
             * screeValJpn : 1R
             * isdeleted : 0
             * logoUrl :
             */
            private int endVal;
            private String countryOrcityId;
            private long createTime;
            private String screeType;
            private int starVal;
            private String houseType;
            private String screeValCn;
            private long updateTime;
            private int id;
            private String screeValJpn;
            private String isdeleted;
            private String logoUrl;

            public void setEndVal(int endVal) {
                this.endVal = endVal;
            }

            public void setCountryOrcityId(String countryOrcityId) {
                this.countryOrcityId = countryOrcityId;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setScreeType(String screeType) {
                this.screeType = screeType;
            }

            public void setStarVal(int starVal) {
                this.starVal = starVal;
            }

            public void setHouseType(String houseType) {
                this.houseType = houseType;
            }

            public void setScreeValCn(String screeValCn) {
                this.screeValCn = screeValCn;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setScreeValJpn(String screeValJpn) {
                this.screeValJpn = screeValJpn;
            }

            public void setIsdeleted(String isdeleted) {
                this.isdeleted = isdeleted;
            }

            public void setLogoUrl(String logoUrl) {
                this.logoUrl = logoUrl;
            }

            public int getEndVal() {
                return endVal;
            }

            public String getCountryOrcityId() {
                return countryOrcityId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public String getScreeType() {
                return screeType;
            }

            public int getStarVal() {
                return starVal;
            }

            public String getHouseType() {
                return houseType;
            }

            public String getScreeValCn() {
                return screeValCn;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public int getId() {
                return id;
            }

            public String getScreeValJpn() {
                return screeValJpn;
            }

            public String getIsdeleted() {
                return isdeleted;
            }

            public String getLogoUrl() {
                return logoUrl;
            }
        }
    }
}
