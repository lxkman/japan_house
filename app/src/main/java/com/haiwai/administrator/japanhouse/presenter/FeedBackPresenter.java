package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/22
 */
public class FeedBackPresenter {
    private Activity activity;
    private FeedBackCallBack callBack;

    public FeedBackPresenter(Activity activity, FeedBackCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     *  用户反馈
     * @param text
     */
    public void requestFeedBack(String text){
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("text", text);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/housefeedback/insertfeedback")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<NoDataBean>(activity, NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                        callBack.requestFeedBack(response);
                    }
                });
    }

    public interface FeedBackCallBack {
        void requestFeedBack(Response<NoDataBean> response);
    }
}
