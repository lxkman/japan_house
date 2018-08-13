package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class QuYuBean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : {"subwaylines":[{"subwayStations":[{"stationNameJpn":"苹果园","isDeleted":"0","stationNameCn":"苹果园","createTime":1527759590000,"lineId":1,"updateTime":1527759592000,"id":1},{"stationNameJpn":"古城","isDeleted":"0","stationNameCn":"古城","createTime":1527759607000,"lineId":1,"updateTime":1527759609000,"id":2},{"stationNameJpn":"八角游乐园","isDeleted":"0","stationNameCn":"八角游乐园","createTime":1527759633000,"lineId":1,"updateTime":1527759635000,"id":3},{"stationNameJpn":"八宝山","isDeleted":"0","stationNameCn":"八宝山","createTime":1527759653000,"lineId":1,"updateTime":1527759656000,"id":4},{"stationNameJpn":"玉泉路","isDeleted":"0","stationNameCn":"玉泉路","createTime":1527759671000,"lineId":1,"updateTime":1527759673000,"id":5}],"isDeleted":"0","createTime":1527759531000,"lineType":0,"twoAdministrativeId":2,"updateTime":1527759533000,"id":1,"lineNameCn":"1号线","lineNameJpn":"1号线"},{"subwayStations":[{"stationNameJpn":"车公庄","isDeleted":"0","stationNameCn":"车公庄","createTime":1527759697000,"lineId":2,"updateTime":1527759699000,"id":6},{"stationNameJpn":"阜成门","isDeleted":"0","stationNameCn":"阜成门","createTime":1527759717000,"lineId":2,"updateTime":1527759719000,"id":7},{"stationNameJpn":"复兴门","isDeleted":"0","stationNameCn":"复兴门","createTime":1527759735000,"lineId":2,"updateTime":1527759737000,"id":8},{"stationNameJpn":"长椿街","isDeleted":"0","stationNameCn":"长椿街","createTime":1527759759000,"lineId":2,"updateTime":1527759760000,"id":9},{"stationNameJpn":"宣武门","isDeleted":"0","stationNameCn":"宣武门","createTime":1527759777000,"lineId":2,"updateTime":1527759779000,"id":10}],"isDeleted":"0","createTime":1527759557000,"lineType":0,"twoAdministrativeId":2,"updateTime":1527759559000,"id":2,"lineNameCn":"2号线","lineNameJpn":"2号线"}],"areas":[{"administrationNameCn":"海淀区","isDeleted":0,"createTime":1527145998000,"hwdcAreaManages":[{"isDeleted":"0","createTime":1527756338000,"threeAdministrativeId":3,"areaNameCn":"公主坟","areaNameJpn":"公主坟","updateTime":1527756339000,"id":1},{"isDeleted":"0","createTime":1527756356000,"threeAdministrativeId":3,"areaNameCn":"中关村","areaNameJpn":"中关村","updateTime":1527756358000,"id":2},{"isDeleted":"0","createTime":1527756377000,"threeAdministrativeId":3,"areaNameCn":"蓟门桥","areaNameJpn":"蓟门桥","updateTime":1527756379000,"id":3},{"isDeleted":"0","createTime":1527756398000,"threeAdministrativeId":3,"areaNameCn":"上地","areaNameJpn":"上地","updateTime":1527756400000,"id":4},{"isDeleted":"0","createTime":1527756419000,"threeAdministrativeId":3,"areaNameCn":"圆明园","areaNameJpn":"圆明园","updateTime":1527756422000,"id":5}],"latitude":116.03,"citylevel":"3","id":3,"administrationNameJpn":"海淀区","parentId":2,"houseNum":0,"status":"0","longitude":39.53},{"administrationNameCn":"朝阳区","isDeleted":0,"createTime":1527759278000,"hwdcAreaManages":[],"latitude":1,"citylevel":"3","id":27,"administrationNameJpn":"朝阳区","parentId":2,"houseNum":0,"status":"0","longitude":1}]}
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
         * subwaylines : [{"subwayStations":[{"stationNameJpn":"苹果园","isDeleted":"0","stationNameCn":"苹果园","createTime":1527759590000,"lineId":1,"updateTime":1527759592000,"id":1},{"stationNameJpn":"古城","isDeleted":"0","stationNameCn":"古城","createTime":1527759607000,"lineId":1,"updateTime":1527759609000,"id":2},{"stationNameJpn":"八角游乐园","isDeleted":"0","stationNameCn":"八角游乐园","createTime":1527759633000,"lineId":1,"updateTime":1527759635000,"id":3},{"stationNameJpn":"八宝山","isDeleted":"0","stationNameCn":"八宝山","createTime":1527759653000,"lineId":1,"updateTime":1527759656000,"id":4},{"stationNameJpn":"玉泉路","isDeleted":"0","stationNameCn":"玉泉路","createTime":1527759671000,"lineId":1,"updateTime":1527759673000,"id":5}],"isDeleted":"0","createTime":1527759531000,"lineType":0,"twoAdministrativeId":2,"updateTime":1527759533000,"id":1,"lineNameCn":"1号线","lineNameJpn":"1号线"},{"subwayStations":[{"stationNameJpn":"车公庄","isDeleted":"0","stationNameCn":"车公庄","createTime":1527759697000,"lineId":2,"updateTime":1527759699000,"id":6},{"stationNameJpn":"阜成门","isDeleted":"0","stationNameCn":"阜成门","createTime":1527759717000,"lineId":2,"updateTime":1527759719000,"id":7},{"stationNameJpn":"复兴门","isDeleted":"0","stationNameCn":"复兴门","createTime":1527759735000,"lineId":2,"updateTime":1527759737000,"id":8},{"stationNameJpn":"长椿街","isDeleted":"0","stationNameCn":"长椿街","createTime":1527759759000,"lineId":2,"updateTime":1527759760000,"id":9},{"stationNameJpn":"宣武门","isDeleted":"0","stationNameCn":"宣武门","createTime":1527759777000,"lineId":2,"updateTime":1527759779000,"id":10}],"isDeleted":"0","createTime":1527759557000,"lineType":0,"twoAdministrativeId":2,"updateTime":1527759559000,"id":2,"lineNameCn":"2号线","lineNameJpn":"2号线"}]
         * areas : [{"administrationNameCn":"海淀区","isDeleted":0,"createTime":1527145998000,"hwdcAreaManages":[{"isDeleted":"0","createTime":1527756338000,"threeAdministrativeId":3,"areaNameCn":"公主坟","areaNameJpn":"公主坟","updateTime":1527756339000,"id":1},{"isDeleted":"0","createTime":1527756356000,"threeAdministrativeId":3,"areaNameCn":"中关村","areaNameJpn":"中关村","updateTime":1527756358000,"id":2},{"isDeleted":"0","createTime":1527756377000,"threeAdministrativeId":3,"areaNameCn":"蓟门桥","areaNameJpn":"蓟门桥","updateTime":1527756379000,"id":3},{"isDeleted":"0","createTime":1527756398000,"threeAdministrativeId":3,"areaNameCn":"上地","areaNameJpn":"上地","updateTime":1527756400000,"id":4},{"isDeleted":"0","createTime":1527756419000,"threeAdministrativeId":3,"areaNameCn":"圆明园","areaNameJpn":"圆明园","updateTime":1527756422000,"id":5}],"latitude":116.03,"citylevel":"3","id":3,"administrationNameJpn":"海淀区","parentId":2,"houseNum":0,"status":"0","longitude":39.53},{"administrationNameCn":"朝阳区","isDeleted":0,"createTime":1527759278000,"hwdcAreaManages":[],"latitude":1,"citylevel":"3","id":27,"administrationNameJpn":"朝阳区","parentId":2,"houseNum":0,"status":"0","longitude":1}]
         */
        private List<SubwaylinesEntity> subwaylines;
        private List<AreasEntity> areas;

        public void setSubwaylines(List<SubwaylinesEntity> subwaylines) {
            this.subwaylines = subwaylines;
        }

        public void setAreas(List<AreasEntity> areas) {
            this.areas = areas;
        }

        public List<SubwaylinesEntity> getSubwaylines() {
            return subwaylines;
        }

        public List<AreasEntity> getAreas() {
            return areas;
        }

        public static class SubwaylinesEntity {
            /**
             * subwayStations : [{"stationNameJpn":"苹果园","isDeleted":"0","stationNameCn":"苹果园","createTime":1527759590000,"lineId":1,"updateTime":1527759592000,"id":1},{"stationNameJpn":"古城","isDeleted":"0","stationNameCn":"古城","createTime":1527759607000,"lineId":1,"updateTime":1527759609000,"id":2},{"stationNameJpn":"八角游乐园","isDeleted":"0","stationNameCn":"八角游乐园","createTime":1527759633000,"lineId":1,"updateTime":1527759635000,"id":3},{"stationNameJpn":"八宝山","isDeleted":"0","stationNameCn":"八宝山","createTime":1527759653000,"lineId":1,"updateTime":1527759656000,"id":4},{"stationNameJpn":"玉泉路","isDeleted":"0","stationNameCn":"玉泉路","createTime":1527759671000,"lineId":1,"updateTime":1527759673000,"id":5}]
             * isDeleted : 0
             * createTime : 1527759531000
             * lineType : 0
             * twoAdministrativeId : 2
             * updateTime : 1527759533000
             * id : 1
             * lineNameCn : 1号线
             * lineNameJpn : 1号线
             */
            private List<SubwayStationsEntity> subwayStations;
            private String isDeleted;
            private long createTime;
            private int lineType;
            private int twoAdministrativeId;
            private long updateTime;
            private int id;
            private String lineNameCn;
            private String lineNameJpn;

            public void setSubwayStations(List<SubwayStationsEntity> subwayStations) {
                this.subwayStations = subwayStations;
            }

            public void setIsDeleted(String isDeleted) {
                this.isDeleted = isDeleted;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setLineType(int lineType) {
                this.lineType = lineType;
            }

            public void setTwoAdministrativeId(int twoAdministrativeId) {
                this.twoAdministrativeId = twoAdministrativeId;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setLineNameCn(String lineNameCn) {
                this.lineNameCn = lineNameCn;
            }

            public void setLineNameJpn(String lineNameJpn) {
                this.lineNameJpn = lineNameJpn;
            }

            public List<SubwayStationsEntity> getSubwayStations() {
                return subwayStations;
            }

            public String getIsDeleted() {
                return isDeleted;
            }

            public long getCreateTime() {
                return createTime;
            }

            public int getLineType() {
                return lineType;
            }

            public int getTwoAdministrativeId() {
                return twoAdministrativeId;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public int getId() {
                return id;
            }

            public String getLineNameCn() {
                return lineNameCn;
            }

            public String getLineNameJpn() {
                return lineNameJpn;
            }

            public static class SubwayStationsEntity {
                /**
                 * stationNameJpn : 苹果园
                 * isDeleted : 0
                 * stationNameCn : 苹果园
                 * createTime : 1527759590000
                 * lineId : 1
                 * updateTime : 1527759592000
                 * id : 1
                 */
                private String stationNameJpn;
                private String isDeleted;
                private String stationNameCn;
                private long createTime;
                private int lineId;
                private long updateTime;
                private int id;

                public void setStationNameJpn(String stationNameJpn) {
                    this.stationNameJpn = stationNameJpn;
                }

                public void setIsDeleted(String isDeleted) {
                    this.isDeleted = isDeleted;
                }

                public void setStationNameCn(String stationNameCn) {
                    this.stationNameCn = stationNameCn;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public void setLineId(int lineId) {
                    this.lineId = lineId;
                }

                public void setUpdateTime(long updateTime) {
                    this.updateTime = updateTime;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getStationNameJpn() {
                    return stationNameJpn;
                }

                public String getIsDeleted() {
                    return isDeleted;
                }

                public String getStationNameCn() {
                    return stationNameCn;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public int getLineId() {
                    return lineId;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public int getId() {
                    return id;
                }
            }
        }

        public static class AreasEntity {
            /**
             * administrationNameCn : 海淀区
             * isDeleted : 0
             * createTime : 1527145998000
             * hwdcAreaManages : [{"isDeleted":"0","createTime":1527756338000,"threeAdministrativeId":3,"areaNameCn":"公主坟","areaNameJpn":"公主坟","updateTime":1527756339000,"id":1},{"isDeleted":"0","createTime":1527756356000,"threeAdministrativeId":3,"areaNameCn":"中关村","areaNameJpn":"中关村","updateTime":1527756358000,"id":2},{"isDeleted":"0","createTime":1527756377000,"threeAdministrativeId":3,"areaNameCn":"蓟门桥","areaNameJpn":"蓟门桥","updateTime":1527756379000,"id":3},{"isDeleted":"0","createTime":1527756398000,"threeAdministrativeId":3,"areaNameCn":"上地","areaNameJpn":"上地","updateTime":1527756400000,"id":4},{"isDeleted":"0","createTime":1527756419000,"threeAdministrativeId":3,"areaNameCn":"圆明园","areaNameJpn":"圆明园","updateTime":1527756422000,"id":5}]
             * latitude : 116.03
             * citylevel : 3
             * id : 3
             * administrationNameJpn : 海淀区
             * parentId : 2
             * houseNum : 0
             * status : 0
             * longitude : 39.53
             */
            private String administrationNameCn;
            private int isDeleted;
            private long createTime;
            private List<HwdcAreaManagesEntity> hwdcAreaManages;
            private double latitude;
            private String citylevel;
            private int id;
            private String administrationNameJpn;
            private int parentId;
            private int houseNum;
            private String status;
            private double longitude;

            public void setAdministrationNameCn(String administrationNameCn) {
                this.administrationNameCn = administrationNameCn;
            }

            public void setIsDeleted(int isDeleted) {
                this.isDeleted = isDeleted;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public void setHwdcAreaManages(List<HwdcAreaManagesEntity> hwdcAreaManages) {
                this.hwdcAreaManages = hwdcAreaManages;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public void setCitylevel(String citylevel) {
                this.citylevel = citylevel;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setAdministrationNameJpn(String administrationNameJpn) {
                this.administrationNameJpn = administrationNameJpn;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public void setHouseNum(int houseNum) {
                this.houseNum = houseNum;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }

            public String getAdministrationNameCn() {
                return administrationNameCn;
            }

            public int getIsDeleted() {
                return isDeleted;
            }

            public long getCreateTime() {
                return createTime;
            }

            public List<HwdcAreaManagesEntity> getHwdcAreaManages() {
                return hwdcAreaManages;
            }

            public double getLatitude() {
                return latitude;
            }

            public String getCitylevel() {
                return citylevel;
            }

            public int getId() {
                return id;
            }

            public String getAdministrationNameJpn() {
                return administrationNameJpn;
            }

            public int getParentId() {
                return parentId;
            }

            public int getHouseNum() {
                return houseNum;
            }

            public String getStatus() {
                return status;
            }

            public double getLongitude() {
                return longitude;
            }

            public static class HwdcAreaManagesEntity {
                /**
                 * isDeleted : 0
                 * createTime : 1527756338000
                 * threeAdministrativeId : 3
                 * areaNameCn : 公主坟
                 * areaNameJpn : 公主坟
                 * updateTime : 1527756339000
                 * id : 1
                 */
                private String isDeleted;
                private long createTime;
                private int threeAdministrativeId;
                private String areaNameCn;
                private String areaNameJpn;
                private long updateTime;
                private int id;

                public void setIsDeleted(String isDeleted) {
                    this.isDeleted = isDeleted;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public void setThreeAdministrativeId(int threeAdministrativeId) {
                    this.threeAdministrativeId = threeAdministrativeId;
                }

                public void setAreaNameCn(String areaNameCn) {
                    this.areaNameCn = areaNameCn;
                }

                public void setAreaNameJpn(String areaNameJpn) {
                    this.areaNameJpn = areaNameJpn;
                }

                public void setUpdateTime(long updateTime) {
                    this.updateTime = updateTime;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIsDeleted() {
                    return isDeleted;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public int getThreeAdministrativeId() {
                    return threeAdministrativeId;
                }

                public String getAreaNameCn() {
                    return areaNameCn;
                }

                public String getAreaNameJpn() {
                    return areaNameJpn;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public int getId() {
                    return id;
                }
            }
        }
    }
}
