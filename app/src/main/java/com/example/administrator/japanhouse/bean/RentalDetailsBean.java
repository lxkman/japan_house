package com.example.administrator.japanhouse.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by   admin on 2018/4/17.
 */

public class RentalDetailsBean implements Parcelable {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomState() {
        return roomState;
    }

    public void setRoomState(String roomState) {
        this.roomState = roomState;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getLessorName() {
        return lessorName;
    }

    public void setLessorName(String lessorName) {
        this.lessorName = lessorName;
    }

    public String getLessorPhone() {
        return lessorPhone;
    }

    public void setLessorPhone(String lessorPhone) {
        this.lessorPhone = lessorPhone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getToward() {
        return toward;
    }

    public void setToward(String toward) {
        this.toward = toward;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    public String getVideoList() {
        return videoList;
    }

    public void setVideoList(String videoList) {
        this.videoList = videoList;
    }

    public String getVideoPic() {
        return videoPic;
    }

    public void setVideoPic(String videoPic) {
        this.videoPic = videoPic;
    }

    /**
     *   title; 标题
     *   roomState; 审核状态
     *   refuseReason;  拒绝理由
     *   lessorName;    出租人姓名
     *   lessorPhone;   出租人电话
     *   location;  房屋位置
     *   distance;  车站距离
     *   floor;     楼层
     *   area;      面积
     *   pattern;   格局
     *   bathroom;  洗卫一体
     *   toward;    朝向
     *   equipment; 周边设备
     *   picList;   房源图片   //集合
     *   videoList; 房源视频
     */


    private String title;
    private String roomState;
    private String refuseReason;
    private String lessorName;
    private String lessorPhone;
    private String location;
    private String distance;
    private String floor;
    private String area;
    private String pattern;
    private String bathroom;
    private String toward;
    private String equipment;
    private List<String> picList;
    private String videoList;
    private String videoPic;

    public RentalDetailsBean(String title, String roomState, String refuseReason, String lessorName, String lessorPhone, String location, String distance, String floor, String area, String pattern, String bathroom, String toward, String equipment, List<String> picList, String videoList, String videoPic) {
        this.title = title;
        this.roomState = roomState;
        this.refuseReason = refuseReason;
        this.lessorName = lessorName;
        this.lessorPhone = lessorPhone;
        this.location = location;
        this.distance = distance;
        this.floor = floor;
        this.area = area;
        this.pattern = pattern;
        this.bathroom = bathroom;
        this.toward = toward;
        this.equipment = equipment;
        this.picList = picList;
        this.videoList = videoList;
        this.videoPic = videoPic;
    }

    protected RentalDetailsBean(Parcel in) {
        title = in.readString();
        roomState = in.readString();
        refuseReason = in.readString();
        lessorName = in.readString();
        lessorPhone = in.readString();
        location = in.readString();
        distance = in.readString();
        floor = in.readString();
        area = in.readString();
        pattern = in.readString();
        bathroom = in.readString();
        toward = in.readString();
        equipment = in.readString();
        picList = in.createStringArrayList();
        videoList = in.readString();
        videoPic = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(roomState);
        dest.writeString(refuseReason);
        dest.writeString(lessorName);
        dest.writeString(lessorPhone);
        dest.writeString(location);
        dest.writeString(distance);
        dest.writeString(floor);
        dest.writeString(area);
        dest.writeString(pattern);
        dest.writeString(bathroom);
        dest.writeString(toward);
        dest.writeString(equipment);
        dest.writeStringList(picList);
        dest.writeString(videoList);
        dest.writeString(videoPic);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RentalDetailsBean> CREATOR = new Creator<RentalDetailsBean>() {
        @Override
        public RentalDetailsBean createFromParcel(Parcel in) {
            return new RentalDetailsBean(in);
        }

        @Override
        public RentalDetailsBean[] newArray(int size) {
            return new RentalDetailsBean[size];
        }
    };
}
