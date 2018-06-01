package com.example.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class QuYuBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"subwaylines":[{"subwayStations":[{"stationNameJpn":"苹果园","isDeleted":"0","createTime":1527759590000,"stationNameCn":"苹果园","lineId":1,"updateTime":1527759592000,"id":1},{"stationNameJpn":"古城","isDeleted":"0","createTime":1527759607000,"stationNameCn":"古城","lineId":1,"updateTime":1527759609000,"id":2},{"stationNameJpn":"八角游乐园","isDeleted":"0","createTime":1527759633000,"stationNameCn":"八角游乐园","lineId":1,"updateTime":1527759635000,"id":3},{"stationNameJpn":"八宝山","isDeleted":"0","createTime":1527759653000,"stationNameCn":"八宝山","lineId":1,"updateTime":1527759656000,"id":4},{"stationNameJpn":"玉泉路","isDeleted":"0","createTime":1527759671000,"stationNameCn":"玉泉路","lineId":1,"updateTime":1527759673000,"id":5}],"isDeleted":"0","createTime":1527759531000,"lineType":0,"twoAdministrativeId":2,"updateTime":1527759533000,"id":1,"lineNameCn":"1号线","lineNameJpn":"1号线"},{"subwayStations":[{"stationNameJpn":"车公庄","isDeleted":"0","createTime":1527759697000,"stationNameCn":"车公庄","lineId":2,"updateTime":1527759699000,"id":6},{"stationNameJpn":"阜成门","isDeleted":"0","createTime":1527759717000,"stationNameCn":"阜成门","lineId":2,"updateTime":1527759719000,"id":7},{"stationNameJpn":"复兴门","isDeleted":"0","createTime":1527759735000,"stationNameCn":"复兴门","lineId":2,"updateTime":1527759737000,"id":8},{"stationNameJpn":"长椿街","isDeleted":"0","createTime":1527759759000,"stationNameCn":"长椿街","lineId":2,"updateTime":1527759760000,"id":9},{"stationNameJpn":"宣武门","isDeleted":"0","createTime":1527759777000,"stationNameCn":"宣武门","lineId":2,"updateTime":1527759779000,"id":10}],"isDeleted":"0","createTime":1527759557000,"lineType":0,"twoAdministrativeId":2,"updateTime":1527759559000,"id":2,"lineNameCn":"2号线","lineNameJpn":"2号线"}],"areas":[{"administrationNameCn":"海淀区","isDeleted":0,"createTime":1527145998000,"hwdcAreaManages":[{"isDeleted":"0","createTime":1527756338000,"threeAdministrativeId":3,"areaNameCn":"公主坟","areaNameJpn":"公主坟","updateTime":1527756339000,"id":1},{"isDeleted":"0","createTime":1527756356000,"threeAdministrativeId":3,"areaNameCn":"中关村","areaNameJpn":"中关村","updateTime":1527756358000,"id":2},{"isDeleted":"0","createTime":1527756377000,"threeAdministrativeId":3,"areaNameCn":"蓟门桥","areaNameJpn":"蓟门桥","updateTime":1527756379000,"id":3},{"isDeleted":"0","createTime":1527756398000,"threeAdministrativeId":3,"areaNameCn":"上地","areaNameJpn":"上地","updateTime":1527756400000,"id":4},{"isDeleted":"0","createTime":1527756419000,"threeAdministrativeId":3,"areaNameCn":"圆明园","areaNameJpn":"圆明园","updateTime":1527756422000,"id":5}],"latitude":1,"citylevel":"3","id":3,"administrationNameJpn":"海淀区","houseNum":0,"parentId":2,"longitude":1,"status":"0"},{"administrationNameCn":"朝阳区","isDeleted":0,"createTime":1527759278000,"hwdcAreaManages":[],"latitude":1,"citylevel":"3","id":27,"administrationNameJpn":"朝阳区","houseNum":0,"parentId":2,"longitude":1,"status":"0"}]}
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
         * subwaylines : [{"subwayStations":[{"stationNameJpn":"苹果园","isDeleted":"0","createTime":1527759590000,"stationNameCn":"苹果园","lineId":1,"updateTime":1527759592000,"id":1},{"stationNameJpn":"古城","isDeleted":"0","createTime":1527759607000,"stationNameCn":"古城","lineId":1,"updateTime":1527759609000,"id":2},{"stationNameJpn":"八角游乐园","isDeleted":"0","createTime":1527759633000,"stationNameCn":"八角游乐园","lineId":1,"updateTime":1527759635000,"id":3},{"stationNameJpn":"八宝山","isDeleted":"0","createTime":1527759653000,"stationNameCn":"八宝山","lineId":1,"updateTime":1527759656000,"id":4},{"stationNameJpn":"玉泉路","isDeleted":"0","createTime":1527759671000,"stationNameCn":"玉泉路","lineId":1,"updateTime":1527759673000,"id":5}],"isDeleted":"0","createTime":1527759531000,"lineType":0,"twoAdministrativeId":2,"updateTime":1527759533000,"id":1,"lineNameCn":"1号线","lineNameJpn":"1号线"},{"subwayStations":[{"stationNameJpn":"车公庄","isDeleted":"0","createTime":1527759697000,"stationNameCn":"车公庄","lineId":2,"updateTime":1527759699000,"id":6},{"stationNameJpn":"阜成门","isDeleted":"0","createTime":1527759717000,"stationNameCn":"阜成门","lineId":2,"updateTime":1527759719000,"id":7},{"stationNameJpn":"复兴门","isDeleted":"0","createTime":1527759735000,"stationNameCn":"复兴门","lineId":2,"updateTime":1527759737000,"id":8},{"stationNameJpn":"长椿街","isDeleted":"0","createTime":1527759759000,"stationNameCn":"长椿街","lineId":2,"updateTime":1527759760000,"id":9},{"stationNameJpn":"宣武门","isDeleted":"0","createTime":1527759777000,"stationNameCn":"宣武门","lineId":2,"updateTime":1527759779000,"id":10}],"isDeleted":"0","createTime":1527759557000,"lineType":0,"twoAdministrativeId":2,"updateTime":1527759559000,"id":2,"lineNameCn":"2号线","lineNameJpn":"2号线"}]
         * areas : [{"administrationNameCn":"海淀区","isDeleted":0,"createTime":1527145998000,"hwdcAreaManages":[{"isDeleted":"0","createTime":1527756338000,"threeAdministrativeId":3,"areaNameCn":"公主坟","areaNameJpn":"公主坟","updateTime":1527756339000,"id":1},{"isDeleted":"0","createTime":1527756356000,"threeAdministrativeId":3,"areaNameCn":"中关村","areaNameJpn":"中关村","updateTime":1527756358000,"id":2},{"isDeleted":"0","createTime":1527756377000,"threeAdministrativeId":3,"areaNameCn":"蓟门桥","areaNameJpn":"蓟门桥","updateTime":1527756379000,"id":3},{"isDeleted":"0","createTime":1527756398000,"threeAdministrativeId":3,"areaNameCn":"上地","areaNameJpn":"上地","updateTime":1527756400000,"id":4},{"isDeleted":"0","createTime":1527756419000,"threeAdministrativeId":3,"areaNameCn":"圆明园","areaNameJpn":"圆明园","updateTime":1527756422000,"id":5}],"latitude":1,"citylevel":"3","id":3,"administrationNameJpn":"海淀区","houseNum":0,"parentId":2,"longitude":1,"status":"0"},{"administrationNameCn":"朝阳区","isDeleted":0,"createTime":1527759278000,"hwdcAreaManages":[],"latitude":1,"citylevel":"3","id":27,"administrationNameJpn":"朝阳区","houseNum":0,"parentId":2,"longitude":1,"status":"0"}]
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
             * subwayStations : [{"stationNameJpn":"苹果园","isDeleted":"0","createTime":1527759590000,"stationNameCn":"苹果园","lineId":1,"updateTime":1527759592000,"id":1},{"stationNameJpn":"古城","isDeleted":"0","createTime":1527759607000,"stationNameCn":"古城","lineId":1,"updateTime":1527759609000,"id":2},{"stationNameJpn":"八角游乐园","isDeleted":"0","createTime":1527759633000,"stationNameCn":"八角游乐园","lineId":1,"updateTime":1527759635000,"id":3},{"stationNameJpn":"八宝山","isDeleted":"0","createTime":1527759653000,"stationNameCn":"八宝山","lineId":1,"updateTime":1527759656000,"id":4},{"stationNameJpn":"玉泉路","isDeleted":"0","createTime":1527759671000,"stationNameCn":"玉泉路","lineId":1,"updateTime":1527759673000,"id":5}]
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
                 * createTime : 1527759590000
                 * stationNameCn : 苹果园
                 * lineId : 1
                 * updateTime : 1527759592000
                 * id : 1
                 */
                private String stationNameJpn;
                private String isDeleted;
                private long createTime;
                private String stationNameCn;
                private int lineId;
                private long updateTime;
                private int id;

                public void setStationNameJpn(String stationNameJpn) {
                    this.stationNameJpn = stationNameJpn;
                }

                public void setIsDeleted(String isDeleted) {
                    this.isDeleted = isDeleted;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public void setStationNameCn(String stationNameCn) {
                    this.stationNameCn = stationNameCn;
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

                public long getCreateTime() {
                    return createTime;
                }

                public String getStationNameCn() {
                    return stationNameCn;
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
             * latitude : 1
             * citylevel : 3
             * id : 3
             * administrationNameJpn : 海淀区
             * houseNum : 0
             * parentId : 2
             * longitude : 1
             * status : 0
             */
            private String administrationNameCn;
            private int isDeleted;
            private long createTime;
            private List<HwdcAreaManagesEntity> hwdcAreaManages;
            private int latitude;
            private String citylevel;
            private int id;
            private String administrationNameJpn;
            private int houseNum;
            private int parentId;
            private int longitude;
            private String status;

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

            public void setLatitude(int latitude) {
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

            public void setHouseNum(int houseNum) {
                this.houseNum = houseNum;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public void setLongitude(int longitude) {
                this.longitude = longitude;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public int getLatitude() {
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

            public int getHouseNum() {
                return houseNum;
            }

            public int getParentId() {
                return parentId;
            }

            public int getLongitude() {
                return longitude;
            }

            public String getStatus() {
                return status;
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
