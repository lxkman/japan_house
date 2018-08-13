package com.haiwai.administrator.japanhouse.bean;

/**
 * Created by Administrator on 2018/6/11.
 */

public class SearchPositonBean {
    private int position;
    private int item_position;
    private String name;
    private int id;
    private double jingdu;
    private double weidu;

    public double getJingdu() {
        return jingdu;
    }

    public void setJingdu(double jingdu) {
        this.jingdu = jingdu;
    }

    public double getWeidu() {
        return weidu;
    }

    public void setWeidu(double weidu) {
        this.weidu = weidu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getItem_position() {
        return item_position;
    }

    public void setItem_position(int item_position) {
        this.item_position = item_position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
