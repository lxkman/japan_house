package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ZuHouseShaiXuanBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"more":[{"data":[{"endVal":5000,"smallHouseType":"0","createTime":1526549181000,"screeType":"2","starVal":3000,"houseType":"0","screeValCn":"3000/月-5000/月","updateTime":1526549178000,"id":1,"screeValJpn":"3000/月-5000/月","isdeleted":"0"}],"nameCn":"租金","nameJpn":"価格"},{"data":[{"endVal":150,"smallHouseType":"0","createTime":1526549181000,"screeType":"1","starVal":100,"houseType":"0","screeValCn":"100-150","updateTime":1526549178000,"id":2,"screeValJpn":"100-150","isdeleted":"0"}],"nameCn":"面积","nameJpn":"価格"},{"data":[{"smallHouseType":"0","createTime":1527059540000,"screeType":"6","houseType":"0","screeValCn":"3分钟以内","updateTime":1527059537000,"id":3,"screeValJpn":"3分钟以内","isdeleted":"0"}],"nameCn":"车站距离","nameJpn":"駅からの距離"},{"data":[{"smallHouseType":"0","createTime":1527059540000,"screeType":"3","houseType":"0","screeValCn":"3层","updateTime":1527059537000,"id":4,"screeValJpn":"3层","isdeleted":"0"}],"nameCn":"楼层","nameJpn":"階建て"},{"data":[{"smallHouseType":"0","createTime":1527059540000,"screeType":"5","houseType":"0","screeValCn":"东","updateTime":1527059537000,"id":5,"screeValJpn":"东","isdeleted":"0"}],"nameCn":"朝向","nameJpn":"向き"},{"data":[{"smallHouseType":"0","screeType":"0","houseType":"0","screeValCn":"1K/1DK/1LDK","id":6,"screeValJpn":"1K/1DK/1LDK","isdeleted":"0"}],"nameCn":"户型","nameJpn":"間取り"},{"data":[{"endVal":2000,"smallHouseType":"0","screeType":"4","starVal":1990,"houseType":"0","screeValCn":"1990-2000","id":7,"screeValJpn":"1990-2000","isdeleted":"0"}],"nameCn":"建筑年份","nameJpn":"建築年分"},{"data":[{"smallHouseType":"0","screeType":"7","houseType":"0","screeValCn":"押金","id":8,"screeValJpn":"押金","isdeleted":"0"}],"nameCn":"初期费用","nameJpn":"初期費用"},{"data":[{"smallHouseType":"0","screeType":"8","houseType":"0","id":9,"isdeleted":"0"}],"nameCn":"人气选择","nameJpn":"人気選択"}],"zujin":[{"endVal":5000,"smallHouseType":"0","createTime":1526549181000,"screeType":"2","starVal":3000,"houseType":"0","screeValCn":"3000/月-5000/月","updateTime":1526549178000,"id":1,"screeValJpn":"3000/月-5000/月","isdeleted":"0"}],"mianji":[{"endVal":150,"smallHouseType":"0","createTime":1526549181000,"screeType":"1","starVal":100,"houseType":"0","screeValCn":"100-150","updateTime":1526549178000,"id":2,"screeValJpn":"100-150","isdeleted":"0"}]}
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
         * more : [{"data":[{"endVal":5000,"smallHouseType":"0","createTime":1526549181000,"screeType":"2","starVal":3000,"houseType":"0","screeValCn":"3000/月-5000/月","updateTime":1526549178000,"id":1,"screeValJpn":"3000/月-5000/月","isdeleted":"0"}],"nameCn":"租金","nameJpn":"価格"},{"data":[{"endVal":150,"smallHouseType":"0","createTime":1526549181000,"screeType":"1","starVal":100,"houseType":"0","screeValCn":"100-150","updateTime":1526549178000,"id":2,"screeValJpn":"100-150","isdeleted":"0"}],"nameCn":"面积","nameJpn":"価格"},{"data":[{"smallHouseType":"0","createTime":1527059540000,"screeType":"6","houseType":"0","screeValCn":"3分钟以内","updateTime":1527059537000,"id":3,"screeValJpn":"3分钟以内","isdeleted":"0"}],"nameCn":"车站距离","nameJpn":"駅からの距離"},{"data":[{"smallHouseType":"0","createTime":1527059540000,"screeType":"3","houseType":"0","screeValCn":"3层","updateTime":1527059537000,"id":4,"screeValJpn":"3层","isdeleted":"0"}],"nameCn":"楼层","nameJpn":"階建て"},{"data":[{"smallHouseType":"0","createTime":1527059540000,"screeType":"5","houseType":"0","screeValCn":"东","updateTime":1527059537000,"id":5,"screeValJpn":"东","isdeleted":"0"}],"nameCn":"朝向","nameJpn":"向き"},{"data":[{"smallHouseType":"0","screeType":"0","houseType":"0","screeValCn":"1K/1DK/1LDK","id":6,"screeValJpn":"1K/1DK/1LDK","isdeleted":"0"}],"nameCn":"户型","nameJpn":"間取り"},{"data":[{"endVal":2000,"smallHouseType":"0","screeType":"4","starVal":1990,"houseType":"0","screeValCn":"1990-2000","id":7,"screeValJpn":"1990-2000","isdeleted":"0"}],"nameCn":"建筑年份","nameJpn":"建築年分"},{"data":[{"smallHouseType":"0","screeType":"7","houseType":"0","screeValCn":"押金","id":8,"screeValJpn":"押金","isdeleted":"0"}],"nameCn":"初期费用","nameJpn":"初期費用"},{"data":[{"smallHouseType":"0","screeType":"8","houseType":"0","id":9,"isdeleted":"0"}],"nameCn":"人气选择","nameJpn":"人気選択"}]
         * zujin : [{"endVal":5000,"smallHouseType":"0","createTime":1526549181000,"screeType":"2","starVal":3000,"houseType":"0","screeValCn":"3000/月-5000/月","updateTime":1526549178000,"id":1,"screeValJpn":"3000/月-5000/月","isdeleted":"0"}]
         * mianji : [{"endVal":150,"smallHouseType":"0","createTime":1526549181000,"screeType":"1","starVal":100,"houseType":"0","screeValCn":"100-150","updateTime":1526549178000,"id":2,"screeValJpn":"100-150","isdeleted":"0"}]
         */
        private List<MoreEntity> more;
        private List<ZujinEntity> zujin;
        private List<MianjiEntity> mianji;

        public void setMore(List<MoreEntity> more) {
            this.more = more;
        }

        public void setZujin(List<ZujinEntity> zujin) {
            this.zujin = zujin;
        }

        public void setMianji(List<MianjiEntity> mianji) {
            this.mianji = mianji;
        }

        public List<MoreEntity> getMore() {
            return more;
        }

        public List<ZujinEntity> getZujin() {
            return zujin;
        }

        public List<MianjiEntity> getMianji() {
            return mianji;
        }

        public static class MoreEntity {
            /**
             * data : [{"endVal":5000,"smallHouseType":"0","createTime":1526549181000,"screeType":"2","starVal":3000,"houseType":"0","screeValCn":"3000/月-5000/月","updateTime":1526549178000,"id":1,"screeValJpn":"3000/月-5000/月","isdeleted":"0"}]
             * nameCn : 租金
             * nameJpn : 価格
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
                 * endVal : 5000
                 * smallHouseType : 0
                 * createTime : 1526549181000
                 * screeType : 2
                 * starVal : 3000
                 * houseType : 0
                 * screeValCn : 3000/月-5000/月
                 * updateTime : 1526549178000
                 * id : 1
                 * screeValJpn : 3000/月-5000/月
                 * isdeleted : 0
                 */
                private int endVal;
                private String smallHouseType;
                private long createTime;
                private String screeType;
                private int starVal;
                private String houseType;
                private String screeValCn;
                private long updateTime;
                private int id;
                private String screeValJpn;
                private String isdeleted;

                public void setEndVal(int endVal) {
                    this.endVal = endVal;
                }

                public void setSmallHouseType(String smallHouseType) {
                    this.smallHouseType = smallHouseType;
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

                public int getEndVal() {
                    return endVal;
                }

                public String getSmallHouseType() {
                    return smallHouseType;
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
            }
        }

        public static class ZujinEntity {
            /**
             * endVal : 5000
             * smallHouseType : 0
             * createTime : 1526549181000
             * screeType : 2
             * starVal : 3000
             * houseType : 0
             * screeValCn : 3000/月-5000/月
             * updateTime : 1526549178000
             * id : 1
             * screeValJpn : 3000/月-5000/月
             * isdeleted : 0
             */
            private int endVal;
            private String smallHouseType;
            private long createTime;
            private String screeType;
            private int starVal;
            private String houseType;
            private String screeValCn;
            private long updateTime;
            private int id;
            private String screeValJpn;
            private String isdeleted;

            public void setEndVal(int endVal) {
                this.endVal = endVal;
            }

            public void setSmallHouseType(String smallHouseType) {
                this.smallHouseType = smallHouseType;
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

            public int getEndVal() {
                return endVal;
            }

            public String getSmallHouseType() {
                return smallHouseType;
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
        }

        public static class MianjiEntity {
            /**
             * endVal : 150
             * smallHouseType : 0
             * createTime : 1526549181000
             * screeType : 1
             * starVal : 100
             * houseType : 0
             * screeValCn : 100-150
             * updateTime : 1526549178000
             * id : 2
             * screeValJpn : 100-150
             * isdeleted : 0
             */
            private int endVal;
            private String smallHouseType;
            private long createTime;
            private String screeType;
            private int starVal;
            private String houseType;
            private String screeValCn;
            private long updateTime;
            private int id;
            private String screeValJpn;
            private String isdeleted;

            public void setEndVal(int endVal) {
                this.endVal = endVal;
            }

            public void setSmallHouseType(String smallHouseType) {
                this.smallHouseType = smallHouseType;
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

            public int getEndVal() {
                return endVal;
            }

            public String getSmallHouseType() {
                return smallHouseType;
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
        }
    }
}
