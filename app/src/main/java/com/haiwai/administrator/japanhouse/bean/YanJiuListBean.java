package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/23.
 */
public class YanJiuListBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"yjTitle":"优品豪装","yjType":3,"isDeleted":"0","createTime":1527064722000,"isTop":1,"imageUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527081635623&di=7ac885b2231146ebbd2030c2509bbbfe&imgtype=0&src=http%3A%2F%2Fwww.deskier.com%2Fuploads%2Fallimg%2F161002%2F1-161002132002.jpg","updateTime":1527064725000,"id":5,"yjContent":"优品豪装"},{"yjTitle":"优品豪装","yjType":3,"isDeleted":"0","createTime":1527064722000,"isTop":1,"imageUrl":"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527081635623&di=28662a4601d9f5f645567cddb77e6fc8&imgtype=0&src=http%3A%2F%2Fimg18.3lian.com%2Fd%2Ffile%2F201709%2F04%2F37dc752c70d144c4917611059fae6e7d.jpg","updateTime":1527064725000,"id":6,"yjContent":"优品豪装"}]
     */
    private String msg;
    private String code;
    private List<DatasEntity> datas;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDatas(List<DatasEntity> datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public List<DatasEntity> getDatas() {
        return datas;
    }

    public static class DatasEntity {
        /**
         * yjTitle : 优品豪装
         * yjType : 3
         * isDeleted : 0
         * createTime : 1527064722000
         * isTop : 1
         * imageUrl : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527081635623&di=7ac885b2231146ebbd2030c2509bbbfe&imgtype=0&src=http%3A%2F%2Fwww.deskier.com%2Fuploads%2Fallimg%2F161002%2F1-161002132002.jpg
         * updateTime : 1527064725000
         * id : 5
         * yjContent : 优品豪装
         */
        private String yjTitle;
        private int yjType;
        private String isDeleted;
        private long createTime;
        private int isTop;
        private String imageUrl;
        private long updateTime;
        private int id;
        private String yjContent;

        public void setYjTitle(String yjTitle) {
            this.yjTitle = yjTitle;
        }

        public void setYjType(int yjType) {
            this.yjType = yjType;
        }

        public void setIsDeleted(String isDeleted) {
            this.isDeleted = isDeleted;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setIsTop(int isTop) {
            this.isTop = isTop;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setYjContent(String yjContent) {
            this.yjContent = yjContent;
        }

        public String getYjTitle() {
            return yjTitle;
        }

        public int getYjType() {
            return yjType;
        }

        public String getIsDeleted() {
            return isDeleted;
        }

        public long getCreateTime() {
            return createTime;
        }

        public int getIsTop() {
            return isTop;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public int getId() {
            return id;
        }

        public String getYjContent() {
            return yjContent;
        }
    }
}
