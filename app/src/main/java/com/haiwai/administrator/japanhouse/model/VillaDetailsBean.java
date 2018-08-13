package com.haiwai.administrator.japanhouse.model;

import java.io.Serializable;

/**
 * Created by   admin on 2018/5/23.
 */

public class VillaDetailsBean  implements Serializable{

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"id":10,"brokerId":15,"houseType":"1R","titleCn":"钟鑫的测试别墅","titleJpn":"测试别墅","articleNameCn":"测试的别墅","articleNameJpn":"测试的别墅","homesLayerCn":"10","homesLayerJpn":"10","areaCn":"44.00","areaJpn":"44.00","ownershipCn":"私人","ownershipJpn":"私人","orientationCn":"北","orientationJpn":"北","architecturalCn":"XXX公司","architecturalJpn":"XXX公司","yearBuiltCn":"50","yearBuiltJpn":"50","districtCn":"商业街","districtJpn":"商业街","trainStationCn":"655路","trainStationJpn":"655路","specificLocationCn":"上海","specificLocationJpn":"上海","streetDistanceCn":"20","streetDistanceJpn":"20","landCn":"90","landJpn":"90","stationDistanceCn":"100","stationDistanceJpn":"100","indoorFacilityCn":"冰箱、彩电、洗衣机","indoorFacilityJpn":"冰箱、彩电、洗衣机","performanceCn":"有","performanceJpn":"有","testimonialCn":"有","testimonialJpn":"有","defectsEnsureCn":"有","defectsEnsureJpn":"有","coveredAreaCn":"66","coveredAreaJpn":"66","sellingPriceCn":"5000000.00","sellingPriceJpn":"5000000.00","buildingConstructionCn":"建筑构造","buildingConstructionJpn":"建筑构造","doichoIrinoDateCn":"2018.6.28","doichoIrinoDateJpn":"2018.6.28","parkCn":"有","parkJpn":"有","buildingWrap":"30","plotRatio":"60","currentSituationCn":"完美","currentSituationJpn":"完美","publicInformationDayCn":"2018.6.28","publicInformationDayJpn":"2018.6.28","kitchenCn":"有","kitchenJpn":"有","toiletCn":"有","toiletJpn":"有","showerRoomCn":"有","showerRoomJpn":"有","remarkCn":"测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容","remarkJpn":"测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容","villaTypeCn":"","villaTypeJpn":"","videoUrls":"","videoImgs":"","roomImgs":"http://hwdchk.oss-cn-hongkong.aliyuncs.com/1530176246066timg (3).jpg,http://hwdchk.oss-cn-hongkong.aliyuncs.com/1530176254739timg (3).jpg,http://hwdchk.oss-cn-hongkong.aliyuncs.com/1530176259287timg (1).jpg","floorSearch":true,"areaSearch":44,"sellingPriceSearch":5000000,"yearBuiltSearch":50,"status":"","createTime":1530176267000,"updateTime":1531288611000,"isDeleted":0,"tableFlag":"4","longitude":39.41,"latitude":116.31,"locationLevel1Cn":"43","locationLevel2Cn":"45","locationLevel3Cn":"46","locationLevel4Cn":"","locationLevel1Jpn":"","locationLevel2Jpn":"","locationLevel3Jpn":"","locationLevel4Jpn":"","hwdcBroker":{"id":15,"brokerName":"FCL经纪人3477","phone":"18135697075","password":"abc123","telePhone":"111","shop":"111","turnover":11,"inYears":11,"period":11,"counts":111,"pic":"http://www.rongcloud.cn/images/logo.png","nickname":"测试经纪人","sex":"0","wechatId":"11","caeateTime":1530157401000,"updateTime":1531298247000,"isDeleted":0,"status":"","tagsCn":"111","tagsJpn":"211","intimacyCn":"","intimacyJpn":"","cityId":0,"areaManage":0,"rongCloudToken":"","token":"45c476f28c4a75ac5a00490494968500","lastTime":1531298247000,"birthday":null,"brokerId":0,"avgStar":0,"areaNameCn":"","areaNameJpn":""},"isSc":1}
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

    public static class DatasBean implements Serializable{
        /**
         * id : 10
         * brokerId : 15
         * houseType : 1R
         * titleCn : 钟鑫的测试别墅
         * titleJpn : 测试别墅
         * articleNameCn : 测试的别墅
         * articleNameJpn : 测试的别墅
         * homesLayerCn : 10
         * homesLayerJpn : 10
         * areaCn : 44.00
         * areaJpn : 44.00
         * ownershipCn : 私人
         * ownershipJpn : 私人
         * orientationCn : 北
         * orientationJpn : 北
         * architecturalCn : XXX公司
         * architecturalJpn : XXX公司
         * yearBuiltCn : 50
         * yearBuiltJpn : 50
         * districtCn : 商业街
         * districtJpn : 商业街
         * trainStationCn : 655路
         * trainStationJpn : 655路
         * specificLocationCn : 上海
         * specificLocationJpn : 上海
         * streetDistanceCn : 20
         * streetDistanceJpn : 20
         * landCn : 90
         * landJpn : 90
         * stationDistanceCn : 100
         * stationDistanceJpn : 100
         * indoorFacilityCn : 冰箱、彩电、洗衣机
         * indoorFacilityJpn : 冰箱、彩电、洗衣机
         * performanceCn : 有
         * performanceJpn : 有
         * testimonialCn : 有
         * testimonialJpn : 有
         * defectsEnsureCn : 有
         * defectsEnsureJpn : 有
         * coveredAreaCn : 66
         * coveredAreaJpn : 66
         * sellingPriceCn : 5000000.00
         * sellingPriceJpn : 5000000.00
         * buildingConstructionCn : 建筑构造
         * buildingConstructionJpn : 建筑构造
         * doichoIrinoDateCn : 2018.6.28
         * doichoIrinoDateJpn : 2018.6.28
         * parkCn : 有
         * parkJpn : 有
         * buildingWrap : 30
         * plotRatio : 60
         * currentSituationCn : 完美
         * currentSituationJpn : 完美
         * publicInformationDayCn : 2018.6.28
         * publicInformationDayJpn : 2018.6.28
         * kitchenCn : 有
         * kitchenJpn : 有
         * toiletCn : 有
         * toiletJpn : 有
         * showerRoomCn : 有
         * showerRoomJpn : 有
         * remarkCn : 测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容
         * remarkJpn : 测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容测试备注内容
         * villaTypeCn :
         * villaTypeJpn :
         * videoUrls :
         * videoImgs :
         * roomImgs : http://hwdchk.oss-cn-hongkong.aliyuncs.com/1530176246066timg (3).jpg,http://hwdchk.oss-cn-hongkong.aliyuncs.com/1530176254739timg (3).jpg,http://hwdchk.oss-cn-hongkong.aliyuncs.com/1530176259287timg (1).jpg
         * floorSearch : true
         * areaSearch : 44
         * sellingPriceSearch : 5000000
         * yearBuiltSearch : 50
         * status :
         * createTime : 1530176267000
         * updateTime : 1531288611000
         * isDeleted : 0
         * tableFlag : 4
         * longitude : 39.41
         * latitude : 116.31
         * locationLevel1Cn : 43
         * locationLevel2Cn : 45
         * locationLevel3Cn : 46
         * locationLevel4Cn :
         * locationLevel1Jpn :
         * locationLevel2Jpn :
         * locationLevel3Jpn :
         * locationLevel4Jpn :
         * hwdcBroker : {"id":15,"brokerName":"FCL经纪人3477","phone":"18135697075","password":"abc123","telePhone":"111","shop":"111","turnover":11,"inYears":11,"period":11,"counts":111,"pic":"http://www.rongcloud.cn/images/logo.png","nickname":"测试经纪人","sex":"0","wechatId":"11","caeateTime":1530157401000,"updateTime":1531298247000,"isDeleted":0,"status":"","tagsCn":"111","tagsJpn":"211","intimacyCn":"","intimacyJpn":"","cityId":0,"areaManage":0,"rongCloudToken":"","token":"45c476f28c4a75ac5a00490494968500","lastTime":1531298247000,"birthday":null,"brokerId":0,"avgStar":0,"areaNameCn":"","areaNameJpn":""}
         * isSc : 1
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
        private double areaSearch;
        private int sellingPriceSearch;
        private int yearBuiltSearch;
        private String status;
        private long createTime;
        private long updateTime;
        private int isDeleted;
        private String tableFlag;
        private double longitude;
        private double latitude;
        private String locationLevel1Cn;
        private String locationLevel2Cn;
        private String locationLevel3Cn;
        private String locationLevel4Cn;
        private String locationLevel1Jpn;
        private String locationLevel2Jpn;
        private String locationLevel3Jpn;
        private String locationLevel4Jpn;
        private HwdcBrokerBean hwdcBroker;
        private int isSc;

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

        public double getAreaSearch() {
            return areaSearch;
        }

        public void setAreaSearch(double areaSearch) {
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

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
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

        public int getIsSc() {
            return isSc;
        }

        public void setIsSc(int isSc) {
            this.isSc = isSc;
        }

        public static class HwdcBrokerBean  implements Serializable{
            /**
             * id : 15
             * brokerName : FCL经纪人3477
             * phone : 18135697075
             * password : abc123
             * telePhone : 111
             * shop : 111
             * turnover : 11
             * inYears : 11
             * period : 11
             * counts : 111
             * pic : http://www.rongcloud.cn/images/logo.png
             * nickname : 测试经纪人
             * sex : 0
             * wechatId : 11
             * caeateTime : 1530157401000
             * updateTime : 1531298247000
             * isDeleted : 0
             * status :
             * tagsCn : 111
             * tagsJpn : 211
             * intimacyCn :
             * intimacyJpn :
             * cityId : 0
             * areaManage : 0
             * rongCloudToken :
             * token : 45c476f28c4a75ac5a00490494968500
             * lastTime : 1531298247000
             * birthday : null
             * brokerId : 0
             * avgStar : 0
             * areaNameCn :
             * areaNameJpn :
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
            private String wechatId;
            private long caeateTime;
            private long updateTime;
            private int isDeleted;
            private String status;
            private String tagsCn;
            private String tagsJpn;
            private String intimacyCn;
            private String intimacyJpn;
            private int cityId;
            private int areaManage;
            private String rongCloudToken;
            private String token;
            private long lastTime;
            private Object birthday;
            private int brokerId;
            private int avgStar;
            private String areaNameCn;
            private String areaNameJpn;

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

            public String getWechatId() {
                return wechatId;
            }

            public void setWechatId(String wechatId) {
                this.wechatId = wechatId;
            }

            public long getCaeateTime() {
                return caeateTime;
            }

            public void setCaeateTime(long caeateTime) {
                this.caeateTime = caeateTime;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getTagsCn() {
                return tagsCn;
            }

            public void setTagsCn(String tagsCn) {
                this.tagsCn = tagsCn;
            }

            public String getTagsJpn() {
                return tagsJpn;
            }

            public void setTagsJpn(String tagsJpn) {
                this.tagsJpn = tagsJpn;
            }

            public String getIntimacyCn() {
                return intimacyCn;
            }

            public void setIntimacyCn(String intimacyCn) {
                this.intimacyCn = intimacyCn;
            }

            public String getIntimacyJpn() {
                return intimacyJpn;
            }

            public void setIntimacyJpn(String intimacyJpn) {
                this.intimacyJpn = intimacyJpn;
            }

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public int getAreaManage() {
                return areaManage;
            }

            public void setAreaManage(int areaManage) {
                this.areaManage = areaManage;
            }

            public String getRongCloudToken() {
                return rongCloudToken;
            }

            public void setRongCloudToken(String rongCloudToken) {
                this.rongCloudToken = rongCloudToken;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public long getLastTime() {
                return lastTime;
            }

            public void setLastTime(long lastTime) {
                this.lastTime = lastTime;
            }

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public int getBrokerId() {
                return brokerId;
            }

            public void setBrokerId(int brokerId) {
                this.brokerId = brokerId;
            }

            public int getAvgStar() {
                return avgStar;
            }

            public void setAvgStar(int avgStar) {
                this.avgStar = avgStar;
            }

            public String getAreaNameCn() {
                return areaNameCn;
            }

            public void setAreaNameCn(String areaNameCn) {
                this.areaNameCn = areaNameCn;
            }

            public String getAreaNameJpn() {
                return areaNameJpn;
            }

            public void setAreaNameJpn(String areaNameJpn) {
                this.areaNameJpn = areaNameJpn;
            }
        }
    }
}
