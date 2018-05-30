package com.example.administrator.japanhouse.model;

import java.util.List;

/**
 * Created by   admin on 2018/5/28.
 */

public class FreeApartmentBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"activityNameCn":"活动名称","activityNameJpn":"活动名称","priceCn":"价格","priceJpn":"价格","summarizeCn":"概述","summarizeJpn":"概述","peopleCount":10,"createTime":1526380661000,"updateTime":1526380661000,"isDeleted":0,"status":"","startTime":1526975553000,"endTime":1526975556000,"images":"","tagsCn":"标签（中文），逗号隔开","tagsJpn":"标签（中文），逗号隔开","kfPhone":"110","bmNum":2,"currentTime":1527555867993}]
     */

    private String msg;
    private String code;
    private List<DatasBean> datas;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 1
         * activityNameCn : 活动名称
         * activityNameJpn : 活动名称
         * priceCn : 价格
         * priceJpn : 价格
         * summarizeCn : 概述
         * summarizeJpn : 概述
         * peopleCount : 10
         * createTime : 1526380661000
         * updateTime : 1526380661000
         * isDeleted : 0
         * status :
         * startTime : 1526975553000
         * endTime : 1526975556000
         * images :
         * tagsCn : 标签（中文），逗号隔开
         * tagsJpn : 标签（中文），逗号隔开
         * kfPhone : 110
         * bmNum : 2
         * currentTime : 1527555867993
         */

        private int id;
        private String activityNameCn;
        private String activityNameJpn;
        private String priceCn;
        private String priceJpn;
        private String summarizeCn;
        private String summarizeJpn;
        private int peopleCount;
        private long createTime;
        private long updateTime;
        private int isDeleted;
        private String status;
        private long startTime;
        private long endTime;
        private String images;
        private String tagsCn;
        private String tagsJpn;
        private String kfPhone;
        private int bmNum;
        private long currentTime;

        private long countTime;
        private long tvTime;

        public long getTvTime() {
            return tvTime;
        }

        public void setTvTime(long tvTime) {
            this.tvTime = tvTime;
        }

        public long getCountTime() {
            return countTime;
        }

        public void setCountTime(long countTime) {
            this.countTime = countTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getActivityNameCn() {
            return activityNameCn;
        }

        public void setActivityNameCn(String activityNameCn) {
            this.activityNameCn = activityNameCn;
        }

        public String getActivityNameJpn() {
            return activityNameJpn;
        }

        public void setActivityNameJpn(String activityNameJpn) {
            this.activityNameJpn = activityNameJpn;
        }

        public String getPriceCn() {
            return priceCn;
        }

        public void setPriceCn(String priceCn) {
            this.priceCn = priceCn;
        }

        public String getPriceJpn() {
            return priceJpn;
        }

        public void setPriceJpn(String priceJpn) {
            this.priceJpn = priceJpn;
        }

        public String getSummarizeCn() {
            return summarizeCn;
        }

        public void setSummarizeCn(String summarizeCn) {
            this.summarizeCn = summarizeCn;
        }

        public String getSummarizeJpn() {
            return summarizeJpn;
        }

        public void setSummarizeJpn(String summarizeJpn) {
            this.summarizeJpn = summarizeJpn;
        }

        public int getPeopleCount() {
            return peopleCount;
        }

        public void setPeopleCount(int peopleCount) {
            this.peopleCount = peopleCount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getTagsCn() {
            return tagsCn;
        }

        public void setTagsCn(String tagsCn) {
            this.tagsCn = tagsCn;
        }

        public String getTagsJpn() {
            return tagsJpn;
        }

        public void setTagsJpn(String tagsJpn) {
            this.tagsJpn = tagsJpn;
        }

        public String getKfPhone() {
            return kfPhone;
        }

        public void setKfPhone(String kfPhone) {
            this.kfPhone = kfPhone;
        }

        public int getBmNum() {
            return bmNum;
        }

        public void setBmNum(int bmNum) {
            this.bmNum = bmNum;
        }

        public long getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(long currentTime) {
            this.currentTime = currentTime;
        }
    }
}
