package com.haiwai.administrator.japanhouse.bean;

/**
 * Created by Mr赵 on 2018/4/17.
 */

public class FragEventBug {
   private String shoufu;
   private String daikuan;
   private String lixi;

    public FragEventBug(String shoufu, String daikuan, String lixi) {
        this.shoufu = shoufu;
        this.daikuan = daikuan;
        this.lixi = lixi;
    }

    public String getShoufu() {
        return shoufu;
    }

    public void setShoufu(String shoufu) {
        this.shoufu = shoufu;
    }

    public String getDaikuan() {
        return daikuan;
    }

    public void setDaikuan(String daikuan) {
        this.daikuan = daikuan;
    }

    public String getLixi() {
        return lixi;
    }

    public void setLixi(String lixi) {
        this.lixi = lixi;
    }
}
