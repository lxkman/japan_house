package com.example.administrator.japanhouse.bean;

import java.io.Serializable;

/**
 * Created by power on 2018/4/20.
 */

public class MarkerBean implements Serializable{
    private double wei;
    private double jing;

    public double getWei() {
        return wei;
    }

    public void setWei(double wei) {
        this.wei = wei;
    }

    public double getJing() {
        return jing;
    }

    public void setJing(double jing) {
        this.jing = jing;
    }

    public MarkerBean(double jing, double wei) {
        this.wei = wei;
        this.jing = jing;
    }
}
