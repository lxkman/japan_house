package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public class ChartBean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : {"monthbfb":0,"yearbfb":0,"zxtlist":[{"days":1526572800000,"avgPrice":1},{"days":1528300800000,"avgPrice":1},{"days":1528646400000,"avgPrice":1},{"days":1528992000000,"avgPrice":1},{"days":1529424000000,"avgPrice":1}],"bigandsmallval":{"endVal":1.0E-4,"bigVal":1.0E-4}}
     */

    private String msg;
    private String code;
    private DatasBean datas;

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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * monthbfb : 0
         * yearbfb : 0
         * zxtlist : [{"days":1526572800000,"avgPrice":1},{"days":1528300800000,"avgPrice":1},{"days":1528646400000,"avgPrice":1},{"days":1528992000000,"avgPrice":1},{"days":1529424000000,"avgPrice":1}]
         * bigandsmallval : {"endVal":1.0E-4,"bigVal":1.0E-4}
         */

        private int monthbfb;
        private int yearbfb;
        private BigandsmallvalBean bigandsmallval;
        private List<ZxtlistBean> zxtlist;

        public float getMonthbfb() {
            return monthbfb;
        }

        public void setMonthbfb(int monthbfb) {
            this.monthbfb = monthbfb;
        }

        public float getYearbfb() {
            return yearbfb;
        }

        public void setYearbfb(int yearbfb) {
            this.yearbfb = yearbfb;
        }

        public BigandsmallvalBean getBigandsmallval() {
            return bigandsmallval;
        }

        public void setBigandsmallval(BigandsmallvalBean bigandsmallval) {
            this.bigandsmallval = bigandsmallval;
        }

        public List<ZxtlistBean> getZxtlist() {
            return zxtlist;
        }

        public void setZxtlist(List<ZxtlistBean> zxtlist) {
            this.zxtlist = zxtlist;
        }

        public static class BigandsmallvalBean {
            /**
             * endVal : 1.0E-4
             * bigVal : 1.0E-4
             */

            private double endVal;
            private double bigVal;

            public double getEndVal() {
                return endVal;
            }

            public void setEndVal(double endVal) {
                this.endVal = endVal;
            }

            public double getBigVal() {
                return bigVal;
            }

            public void setBigVal(double bigVal) {
                this.bigVal = bigVal;
            }
        }

        public static class ZxtlistBean {
            /**
             * days : 1526572800000
             * avgPrice : 1
             */

            private long days;
            private double avgPrice;

            public long getDays() {
                return days;
            }

            public void setDays(long days) {
                this.days = days;
            }

            public double getAvgPrice() {
                return avgPrice;
            }

            public void setAvgPrice(double avgPrice) {
                this.avgPrice = avgPrice;
            }
        }
    }
}
