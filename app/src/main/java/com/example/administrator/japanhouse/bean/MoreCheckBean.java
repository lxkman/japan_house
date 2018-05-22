package com.example.administrator.japanhouse.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class MoreCheckBean {
    private String name;
    private List<OneCheckBean> checkBeanList=new ArrayList<>();

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
}
