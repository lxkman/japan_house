package com.example.administrator.japanhouse.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 作者：  admin on 2018/6/6 09:19
 * 邮箱：github.com
 */
public class SingUpBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":1,"activityNameCn":"活动名称","activityNameJpn":"活动名称","priceCn":"价格","priceJpn":"价格","summarizeCn":"概述","summarizeJpn":"概述","peopleCount":10,"createTime":1526380661000,"updateTime":1526380661000,"isDeleted":0,"status":"","startTime":1526975553000,"endTime":1527753156000,"images":"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1527823590&di=8869e90fa7328ac62c094d27b4bfbe02&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg,https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527833774869&di=554e1ab6c341d02bd30f898955c119f5&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F130107%2F1-13010F92F9.jpg","tagsJpn":"标签（中文），逗号隔开","kfPhone":"110","bmNum":1,"currentTime":1528186368886}]
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

    public static class DatasBean implements Parcelable{
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
         * endTime : 1527753156000
         * images : https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1527823590&di=8869e90fa7328ac62c094d27b4bfbe02&src=http://www.08lr.cn/uploads/allimg/170513/1-1F513100951.jpg,https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527833774869&di=554e1ab6c341d02bd30f898955c119f5&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F130107%2F1-13010F92F9.jpg
         * tagsJpn : 标签（中文），逗号隔开
         * kfPhone : 110
         * bmNum : 1
         * currentTime : 1528186368886
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
        private String tagsJpn;
        private String kfPhone;
        private int bmNum;
        private long currentTime;

        protected DatasBean(Parcel in) {
            id = in.readInt();
            activityNameCn = in.readString();
            activityNameJpn = in.readString();
            priceCn = in.readString();
            priceJpn = in.readString();
            summarizeCn = in.readString();
            summarizeJpn = in.readString();
            peopleCount = in.readInt();
            createTime = in.readLong();
            updateTime = in.readLong();
            isDeleted = in.readInt();
            status = in.readString();
            startTime = in.readLong();
            endTime = in.readLong();
            images = in.readString();
            tagsJpn = in.readString();
            kfPhone = in.readString();
            bmNum = in.readInt();
            currentTime = in.readLong();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(activityNameCn);
            dest.writeString(activityNameJpn);
            dest.writeString(priceCn);
            dest.writeString(priceJpn);
            dest.writeString(summarizeCn);
            dest.writeString(summarizeJpn);
            dest.writeInt(peopleCount);
            dest.writeLong(createTime);
            dest.writeLong(updateTime);
            dest.writeInt(isDeleted);
            dest.writeString(status);
            dest.writeLong(startTime);
            dest.writeLong(endTime);
            dest.writeString(images);
            dest.writeString(tagsJpn);
            dest.writeString(kfPhone);
            dest.writeInt(bmNum);
            dest.writeLong(currentTime);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<DatasBean> CREATOR = new Creator<DatasBean>() {
            @Override
            public DatasBean createFromParcel(Parcel in) {
                return new DatasBean(in);
            }

            @Override
            public DatasBean[] newArray(int size) {
                return new DatasBean[size];
            }
        };

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
