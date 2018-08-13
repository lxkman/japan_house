package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */

public class OldHouseShaiXuanBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"shoujia":[{"endVal":2000,"screeType":"2","starVal":1000,"createTime":1526547551000,"houseType":"0","screeValCn":"1000-2000元","updateTime":1526547554000,"id":2,"screeValJpn":"1000-2000元","isdeleted":"0"}],"more":[{"data":[{"endVal":100,"screeType":"1","starVal":10,"createTime":1526547496000,"houseType":"0","screeValCn":"10-100平方米","updateTime":1526547498000,"id":1,"screeValJpn":"10-100平方米","isdeleted":"0"},{"endVal":1000,"screeType":"1","starVal":100,"createTime":1526873143000,"houseType":"0","screeValCn":"100-1000平方米","updateTime":1526873145000,"id":3,"screeValJpn":"100-1000平方米","isdeleted":"0"}],"nameCn":"面积","nameJpn":"面積"},{"data":[{"endVal":2000,"screeType":"2","starVal":1000,"createTime":1526547551000,"houseType":"0","screeValCn":"1000-2000元","updateTime":1526547554000,"id":2,"screeValJpn":"1000-2000元","isdeleted":"0"}],"nameCn":"售价","nameJpn":"価格"}],"mianji":[{"endVal":100,"screeType":"1","starVal":10,"createTime":1526547496000,"houseType":"0","screeValCn":"10-100平方米","updateTime":1526547498000,"id":1,"screeValJpn":"10-100平方米","isdeleted":"0"},{"endVal":1000,"screeType":"1","starVal":100,"createTime":1526873143000,"houseType":"0","screeValCn":"100-1000平方米","updateTime":1526873145000,"id":3,"screeValJpn":"100-1000平方米","isdeleted":"0"}]}
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
         * shoujia : [{"endVal":2000,"screeType":"2","starVal":1000,"createTime":1526547551000,"houseType":"0","screeValCn":"1000-2000元","updateTime":1526547554000,"id":2,"screeValJpn":"1000-2000元","isdeleted":"0"}]
         * more : [{"data":[{"endVal":100,"screeType":"1","starVal":10,"createTime":1526547496000,"houseType":"0","screeValCn":"10-100平方米","updateTime":1526547498000,"id":1,"screeValJpn":"10-100平方米","isdeleted":"0"},{"endVal":1000,"screeType":"1","starVal":100,"createTime":1526873143000,"houseType":"0","screeValCn":"100-1000平方米","updateTime":1526873145000,"id":3,"screeValJpn":"100-1000平方米","isdeleted":"0"}],"nameCn":"面积","nameJpn":"面積"},{"data":[{"endVal":2000,"screeType":"2","starVal":1000,"createTime":1526547551000,"houseType":"0","screeValCn":"1000-2000元","updateTime":1526547554000,"id":2,"screeValJpn":"1000-2000元","isdeleted":"0"}],"nameCn":"售价","nameJpn":"価格"}]
         * mianji : [{"endVal":100,"screeType":"1","starVal":10,"createTime":1526547496000,"houseType":"0","screeValCn":"10-100平方米","updateTime":1526547498000,"id":1,"screeValJpn":"10-100平方米","isdeleted":"0"},{"endVal":1000,"screeType":"1","starVal":100,"createTime":1526873143000,"houseType":"0","screeValCn":"100-1000平方米","updateTime":1526873145000,"id":3,"screeValJpn":"100-1000平方米","isdeleted":"0"}]
         */
        private List<ShoujiaEntity> shoujia;
        private List<MoreEntity> more;
        private List<MianjiEntity> mianji;

        public void setShoujia(List<ShoujiaEntity> shoujia) {
            this.shoujia = shoujia;
        }

        public void setMore(List<MoreEntity> more) {
            this.more = more;
        }

        public void setMianji(List<MianjiEntity> mianji) {
            this.mianji = mianji;
        }

        public List<ShoujiaEntity> getShoujia() {
            return shoujia;
        }

        public List<MoreEntity> getMore() {
            return more;
        }

        public List<MianjiEntity> getMianji() {
            return mianji;
        }

        public static class ShoujiaEntity {
            /**
             * endVal : 2000
             * screeType : 2
             * starVal : 1000
             * createTime : 1526547551000
             * houseType : 0
             * screeValCn : 1000-2000元
             * updateTime : 1526547554000
             * id : 2
             * screeValJpn : 1000-2000元
             * isdeleted : 0
             */
            private int endVal;
            private String screeType;
            private int starVal;
            private long createTime;
            private String houseType;
            private String screeValCn;
            private long updateTime;
            private int id;
            private String screeValJpn;
            private String isdeleted;

            public void setEndVal(int endVal) {
                this.endVal = endVal;
            }

            public void setScreeType(String screeType) {
                this.screeType = screeType;
            }

            public void setStarVal(int starVal) {
                this.starVal = starVal;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
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

            public String getScreeType() {
                return screeType;
            }

            public int getStarVal() {
                return starVal;
            }

            public long getCreateTime() {
                return createTime;
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

        public static class MoreEntity {
            /**
             * data : [{"endVal":100,"screeType":"1","starVal":10,"createTime":1526547496000,"houseType":"0","screeValCn":"10-100平方米","updateTime":1526547498000,"id":1,"screeValJpn":"10-100平方米","isdeleted":"0"},{"endVal":1000,"screeType":"1","starVal":100,"createTime":1526873143000,"houseType":"0","screeValCn":"100-1000平方米","updateTime":1526873145000,"id":3,"screeValJpn":"100-1000平方米","isdeleted":"0"}]
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
                 * endVal : 100
                 * screeType : 1
                 * starVal : 10
                 * createTime : 1526547496000
                 * houseType : 0
                 * screeValCn : 10-100平方米
                 * updateTime : 1526547498000
                 * id : 1
                 * screeValJpn : 10-100平方米
                 * isdeleted : 0
                 */
                private int endVal;
                private String screeType;
                private int starVal;
                private long createTime;
                private String houseType;
                private String screeValCn;
                private long updateTime;
                private int id;
                private String screeValJpn;
                private String isdeleted;

                public void setEndVal(int endVal) {
                    this.endVal = endVal;
                }

                public void setScreeType(String screeType) {
                    this.screeType = screeType;
                }

                public void setStarVal(int starVal) {
                    this.starVal = starVal;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
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

                public String getScreeType() {
                    return screeType;
                }

                public int getStarVal() {
                    return starVal;
                }

                public long getCreateTime() {
                    return createTime;
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

        public static class MianjiEntity {
            /**
             * endVal : 100
             * screeType : 1
             * starVal : 10
             * createTime : 1526547496000
             * houseType : 0
             * screeValCn : 10-100平方米
             * updateTime : 1526547498000
             * id : 1
             * screeValJpn : 10-100平方米
             * isdeleted : 0
             */
            private int endVal;
            private String screeType;
            private int starVal;
            private long createTime;
            private String houseType;
            private String screeValCn;
            private long updateTime;
            private int id;
            private String screeValJpn;
            private String isdeleted;

            public void setEndVal(int endVal) {
                this.endVal = endVal;
            }

            public void setScreeType(String screeType) {
                this.screeType = screeType;
            }

            public void setStarVal(int starVal) {
                this.starVal = starVal;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
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

            public String getScreeType() {
                return screeType;
            }

            public int getStarVal() {
                return starVal;
            }

            public long getCreateTime() {
                return createTime;
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
