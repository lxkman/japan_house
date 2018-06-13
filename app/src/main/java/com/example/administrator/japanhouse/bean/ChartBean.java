package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public class ChartBean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : {"monthbfb":"0.0%","yearbfb":"200.0%","zxtlist":[{"days":1526572800000,"avgPrice":2},{"days":1528646400000,"avgPrice":2}],"bigandsmallval":{"endVal":2,"bigVal":2}}
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
         * monthbfb : 0.0%
         * yearbfb : 200.0%
         * zxtlist : [{"days":1526572800000,"avgPrice":2},{"days":1528646400000,"avgPrice":2}]
         * bigandsmallval : {"endVal":2,"bigVal":2}
         */

        private Float monthbfb;
        private Float yearbfb;
        private BigandsmallvalBean bigandsmallval;
        private List<ZxtlistBean> zxtlist;

        public Float getMonthbfb() {
            return monthbfb;
        }

        public void setMonthbfb(Float monthbfb) {
            this.monthbfb = monthbfb;
        }

        public Float getYearbfb() {
            return yearbfb;
        }

        public void setYearbfb(Float yearbfb) {
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
             * endVal : 2
             * bigVal : 2
             */

            private int endVal;
            private int bigVal;

            public int getEndVal() {
                return endVal;
            }

            public void setEndVal(int endVal) {
                this.endVal = endVal;
            }

            public int getBigVal() {
                return bigVal;
            }

            public void setBigVal(int bigVal) {
                this.bigVal = bigVal;
            }
        }

        public static class ZxtlistBean {
            /**
             * days : 1526572800000
             * avgPrice : 2
             */

            private long days;
            private int avgPrice;

            public long getDays() {
                return days;
            }

            public void setDays(long days) {
                this.days = days;
            }

            public int getAvgPrice() {
                return avgPrice;
            }

            public void setAvgPrice(int avgPrice) {
                this.avgPrice = avgPrice;
            }
        }
    }
}
