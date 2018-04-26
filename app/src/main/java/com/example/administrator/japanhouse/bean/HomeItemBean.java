package com.example.administrator.japanhouse.bean;

import java.io.Serializable;

/**
 * Created by power on 2018/4/10.
 */

public class HomeItemBean implements Serializable {
    private String title;
    private int img;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public HomeItemBean(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public HomeItemBean(boolean isChecked, int img, String title) {
        this.isChecked = isChecked;
        this.img = img;
        this.title = title;
    }
}
