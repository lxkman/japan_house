package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.model.RichTextBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/22
 */
public class RichTextPresenter {
    private Activity activity;
    private RichTextCallBack callBack;

    public RichTextPresenter(Activity activity, RichTextCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 获取各种协议富文本
     * @param type
     */
    public void getRichText(int type){
        HttpParams params = new HttpParams();
        params.put("type", type);
        OkGo.<RichTextBean>post(MyUrls.BASEURL + "/app/textsource/geth5")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<RichTextBean>(activity, RichTextBean.class) {
                    @Override
                    public void onSuccess(Response<RichTextBean> response) {
                        callBack.getRichText(response);
                    }
                });
    }


    public interface RichTextCallBack {
        void getRichText(Response<RichTextBean> response);
    }
}
