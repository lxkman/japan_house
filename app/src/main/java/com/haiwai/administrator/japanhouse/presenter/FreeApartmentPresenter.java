package com.haiwai.administrator.japanhouse.presenter;

import android.app.Activity;

import com.haiwai.administrator.japanhouse.MyApplication;
import com.haiwai.administrator.japanhouse.callback.DialogCallback;
import com.haiwai.administrator.japanhouse.model.FreeApartmentBean;
import com.haiwai.administrator.japanhouse.model.NoDataBean;
import com.haiwai.administrator.japanhouse.utils.MyUrls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

/**
 * Created by   admin on 2018/5/28.
 */

public class FreeApartmentPresenter {
    private Activity activity;
    private FreeApartmentCallBack callBack;

    public FreeApartmentPresenter(Activity activity, FreeApartmentCallBack callBack) {
        this.activity = activity;
        this.callBack = callBack;
    }

    /**
     * 免费看房列表
     *
     * @param pageNo
     */
    public void getFreeApartmentList(int pageNo) {
        HttpParams params = new HttpParams();
        params.put("token", MyApplication.getUserToken());
        params.put("pageNo", pageNo);
        OkGo.<FreeApartmentBean>post(MyUrls.BASEURL + "/app/seehouse/seehouselist")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<FreeApartmentBean>(activity, FreeApartmentBean.class) {
                    @Override
                    public void onSuccess(Response<FreeApartmentBean> response) {
                        callBack.getFreeApartmentList(response);
                    }

                    @Override
                    public void onError(Response<FreeApartmentBean> response) {
                        super.onError(response);
                        callBack.freeApartmentNetwork();
                    }
                });
    }

    /**
     * 报名
     *
     * @param userId
     * @param activityId
     * @param phone
     * @param userName
     */
    public void getSignUp(String userId, int activityId, String phone, String userName) {
        HttpParams params = new HttpParams();
        params.put("userId", userId);
        params.put("activityId", activityId);
        params.put("phone", phone);
        params.put("userName", userName);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/applyuser/insertappleuser")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<NoDataBean>(activity, NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                        callBack.getSignUp(response);
                    }
                });
    }

    public interface FreeApartmentCallBack {
        void getFreeApartmentList(Response<FreeApartmentBean> response);

        void getSignUp(Response<NoDataBean> response);

        void freeApartmentNetwork();
    }
}
