package com.example.administrator.japanhouse.model;

/**
 * admin  2018/6/22
 */
public class RichTextBean {
    /**
     * msg : 请求成功
     * code : 200
     * datas : {"content_jpn":"45465","content_cn":"4554546","title_cn":"45465456","title_jpn":"46546"}
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
         * content_jpn : 45465
         * content_cn : 4554546
         * title_cn : 45465456
         * title_jpn : 46546
         */

        private String content_jpn;
        private String content_cn;
        private String title_cn;
        private String title_jpn;

        public String getContent_jpn() {
            return content_jpn;
        }

        public void setContent_jpn(String content_jpn) {
            this.content_jpn = content_jpn;
        }

        public String getContent_cn() {
            return content_cn;
        }

        public void setContent_cn(String content_cn) {
            this.content_cn = content_cn;
        }

        public String getTitle_cn() {
            return title_cn;
        }

        public void setTitle_cn(String title_cn) {
            this.title_cn = title_cn;
        }

        public String getTitle_jpn() {
            return title_jpn;
        }

        public void setTitle_jpn(String title_jpn) {
            this.title_jpn = title_jpn;
        }
    }
}
