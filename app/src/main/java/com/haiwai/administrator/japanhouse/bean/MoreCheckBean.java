package com.haiwai.administrator.japanhouse.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class MoreCheckBean {
    private String name;
    private int id;
    private boolean isChecked;
    private List<OneCheckBean> checkBeanList = new ArrayList<>();

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OneCheckBean> getCheckBeanList() {
        return checkBeanList;
    }

    public void setCheckBeanList(List<OneCheckBean> checkBeanList) {
        this.checkBeanList = checkBeanList;
    }

    public MoreCheckBean(boolean isChecked, String name) {
        this.isChecked = isChecked;
        this.name = name;
    }

    public MoreCheckBean() {
    }
}
