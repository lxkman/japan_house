package com.haiwai.administrator.japanhouse.model;

import java.util.List;

/**
 * 作者：  admin on 2018/6/5 15:15
 * 邮箱：github.com
 */
public class SellHouseBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"userName":"张三","userContact":"13463109341","housingLocation":"房屋位置","stationDistance":1000,"floor":3,"area":100,"pattern":"格局","bathroomTogether":0,"toward":"南","surroundingFacilities":"周边环境","videoUrl":"","videoImageUrl":"","imgs":"","createTime":1528166345000,"updateTime":1528166347000,"isDeleted":"0","entrustType":"0","auditState":"1","userId":17}]
     */

    private String msg;
    private String code;
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 1
         * userName : 张三
         * userContact : 13463109341
         * housingLocation : 房屋位置
         * stationDistance : 1000
         * floor : 3
         * area : 100
         * pattern : 格局
         * bathroomTogether : 0
         * toward : 南
         * surroundingFacilities : 周边环境
         * videoUrl :
         * videoImageUrl :
         * imgs :
         * createTime : 1528166345000
         * updateTime : 1528166347000
         * isDeleted : 0
         * entrustType : 0
         * auditState : 1
         * userId : 17
         */

        private int id;
        private String userName;
        private String userContact;
        private String housingLocation;
        private int stationDistance;
        private int floor;
        private int area;
        private String pattern;
        private int bathroomTogether;
        private String toward;
        private String surroundingFacilities;
        private String videoUrl;
        private String videoImageUrl;
        private String imgs;
        private long createTime;
        private long updateTime;
        private String isDeleted;
        private String entrustType;
        private String auditState;
        private int userId;
        private String operationText;

        public String getOperationText() {
            return operationText;
        }

        public void setOperationText(String operationText) {
            this.operationText = operationText;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserContact() {
            return userContact;
        }

        public void setUserContact(String userContact) {
            this.userContact = userContact;
        }

        public String getHousingLocation() {
            return housingLocation;
        }

        public void setHousingLocation(String housingLocation) {
            this.housingLocation = housingLocation;
        }

        public int getStationDistance() {
            return stationDistance;
        }

        public void setStationDistance(int stationDistance) {
            this.stationDistance = stationDistance;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public int getBathroomTogether() {
            return bathroomTogether;
        }

        public void setBathroomTogether(int bathroomTogether) {
            this.bathroomTogether = bathroomTogether;
        }

        public String getToward() {
            return toward;
        }

        public void setToward(String toward) {
            this.toward = toward;
        }

        public String getSurroundingFacilities() {
            return surroundingFacilities;
        }

        public void setSurroundingFacilities(String surroundingFacilities) {
            this.surroundingFacilities = surroundingFacilities;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getVideoImageUrl() {
            return videoImageUrl;
        }

        public void setVideoImageUrl(String videoImageUrl) {
            this.videoImageUrl = videoImageUrl;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getEntrustType() {
            return entrustType;
        }

        public void setEntrustType(String entrustType) {
            this.entrustType = entrustType;
        }

        public String getAuditState() {
            return auditState;
        }

        public void setAuditState(String auditState) {
            this.auditState = auditState;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
