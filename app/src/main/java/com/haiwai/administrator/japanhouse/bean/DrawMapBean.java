package com.haiwai.administrator.japanhouse.bean;


import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */

public class DrawMapBean {
    private String msg;
    private List<Point> pointList = new ArrayList<>();

    public DrawMapBean(String msg, List<Point> pointList) {
        this.msg = msg;
        this.pointList = pointList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }
}
