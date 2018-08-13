package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.model.ManagerBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * admin  2018/6/22
 */
public class SearchManagerPresenter {
    private Activity activity;

    private SearchManagerCallBack callBack;

    public SearchManagerPresenter(Activity activity, SearchManagerCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     *用户手机号搜索
     * @param phone
     */
    public void getManager(String phone){
        HttpParams params = new HttpParams();
        params.put("phone", phone);
        OkGo.<ManagerBean>post(MyUrls.BASEURL + "/app/broker/getbrokerinfobyphone")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<ManagerBean>(activity, ManagerBean.class) {
                    @Override
                    public void onSuccess(Response<ManagerBean> response) {
                        callBack.getManager(response);
                    }
                });
    }

    public interface SearchManagerCallBack {
        void getManager(Response<ManagerBean> response);
    }
}
