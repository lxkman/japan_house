package com.haiwai.administrator.japanhouse.model;

import java.util.List;

/**
 * Created by   admin on 2018/5/21.
 */

public class TopSearchHintBean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : [{"val":"1"}]
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
         * val : 1
         */

        private String val;

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }
}
