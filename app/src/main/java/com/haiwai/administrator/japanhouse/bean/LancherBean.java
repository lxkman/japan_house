package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class LancherBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"id":59,"imageName":"","imageLink":"","imageDesc":"","imageStatus":0,"imageType":1,"isDeleted":0,"createTime":1523444722000,"updateTime":1523496539000,"activeTime":1526378446000,"sort":0,"isActive":0,"imageUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526970228144&di=46b1f199fba90a27b360cd9809fb4ef0&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F120830%2F1-120S0104046.jpg"},{"id":60,"imageName":"","imageLink":"","imageDesc":"","imageStatus":0,"imageType":1,"isDeleted":0,"createTime":1523444733000,"updateTime":1523496546000,"activeTime":1526378447000,"sort":0,"isActive":0,"imageUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526970228144&di=fe062befeb33dc0efabe48e27d0fe1b6&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F130107%2F1-13010F92F9.jpg"}]
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
         * id : 59
         * imageName :
         * imageLink :
         * imageDesc :
         * imageStatus : 0
         * imageType : 1
         * isDeleted : 0
         * createTime : 1523444722000
         * updateTime : 1523496539000
         * activeTime : 1526378446000
         * sort : 0
         * isActive : 0
         * imageUrl : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1526970228144&di=46b1f199fba90a27b360cd9809fb4ef0&imgtype=0&src=http%3A%2F%2Fold.bz55.com%2Fuploads%2Fallimg%2F120830%2F1-120S0104046.jpg
         */

        private int id;
        private String imageName;
        private String imageLink;
        private String imageDesc;
        private int imageStatus;
        private int imageType;
        private int isDeleted;
        private long createTime;
        private long updateTime;
        private long activeTime;
        private int sort;
        private int isActive;
        private String imageUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public String getImageLink() {
            return imageLink;
        }

        public void setImageLink(String imageLink) {
            this.imageLink = imageLink;
        }

        public String getImageDesc() {
            return imageDesc;
        }

        public void setImageDesc(String imageDesc) {
            this.imageDesc = imageDesc;
        }

        public int getImageStatus() {
            return imageStatus;
        }

        public void setImageStatus(int imageStatus) {
            this.imageStatus = imageStatus;
        }

        public int getImageType() {
            return imageType;
        }

        public void setImageType(int imageType) {
            this.imageType = imageType;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
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

        public long getActiveTime() {
            return activeTime;
        }

        public void setActiveTime(long activeTime) {
            this.activeTime = activeTime;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
