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
public class AppraiseManagerPresenter {
    private Activity activity;
    private AppraiseCallBack callBack;

    public AppraiseManagerPresenter(Activity activity, AppraiseCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     *  评价经纪人
     * @param brokerId
     * @param text
     * @param starNum
     */
    public void appraiseManagerRequest(String brokerId, String text, int starNum){
        HttpParams params = new HttpParams();
        params.put("userId", MyApplication.getUserId(activity));
        params.put("brokerId", brokerId);
        params.put("text", text);
        params.put("starNum", starNum);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/fivestar/insertfivestar")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<NoDataBean>(activity, NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                        callBack.appraiseManagerRequest(response);
                    }
                });
    }

    public interface AppraiseCallBack {
        void appraiseManagerRequest(Response<NoDataBean> response);
    }
}
