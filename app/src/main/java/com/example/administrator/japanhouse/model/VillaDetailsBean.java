package com.example.administrator.japanhouse.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by   admin on 2018/5/23.
 */

public class VillaDetailsBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"id":1,"brokerId":1,"houseType":"","titleCn":"别墅标题","titleJpn":"别墅标题","articleNameCn":"","articleNameJpn":"","homesLayerCn":"","homesLayerJpn":"","areaCn":"","areaJpn":"","ownershipCn":"","ownershipJpn":"","orientationCn":"","orientationJpn":"","architecturalCn":"","architecturalJpn":"","yearBuiltCn":"","yearBuiltJpn":"","districtCn":"","districtJpn":"","trainStationCn":"1","trainStationJpn":"1","specificLocationCn":"1","specificLocationJpn":"1","streetDistanceCn":"","streetDistanceJpn":"","landCn":"","landJpn":"","stationDistanceCn":"","stationDistanceJpn":"","indoorFacilityCn":"","indoorFacilityJpn":"","performanceCn":"","performanceJpn":"","testimonialCn":"","testimonialJpn":"","defectsEnsureCn":"","defectsEnsureJpn":"","coveredAreaCn":"","coveredAreaJpn":"","sellingPriceCn":"","sellingPriceJpn":"","buildingConstructionCn":"","buildingConstructionJpn":"","doichoIrinoDateCn":"","doichoIrinoDateJpn":"","parkCn":"","parkJpn":"","buildingWrap":"","plotRatio":"","currentSituationCn":"","currentSituationJpn":"","publicInformationDayCn":"","publicInformationDayJpn":"","kitchenCn":"","kitchenJpn":"","toiletCn":"","toiletJpn":"","showerRoomCn":"","showerRoomJpn":"","remarkCn":"","remarkJpn":"","villaTypeCn":"","villaTypeJpn":"","videoUrls":"","videoImgs":"","roomImgs":"","floorSearch":true,"areaSearch":0,"sellingPriceSearch":0,"yearBuiltSearch":0,"status":"","createTime":1527044102585,"updateTime":1527044102585,"isDeleted":0,"tableFlag":"","longitude":0,"latitude":0,"locationLevel1Cn":"","locationLevel2Cn":"","locationLevel3Cn":"","locationLevel4Cn":"","locationLevel1Jpn":"","locationLevel2Jpn":"","locationLevel3Jpn":"","locationLevel4Jpn":"","hwdcBroker":{"id":1,"brokerName":"姓名","phone":"1234","password":"1232132","telePhone":"123132","shop":"所属门店","turnover":100,"inYears":3,"period":1,"counts":60,"pic":"http://img5.imgtn.bdimg.com/it/u=1033062629,3975475618&fm=27&gp=0.jpg","nickname":"昵称","sex":"0","wechatId":null,"caeateTime":1525781849000,"updateTime":null,"isDeleted":0,"status":null}}
     */

    private String msg;
    private String code;
    private DatasBean datas;

    protected VillaDetailsBean(Parcel in) {
        msg = in.readString();
        code = in.readString();
    }

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

    public static class DatasBean implements Parcelable {
        /**
         "id": 1,  //别墅ID
         "brokerId": 1, //经纪人ID
         "houseType": "", //别墅类型
         "titleCn": "别墅标题",
         "titleJpn": "别墅标题",
         "articleNameCn": "", //物件名称（汉语）
         "articleNameJpn": "",
         "homesLayerCn": "", //楼层（中文）
         "homesLayerJpn": "",
         "areaCn": "", //土地面积（中文） 平方米
         "areaJpn": "",
         "ownershipCn": "", //所有权(中文)
         "ownershipJpn": "",
         "orientationCn": "", //朝向(中文)
         "orientationJpn": "",
         "architecturalCn": "", //建筑设计公司(中文)
         "architecturalJpn": "",
         "yearBuiltCn": "", //建筑年份(中文)
         "yearBuiltJpn": "",
         "districtCn": "", //地段(中文)
         "districtJpn": "",
         "trainStationCn": "1", //主要车站
         "trainStationJpn": "1",
         "specificLocationCn": "1", //具体位置（中文）
         "specificLocationJpn": "1",
         "streetDistanceCn": "", //道路临街距离(中文)
         "streetDistanceJpn": "",
         "landCn": "", //土地(中文)
         "landJpn": "",
         "stationDistanceCn": "", //车站距离（中文）
         "stationDistanceJpn": "",
         "indoorFacilityCn": "", //室内设施（中文）
         "indoorFacilityJpn": "",
         "performanceCn": "", //性能（中文）
         "performanceJpn": "",
         "testimonialCn": "", //证明书（中文）
         "testimonialJpn": "",
         "defectsEnsureCn": "", //瑕疵保证（中文）
         "defectsEnsureJpn": "",
         "coveredAreaCn": "", //建筑面积（中文）
         "coveredAreaJpn": "",
         "sellingPriceCn": "", //售价(中文)
         "sellingPriceJpn": "",
         "buildingConstructionCn": "", //建筑构造(中文)
         "buildingConstructionJpn": "",
         "doichoIrinoDateCn": "", //入居日期（中文）
         "doichoIrinoDateJpn": "",
         "parkCn": "", //停车场（中文）
         "parkJpn": "",
         "buildingWrap": "", //建筑覆盖（中文、日文共用）
         "plotRatio": "", //容积率(中文、日文共用)
         "currentSituationCn": "", //现状（中文）
         "currentSituationJpn": "",
         "publicInformationDayCn": "", //公开情报日(中文
         "publicInformationDayJpn": "",
         "kitchenCn": "", //厨房(中文)
         "kitchenJpn": "",
         "toiletCn": "", //卫生间（中文）
         "toiletJpn": "",
         "showerRoomCn": "", //浴室(中文)
         "showerRoomJpn": "",
         "remarkCn": "", //备注(中文)
         "remarkJpn": "",
         "villaTypeCn": "", //别墅类型（中文）
         "villaTypeJpn": "",
         "videoUrls": "", //视频路径
         "videoImgs": "", //视频封面图，多张，逗号隔开
         "roomImgs": "", //别墅图片，多张，逗号隔开
         "floorSearch": true,
         "areaSearch": 0,
         "sellingPriceSearch": 0,
         "yearBuiltSearch": 0,
         "status": "",
         "createTime": 1527044102585,
         "updateTime": 1527044102585,
         "isDeleted": 0,
         "tableFlag": "",
         "longitude": 0,
         "latitude": 0,
         "locationLevel1Cn": "",
         "locationLevel2Cn": "",
         "locationLevel3Cn": "",
         "locationLevel4Cn": "",
         "locationLevel1Jpn": "",
         "locationLevel2Jpn": "",
         "locationLevel3Jpn": "",
         "locationLevel4Jpn": "",
         "hwdcBroker": {
         "id": 1,
         "brokerName": "姓名",
         "phone": "1234",
         "password": "1232132",
         "telePhone": "123132",
         "shop": "所属门店",
         "turnover": 100,
         "inYears": 3,
         "period": 1,
         "counts": 60,
         "pic": "http://img5.imgtn.bdimg.com/it/u=1033062629,3975475618&fm=27&gp=0.jpg",
         "nickname": "昵称",
         "sex": "0",
         "wechatId": null,
         "caeateTime": 1525781849000,
         "updateTime": null,
         "isDeleted": 0,
         "status": null
         */

        private int id;
        private int brokerId;
        private String houseType;
        private String titleCn;
        private String titleJpn;
        private String articleNameCn;
        private String articleNameJpn;
        private String homesLayerCn;
        private String homesLayerJpn;
        private String areaCn;
        private String areaJpn;
        private String ownershipCn;
        private String ownershipJpn;
        private String orientationCn;
        private String orientationJpn;
        private String architecturalCn;
        private String architecturalJpn;
        private String yearBuiltCn;
        private String yearBuiltJpn;
        private String districtCn;
        private String districtJpn;
        private String trainStationCn;
        private String trainStationJpn;
        private String specificLocationCn;
        private String specificLocationJpn;
        private String streetDistanceCn;
        private String streetDistanceJpn;
        private String landCn;
        private String landJpn;
        private String stationDistanceCn;
        private String stationDistanceJpn;
        private String indoorFacilityCn;
        private String indoorFacilityJpn;
        private String performanceCn;
        private String performanceJpn;
        private String testimonialCn;
        private String testimonialJpn;
        private String defectsEnsureCn;
        private String defectsEnsureJpn;
        private String coveredAreaCn;
        private String coveredAreaJpn;
        private String sellingPriceCn;
        private String sellingPriceJpn;
        private String buildingConstructionCn;
        private String buildingConstructionJpn;
        private String doichoIrinoDateCn;
        private String doichoIrinoDateJpn;
        private String parkCn;
        private String parkJpn;
        private String buildingWrap;
        private String plotRatio;
        private String currentSituationCn;
        private String currentSituationJpn;
        private String publicInformationDayCn;
        private String publicInformationDayJpn;
        private String kitchenCn;
        private String kitchenJpn;
        private String toiletCn;
        private String toiletJpn;
        private String showerRoomCn;
        private String showerRoomJpn;
        private String remarkCn;
        private String remarkJpn;
        private String villaTypeCn;
        private String villaTypeJpn;
        private String videoUrls;
        private String videoImgs;
        private String roomImgs;
        private boolean floorSearch;
        private int areaSearch;
        private int sellingPriceSearch;
        private int yearBuiltSearch;
        private String status;
        private long createTime;
        private long updateTime;
        private int isDeleted;
        private String tableFlag;
        private int longitude;
        private int latitude;
        private String locationLevel1Cn;
        private String locationLevel2Cn;
        private String locationLevel3Cn;
        private String locationLevel4Cn;
        private String locationLevel1Jpn;
        private String locationLevel2Jpn;
        private String locationLevel3Jpn;
        private String locationLevel4Jpn;
        private HwdcBrokerBean hwdcBroker;

        protected DatasBean(Parcel in) {
            id = in.readInt();
            brokerId = in.readInt();
            houseType = in.readString();
            titleCn = in.readString();
            titleJpn = in.readString();
            articleNameCn = in.readString();
            articleNameJpn = in.readString();
            homesLayerCn = in.readString();
            homesLayerJpn = in.readString();
            areaCn = in.readString();
            areaJpn = in.readString();
            ownershipCn = in.readString();
            ownershipJpn = in.readString();
            orientationCn = in.readString();
            orientationJpn = in.readString();
            architecturalCn = in.readString();
            architecturalJpn = in.readString();
            yearBuiltCn = in.readString();
            yearBuiltJpn = in.readString();
            districtCn = in.readString();
            districtJpn = in.readString();
            trainStationCn = in.readString();
            trainStationJpn = in.readString();
            specificLocationCn = in.readString();
            specificLocationJpn = in.readString();
            streetDistanceCn = in.readString();
            streetDistanceJpn = in.readString();
            landCn = in.readString();
            landJpn = in.readString();
            stationDistanceCn = in.readString();
            stationDistanceJpn = in.readString();
            indoorFacilityCn = in.readString();
            indoorFacilityJpn = in.readString();
            performanceCn = in.readString();
            performanceJpn = in.readString();
            testimonialCn = in.readString();
            testimonialJpn = in.readString();
            defectsEnsureCn = in.readString();
            defectsEnsureJpn = in.readString();
            coveredAreaCn = in.readString();
            coveredAreaJpn = in.readString();
            sellingPriceCn = in.readString();
            sellingPriceJpn = in.readString();
            buildingConstructionCn = in.readString();
            buildingConstructionJpn = in.readString();
            doichoIrinoDateCn = in.readString();
            doichoIrinoDateJpn = in.readString();
            parkCn = in.readString();
            parkJpn = in.readString();
            buildingWrap = in.readString();
            plotRatio = in.readString();
            currentSituationCn = in.readString();
            currentSituationJpn = in.readString();
            publicInformationDayCn = in.readString();
            publicInformationDayJpn = in.readString();
            kitchenCn = in.readString();
            kitchenJpn = in.readString();
            toiletCn = in.readString();
            toiletJpn = in.readString();
            showerRoomCn = in.readString();
            showerRoomJpn = in.readString();
            remarkCn = in.readString();
            remarkJpn = in.readString();
            villaTypeCn = in.readString();
            villaTypeJpn = in.readString();
            videoUrls = in.readString();
            videoImgs = in.readString();
            roomImgs = in.readString();
            floorSearch = in.readByte() != 0;
            areaSearch = in.readInt();
            sellingPriceSearch = in.readInt();
            yearBuiltSearch = in.readInt();
            status = in.readString();
            createTime = in.readLong();
            updateTime = in.readLong();
            isDeleted = in.readInt();
            tableFlag = in.readString();
            longitude = in.readInt();
            latitude = in.readInt();
            locationLevel1Cn = in.readString();
            locationLevel2Cn = in.readString();
            locationLevel3Cn = in.readString();
            locationLevel4Cn = in.readString();
            locationLevel1Jpn = in.readString();
            locationLevel2Jpn = in.readString();
            locationLevel3Jpn = in.readString();
            locationLevel4Jpn = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(brokerId);
            dest.writeString(houseType);
            dest.writeString(titleCn);
            dest.writeString(titleJpn);
            dest.writeString(articleNameCn);
            dest.writeString(articleNameJpn);
            dest.writeString(homesLayerCn);
            dest.writeString(homesLayerJpn);
            dest.writeString(areaCn);
            dest.writeString(areaJpn);
            dest.writeString(ownershipCn);
            dest.writeString(ownershipJpn);
            dest.writeString(orientationCn);
            dest.writeString(orientationJpn);
            dest.writeString(architecturalCn);
            dest.writeString(architecturalJpn);
            dest.writeString(yearBuiltCn);
            dest.writeString(yearBuiltJpn);
            dest.writeString(districtCn);
            dest.writeString(districtJpn);
            dest.writeString(trainStationCn);
            dest.writeString(trainStationJpn);
            dest.writeString(specificLocationCn);
            dest.writeString(specificLocationJpn);
            dest.writeString(streetDistanceCn);
            dest.writeString(streetDistanceJpn);
            dest.writeString(landCn);
            dest.writeString(landJpn);
            dest.writeString(stationDistanceCn);
            dest.writeString(stationDistanceJpn);
            dest.writeString(indoorFacilityCn);
            dest.writeString(indoorFacilityJpn);
            dest.writeString(performanceCn);
            dest.writeString(performanceJpn);
            dest.writeString(testimonialCn);
            dest.writeString(testimonialJpn);
            dest.writeString(defectsEnsureCn);
            dest.writeString(defectsEnsureJpn);
            dest.writeString(coveredAreaCn);
            dest.writeString(coveredAreaJpn);
            dest.writeString(sellingPriceCn);
            dest.writeString(sellingPriceJpn);
            dest.writeString(buildingConstructionCn);
            dest.writeString(buildingConstructionJpn);
            dest.writeString(doichoIrinoDateCn);
            dest.writeString(doichoIrinoDateJpn);
            dest.writeString(parkCn);
            dest.writeString(parkJpn);
            dest.writeString(buildingWrap);
            dest.writeString(plotRatio);
            dest.writeString(currentSituationCn);
            dest.writeString(currentSituationJpn);
            dest.writeString(publicInformationDayCn);
            dest.writeString(publicInformationDayJpn);
            dest.writeString(kitchenCn);
            dest.writeString(kitchenJpn);
            dest.writeString(toiletCn);
            dest.writeString(toiletJpn);
            dest.writeString(showerRoomCn);
            dest.writeString(showerRoomJpn);
            dest.writeString(remarkCn);
            dest.writeString(remarkJpn);
            dest.writeString(villaTypeCn);
            dest.writeString(villaTypeJpn);
            dest.writeString(videoUrls);
            dest.writeString(videoImgs);
            dest.writeString(roomImgs);
            dest.writeByte((byte) (floorSearch ? 1 : 0));
            dest.writeInt(areaSearch);
            dest.writeInt(sellingPriceSearch);
            dest.writeInt(yearBuiltSearch);
            dest.writeString(status);
            dest.writeLong(createTime);
            dest.writeLong(updateTime);
            dest.writeInt(isDeleted);
            dest.writeString(tableFlag);
            dest.writeInt(longitude);
            dest.writeInt(latitude);
            dest.writeString(locationLevel1Cn);
            dest.writeString(locationLevel2Cn);
            dest.writeString(locationLevel3Cn);
            dest.writeString(locationLevel4Cn);
            dest.writeString(locationLevel1Jpn);
            dest.writeString(locationLevel2Jpn);
            dest.writeString(locationLevel3Jpn);
            dest.writeString(locationLevel4Jpn);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<DatasBean> CREATOR = new Creator<DatasBean>() {
            @Override
            public DatasBean createFromParcel(Parcel in) {
                return new DatasBean(in);
            }

            @Override
            public DatasBean[] newArray(int size) {
                return new DatasBean[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBrokerId() {
            return brokerId;
        }

        public void setBrokerId(int brokerId) {
            this.brokerId = brokerId;
        }

        public String getHouseType() {
            return houseType;
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public String getTitleCn() {
            return titleCn;
        }

        public void setTitleCn(String titleCn) {
            this.titleCn = titleCn;
        }

        public String getTitleJpn() {
            return titleJpn;
        }

        public void setTitleJpn(String titleJpn) {
            this.titleJpn = titleJpn;
        }

        public String getArticleNameCn() {
            return articleNameCn;
        }

        public void setArticleNameCn(String articleNameCn) {
            this.articleNameCn = articleNameCn;
        }

        public String getArticleNameJpn() {
            return articleNameJpn;
        }

        public void setArticleNameJpn(String articleNameJpn) {
            this.articleNameJpn = articleNameJpn;
        }

        public String getHomesLayerCn() {
            return homesLayerCn;
        }

        public void setHomesLayerCn(String homesLayerCn) {
            this.homesLayerCn = homesLayerCn;
        }

        public String getHomesLayerJpn() {
            return homesLayerJpn;
        }

        public void setHomesLayerJpn(String homesLayerJpn) {
            this.homesLayerJpn = homesLayerJpn;
        }

        public String getAreaCn() {
            return areaCn;
        }

        public void setAreaCn(String areaCn) {
            this.areaCn = areaCn;
        }

        public String getAreaJpn() {
            return areaJpn;
        }

        public void setAreaJpn(String areaJpn) {
            this.areaJpn = areaJpn;
        }

        public String getOwnershipCn() {
            return ownershipCn;
        }

        public void setOwnershipCn(String ownershipCn) {
            this.ownershipCn = ownershipCn;
        }

        public String getOwnershipJpn() {
            return ownershipJpn;
        }

        public void setOwnershipJpn(String ownershipJpn) {
            this.ownershipJpn = ownershipJpn;
        }

        public String getOrientationCn() {
            return orientationCn;
        }

        public void setOrientationCn(String orientationCn) {
            this.orientationCn = orientationCn;
        }

        public String getOrientationJpn() {
            return orientationJpn;
        }

        public void setOrientationJpn(String orientationJpn) {
            this.orientationJpn = orientationJpn;
        }

        public String getArchitecturalCn() {
            return architecturalCn;
        }

        public void setArchitecturalCn(String architecturalCn) {
            this.architecturalCn = architecturalCn;
        }

        public String getArchitecturalJpn() {
            return architecturalJpn;
        }

        public void setArchitecturalJpn(String architecturalJpn) {
            this.architecturalJpn = architecturalJpn;
        }

        public String getYearBuiltCn() {
            return yearBuiltCn;
        }

        public void setYearBuiltCn(String yearBuiltCn) {
            this.yearBuiltCn = yearBuiltCn;
        }

        public String getYearBuiltJpn() {
            return yearBuiltJpn;
        }

        public void setYearBuiltJpn(String yearBuiltJpn) {
            this.yearBuiltJpn = yearBuiltJpn;
        }

        public String getDistrictCn() {
            return districtCn;
        }

        public void setDistrictCn(String districtCn) {
            this.districtCn = districtCn;
        }

        public String getDistrictJpn() {
            return districtJpn;
        }

        public void setDistrictJpn(String districtJpn) {
            this.districtJpn = districtJpn;
        }

        public String getTrainStationCn() {
            return trainStationCn;
        }

        public void setTrainStationCn(String trainStationCn) {
            this.trainStationCn = trainStationCn;
        }

        public String getTrainStationJpn() {
            return trainStationJpn;
        }

        public void setTrainStationJpn(String trainStationJpn) {
            this.trainStationJpn = trainStationJpn;
        }

        public String getSpecificLocationCn() {
            return specificLocationCn;
        }

        public void setSpecificLocationCn(String specificLocationCn) {
            this.specificLocationCn = specificLocationCn;
        }

        public String getSpecificLocationJpn() {
            return specificLocationJpn;
        }

        public void setSpecificLocationJpn(String specificLocationJpn) {
            this.specificLocationJpn = specificLocationJpn;
        }

        public String getStreetDistanceCn() {
            return streetDistanceCn;
        }

        public void setStreetDistanceCn(String streetDistanceCn) {
            this.streetDistanceCn = streetDistanceCn;
        }

        public String getStreetDistanceJpn() {
            return streetDistanceJpn;
        }

        public void setStreetDistanceJpn(String streetDistanceJpn) {
            this.streetDistanceJpn = streetDistanceJpn;
        }

        public String getLandCn() {
            return landCn;
        }

        public void setLandCn(String landCn) {
            this.landCn = landCn;
        }

        public String getLandJpn() {
            return landJpn;
        }

        public void setLandJpn(String landJpn) {
            this.landJpn = landJpn;
        }

        public String getStationDistanceCn() {
            return stationDistanceCn;
        }

        public void setStationDistanceCn(String stationDistanceCn) {
            this.stationDistanceCn = stationDistanceCn;
        }

        public String getStationDistanceJpn() {
            return stationDistanceJpn;
        }

        public void setStationDistanceJpn(String stationDistanceJpn) {
            this.stationDistanceJpn = stationDistanceJpn;
        }

        public String getIndoorFacilityCn() {
            return indoorFacilityCn;
        }

        public void setIndoorFacilityCn(String indoorFacilityCn) {
            this.indoorFacilityCn = indoorFacilityCn;
        }

        public String getIndoorFacilityJpn() {
            return indoorFacilityJpn;
        }

        public void setIndoorFacilityJpn(String indoorFacilityJpn) {
            this.indoorFacilityJpn = indoorFacilityJpn;
        }

        public String getPerformanceCn() {
            return performanceCn;
        }

        public void setPerformanceCn(String performanceCn) {
            this.performanceCn = performanceCn;
        }

        public String getPerformanceJpn() {
            return performanceJpn;
        }

        public void setPerformanceJpn(String performanceJpn) {
            this.performanceJpn = performanceJpn;
        }

        public String getTestimonialCn() {
            return testimonialCn;
        }

        public void setTestimonialCn(String testimonialCn) {
            this.testimonialCn = testimonialCn;
        }

        public String getTestimonialJpn() {
            return testimonialJpn;
        }

        public void setTestimonialJpn(String testimonialJpn) {
            this.testimonialJpn = testimonialJpn;
        }

        public String getDefectsEnsureCn() {
            return defectsEnsureCn;
        }

        public void setDefectsEnsureCn(String defectsEnsureCn) {
            this.defectsEnsureCn = defectsEnsureCn;
        }

        public String getDefectsEnsureJpn() {
            return defectsEnsureJpn;
        }

        public void setDefectsEnsureJpn(String defectsEnsureJpn) {
            this.defectsEnsureJpn = defectsEnsureJpn;
        }

        public String getCoveredAreaCn() {
            return coveredAreaCn;
        }

        public void setCoveredAreaCn(String coveredAreaCn) {
            this.coveredAreaCn = coveredAreaCn;
        }

        public String getCoveredAreaJpn() {
            return coveredAreaJpn;
        }

        public void setCoveredAreaJpn(String coveredAreaJpn) {
            this.coveredAreaJpn = coveredAreaJpn;
        }

        public String getSellingPriceCn() {
            return sellingPriceCn;
        }

        public void setSellingPriceCn(String sellingPriceCn) {
            this.sellingPriceCn = sellingPriceCn;
        }

        public String getSellingPriceJpn() {
            return sellingPriceJpn;
        }

        public void setSellingPriceJpn(String sellingPriceJpn) {
            this.sellingPriceJpn = sellingPriceJpn;
        }

        public String getBuildingConstructionCn() {
            return buildingConstructionCn;
        }

        public void setBuildingConstructionCn(String buildingConstructionCn) {
            this.buildingConstructionCn = buildingConstructionCn;
        }

        public String getBuildingConstructionJpn() {
            return buildingConstructionJpn;
        }

        public void setBuildingConstructionJpn(String buildingConstructionJpn) {
            this.buildingConstructionJpn = buildingConstructionJpn;
        }

        public String getDoichoIrinoDateCn() {
            return doichoIrinoDateCn;
        }

        public void setDoichoIrinoDateCn(String doichoIrinoDateCn) {
            this.doichoIrinoDateCn = doichoIrinoDateCn;
        }

        public String getDoichoIrinoDateJpn() {
            return doichoIrinoDateJpn;
        }

        public void setDoichoIrinoDateJpn(String doichoIrinoDateJpn) {
            this.doichoIrinoDateJpn = doichoIrinoDateJpn;
        }

        public String getParkCn() {
            return parkCn;
        }

        public void setParkCn(String parkCn) {
            this.parkCn = parkCn;
        }

        public String getParkJpn() {
            return parkJpn;
        }

        public void setParkJpn(String parkJpn) {
            this.parkJpn = parkJpn;
        }

        public String getBuildingWrap() {
            return buildingWrap;
        }

        public void setBuildingWrap(String buildingWrap) {
            this.buildingWrap = buildingWrap;
        }

        public String getPlotRatio() {
            return plotRatio;
        }

        public void setPlotRatio(String plotRatio) {
            this.plotRatio = plotRatio;
        }

        public String getCurrentSituationCn() {
            return currentSituationCn;
        }

        public void setCurrentSituationCn(String currentSituationCn) {
            this.currentSituationCn = currentSituationCn;
        }

        public String getCurrentSituationJpn() {
            return currentSituationJpn;
        }

        public void setCurrentSituationJpn(String currentSituationJpn) {
            this.currentSituationJpn = currentSituationJpn;
        }

        public String getPublicInformationDayCn() {
            return publicInformationDayCn;
        }

        public void setPublicInformationDayCn(String publicInformationDayCn) {
            this.publicInformationDayCn = publicInformationDayCn;
        }

        public String getPublicInformationDayJpn() {
            return publicInformationDayJpn;
        }

        public void setPublicInformationDayJpn(String publicInformationDayJpn) {
            this.publicInformationDayJpn = publicInformationDayJpn;
        }

        public String getKitchenCn() {
            return kitchenCn;
        }

        public void setKitchenCn(String kitchenCn) {
            this.kitchenCn = kitchenCn;
        }

        public String getKitchenJpn() {
            return kitchenJpn;
        }

        public void setKitchenJpn(String kitchenJpn) {
            this.kitchenJpn = kitchenJpn;
        }

        public String getToiletCn() {
            return toiletCn;
        }

        public void setToiletCn(String toiletCn) {
            this.toiletCn = toiletCn;
        }

        public String getToiletJpn() {
            return toiletJpn;
        }

        public void setToiletJpn(String toiletJpn) {
            this.toiletJpn = toiletJpn;
        }

        public String getShowerRoomCn() {
            return showerRoomCn;
        }

        public void setShowerRoomCn(String showerRoomCn) {
            this.showerRoomCn = showerRoomCn;
        }

        public String getShowerRoomJpn() {
            return showerRoomJpn;
        }

        public void setShowerRoomJpn(String showerRoomJpn) {
            this.showerRoomJpn = showerRoomJpn;
        }

        public String getRemarkCn() {
            return remarkCn;
        }

        public void setRemarkCn(String remarkCn) {
            this.remarkCn = remarkCn;
        }

        public String getRemarkJpn() {
            return remarkJpn;
        }

        public void setRemarkJpn(String remarkJpn) {
            this.remarkJpn = remarkJpn;
        }

        public String getVillaTypeCn() {
            return villaTypeCn;
        }

        public void setVillaTypeCn(String villaTypeCn) {
            this.villaTypeCn = villaTypeCn;
        }

        public String getVillaTypeJpn() {
            return villaTypeJpn;
        }

        public void setVillaTypeJpn(String villaTypeJpn) {
            this.villaTypeJpn = villaTypeJpn;
        }

        public String getVideoUrls() {
            return videoUrls;
        }

        public void setVideoUrls(String videoUrls) {
            this.videoUrls = videoUrls;
        }

        public String getVideoImgs() {
            return videoImgs;
        }

        public void setVideoImgs(String videoImgs) {
            this.videoImgs = videoImgs;
        }

        public String getRoomImgs() {
            return roomImgs;
        }

        public void setRoomImgs(String roomImgs) {
            this.roomImgs = roomImgs;
        }

        public boolean isFloorSearch() {
            return floorSearch;
        }

        public void setFloorSearch(boolean floorSearch) {
            this.floorSearch = floorSearch;
        }

        public int getAreaSearch() {
            return areaSearch;
        }

        public void setAreaSearch(int areaSearch) {
            this.areaSearch = areaSearch;
        }

        public int getSellingPriceSearch() {
            return sellingPriceSearch;
        }

        public void setSellingPriceSearch(int sellingPriceSearch) {
            this.sellingPriceSearch = sellingPriceSearch;
        }

        public int getYearBuiltSearch() {
            return yearBuiltSearch;
        }

        public void setYearBuiltSearch(int yearBuiltSearch) {
            this.yearBuiltSearch = yearBuiltSearch;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getTableFlag() {
            return tableFlag;
        }

        public void setTableFlag(String tableFlag) {
            this.tableFlag = tableFlag;
        }

        public int getLongitude() {
            return longitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }

        public int getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }

        public String getLocationLevel1Cn() {
            return locationLevel1Cn;
        }

        public void setLocationLevel1Cn(String locationLevel1Cn) {
            this.locationLevel1Cn = locationLevel1Cn;
        }

        public String getLocationLevel2Cn() {
            return locationLevel2Cn;
        }

        public void setLocationLevel2Cn(String locationLevel2Cn) {
            this.locationLevel2Cn = locationLevel2Cn;
        }

        public String getLocationLevel3Cn() {
            return locationLevel3Cn;
        }

        public void setLocationLevel3Cn(String locationLevel3Cn) {
            this.locationLevel3Cn = locationLevel3Cn;
        }

        public String getLocationLevel4Cn() {
            return locationLevel4Cn;
        }

        public void setLocationLevel4Cn(String locationLevel4Cn) {
            this.locationLevel4Cn = locationLevel4Cn;
        }

        public String getLocationLevel1Jpn() {
            return locationLevel1Jpn;
        }

        public void setLocationLevel1Jpn(String locationLevel1Jpn) {
            this.locationLevel1Jpn = locationLevel1Jpn;
        }

        public String getLocationLevel2Jpn() {
            return locationLevel2Jpn;
        }

        public void setLocationLevel2Jpn(String locationLevel2Jpn) {
            this.locationLevel2Jpn = locationLevel2Jpn;
        }

        public String getLocationLevel3Jpn() {
            return locationLevel3Jpn;
        }

        public void setLocationLevel3Jpn(String locationLevel3Jpn) {
            this.locationLevel3Jpn = locationLevel3Jpn;
        }

        public String getLocationLevel4Jpn() {
            return locationLevel4Jpn;
        }

        public void setLocationLevel4Jpn(String locationLevel4Jpn) {
            this.locationLevel4Jpn = locationLevel4Jpn;
        }

        public HwdcBrokerBean getHwdcBroker() {
            return hwdcBroker;
        }

        public void setHwdcBroker(HwdcBrokerBean hwdcBroker) {
            this.hwdcBroker = hwdcBroker;
        }

        public static class HwdcBrokerBean {
            /**
             * id : 1
             * brokerName : 姓名
             * phone : 1234
             * password : 1232132
             * telePhone : 123132
             * shop : 所属门店
             * turnover : 100
             * inYears : 3
             * period : 1
             * counts : 60
             * pic : http://img5.imgtn.bdimg.com/it/u=1033062629,3975475618&fm=27&gp=0.jpg
             * nickname : 昵称
             * sex : 0
             * wechatId : null
             * caeateTime : 1525781849000
             * updateTime : null
             * isDeleted : 0
             * status : null
             */

            private int id;
            private String brokerName;
            private String phone;
            private String password;
            private String telePhone;
            private String shop;
            private int turnover;
            private int inYears;
            private int period;
            private int counts;
            private String pic;
            private String nickname;
            private String sex;
            private Object wechatId;
            private long caeateTime;
            private Object updateTime;
            private int isDeleted;
            private Object status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBrokerName() {
                return brokerName;
            }

            public void setBrokerName(String brokerName) {
                this.brokerName = brokerName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getTelePhone() {
                return telePhone;
            }

            public void setTelePhone(String telePhone) {
                this.telePhone = telePhone;
            }

            public String getShop() {
                return shop;
            }

            public void setShop(String shop) {
                this.shop = shop;
            }

            public int getTurnover() {
                return turnover;
            }

            public void setTurnover(int turnover) {
                this.turnover = turnover;
            }

            public int getInYears() {
                return inYears;
            }

            public void setInYears(int inYears) {
                this.inYears = inYears;
            }

            public int getPeriod() {
                return period;
            }

            public void setPeriod(int period) {
                this.period = period;
            }

            public int getCounts() {
                return counts;
            }

            public void setCounts(int counts) {
                this.counts = counts;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public Object getWechatId() {
                return wechatId;
            }

            public void setWechatId(Object wechatId) {
                this.wechatId = wechatId;
            }

            public long getCaeateTime() {
                return caeateTime;
            }

            public void setCaeateTime(long caeateTime) {
                this.caeateTime = caeateTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public int getIsDeleted() {
                return isDeleted;
            }

            public void setIsDeleted(int isDeleted) {
                this.isDeleted = isDeleted;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }
        }
    }
}
