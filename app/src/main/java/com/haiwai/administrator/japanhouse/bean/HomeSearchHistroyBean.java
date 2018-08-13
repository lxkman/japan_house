package com.haiwai.administrator.japanhouse.bean;

/**
 * Created by Administrator on 2018/6/12.
 */
public class HomeSearchHistroyBean {
    private int state;
    private int state2;
    private String content;

    public HomeSearchHistroyBean(int state, int state2, String content) {
        this.state = state;
        this.state2 = state2;
        this.content = content;
    }

    public HomeSearchHistroyBean(int state, String content) {
        this.state = state;
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState2() {
        return state2;
    }

    public void setState2(int state2) {
        this.state2 = state2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
