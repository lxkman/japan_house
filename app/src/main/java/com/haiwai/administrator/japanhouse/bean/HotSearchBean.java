package com.haiwai.administrator.japanhouse.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */

public class HotSearchBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"createTime":1529460872000,"tagType":1,"updateTime":1529460875000,"id":2,"tagText":"788787989"},{"createTime":1529460872000,"tagType":1,"updateTime":1529460875000,"id":10,"tagText":"啧啧啧"}]
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
         * createTime : 1529460872000
         * tagType : 1
         * updateTime : 1529460875000
         * id : 2
         * tagText : 788787989
         */
        private long createTime;
        private int tagType;
        private long updateTime;
        private int id;
        private String tagText;

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setTagType(int tagType) {
            this.tagType = tagType;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTagText(String tagText) {
            this.tagText = tagText;
        }

        public long getCreateTime() {
            return createTime;
        }

        public int getTagType() {
            return tagType;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public int getId() {
            return id;
        }

        public String getTagText() {
            return tagText;
        }
    }
}
