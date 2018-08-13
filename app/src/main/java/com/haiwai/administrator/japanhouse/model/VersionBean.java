package com.haiwai.administrator.japanhouse.model;

/**
 * admin  2018/6/29
 */
public class VersionBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : {"id":1,"versionNumber":"1","appDownUrl":"这是下载的URL","createTime":1530257873000,"updateTime":1530257876000}
     */

    private String msg;
    private String code;
    private DatasBean datas;

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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * id : 1
         * versionNumber : 1
         * appDownUrl : 这是下载的URL
         * createTime : 1530257873000
         * updateTime : 1530257876000
         */

        private int id;
        private String versionNumber;
        private String appDownUrl;
        private long createTime;
        private long updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersionNumber() {
            return versionNumber;
        }

        public void setVersionNumber(String versionNumber) {
            this.versionNumber = versionNumber;
        }

        public String getAppDownUrl() {
            return appDownUrl;
        }

        public void setAppDownUrl(String appDownUrl) {
            this.appDownUrl = appDownUrl;
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
    }
}
