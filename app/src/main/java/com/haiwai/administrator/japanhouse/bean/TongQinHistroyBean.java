package com.haiwai.administrator.japanhouse.bean;

/**
 * Created by Administrator on 2018/5/25.
 */

public class TongQinHistroyBean {
    private String address;
    private double latitude;
    private double longitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public TongQinHistroyBean(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
