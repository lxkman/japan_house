package com.example.administrator.japanhouse.presenter;

import android.app.Activity;

import com.example.administrator.japanhouse.callback.JsonCallback;
import com.example.administrator.japanhouse.model.FreeApartmentBean;
import com.example.administrator.japanhouse.model.NoDataBean;
import com.example.administrator.japanhouse.utils.MyUrls;
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
     * @param pageNo
     */
    public void getFreeApartmentList(int pageNo){
        HttpParams params = new HttpParams();
        params.put("pageNo", pageNo);
        OkGo.<FreeApartmentBean>post(MyUrls.BASEURL + "/app/seehouse/seehouselist")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<FreeApartmentBean>(FreeApartmentBean.class) {
                    @Override
                    public void onSuccess(Response<FreeApartmentBean> response) {
                        callBack.getFreeApartmentList(response);
                    }
                });
    }

    /**
     *  报名
     *
     * @param userId
     * @param activityId
     * @param phone
     * @param userName
     */
    public void getSignUp(int userId, int activityId, String phone, String userName){
        HttpParams params = new HttpParams();
        params.put("userId", userId);
        params.put("activityId", activityId);
        params.put("phone", phone);
        params.put("userName", userName);
        OkGo.<NoDataBean>post(MyUrls.BASEURL + "/app/applyuser/insertappleuser")
                .tag(this)
                .params(params)
                .execute(new JsonCallback<NoDataBean>(NoDataBean.class) {
                    @Override
                    public void onSuccess(Response<NoDataBean> response) {
                        callBack.getSignUp(response);
                    }
                });
    }

    public interface FreeApartmentCallBack{
        void getFreeApartmentList(Response<FreeApartmentBean> response);
        void getSignUp(Response<NoDataBean> response);
    }
}
