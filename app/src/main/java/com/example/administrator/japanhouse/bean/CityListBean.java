package com.example.administrator.japanhouse.bean;

import com.mcxtzhang.indexlib.suspension.ISuspensionInterface;

import java.util.List;

/**
 * Created by Administrator on 2018/6/7.
 */
public class CityListBean {


    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"cityList":[{"administrationNameCn":"北京市","isDeleted":0,"createTime":1527145996000,"latitude":1,"citylevel":"2","id":2,"administrationNameJpn":"北京市","parentId":1,"status":"0","longitude":1}],"key":"B"},{"cityList":[{"administrationNameCn":"重庆市","isDeleted":0,"createTime":1527503385000,"latitude":1,"citylevel":"2","id":26,"administrationNameJpn":"重庆市","parentId":25,"status":"0","longitude":1}],"key":"C"},{"cityList":[{"administrationNameCn":"广州市","isDeleted":0,"createTime":1527503257000,"latitude":1,"citylevel":"2","id":20,"administrationNameJpn":"广州市","parentId":19,"status":"0","longitude":1}],"key":"G"},{"cityList":[{"administrationNameCn":"杭州市","isDeleted":0,"createTime":1527503347000,"latitude":1,"citylevel":"2","id":24,"administrationNameJpn":"杭州市","parentId":23,"status":"0","longitude":1}],"key":"H"},{"cityList":[{"administrationNameCn":"上海市","isDeleted":0,"createTime":1527503210000,"latitude":1,"citylevel":"2","id":18,"administrationNameJpn":"上海市","parentId":17,"status":"0","longitude":1},{"administrationNameCn":"深圳市","isDeleted":0,"createTime":1527503297000,"latitude":1,"citylevel":"2","id":22,"administrationNameJpn":"深圳市","parentId":21,"status":"0","longitude":1}],"key":"S"}]
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

    public static class DatasEntity implements ISuspensionInterface{
        /**
         * cityList : [{"administrationNameCn":"北京市","isDeleted":0,"createTime":1527145996000,"latitude":1,"citylevel":"2","id":2,"administrationNameJpn":"北京市","parentId":1,"status":"0","longitude":1}]
         * key : B
         */
        private List<CityListEntity> cityList;
        private String key;

        public void setCityList(List<CityListEntity> cityList) {
            this.cityList = cityList;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<CityListEntity> getCityList() {
            return cityList;
        }

        public String getKey() {
            return key;
        }

        @Override
        public boolean isShowSuspension() {
            return true;
        }

        @Override
        public String getSuspensionTag() {
            return key;
        }

        public static class CityListEntity {
            /**
             * administrationNameCn : 北京市
             * isDeleted : 0
             * createTime : 1527145996000
             * latitude : 1
             * citylevel : 2
             * id : 2
             * administrationNameJpn : 北京市
             * parentId : 1
             * status : 0
             * longitude : 1
             */
            private String administrationNameCn;
            private int isDeleted;
            private long createTime;
            private int latitude;
            private String citylevel;
            private int id;
            private String administrationNameJpn;
            private int parentId;
            private String status;
            private int longitude;

            public void setAdministrationNameCn(String administrationNameCn) {
                this.administrationNameCn = administrationNameCn;
            }

            public void setIsDeleted(int isDeleted) {
                this.isDeleted = isDeleted;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
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

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setLongitude(int longitude) {
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

            public int getParentId() {
                return parentId;
            }

            public String getStatus() {
                return status;
            }

            public int getLongitude() {
                return longitude;
            }
        }
    }
}
